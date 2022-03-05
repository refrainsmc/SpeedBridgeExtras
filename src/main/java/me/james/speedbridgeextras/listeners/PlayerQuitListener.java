package me.james.speedbridgeextras.listeners;

import me.james.speedbridgeextras.SpeedBridgeExtras;
import me.james.speedbridgeextras.methods.DeletePlayerReplayFile;
import me.jumper251.replay.api.ReplayAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class PlayerQuitListener implements Listener {
    Plugin plugin = SpeedBridgeExtras.getPlugin(SpeedBridgeExtras.class);
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        player.removeMetadata("currentlyWatching", plugin);
        ReplayAPI.getInstance().stopReplay(player.getName(), false);
        DeletePlayerReplayFile.delete(player);
    }
}
