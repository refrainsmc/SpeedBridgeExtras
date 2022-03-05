package me.james.speedbridgeextras.listeners;

import me.james.speedbridgeextras.methods.DeletePlayerReplayFile;
import me.jumper251.replay.api.ReplayAPI;
import me.tofpu.speedbridge.api.SpeedBridgeAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportListener implements Listener {
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        Player player = e.getPlayer();
        boolean currentlyWatching = !player.getMetadata("currentlyWatching").isEmpty();
        boolean playing = SpeedBridgeAPI.getGameService().isPlaying(player);
        if (!playing) {
            ReplayAPI.getInstance().stopReplay(player.getName(), false);
            DeletePlayerReplayFile.delete(player);
            return;
        }
        if (currentlyWatching) {
            return;
        }
        ReplayAPI.getInstance().stopReplay(player.getName(), true);
        ReplayAPI.getInstance().recordReplay(player.getName(), player, player);
    }
}
