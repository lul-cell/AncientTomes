package com.bawnorton.ancienttomes.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static com.bawnorton.ancienttomes.Matrix.enchantmentMatrix;
import static com.bawnorton.ancienttomes.AncientTomes.instance;

public class AncientEnchants {
    private final String enchantmentName;

    public AncientEnchants(String enchantmentName) {
        this.enchantmentName = enchantmentName;
    }

    public static Hashtable<String, Object[]> getEnchantmentMatrix() {
        return enchantmentMatrix;
    }

    public ItemStack getBook() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        Enchantment enchantment = (Enchantment) enchantmentMatrix.get(enchantmentName)[0];
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GOLD + "Ancient Tome");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "Can only be applied to increase");
        lore.add(ChatColor.LIGHT_PURPLE + "the level of an existing enchantment");
        meta.setLore(lore);
        item.setItemMeta(meta);
        item.addUnsafeEnchantment(enchantment, (Integer) enchantmentMatrix.get(enchantmentName)[1]);
        ShapelessRecipe slr = new ShapelessRecipe(NamespacedKey.minecraft("createbook_" + enchantmentName.replace(" ", "_").toLowerCase()), item);
        slr.addIngredient(3, Material.PAPER);
        slr.addIngredient(1, Material.LEATHER);
        instance.getServer().addRecipe(slr);
        return item;
    }
    public ItemStack getPage() {
        ItemStack item = new ItemStack(Material.PAPER, 1);
        Enchantment enchantment = (Enchantment) enchantmentMatrix.get(enchantmentName)[0];
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN + "Tome Page");
        item.setItemMeta(meta);
        item.addUnsafeEnchantment(enchantment, (Integer) enchantmentMatrix.get(enchantmentName)[1]);
        return item;
    }
}
