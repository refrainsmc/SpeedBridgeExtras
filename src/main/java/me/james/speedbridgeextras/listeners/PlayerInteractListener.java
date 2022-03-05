package me.james.speedbridgeextras.listeners;

import me.tofpu.speedbridge.api.SpeedBridgeAPI;
import me.tofpu.speedbridge.api.user.User;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.PHYSICAL)) {
            if (e.getClickedBlock().getType() == Material.GOLD_PLATE) {
                User speedBridgeUser = SpeedBridgeAPI.getUserService().get(e.getPlayer().getUniqueId());
                if (!SpeedBridgeAPI.getGameService().hasTimer(speedBridgeUser)) {
                    return;
                }
                SpeedBridgeAPI.getGameService().updateTimer(speedBridgeUser);
                SpeedBridgeAPI.getGameService().reset(speedBridgeUser);
            }
        }
    }
}
