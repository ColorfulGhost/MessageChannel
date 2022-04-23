package moe.iacg.messagechannel.api;

import com.google.gson.Gson;
import moe.iacg.messagechannel.util.PlayerUtil;
import moe.iacg.messagechannel.util.http.HttpClientUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class HttpApi {

    private final static String BASE_URL = "http://192.168.1.220:8081/minecraft/";

    public static void doPostChatMessage(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        PlayerMessage playerMessage = new PlayerMessage();
        playerMessage.setUUID(player.getStringUUID());
        playerMessage.setUsername(event.getUsername());
        playerMessage.setChatMessage(event.getMessage());
        String api = BASE_URL + "hook/chatMessage";

        Gson gson = new Gson();
        String jsonStr = gson.toJson(playerMessage);
        try {
            HttpClientUtil.doPost(api, jsonStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doPostPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {

        String name = PlayerUtil.getName(event.getPlayer());
        String api = BASE_URL + "hook/playerJoin";

        try {
            HttpClientUtil.doPost(api, new String((name + "悄悄的进入了游戏...开卷！").getBytes(), "GBK"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
