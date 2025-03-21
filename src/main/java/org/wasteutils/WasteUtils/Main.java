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
import org.wasteutils.WasteUtils.Commands.CommandManager;
import org.wasteutils.WasteUtils.Listeners.LoggingListener;
import org.wasteutils.WasteUtils.Listeners.PlayerListener;
import org.wasteutils.WasteUtils.TabCompleter.MatchmakingTabCompleter;
import org.wasteutils.WasteUtils.TabCompleter.WasteUtilsTabCompleter;

import java.io.File;


public class Main extends JavaPlugin {
    public FileConfiguration lang;
    private static Economy economy = null;
    private static Permission permissions = null;
    private static Chat chat = null;
    public Boolean isVaultInstalled;
    public Boolean isDACInstalled;
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
        if (pluginID.equals("Vault")) {
            RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
            if (rsp == null) {
                return false;
            }
            economy = rsp.getProvider();
        }
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

    public String addPrefix(String message) {return this.lang.getString("plugin.prefix") + message;}

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e Plugin was Enabled.");
        getServer().getPluginManager().registerEvents(new LoggingListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        this.loadConfigLang();
        if (!dependCheck("Vault")) {
            this.isVaultInstalled = Boolean.FALSE;
            Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e No vault detected! Is vault installed correctly?");
            Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e Vault not found, skipping economy system.");
        } else {
            chat=getChat();
            economy=getEconomy();//load only when installed
            permissions=getPermissions();
        }
        if (!dependCheck("DiAntiCheat")) {
            this.isDACInstalled = Boolean.FALSE;
            getLogger().severe("Di 反作弊 未找到。对服务器的保护将禁用。");
            getLogger().warning("我们非常建议你安装 Di 反作弊。");
        }
        getCommand("wasteutils").setExecutor(new CommandManager(this));
        getCommand("matchmaking").setExecutor(new CommandManager(this));
        getCommand("wasteutils").setTabCompleter(new WasteUtilsTabCompleter());
        getCommand("matchmaking").setTabCompleter(new MatchmakingTabCompleter());
        getCommand("economies").setExecutor(new CommandManager(this));
        getCommand("uid").setExecutor(new CommandManager(this));
        SQLiteAPI.connectDb(this);
    }


    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§3WasteUtils§f >>§e Plugin was Disabled.");
    }

    public void onSevereError(String stopReason) {
        getLogger().severe(String.format("Severe Error! - %s - WasteUtils Powered by DiGame", stopReason));
        getServer().getPluginManager().disablePlugin(this);
    }

}