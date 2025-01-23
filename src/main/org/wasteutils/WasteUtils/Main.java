package org.wasteutils.WasteUtils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onLoad() {
        this.saveDefaultConfig();
        this.reloadConfig();
        Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§ePlugin was loaded. Hello from WasteUtils V0.0.1 Demo!");
        Boolean isDebugging = this.getConfig().getBoolean("debug");
        if (isDebugging = Boolean.TRUE) {
            Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§eDebugger was enabled.");
        }
    }


    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§ePlugin was Enabled.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§ePlugin was Disabled.");
    }
}