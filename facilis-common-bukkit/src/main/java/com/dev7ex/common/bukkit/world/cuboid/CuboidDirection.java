package com.dev7ex.common.bukkit.world.cuboid;

/**
 * @author desht
 * @since 27.08.2013
 */
public enum CuboidDirection {

    NORTH,
    EAST,
    SOUTH,
    WEST,
    UP,
    DOWN,
    HORIZONTAL,
    VERTICAL,
    BOTH,
    UNKNOWN;

    /**
     * Returns the opposite direction of the current direction.
     *
     * <p>For example, if the current direction is NORTH, this method will return SOUTH.
     * If the current direction is HORIZONTAL, it will return VERTICAL, and vice versa.
     * If the direction is BOTH or UNKNOWN, it returns the same direction.</p>
     *
     * @return The opposite direction, or the same direction if no direct opposite exists.
     */
    public CuboidDirection getOpposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case EAST -> WEST;
            case SOUTH -> NORTH;
            case WEST -> EAST;
            case UP -> DOWN;
            case DOWN -> UP;
            case HORIZONTAL -> VERTICAL;
            case VERTICAL -> HORIZONTAL;
            case BOTH -> BOTH;
            default -> UNKNOWN;
        };
    }

}
