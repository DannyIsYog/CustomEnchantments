package me.dannyisyog.enchantments.treecapitator;

import me.dannyisyog.enchantments.CEnchantment;
import me.dannyisyog.enchantments.EnchantWrapper;
import org.bukkit.enchantments.Enchantment;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Treecapitator extends CEnchantment {
    public static final Enchantment TREECAPITATOR = new EnchantWrapper("treecapitator", "Treecapitator", 1);

    public static void register() {
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(TREECAPITATOR);

        if (!registered) {
            registerEnchantment(TREECAPITATOR);
        }
    }
}
