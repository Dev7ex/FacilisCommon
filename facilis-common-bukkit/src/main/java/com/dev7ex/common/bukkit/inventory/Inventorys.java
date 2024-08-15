package com.dev7ex.common.bukkit.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Dev7ex
 * @since 19.04.2023
 */
public class Inventorys {

    public static Optional<Inventory> getByTitle(@NotNull final Player player, @NotNull final String title) {
        if (!player.getOpenInventory().getTitle().equalsIgnoreCase(title)) {
            return Optional.empty();
        }
        return Optional.of(player.getOpenInventory().getTopInventory());
    }

    public static boolean isSimilar(@Nullable final Inventory firstInventory, @Nullable final Inventory secondInventory) {
        return ((firstInventory == null) || (secondInventory == null)) || ((firstInventory.getHolder()) != (secondInventory.getHolder()));
    }

    public static void fillBorder(@NotNull final Inventory inventory, @NotNull final ItemStack itemStack) {
        final int size = inventory.getSize();

        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, itemStack);
        }

        for (int i = size - 9; i < size; i++) {
            inventory.setItem(i, itemStack);
        }

        for (int i = 9; i < size; i += 9) {
            inventory.setItem(i, itemStack);
        }

        for (int i = 17; i < size; i += 9) {
            inventory.setItem(i, itemStack);
        }
    }

    public static String toBase64(@NotNull final Inventory inventory) {
        try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            try (final BukkitObjectOutputStream bukkitOutputStream = new BukkitObjectOutputStream(outputStream)) {
                bukkitOutputStream.writeInt(inventory.getSize());

                for (int i = 0; i < inventory.getSize(); i++) {
                    bukkitOutputStream.writeObject(inventory.getItem(i));
                }
                return Base64Coder.encodeLines(outputStream.toByteArray());
            }

        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        throw new IllegalStateException("Could not convert inventory to Base64");
    }

    public static Inventory fromBase64(@NotNull final String s) {
        try (final ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(s))) {
            try (final BukkitObjectInputStream bukkitInputStream = new BukkitObjectInputStream(inputStream)) {
                final Inventory inventory = Bukkit.createInventory(null, bukkitInputStream.readInt());

                for (int i = 0; i < inventory.getSize(); i++) {
                    inventory.setItem(i, (ItemStack) bukkitInputStream.readObject());
                }
                return inventory;

            } catch (final ClassNotFoundException exception) {
                exception.printStackTrace();
            }

        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        throw new RuntimeException("Unable to convert Inventory from Base64");
    }

}
