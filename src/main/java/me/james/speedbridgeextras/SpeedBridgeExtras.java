package me.james.speedbridgeextras;

import me.james.speedbridgeextras.commands.LastReplayCommand;
import me.james.speedbridgeextras.listeners.*;
import me.james.speedbridgeextras.listeners.PlayerInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SpeedBridgeExtras extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerTeleportListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new ReplaySessionFinishListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getCommand("lastreplay").setExecutor(new LastReplayCommand());
    }

    @Override
    public void onDisable() {
        final File folder = new File(getServer().getWorldContainer() + "/plugins/AdvancedReplay/replays");
        for(File f: folder.listFiles()) {
            f.delete();
        }
    }
}
