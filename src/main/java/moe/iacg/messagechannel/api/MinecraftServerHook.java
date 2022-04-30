package moe.iacg.messagechannel.api;

import moe.iacg.messagechannel.common.ConfigLoader;
import net.minecraft.Util;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.PlayerList;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.apache.http.impl.bootstrap.HttpServer;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MinecraftServerHook {
    // Get minecraft server.


    public static void sendMessageToServer(String message) throws UnsupportedEncodingException {
        final MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) {
            return;
        }
        final PlayerList playerList = server.getPlayerList();
        BaseComponent textComponent = new TextComponent("");
        //BOTName
        textComponent.append("【");
        textComponent.append(ConfigLoader.configs.get(ConfigLoader.ConfigNames.BOT_NAME).get());
        textComponent.append("】");
        textComponent.append(new String(message.getBytes(), "GBK"));
        playerList.broadcastMessage(textComponent, ChatType.SYSTEM, Util.NIL_UUID);
    }


}
