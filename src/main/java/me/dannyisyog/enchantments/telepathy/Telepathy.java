package me.dannyisyog.enchantments.telepathy;

import me.dannyisyog.enchantments.CEnchantment;
import me.dannyisyog.enchantments.EnchantWrapper;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Telepathy extends CEnchantment {
    public static final Enchantment TELEPATHY = new EnchantWrapper("telepathy", "Telepathy", 1);

    public static void register() {
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(TELEPATHY);

        if (!registered) {
            registerEnchantment(TELEPATHY);
        }
    }

}
