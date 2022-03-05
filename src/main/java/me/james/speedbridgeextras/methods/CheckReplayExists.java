package me.james.speedbridgeextras.methods;

import me.james.speedbridgeextras.SpeedBridgeExtras;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class CheckReplayExists {
    public static boolean check(Player player) {
        Plugin plugin = SpeedBridgeExtras.getPlugin(SpeedBridgeExtras.class);
        File file = new File(plugin.getServer().getWorldContainer() + "/plugins/AdvancedReplay/replays/" + player.getName() + ".replay");
        return file.exists();
    }
}
