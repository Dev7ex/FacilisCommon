package com.dev7ex.common.bukkit.util;

import com.dev7ex.common.bukkit.BukkitCommon;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Represents different Minecraft protocol versions.
 * Provides methods for obtaining protocol versions based on protocol number or version name.
 *
 * @author Dev7ex
 * @since 23.03.2024
 */
@Getter(AccessLevel.PUBLIC)
public enum ProtocolVersion {

    UNKNOWN(-1, "x.x"),
    MINECRAFT_1_8(47, "1.8"),
    MINECRAFT_1_9(107, "1.9"),
    MINECRAFT_1_9_1(108, "1.9.1"),
    MINECRAFT_1_9_2(109, "1.9.2"),
    MINECRAFT_1_9_4(110, "1.9.4"),
    MINECRAFT_1_10(210, "1.10"),
    MINECRAFT_1_11(315, "1.11"),
    MINECRAFT_1_11_1(316, "1.11.1"),
    MINECRAFT_1_12(335, "1.12"),
    MINECRAFT_1_12_1(338, "1.12.1"),
    MINECRAFT_1_12_2(340, "1.12.2"),
    MINECRAFT_1_13(393, "1.13"),
    MINECRAFT_1_13_1(401, "1.13.1"),
    MINECRAFT_1_13_2(404, "1.13.2"),
    MINECRAFT_1_14(477, "1.14"),
    MINECRAFT_1_14_1(480, "1.14.1"),
    MINECRAFT_1_14_2(485, "1.14.2"),
    MINECRAFT_1_14_3(490, "1.14.3"),
    MINECRAFT_1_14_4(498, "1.14.4"),
    MINECRAFT_1_15(573, "1.15"),
    MINECRAFT_1_15_1(575, "1.15.1"),
    MINECRAFT_1_15_2(578, "1.15.2"),
    MINECRAFT_1_16(735, "1.16"),
    MINECRAFT_1_16_1(736, "1.16.1"),
    MINECRAFT_1_16_2(751, "1.16.2"),
    MINECRAFT_1_16_3(753, "1.16.3"),
    MINECRAFT_1_16_4(754, "1.16.4"),
    MINECRAFT_1_16_5(754, "1.16.5"),
    MINECRAFT_1_17(755, "1.17"),
    MINECRAFT_1_17_1(756, "1.17.1"),
    MINECRAFT_1_18(757, "1.18"),
    MINECRAFT_1_18_1(758, "1.18.1"),
    MINECRAFT_1_18_2(759, "1.18.2"),
    MINECRAFT_1_18_3(760, "1.18.3"),
    MINECRAFT_1_18_4(761, "1.18.4"),
    MINECRAFT_1_19(762, "1.19"),
    MINECRAFT_1_19_1(763, "1.19.1"),
    MINECRAFT_1_19_2(764, "1.19.2"),
    MINECRAFT_1_19_3(765, "1.19.3"),
    MINECRAFT_1_20(766, "1.20"),
    MINECRAFT_1_20_1(763, "1.20.1"),
    MINECRAFT_1_20_2(764, "1.20.2"),
    MINECRAFT_1_20_3(765, "1.20.3"),
    MINECRAFT_1_20_4(765, "1.20.4"),
    MINECRAFT_1_20_5(765, "1.20.5"),
    MINECRAFT_1_20_6(765, "1.20.6");

    private final int protocolVersion;
    private final String name;

    ProtocolVersion(final int protocolVersion, @NotNull final String name) {
        this.protocolVersion = protocolVersion;
        this.name = name;
    }

    /**
     * Retrieves the ProtocolVersion associated with the given protocol number.
     *
     * @param protocolVersion The protocol number.
     * @return The corresponding ProtocolVersion, or UNKNOWN if not found.
     */
    public static ProtocolVersion getProtocolVersion(final int protocolVersion) {
        return Arrays.stream(ProtocolVersion.values())
                .filter(protocol -> protocol.getProtocolVersion() == protocolVersion)
                .findAny()
                .orElse(ProtocolVersion.UNKNOWN);
    }

    /**
     * Retrieves the ProtocolVersion associated with the current Minecraft version.
     *
     * @return The ProtocolVersion for the current Minecraft version, or UNKNOWN if not found.
     */
    public static ProtocolVersion getCurrentProtocolVersion() {
        return Arrays.stream(ProtocolVersion.values())
                .filter(protocol -> protocol.getName().equalsIgnoreCase(BukkitCommon.getMinecraftVersion()))
                .findFirst()
                .orElse(ProtocolVersion.UNKNOWN);
    }

}
