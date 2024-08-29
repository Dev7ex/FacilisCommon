package com.dev7ex.common.bukkit.scoreboard.team;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 21.07.2023
 */
public class ScoreboardTeamBuilder {

    private final Scoreboard scoreboard;
    private String name;
    private String displayName;
    private String prefix;
    private String suffix;
    private ChatColor color;
    private boolean allowFriendlyFire;
    private boolean canSeeFriendlyInvisibles;
    private NameTagVisibility nameTagVisibility;
    private String entry;
    private Team team;

    public ScoreboardTeamBuilder(@NotNull final Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public ScoreboardTeamBuilder setName(@NotNull final String name) {
        this.name = name;
        return this;
    }

    public ScoreboardTeamBuilder setDisplayName(@NotNull final String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ScoreboardTeamBuilder setPrefix(@NotNull final String prefix) {
        this.prefix = prefix;
        return this;
    }

    public ScoreboardTeamBuilder setSuffix(@NotNull final String suffix) {
        this.suffix = suffix;
        return this;
    }

    public ScoreboardTeamBuilder setColor(@NotNull final ChatColor color) {
        this.color = color;
        return this;
    }

    public ScoreboardTeamBuilder setAllowFriendlyFire(final boolean allowFriendlyFire) {
        this.allowFriendlyFire = allowFriendlyFire;
        return this;
    }

    public ScoreboardTeamBuilder setCanSeeFriendlyInvisibles(final boolean canSeeFriendlyInvisibles) {
        this.canSeeFriendlyInvisibles = canSeeFriendlyInvisibles;
        return this;
    }

    @Deprecated
    public ScoreboardTeamBuilder setNameTagVisibility(@NotNull final NameTagVisibility nameTagVisibility) {
        this.nameTagVisibility = nameTagVisibility;
        return this;
    }

    public Team build() {
        this.team = this.scoreboard.registerNewTeam(this.name);

        if (this.prefix != null) {
            this.team.setPrefix(this.prefix);
        }

        if (this.suffix != null) {
            this.team.setSuffix(this.suffix);
        }

        if (this.color != null) {
            this.team.setColor(this.color);
        }

        this.team.setAllowFriendlyFire(this.allowFriendlyFire);
        this.team.setCanSeeFriendlyInvisibles(this.canSeeFriendlyInvisibles);

        if (this.nameTagVisibility != null) {
            this.team.setNameTagVisibility(this.nameTagVisibility);
        }
        return this.team;
    }

}
