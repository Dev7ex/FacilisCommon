package com.dev7ex.common.bukkit.inventory.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Dev7ex
 * @since 15.05.2024
 */
public class EnchantedBookBuilder {

    private final ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);

    public EnchantedBookBuilder addEnchantment(@NotNull final Enchantment enchantment, final int level, final boolean ignoreRestriction) {
        final EnchantmentStorageMeta meta = (EnchantmentStorageMeta) this.itemStack.getItemMeta();
        Objects.requireNonNull(meta).addEnchant(enchantment, level, ignoreRestriction);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return this.itemStack;
    }

}
