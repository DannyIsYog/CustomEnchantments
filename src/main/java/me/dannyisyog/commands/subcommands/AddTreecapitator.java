package me.dannyisyog.commands.subcommands;

import me.dannyisyog.commands.SubCommand;
import me.dannyisyog.enchantments.treecapitator.Treecapitator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AddTreecapitator extends SubCommand {
    @Override
    public String getName() {
        return "addTreecapitator";
    }

    @Override
    public String getDescription() {
        return "Adds Treecapitator";
    }

    @Override
    public String getSyntax() {
        return "/ce addTreecapitator";
    }

    @Override
    public void perform(Player player, String[] args) {
        //verifies if the player has an item in the main hand
        if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            return;
        }

        //verifies if the item has the enchantment Telepathy
        if(player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Treecapitator.TREECAPITATOR)) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        item.addUnsafeEnchantment(Treecapitator.TREECAPITATOR, 1);

        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Treecapitator I");
        if (meta.hasLore()) {
            for (String l: meta.getLore()) {
                lore.add(l);
            }
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

    }
}
