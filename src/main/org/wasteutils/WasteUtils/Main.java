package org.wasteutils.WasteUtils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.wasteutils.WasteUtils.Listeners.LoggingListener;
import org.wasteutils.WasteUtils.commands.CommandMatchMaking;
import org.wasteutils.WasteUtils.commands.CommandReload;

import java.io.File;

import static org.bukkit.configuration.file.YamlConfiguration.loadConfiguration;


public class Main extends JavaPlugin {
    public File file = new File(getDataFolder()+"message", getConfig().getString("message.lang")+".yml");
    public FileConfiguration lang = loadConfiguration(file);

    public static void main(String[] args) {
    }
    @Override
    public void onLoad() {
        this.saveDefaultConfig();
        this.reloadConfig();
        Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e Plugin was loaded. Hello from WasteUtils V0.0.1 Demo!");
        boolean isDebugging = this.getConfig().getBoolean("debug");
        if (isDebugging == Boolean.TRUE) {
            Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e Debugger was enabled.(Idk why to do this)");
        }

    }

    public void loadConfig(){
        this.reloadConfig();
        File file = new File(getDataFolder()+"message", getConfig().getString("message.lang")+".yml");
        this.lang = loadConfiguration(file);
    }

    public String addPrefix(String message){return this.lang.getString("plugin.prefix"+message);}

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e Plugin was Enabled.");
        getServer().getPluginManager().registerEvents(new LoggingListener(), this);
        this.getCommand("matchmaking").setExecutor(new CommandMatchMaking());
        this.getCommand("wureload").setExecutor(new CommandReload());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e Plugin was Disabled.");
    }
}