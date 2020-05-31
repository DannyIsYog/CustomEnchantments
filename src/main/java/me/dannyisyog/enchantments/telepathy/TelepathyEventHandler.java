package me.dannyisyog.enchantments.telepathy;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.Collection;

public class TelepathyEventHandler implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        //verifies if the player has an item in the main hand
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            return;
        }
        //verifies if the item has itemMeta (to check enchantments later)
        if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
            return;
        }
        //verifies if the item has the enchantment Telepathy
        if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Telepathy.TELEPATHY)) {
            return;
        }
        //verifies if the player is not in creative nor spectator mode
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR) {
            return;
        }
        //verifies if the inventory has space
        if (event.getPlayer().getInventory().firstEmpty() == -1) {
            return;
        }
        //verifies if it's not a chest (kinda annoying)
        if (event.getBlock().getState() instanceof Container) {
            return;
        }

        event.setDropItems(false);
        Player player = event.getPlayer();
        Block block = event.getBlock();

        Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());

        if (drops.isEmpty()) {
            return;
        }

        player.getInventory().addItem(drops.iterator().next());
    }
}
