package moe.iacg.messagechannel.events;

import moe.iacg.messagechannel.api.HttpApi;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static moe.iacg.messagechannel.MessageChannel.MOD_ID;


@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    @OnlyIn(Dist.DEDICATED_SERVER)
    public static void onServerChat(final ServerChatEvent event) {
        HttpApi.doPostChatMessage(event);
    }

    @SubscribeEvent
    @OnlyIn(Dist.DEDICATED_SERVER)
    public static void onServerAboutToStartEvent(final ServerStartedEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server started");
    }

    @SubscribeEvent
    @OnlyIn(Dist.DEDICATED_SERVER)
    public void playerJoin(final PlayerEvent.PlayerLoggedInEvent event) {
        HttpApi.doPostPlayerJoin(event);
    }

}
