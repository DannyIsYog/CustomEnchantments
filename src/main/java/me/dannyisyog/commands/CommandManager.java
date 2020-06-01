package me.dannyisyog.commands;

import me.dannyisyog.commands.SubCommand;
import me.dannyisyog.commands.subcommands.AddBuild;
import me.dannyisyog.commands.subcommands.AddTelepathy;
import me.dannyisyog.commands.subcommands.AddTreecapitator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final ArrayList<SubCommand> subcommands = new ArrayList<>();

    public CommandManager() {
        subcommands.add(new AddTelepathy());
        subcommands.add(new AddTreecapitator());
        subcommands.add(new AddBuild());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length > 0) {
            for (int i = 0; i < getSubcommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                    getSubcommands().get(i).perform(player, args);
                }
            }
        }

        return true;
    }

    public ArrayList<SubCommand> getSubcommands() {
        return subcommands;
    }
}
