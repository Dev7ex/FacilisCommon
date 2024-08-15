package com.dev7ex.common.bukkit.world;

import java.util.Arrays;

/**
 * Enum representing different times of day in a game world.
 * Each time has a name, time value, and identification number.
 *
 * @author Dev7ex
 * @since 16.11.2020
 */
public enum WorldTime {

    NONE("Unknown", 0, 0),
    DAY("Day", 1000, 1),
    NOON("Noon", 6000, 2),
    AFTERNOON("Afternoon", 12000, 3),
    NIGHT("Night", 18000, 4);

    private final int timeValue;
    private final String name;
    private final int id;

    /**
     * Constructor for WorldTime enum.
     *
     * @param name      The name of the time of day.
     * @param timeValue The in-game time value associated with this time.
     * @param id        The unique identification number of this time.
     */
    WorldTime(final String name, final int timeValue, final int id) {
        this.name = name;
        this.timeValue = timeValue;
        this.id = id;
    }

    /**
     * Retrieves a WorldTime enum value based on its identification number.
     *
     * @param id The identification number of the WorldTime.
     * @return The corresponding WorldTime enum value, or NONE if no match is found.
     */
    public static WorldTime getById(final int id) {
        return Arrays.stream(WorldTime.values())
                .filter(worldTime -> worldTime.getId() == id)
                .findFirst()
                .orElse(WorldTime.NONE);
    }

    /**
     * Retrieves the name of this WorldTime.
     *
     * @return The name of this WorldTime.
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Retrieves the in-game time value associated with this WorldTime.
     *
     * @return The in-game time value of this WorldTime.
     */
    public final int getTimeValue() {
        return this.timeValue;
    }

    /**
     * Retrieves the identification number of this WorldTime.
     *
     * @return The identification number of this WorldTime.
     */
    public final int getId() {
        return this.id;
    }

}
