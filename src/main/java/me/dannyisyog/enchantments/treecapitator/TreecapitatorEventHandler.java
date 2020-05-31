package me.dannyisyog.enchantments.treecapitator;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class TreecapitatorEventHandler implements Listener {

    private final ArrayList<Material> WoodLogs = new ArrayList<>();
    private final ArrayList<Vector> BlocksToCheck = new ArrayList<>();

    public TreecapitatorEventHandler() {
        WoodLogs.add(Material.ACACIA_LOG);
        WoodLogs.add(Material.BIRCH_LOG);
        WoodLogs.add(Material.DARK_OAK_LOG);
        WoodLogs.add(Material.JUNGLE_LOG);
        WoodLogs.add(Material.OAK_LOG);
        WoodLogs.add(Material.SPRUCE_LOG);

        BlocksToCheck.add(new Vector(0,1,0));
        BlocksToCheck.add(new Vector(1,1,0));
        BlocksToCheck.add(new Vector(-1,1,0));
        BlocksToCheck.add(new Vector(0,1,1));
        BlocksToCheck.add(new Vector(0,1,-1));
        BlocksToCheck.add(new Vector(1,1,1));
        BlocksToCheck.add(new Vector(1,1,-1));
        BlocksToCheck.add(new Vector(-1,1,-1));
        BlocksToCheck.add(new Vector(-1,1,1));
        BlocksToCheck.add(new Vector(1,0,0));
        BlocksToCheck.add(new Vector(-1,0,0));
        BlocksToCheck.add(new Vector(0,0,1));
        BlocksToCheck.add(new Vector(0,0,-1));
        BlocksToCheck.add(new Vector(1,0,1));
        BlocksToCheck.add(new Vector(-1,0,-1));
        BlocksToCheck.add(new Vector(1,0,-1));
        BlocksToCheck.add(new Vector(-1,0,1));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (!WoodLogs.contains(event.getBlock().getType())) {
            return;
        }
        //verifies if the player has an item in the main hand
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            return;
        }
        //verifies if the item has itemMeta (to check enchantments later)
        if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
            return;
        }
        //verifies if the item has the enchantment Treecapitator
        if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Treecapitator.TREECAPITATOR)) {
            return;
        }
        //verifies if the player is not in creative nor spectator mode
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR) {
            return;
        }

        Block block = event.getBlock();
        Location location = block.getLocation();
        ItemStack axe = event.getPlayer().getInventory().getItemInMainHand();

        BreakTree(block, location, axe);
    }

    private void BreakTree(Block block, Location location, ItemStack axe) {

        block.breakNaturally(axe);
        Damageable dmg = (Damageable) axe.getItemMeta();
        dmg.setDamage(dmg.getDamage() + 1);
        axe.setItemMeta((ItemMeta) dmg);

        for (Vector v: BlocksToCheck) {
            Location newLocation = location.clone().add(v);
            Block newBlock = newLocation.getBlock();
            Material m = newBlock.getType();
            System.out.println(m.toString());
            if (WoodLogs.contains(m)) {
                BreakTree(newBlock, newLocation, axe);
            }
        }
    }
}
