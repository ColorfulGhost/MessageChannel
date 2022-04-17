package com.nocease.pojo;

import org.apache.http.Header;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 响应内容：实体类
 */
public class HttpClientResult implements Serializable {

    private static final long serialVersionUID = 2168152194164783950L;


    private int code;


    private String content;

    private String StatusLine;

    private Header[] allHeader;

    private Header[] allCookie;

    public HttpClientResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatusLine() {
        return StatusLine;
    }

    public void setStatusLine(String statusLine) {
        StatusLine = statusLine;
    }

    public Header[] getAllHeader() {
        return allHeader;
    }

    public void setAllHeader(Header[] allHeader) {
        this.allHeader = allHeader;
    }

    public Header[] getAllCookie() {
        return allCookie;
    }

    public void setAllCookie(Header[] allCookie) {
        this.allCookie = allCookie;
    }

    @Override
    public String toString() {
        return "HttpClientResult{" +
                "code=" + code +
                ", content='" + content + '\'' +
                ", StatusLine='" + StatusLine + '\'' +
                ", allHeader=" + Arrays.toString(allHeader) +
                ", allCookie=" + Arrays.toString(allCookie) +
                '}';
    }
}