package me.james.speedbridgeextras.commands;

import me.james.speedbridgeextras.SpeedBridgeExtras;
import me.james.speedbridgeextras.methods.CheckReplayExists;
import me.jumper251.replay.api.ReplayAPI;
import me.tofpu.speedbridge.api.SpeedBridgeAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class LastReplayCommand implements CommandExecutor {
    Plugin plugin = SpeedBridgeExtras.getPlugin(SpeedBridgeExtras.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.getMetadata("currentlyWatching").isEmpty()) {
                player.sendMessage(ChatColor.RED + "You are already watching a replay!");
                return true;
            }
            if (!SpeedBridgeAPI.getGameService().isPlaying(player)) {
                player.sendMessage(ChatColor.RED + "You must be playing to use this command.");
                return true;
            }
            if (!CheckReplayExists.check(player)) {
                player.sendMessage(ChatColor.RED + "Could not find your last replay.");
                return true;
            }
            ReplayAPI.getInstance().stopReplay(player.getName(), false);
            player.setMetadata("currentlyWatching", new FixedMetadataValue(plugin, true));
            SpeedBridgeAPI.getGameService().reset(SpeedBridgeAPI.getUserService().get(player.getUniqueId()));
            player.getInventory().clear();
            ReplayAPI.getInstance().playReplay(player.getName(), player);
            return true;
        }
        return false;
    }
}
