package com.dev7ex.common.bukkit.inventory.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Dev7ex
 * @since 19.04.2023
 */
public class ItemStackBuilder {

    private final ItemStack itemStack;

    public ItemStackBuilder(@NotNull final Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemStackBuilder(@NotNull final Material material, final int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    public ItemStackBuilder() {
        this(Material.STONE);
    }

    public ItemStackBuilder setDisplayName(@NotNull final String name) {
        this.editItemMeta(itemMeta -> itemMeta.setDisplayName(name));
        return this;
    }

    public ItemStackBuilder setAmount(final int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemStackBuilder setLore(@NotNull final String... lore) {
        this.editItemMeta(itemMeta -> itemMeta.setLore(Arrays.asList(lore)));
        return this;
    }

    public ItemStackBuilder setLore(@NotNull final List<String> lore) {
        this.editItemMeta(itemMeta -> itemMeta.setLore(lore));
        return this;
    }

    public ItemStackBuilder addFlags(@NotNull final ItemFlag... itemFlags) {
        this.editItemMeta(itemMeta -> itemMeta.addItemFlags(itemFlags));
        return this;
    }

    public ItemStackBuilder setUnbreakable(final boolean unbreakable) {
        this.editItemMeta(itemMeta -> itemMeta.setUnbreakable(unbreakable));
        return this;
    }

    public ItemStackBuilder addEnchantment(@NotNull final Enchantment enchantment, final int level) {
        if ((!enchantment.canEnchantItem(this.itemStack)) || (level > enchantment.getMaxLevel())) {
            this.itemStack.addUnsafeEnchantment(enchantment, level);

        } else {
            this.itemStack.addEnchantment(enchantment, level);
        }
        return this;
    }

    public ItemStackBuilder editItemMeta(final Consumer<ItemMeta> consumer) {
        final ItemMeta meta = this.itemStack.getItemMeta();
        consumer.accept(meta);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return this.itemStack;
    }

}
