package com.dev7ex.common.bukkit.world.cuboid;

import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Iterator;

/**
 * @author desht
 * @since  27.08.2013
 */
public final class CuboidIterator implements Iterator<Block> {

    private final World world;
    private final int baseX, baseY, baseZ;
    private int x, y, z;
    private final int sizeX, sizeY, sizeZ;

    public CuboidIterator(final World world, final int x1, final int y1, final int z1, final int x2, final int y2, final int z2) {
        this.world = world;
        this.baseX = x1;
        this.baseY = y1;
        this.baseZ = z1;
        this.sizeX = Math.abs(x2 - x1) + 1;
        this.sizeY = Math.abs(y2 - y1) + 1;
        this.sizeZ = Math.abs(z2 - z1) + 1;
        this.x = this.y = this.z = 0;
    }

    public final boolean hasNext() {
        return ((this.x < this.sizeX) && (this.y < this.sizeY) && (this.z < this.sizeZ));
    }

    public final Block next() {
        final Block block = this.world.getBlockAt(this.baseX + this.x, this.baseY + this.y, this.baseZ + this.z);
        if(++x >= this.sizeX) {
            this.x = 0;
            if(++this.y >= this.sizeY) {
                this.y = 0;
                ++this.z;
            }
        }
        return block;
    }
}
