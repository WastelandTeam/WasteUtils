package org.wasteutils.WasteUtils;

/*
Mao_mao_shen，你气死我了
xianyu1145, 别jb催了，再催我就给你玛莎拉
全体玩家们好，我们还在蒸
*/


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.wasteutils.WasteUtils.Listeners.LoggingListener;

import java.io.File;


public class Main extends JavaPlugin {
    public FileConfiguration lang;

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
        File messageDir = new File(getDataFolder(), "message");
        String langFileName = getConfig().getString("message.lang") + ".yml";
        File langFile = new File(messageDir, langFileName);
        this.lang = YamlConfiguration.loadConfiguration(langFile);
    }

    public String addPrefix(String message) {
        return this.lang.getString("plugin.prefix") + message;
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e Plugin was Enabled.");
        getServer().getPluginManager().registerEvents(new LoggingListener(this), this);

        String langFileName = getConfig().getString("message.lang") + ".yml";
        File langFile = new File(getDataFolder() + File.separator + "message", langFileName);
        if (!langFile.exists()) {
            saveResource("message" + File.separator + langFileName, false);
        }
        this.lang = YamlConfiguration.loadConfiguration(langFile);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e Plugin was Disabled.");
    }
}