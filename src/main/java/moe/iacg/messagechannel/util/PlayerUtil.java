package moe.iacg.messagechannel.util;

import net.minecraft.world.entity.player.Player;

public class PlayerUtil {

    public static String getName(Player player) {
        String name = player.getDisplayName().getContents();

        if (name.isEmpty()) {
            name = player.getName().getContents();
        }

        return name;
    }
}
