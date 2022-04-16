package moe.iacg.messagechannel.events;

import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static moe.iacg.messagechannel.MessageChannel.MOD_ID;


@Mod.EventBusSubscriber(modid = MOD_ID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onServerChat(ServerChatEvent event) {
        LOGGER.debug(event.getPlayer().getName().getString() + ":" + event.getMessage());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

}
