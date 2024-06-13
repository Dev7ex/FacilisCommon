package com.dev7ex.common.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A builder class for creating Bukkit entities with customizable attributes.
 * This class simplifies the process of spawning entities with specific configurations.
 *
 * <p>Example usage:
 * <pre>{@code
 * Location spawnLocation = new Location(world, x, y, z);
 * EntityBuilder builder = new EntityBuilder(EntityType.ZOMBIE, spawnLocation)
 *                          .setName("Custom Zombie")
 *                          .setNameVisible(true)
 *                          .setAIEnabled(true)
 *                          .setSilent(true)
 *                          .setInvulnerable(true)
 *                          .setGlowing(true)
 *                          .setPersistent(true)
 *                          .setGravity(false)
 *                          .setSwimming(true);
 * Entity zombie = builder.build();
 * }</pre>
 *
 * @author Dev7ex
 * @since 04.03.2024
 */
public class EntityBuilder {

    private final EntityType entityType;
    private final Location location;
    private String name;
    private final boolean nameVisible = true;
    private final boolean silent = false;
    private final boolean invulnerable = false;
    private boolean glowing = false;
    private boolean persistent = false;
    private boolean gravity = true;

    /**
     * Constructs a new EntityBuilder for the specified entity type and location.
     *
     * @param entityType the type of entity to be spawned.
     * @param location   the location where the entity will be spawned.
     */
    public EntityBuilder(@NotNull final EntityType entityType, @NotNull final Location location) {
        this.entityType = entityType;
        this.location = location;
    }

    // Existing methods for setting properties

    /**
     * Sets whether the entity should glow.
     *
     * @param glowing true if the entity should glow, false otherwise.
     * @return the updated EntityBuilder instance.
     */
    public EntityBuilder setGlowing(final boolean glowing) {
        this.glowing = glowing;
        return this;
    }

    /**
     * Sets whether the entity should be persistent.
     *
     * @param persistent true if the entity should be persistent, false otherwise.
     * @return the updated EntityBuilder instance.
     */
    public EntityBuilder setPersistent(final boolean persistent) {
        this.persistent = persistent;
        return this;
    }

    /**
     * Sets whether gravity affects the entity.
     *
     * @param gravity true if gravity should affect the entity, false otherwise.
     * @return the updated EntityBuilder instance.
     */
    public EntityBuilder setGravity(final boolean gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * Builds and spawns the entity with the configured attributes.
     *
     * @return the spawned entity.
     */
    public Entity build() {
        final Entity entity = Objects.requireNonNull(this.location.getWorld())
                .spawn(this.location, Objects.requireNonNull(this.entityType.getEntityClass()));

        if (this.name != null) {
            entity.setCustomName(this.name);
            entity.setCustomNameVisible(this.nameVisible);
        }
        entity.setSilent(this.silent);
        entity.setInvulnerable(this.invulnerable);

        entity.setGlowing(this.glowing);
        entity.setPersistent(this.persistent);
        entity.setGravity(this.gravity);

        return entity;
    }

}