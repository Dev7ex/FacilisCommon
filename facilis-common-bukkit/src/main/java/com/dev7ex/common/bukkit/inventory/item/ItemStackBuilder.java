package com.dev7ex.common.bukkit.inventory.item;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * A utility class for building ItemStack objects with customizable attributes.
 * This class provides methods for setting display name, lore, enchantments,
 * item flags, unbreakable status, custom model data, owner (for SkullMeta),
 * and more.
 *
 * @author Dev7ex
 * @since 19.04.2023
 */
public class ItemStackBuilder {

    private final ItemStack itemStack;

    /**
     * Constructs an ItemStackBuilder with the specified material.
     *
     * @param material The material of the ItemStack.
     */
    public ItemStackBuilder(@NotNull final Material material) {
        this.itemStack = new ItemStack(material);
    }

    /**
     * Constructs an ItemStackBuilder with the specified itemstack.
     *
     * @param itemStack The temStack.
     */
    public ItemStackBuilder(@NotNull final ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Constructs an ItemStackBuilder with the specified material and amount.
     *
     * @param material The material of the ItemStack.
     * @param amount   The amount of items in the ItemStack.
     */
    public ItemStackBuilder(@NotNull final Material material, final int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    /**
     * Constructs an ItemStackBuilder with the default material (STONE).
     */
    public ItemStackBuilder() {
        this(Material.STONE);
    }

    /**
     * Sets the display name of the ItemStack.
     *
     * @param name The display name to set.
     * @return This ItemStackBuilder instance.
     */
    public ItemStackBuilder setDisplayName(@NotNull final String name) {
        this.editItemMeta(itemMeta -> itemMeta.setDisplayName(name));
        return this;
    }

    /**
     * Sets the amount of items in the ItemStack.
     *
     * @param amount The amount to set.
     * @return This ItemStackBuilder instance.
     */
    public ItemStackBuilder setAmount(final int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    /**
     * Sets the lore of the ItemStack.
     *
     * @param lore The lore to set.
     * @return This ItemStackBuilder instance.
     */
    public ItemStackBuilder setLore(@NotNull final String... lore) {
        this.editItemMeta(itemMeta -> itemMeta.setLore(Arrays.asList(lore)));
        return this;
    }

    /**
     * Sets the lore of the ItemStack.
     *
     * @param lore The lore to set.
     * @return This ItemStackBuilder instance.
     */
    public ItemStackBuilder setLore(@NotNull final List<String> lore) {
        this.editItemMeta(itemMeta -> itemMeta.setLore(lore));
        return this;
    }

    /**
     * Adds item flags to the ItemStack.
     *
     * @param itemFlags The item flags to add.
     * @return This ItemStackBuilder instance.
     */
    public ItemStackBuilder addFlags(@NotNull final ItemFlag... itemFlags) {
        this.editItemMeta(itemMeta -> itemMeta.addItemFlags(itemFlags));
        return this;
    }

    /**
     * Sets the unbreakable status of the ItemStack.
     *
     * @param unbreakable Whether the ItemStack should be unbreakable.
     * @return This ItemStackBuilder instance.
     */
    public ItemStackBuilder setUnbreakable(final boolean unbreakable) {
        this.editItemMeta(itemMeta -> itemMeta.setUnbreakable(unbreakable));
        return this;
    }

    /**
     * Sets the custom model data of the ItemStack.
     *
     * @param customModelData The custom model data value to set.
     * @return This ItemStackBuilder instance.
     */
    public ItemStackBuilder setCustomModelData(final int customModelData) {
        this.editItemMeta(itemMeta -> itemMeta.setCustomModelData(customModelData));
        return this;
    }

    /**
     * Sets the owner of the SkullMeta.
     *
     * @param player The owner of the skull.
     * @return This ItemStackBuilder instance.
     */
    public ItemStackBuilder setOwner(final OfflinePlayer player) {
        this.editSkullMeta(skullMeta -> skullMeta.setOwningPlayer(player));
        return this;
    }

    /**
     * Adds an enchantment to the ItemStack.
     *
     * @param enchantment The enchantment to add.
     * @param level       The level of the enchantment.
     * @return This ItemStackBuilder instance.
     */
    public ItemStackBuilder addEnchantment(@NotNull final Enchantment enchantment, final int level) {
        if ((!enchantment.canEnchantItem(this.itemStack)) || (level > enchantment.getMaxLevel())) {
            this.itemStack.addUnsafeEnchantment(enchantment, level);
        } else {
            this.itemStack.addEnchantment(enchantment, level);
        }
        return this;
    }

    /**
     * Edits the ItemMeta of the ItemStack.
     *
     * @param consumer The consumer to apply changes to the ItemMeta.
     * @return This ItemStackBuilder instance.
     */
    public ItemStackBuilder editItemMeta(final Consumer<ItemMeta> consumer) {
        final ItemMeta meta = this.itemStack.getItemMeta();
        consumer.accept(meta);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    /**
     * Edits the SkullMeta of the ItemStack.
     *
     * @param consumer The consumer to apply changes to the SkullMeta.
     * @return This ItemStackBuilder instance.
     */
    public ItemStackBuilder editSkullMeta(final Consumer<SkullMeta> consumer) {
        final ItemMeta meta = this.itemStack.getItemMeta();
        consumer.accept((SkullMeta) meta);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    /**
     * Builds the ItemStack with the configured attributes.
     *
     * @return The built ItemStack.
     */
    public ItemStack build() {
        return this.itemStack;
    }

}