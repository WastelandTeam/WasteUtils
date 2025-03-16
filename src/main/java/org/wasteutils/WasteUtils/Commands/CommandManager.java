package org.wasteutils.WasteUtils.Commands;

/*
Commands manager
If executed, it will be filter in here.

*/

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.wasteutils.WasteUtils.Exceptions.CommandNotFoundException;
import org.wasteutils.WasteUtils.Main;
import org.wasteutils.WasteUtils.SQLiteAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.bukkit.Bukkit.getPlayerExact;

public class CommandManager implements CommandExecutor {
    private final Main plugin;

    public CommandManager(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("wasteutils")) {
            return CommandWasteUtils.CmdHandler(commandSender, command, s, strings, this.plugin);
        } else if (command.getName().equalsIgnoreCase("economies")) {
            if (this.plugin.isVaultInstalled == Boolean.TRUE) {
                return CommandEconomies.CmdHandler(commandSender, command, s, strings, this.plugin);
            } else {
                commandSender.sendMessage(this.plugin.addPrefix("Please install Vault before executing this main command!"));
                return false;
            }
        } else if (command.getName().equalsIgnoreCase("matchmaking")) {
            return CommandMatchMaking.CmdHandler(commandSender, command, s, strings, this.plugin);
        } else if (command.getName().equalsIgnoreCase("uid")) {
            if (commandSender.getName() != "CONSOLE") {
                Player player = getPlayerExact(commandSender.getName());
                UUID uuid = player.getUniqueId();
                ResultSet query = SQLiteAPI.queryDb(String.format("select * from player where uuid = \"%s\"", uuid));
                String uid = null;
                try {
                    uid = query.getString("uid");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    commandSender.sendMessage(this.plugin.addPrefix(String.format("你的UID是：%s", uid)));

                }
                return true;
            } else {
                commandSender.sendMessage(plugin.addPrefix(plugin.lang.getString("plugin.isconsole")));
            }
        } else {
            throw new CommandNotFoundException("Trying to redirect a nonexistent CmdHandler object.");
        }
        return false;
    }
}
