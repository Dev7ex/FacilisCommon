package com.dev7ex.common.bukkit.world.cuboid;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author desht
 * @since 27.08.2013
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Cuboid implements Cloneable, Iterable<Block> {

    private Location firstLocation, secondLocation;
    private final String worldName;
    private final int x1, y1, z1, x2, y2, z2;

    public Cuboid(@NotNull final Location firstLocation, @NotNull final Location secondLocation) {
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
        this.worldName = Objects.requireNonNull(firstLocation.getWorld()).getName();

        this.x1 = Math.min(this.firstLocation.getBlockX(), this.secondLocation.getBlockX());
        this.y1 = Math.min(this.firstLocation.getBlockY(), this.secondLocation.getBlockY());
        this.z1 = Math.min(this.firstLocation.getBlockZ(), this.secondLocation.getBlockZ());
        this.x2 = Math.max(this.firstLocation.getBlockX(), this.secondLocation.getBlockX());
        this.y2 = Math.max(this.firstLocation.getBlockY(), this.secondLocation.getBlockY());
        this.z2 = Math.max(this.firstLocation.getBlockZ(), this.secondLocation.getBlockZ());
    }

    public Cuboid(@NotNull final Location firstLocation) {
        this(firstLocation, firstLocation);
    }

    public Cuboid(@NotNull final Cuboid otherRegion) {
        this(otherRegion.getWorld().getName(), otherRegion.x1, otherRegion.y1, otherRegion.z1, otherRegion.x2, otherRegion.y2, otherRegion.z2);
    }

    public Cuboid(final Map<String, Object> map) {
        this.worldName = (String) map.get("worldName");
        this.x1 = (int) map.get("x1");
        this.x2 = (int) map.get("x2");
        this.y1 = (int) map.get("y1");
        this.y2 = (int) map.get("y2");
        this.z1 = (int) map.get("z1");
        this.z2 = (int) map.get("z2");
    }

    public Map<String, Object> serialize() {
        final Map<String, Object> map = Maps.newHashMap();
        map.put("worldName", this.worldName);
        map.put("x1", this.x1);
        map.put("y1", this.y1);
        map.put("z1", this.z1);
        map.put("x2", this.x2);
        map.put("y2", this.y2);
        map.put("z2", this.z2);
        return map;
    }

    public Cuboid(final String worldName, final int x1, final int y1, final int z1, final int x2, final int y2, final int z2) {
        this.worldName = worldName;
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        this.z2 = Math.max(z1, z2);
    }

    public final List<Chunk> getChunks() {
        final List<Chunk> chunks = Lists.newArrayList();

        final World world = this.getWorld();
        final int x1 = this.getLowerX() & ~0xf;
        final int x2 = this.getUpperX() & ~0xf;
        final int z1 = this.getLowerZ() & ~0xf;
        final int z2 = this.getUpperZ() & ~0xf;
        for (int x = x1; x <= x2; x += 16) {
            for (int z = z1; z <= z2; z += 16) {
                chunks.add(world.getChunkAt(x >> 4, z >> 4));
            }
        }
        return chunks;
    }

    public final List<Block> getBlocks() {
        final Iterator<Block> blockIterator = this.iterator();
        final List<Block> blockList = Lists.newArrayList();
        while (blockIterator.hasNext()) {
            blockList.add(blockIterator.next());
        }
        return blockList;
    }

    public final Location getCenter() {
        final int x1 = this.getUpperX() + 1;
        final int y1 = this.getUpperY() + 1;
        final int z1 = this.getUpperZ() + 1;
        return new Location(this.getWorld(), this.getLowerX() + (x1 - this.getLowerX()) / 2.0, this.getLowerY() + (y1 - this.getLowerY()) / 2.0, this.getLowerZ() + (z1 - this.getLowerZ()) / 2.0);
    }

    public final Cuboid getFace(final CuboidDirection regionDirection) {
        switch (regionDirection) {
            case DOWN:
                return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y1, this.z2);

            case UP:
                return new Cuboid(this.worldName, this.x1, this.y2, this.z1, this.x2, this.y2, this.z2);

            case NORTH:
                return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x1, this.y2, this.z2);

            case SOUTH:
                return new Cuboid(this.worldName, this.x2, this.y1, this.z1, this.x2, this.y2, this.z2);

            case EAST:
                return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, this.z1);

            case WEST:
                return new Cuboid(this.worldName, this.x1, this.y1, this.z2, this.x2, this.y2, this.z2);

            default:
                throw new IllegalArgumentException("Invalid direction " + regionDirection);
        }
    }

    public final Cuboid expand(final CuboidDirection regionDirection, final int amount) {
        switch (regionDirection) {
            case NORTH:
                return new Cuboid(
                        this.worldName, this.x1 - amount, this.y1, this.z1, this.x2, this.y2, this.z2);
            case SOUTH:
                return new Cuboid(
                        this.worldName, this.x1, this.y1, this.z1, this.x2 + amount, this.y2, this.z2);
            case EAST:
                return new Cuboid(
                        this.worldName, this.x1, this.y1, this.z1 - amount, this.x2, this.y2, this.z2);
            case WEST:
                return new Cuboid(
                        this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, this.z2 + amount);
            case DOWN:
                return new Cuboid(
                        this.worldName, this.x1, this.y1 - amount, this.z1, this.x2, this.y2, this.z2);
            case UP:
                return new Cuboid(
                        this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2 + amount, this.z2);
            default:
                throw new IllegalArgumentException("Invalid direction " + regionDirection);
        }
    }

    public final boolean isOnlyContaining(final Material material) {
        for (final Block b : this) {
            if (b.getType() != material) {
                return false;
            }
        }
        return true;
    }

    public final Block getRelativeBlock(final World world, final int x, final int y, final int z) {
        return this.getWorld().getBlockAt(this.x1 + x, this.y1 + y, this.z1 + z);
    }

    @Override
    public final Iterator<Block> iterator() {
        return new CuboidIterator(
                Bukkit.getWorld(this.worldName), this.x1, this.y1, this.z1, this.x2, this.y2, this.z2);
    }

    public final boolean contains(final int x, final int y, final int z) {
        return x >= this.x1
                && x <= this.x2
                && y >= this.y1
                && y <= this.y2
                && z >= this.z1
                && z <= this.z2;
    }

    public Cuboid getBoundingCuboid(final Cuboid other) {
        if (other == null) {
            return this;
        }

        final int xMin = Math.min(this.getLowerX(), other.getLowerX());
        final int yMin = Math.min(this.getLowerY(), other.getLowerY());
        final int zMin = Math.min(this.getLowerZ(), other.getLowerZ());
        final int xMax = Math.max(this.getUpperX(), other.getUpperX());
        final int yMax = Math.max(this.getUpperY(), other.getUpperY());
        final int zMax = Math.max(this.getUpperZ(), other.getUpperZ());

        return new Cuboid(this.worldName, xMin, yMin, zMin, xMax, yMax, zMax);
    }

    public final Block[] getCorners() {
        final Block[] res = new Block[8];
        final World world = this.getWorld();
        res[0] = world.getBlockAt(this.x1, this.y1, this.z1);
        res[1] = world.getBlockAt(this.x1, this.y1, this.z2);
        res[2] = world.getBlockAt(this.x1, this.y2, this.z1);
        res[3] = world.getBlockAt(this.x1, this.y2, this.z2);
        res[4] = world.getBlockAt(this.x2, this.y1, this.z1);
        res[5] = world.getBlockAt(this.x2, this.y1, this.z2);
        res[6] = world.getBlockAt(this.x2, this.y2, this.z1);
        res[7] = world.getBlockAt(this.x2, this.y2, this.z2);
        return res;
    }

    public final boolean contains(final Block block) {
        return this.contains(block.getLocation());
    }

    public final boolean contains(final Location location) {
        if (!this.worldName.equals(location.getWorld().getName())) {
            return false;
        }
        return this.contains(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public final int getVolume() {
        return this.getSizeX() * this.getSizeY() * this.getSizeZ();
    }

    public final World getWorld() {
        return Bukkit.getWorld(this.worldName);
    }

    public final int getSizeX() {
        return (this.x2 - this.x1) + 1;
    }

    public final int getSizeY() {
        return (this.y2 - this.y1) + 1;
    }

    public final int getSizeZ() {
        return (this.z2 - this.z1) + 1;
    }

    public int getLowerX() {
        return this.x1;
    }

    public final int getLowerY() {
        return this.y1;
    }

    public final int getLowerZ() {
        return this.z1;
    }

    public final int getUpperX() {
        return this.x2;
    }

    public final int getUpperY() {
        return this.y2;
    }

    public final int getUpperZ() {
        return this.z2;
    }

    @Override
    public Cuboid clone() {
        try {
            final Cuboid clone = (Cuboid) super.clone();
            clone.setFirstLocation(this.firstLocation.clone());
            clone.setSecondLocation(this.secondLocation.clone());

            return clone;

        } catch (final CloneNotSupportedException exception) {
            throw new AssertionError();
        }
    }

}
