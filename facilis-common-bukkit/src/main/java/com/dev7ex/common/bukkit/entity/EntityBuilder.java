package com.dev7ex.common.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Dev7ex
 * @since 04.03.2024
 */
public class EntityBuilder {

    private final EntityType entityType;
    private final Location location;
    private String name;
    private boolean nameVisible = true;
    public EntityBuilder(@NotNull final EntityType entityType, @NotNull final Location location) {
        this.entityType = entityType;
        this.location = location;
    }

    public EntityBuilder setName(@NotNull final String name) {
        this.name = name;
        return this;
    }

    public EntityBuilder setNameVisible(final boolean nameVisible) {
        this.nameVisible = nameVisible;
        return this;
    }

    public Entity build() {
        final Entity entity = Objects.requireNonNull(location.getWorld()).spawn(this.location, Objects.requireNonNull(this.entityType.getEntityClass()));

        if (this.name != null) {
            entity.setCustomName(this.name);
            entity.setCustomNameVisible(this.nameVisible);
        }

        return entity;
    }

}
