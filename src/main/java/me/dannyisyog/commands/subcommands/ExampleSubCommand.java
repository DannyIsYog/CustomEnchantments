package me.dannyisyog.commands.subcommands;

import me.dannyisyog.commands.SubCommand;
import org.bukkit.entity.Player;

public class ExampleSubCommand extends SubCommand {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getSyntax() {
        return null;
    }

    //This is what the command will do when it's called
    @Override
    public void perform(Player player, String[] args) {

    }
}
