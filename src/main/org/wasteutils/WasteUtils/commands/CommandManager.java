package org.wasteutils.WasteUtils.commands;

/*
Commands manager
If executed, it will be filter in here.

*/

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.wasteutils.WasteUtils.Main;

public class CommandManager implements CommandExecutor {
    private final Main plugin;

    public CommandManager(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("wasteutils")) {
            return CommandWasteUtils.CmdHandler(commandSender, command, s, strings, this.plugin);
        } else {
            return false;
        }
    }
}
