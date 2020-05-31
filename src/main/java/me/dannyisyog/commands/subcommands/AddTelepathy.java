package me.dannyisyog.commands.subcommands;

import me.dannyisyog.commands.SubCommand;
import me.dannyisyog.enchantments.telepathy.Telepathy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AddTelepathy extends SubCommand {
    @Override
    public String getName() {
        return "addTelepathy";
    }

    @Override
    public String getDescription() {
        return "Adds the Telepathy enchantment to the item the player is holding";
    }

    @Override
    public String getSyntax() {
        return "/ce addTelepathy";
    }

    //This is what the command will do when it's called
    @Override
    public void perform(Player player, String[] args) {

        //verifies if the player has an item in the main hand
        if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            return;
        }

        //verifies if the item has the enchantment Telepathy
        if(player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Telepathy.TELEPATHY)) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        item.addUnsafeEnchantment(Telepathy.TELEPATHY, 1);

        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Telepathy I");
        if (meta.hasLore()) {
            for (String l: meta.getLore()) {
                lore.add(l);
            }
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        //player.getInventory().addItem(item);
    }
}
