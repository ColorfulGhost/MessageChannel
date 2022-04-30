package moe.iacg.messagechannel.common;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.LinkedHashMap;
import java.util.Map;

import static moe.iacg.messagechannel.common.ConfigLoader.ConfigNames.*;

public class ConfigLoader {


    public static ForgeConfigSpec SERVER_CONFIG;
    public static Map<String, ForgeConfigSpec.ConfigValue<String>> configs;

    static {
        ForgeConfigSpec.Builder serverBuilder = new ForgeConfigSpec.Builder();
        setBuilder(serverBuilder);

        SERVER_CONFIG = serverBuilder.build();

    }


    public static class ConfigNames {
        public static final String BOT_NAME = "bot_name";
        public static final String HOST = "host";

    }

    private static void setBuilder(ForgeConfigSpec.Builder builder) {
        configs = new LinkedHashMap<>();
        configs.put(BOT_NAME, builder.comment("Bot名称").define(BOT_NAME, "ChatBot"));
        configs.put(HOST, builder.comment("服务器Host").define(HOST, "localhost"));
    }
}
