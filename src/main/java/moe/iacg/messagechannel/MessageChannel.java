package moe.iacg.messagechannel;

import moe.iacg.messagechannel.api.HttpApi;
import moe.iacg.messagechannel.common.ConfigLoader;
import moe.iacg.messagechannel.util.http.HttpServerUtil;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.network.NetworkConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.stream.Collectors;

import static moe.iacg.messagechannel.MessageChannel.MOD_ID;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(MOD_ID)
@OnlyIn(Dist.DEDICATED_SERVER)
public class MessageChannel {


    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "message_channel";

    public MessageChannel() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigLoader.SERVER_CONFIG);
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));

        ConfigLoader.configs.get(ConfigLoader.ConfigNames.BOT_NAME).get();
    }

}
