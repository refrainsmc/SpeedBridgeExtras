package me.james.speedbridgeextras.listeners;

import me.james.speedbridgeextras.SpeedBridgeExtras;
import me.jumper251.replay.api.ReplaySessionFinishEvent;
import me.tofpu.speedbridge.api.SpeedBridgeAPI;
import me.tofpu.speedbridge.api.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ReplaySessionFinishListener implements Listener {

    Plugin plugin = SpeedBridgeExtras.getPlugin(SpeedBridgeExtras.class);

    @EventHandler
    public void onPlayerReplayFinish(ReplaySessionFinishEvent e) {
        Player player = e.getPlayer();
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.removeMetadata("currentlyWatching", plugin);
            SpeedBridgeAPI.getGameService().leave(player);
        }, 1L);
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.teleport(SpeedBridgeAPI.getLobbyService().getLobbyLocation());
        }, 4L);
    }
}
