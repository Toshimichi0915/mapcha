package com.envyclient.mapcha.impl.managers;


import com.envyclient.mapcha.Mapcha;
import com.envyclient.mapcha.api.manager.Manager;
import com.envyclient.mapcha.api.subcommand.SubCommand;
import com.envyclient.mapcha.impl.commands.subcommands.HelpSubCommand;
import com.envyclient.mapcha.impl.commands.subcommands.ReloadSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SubCommandManager extends Manager<SubCommand> {

    public SubCommandManager() {
        registerCommands();
    }

    private void registerCommands() {
        getContents().add(new HelpSubCommand("help - { shows all the commands }"));
        getContents().add(new ReloadSubCommand("reload - { reloads the info.json file }"));
    }

    public boolean processSubCommand(Command command, CommandSender commandSender, String[] args) {

        if (args != null && args.length >= 1) {

            SubCommand subCommand = getCommand(args[0]);

            if (subCommand != null) {
                if (!subCommand.onCommand(command, commandSender, args))
                    commandSender.sendMessage(Mapcha.INSTANCE.PREFIX + subCommand.getUsage(command));
            } else {
                commandSender.sendMessage(Mapcha.INSTANCE.PREFIX + " Try " + command.getName() + " help.");
            }
        } else {
            commandSender.sendMessage(Mapcha.INSTANCE.PREFIX + " Try " + command.getName() + " help.");
        }

        return true;
    }

    private SubCommand getCommand(String name) {
        for (SubCommand subCommand : getContents()) {
            if (subCommand.getName().equalsIgnoreCase(name))
                return subCommand;
        }
        return null;
    }

}
