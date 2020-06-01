package me.dannyisyog.enchantments.builder;

import me.dannyisyog.enchantments.CEnchantment;
import me.dannyisyog.enchantments.EnchantWrapper;
import org.bukkit.enchantments.Enchantment;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Builder extends CEnchantment {
    public static final Enchantment BUILDER = new EnchantWrapper("builder", "Builder", 1);

    public static void register() {
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(BUILDER);

        if (!registered) {
            registerEnchantment(BUILDER);
        }
    }
}
