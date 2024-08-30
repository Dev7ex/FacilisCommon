package com.dev7ex.common.bukkit.world.cuboid;

import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator for traversing all blocks within a defined cuboid in a Minecraft world.
 *
 * <p>This iterator moves through the cuboid in a nested loop order, starting from the
 * lowest corner (x1, y1, z1) and iterating to the opposite corner (x2, y2, z2).</p>
 *
 * <p>The iteration order is along the X axis first, then Y, and finally Z. When the iterator
 * reaches the end of one dimension, it resets that dimension and increments the next.</p>
 *
 * @author desht
 * @since 27.08.2013
 */
public final class CuboidIterator implements Iterator<Block> {

    private final World world;
    private final int baseX, baseY, baseZ;
    private int currentX, currentY, currentZ;
    private final int sizeX, sizeY, sizeZ;

    /**
     * Constructs a CuboidIterator for a specified cuboid defined by two corners.
     *
     * @param world The world in which the cuboid exists.
     * @param x1    The X coordinate of the first corner.
     * @param y1    The Y coordinate of the first corner.
     * @param z1    The Z coordinate of the first corner.
     * @param x2    The X coordinate of the opposite corner.
     * @param y2    The Y coordinate of the opposite corner.
     * @param z2    The Z coordinate of the opposite corner.
     */
    public CuboidIterator(final World world, final int x1, final int y1, final int z1, final int x2, final int y2, final int z2) {
        this.world = world;
        this.baseX = x1;
        this.baseY = y1;
        this.baseZ = z1;
        this.sizeX = Math.abs(x2 - x1) + 1;
        this.sizeY = Math.abs(y2 - y1) + 1;
        this.sizeZ = Math.abs(z2 - z1) + 1;
        this.currentX = this.currentY = this.currentZ = 0;
    }

    /**
     * Checks if there are more blocks to iterate over in the cuboid.
     *
     * @return true if there are more blocks to iterate over, false otherwise.
     */
    @Override
    public boolean hasNext() {
        return ((this.currentX < this.sizeX) && (this.currentY < this.sizeY) && (this.currentZ < this.sizeZ));
    }

    /**
     * Returns the next block in the cuboid.
     *
     * <p>This method traverses the cuboid in X, Y, Z order. When the end of one dimension is
     * reached, it resets that dimension and increments the next one.</p>
     *
     * @return The next Block in the cuboid.
     * @throws NoSuchElementException if there are no more blocks to iterate over.
     */
    @Override
    public Block next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("No more blocks to iterate over.");
        }

        final Block block = this.world.getBlockAt(this.baseX + this.currentX, this.baseY + this.currentY, this.baseZ + this.currentZ);

        if (++this.currentX >= this.sizeX) {
            this.currentX = 0;
            if (++this.currentY >= this.sizeY) {
                this.currentY = 0;
                ++this.currentZ;
            }
        }
        return block;
    }

    /**
     * The remove method is not supported by this iterator.
     *
     * @throws UnsupportedOperationException if this method is called.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove operation is not supported.");
    }

}

