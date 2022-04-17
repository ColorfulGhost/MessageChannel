package moe.iacg.messagechannel.util.http;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import moe.iacg.messagechannel.api.MinecraftServerHook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class HttpServerUtil {

    public static void start() throws IOException {
        //创建一个HttpServer实例，并绑定到指定的IP地址和端口号
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8288), 0);

        //创建一个HttpContext，将路径为/myserver请求映射到MyHttpHandler处理器
        httpServer.createContext("/minecraft", new MyHttpHandler());

        //设置服务器的线程池对象
        httpServer.setExecutor(Executors.newFixedThreadPool(10));

        //启动服务器
        httpServer.start();
    }


    public static class  MyHttpHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) {
            try {
                StringBuilder responseText = new StringBuilder();
                responseText.append("请求方法：").append(httpExchange.getRequestMethod()).append("<br/>");
                String requestParam = getRequestParam(httpExchange);
                MinecraftServerHook.sendMessageToServer(requestParam);

                responseText.append("请求参数：").append(requestParam).append("<br/>");
                responseText.append("请求头：<br/>").append(getRequestHeader(httpExchange));
                handleResponse(httpExchange, responseText.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        /**
         * 获取请求头
         * @param httpExchange
         * @return
         */
        private String getRequestHeader(HttpExchange httpExchange) {
            Headers headers = httpExchange.getRequestHeaders();
            return headers.entrySet().stream()
                    .map((Map.Entry<String, List<String>> entry) -> entry.getKey() + ":" + entry.getValue().toString())
                    .collect(Collectors.joining("<br/>"));
        }

        /**
         * 获取请求参数
         * @param httpExchange
         * @return
         * @throws Exception
         */
        private String getRequestParam(HttpExchange httpExchange) throws Exception {
            String paramStr = "";

            if (httpExchange.getRequestMethod().equals("GET")) {
                //GET请求读queryString
                paramStr = httpExchange.getRequestURI().getQuery();
            } else {
                //非GET请求读请求体
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), "GBK"));
                StringBuilder requestBodyContent = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    requestBodyContent.append(line);
                }
                paramStr = requestBodyContent.toString();
            }

            return paramStr;
        }

        /**
         * 处理响应
         * @param httpExchange
         * @param responsetext
         * @throws Exception
         */
        private void handleResponse(HttpExchange httpExchange, String responsetext) throws Exception {
            //生成html
            StringBuilder responseContent = new StringBuilder();
            responseContent.append("<html>")
                    .append("<body>")
                    .append(responsetext)
                    .append("</body>")
                    .append("</html>");
            String responseContentStr = responseContent.toString();
            byte[] responseContentByte = responseContentStr.getBytes("GBK");

            //设置响应头，必须在sendResponseHeaders方法之前设置！
            httpExchange.getResponseHeaders().add("Content-Type:", "text/html;charset=utf-8");

            //设置响应码和响应体长度，必须在getResponseBody方法之前调用！
            httpExchange.sendResponseHeaders(200, responseContentByte.length);

            OutputStream out = httpExchange.getResponseBody();
            out.write(responseContentByte);
            out.flush();
            out.close();
        }
    }

}