package me.dannyisyog;

import me.dannyisyog.commands.CommandManager;
import me.dannyisyog.enchantments.telepathy.Telepathy;
import me.dannyisyog.enchantments.telepathy.TelepathyEventHandler;
import me.dannyisyog.enchantments.treecapitator.Treecapitator;
import me.dannyisyog.enchantments.treecapitator.TreecapitatorEventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomEnchantments extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin Starting");


        //Sets up the default config file
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Register the Custom Enchantments
        Telepathy.register();
        Treecapitator.register();

        //Register Events
        getServer().getPluginManager().registerEvents(new TelepathyEventHandler(), this);
        getServer().getPluginManager().registerEvents(new TreecapitatorEventHandler(), this);

        //This registers the command manager, don't forget to change the command prefix
        this.getCommand("ce").setExecutor(new CommandManager());

    }

    @Override
    public void onDisable() {
        System.out.println("Plugin Shutting Down");
    }
}
