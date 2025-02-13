package org.wasteutils.WasteUtils;

/*
Authors: xiaodi001, mao_mao_shen
Github: xiaodi001-01
From xiaodi001-01:全体玩家们好，我们还在蒸
From commit-8883419:忘记注册了，嘿嘿，顺手的事
*/


//这里红了记得去右边的大象图标点刷新
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.wasteutils.WasteUtils.Listeners.LoggingListener;
import org.wasteutils.WasteUtils.TabCompleter.WasteUtilsTabCompleter;
import org.wasteutils.WasteUtils.commands.CommandManager;

import java.io.File;


public class Main extends JavaPlugin {
    public FileConfiguration lang;
    private static Economy economy = null;
    private static final Permission permissions = null;
    private static final Chat chat = null;
    public Boolean isVaultInstalled;

    public static void main(String[] args) {
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static Permission getPermissions() {
        return permissions;
    }

    public static Chat getChat() {
        return chat;
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

    private boolean dependCheck(String pluginID) {
        if (getServer().getPluginManager().getPlugin(pluginID) == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return true;
    }

    public void loadConfigLang() {
        this.reloadConfig();
        String langFileName = getConfig().getString("message.lang") + ".yml";
        File langFile = new File(getDataFolder() + File.separator + "message", langFileName);
        if (!langFile.exists()) {
            saveResource("message" + File.separator + langFileName, false);
        }
        this.lang = YamlConfiguration.loadConfiguration(langFile);
    }

    public String addPrefix(String message) {
        return this.lang.getString("plugin.prefix") + message;
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e Plugin was Enabled.");
        getServer().getPluginManager().registerEvents(new LoggingListener(this), this);
        this.loadConfigLang();
        if (!dependCheck("Vault")) {
            this.isVaultInstalled = Boolean.FALSE;
            Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e No vault detected! Is vault installed correctly?");
            Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e Vault not found, skipping economy system.");
        }
        this.getCommand("wasteutils").setTabCompleter(new WasteUtilsTabCompleter());
        this.getCommand("wasteutils").setExecutor(new CommandManager(this));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e Plugin was Disabled.");
    }

    public void onSevereError(String stopReason) {
        getLogger().severe(String.format("Severe Error! - [%s] - WasteUtils Powered by DiGame", stopReason));
        getServer().getPluginManager().disablePlugin(this);
    }

}