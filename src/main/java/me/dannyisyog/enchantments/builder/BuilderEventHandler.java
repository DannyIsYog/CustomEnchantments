package me.dannyisyog.enchantments.builder;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class BuilderEventHandler implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        ArrayList<Location> checked = new ArrayList<>();
        ArrayList<Vector> vectors = new ArrayList<>();

        //verifies if the player has an item in the main hand
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            return;
        }
        //verifies if the item has itemMeta (to check enchantments later)
        if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
            return;
        }
        //verifies if the item has the enchantment Telepathy
        if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Builder.BUILDER)) {
            return;
        }
        if (event.getClickedBlock().equals(Material.AIR)) {
            return;
        }
        System.out.println(event.getClickedBlock().getType().toString());
        Block block = event.getClickedBlock();
        Material m = block.getType();
        Location  originalLocation = block.getLocation();
        Vector direction = event.getBlockFace().getDirection();

        getAdjacentVectors(direction.clone(), vectors);

        Location copyLocation = originalLocation.clone();
        buildBlock(copyLocation, direction, checked, vectors);

        for (Location l: checked) {
            l.clone().add(direction).getBlock().setType(m);
        }


    }

    private void buildBlock(Location location,Vector direction, ArrayList<Location> checked, ArrayList<Vector> vectors) {


        for (Vector vec: vectors) {
            Location checking;
            checking = location.clone().add(vec);
            if (checking.getBlock().getType().equals(location.getBlock().getType())) {
                if (!checked.contains(checking)) {
                    checked.add(checking);
                    //checking.clone().add(direction).getBlock().setType(location.getBlock().getType());
                    buildBlock(checking, direction, checked, vectors);
                }
            }
        }
    }

    private void getAdjacentVectors(Vector v, ArrayList<Vector> p) {

        //Verifies if the vector is positive or negative
        if (!(v.getX()==1 || v.getY()==1 || v.getZ()==1)) {

            v.multiply(-1);

        }

        v.subtract(new Vector(1,1,1));
        v.multiply(-1);

        p.add(new Vector(0,0,v.getZ()));
        p.add(new Vector(0,v.getY(),0));
        p.add(new Vector(v.getX(),0,0));
        p.add(p.get(0).clone().multiply(-1));
        p.add(p.get(1).clone().multiply(-1));
        p.add(p.get(2).clone().multiply(-1));
    }
}
