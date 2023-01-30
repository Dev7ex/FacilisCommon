package com.dev7ex.common.bukkit.world.cuboid;

/**
 * @author desht
 * @since 27.08.2013
 */
public enum CuboidDirection {

    North,
    East,
    South,
    West,
    Up,
    Down,
    Horizontal,
    Vertical,
    Both,
    Unknown;

    public CuboidDirection opposite() {
        switch (this) {
            case North:
                return South;
            case East:
                return West;
            case South:
                return North;
            case West:
                return East;
            case Horizontal:
                return Vertical;
            case Vertical:
                return Horizontal;
            case Up:
                return Down;
            case Down:
                return Up;
            case Both:
                return Both;
            default:
                return Unknown;
        }
    }
}
