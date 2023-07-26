package com.dev7ex.common.bukkit.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Dev7ex
 * @since 21.07.2023
 */
public class ScoreboardBuilder {

    private final Scoreboard scoreboard;
    private Objective objective;
    private String name;
    private String criteria;
    private String displayName;
    private DisplaySlot displaySlot;

    public ScoreboardBuilder(@NotNull final ScoreboardType type) {
        if (type == ScoreboardType.NEW) {
            this.scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
            return;
        }
        this.scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
    }

    public ScoreboardBuilder setName(@NotNull final String name) {
        this.name = name;
        return this;
    }

    public ScoreboardBuilder setCriteria(@NotNull final String criteria) {
        this.criteria = criteria;
        return this;
    }

    public ScoreboardBuilder setDisplayName(@NotNull final String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ScoreboardBuilder setDisplaySlot(@NotNull final DisplaySlot displaySlot) {
        this.displaySlot = displaySlot;
        return this;
    }

    public ScoreboardBuilder buildObjective() {
        this.objective = this.scoreboard.registerNewObjective(this.name, this.criteria, this.displayName);
        this.objective.setDisplaySlot(this.displaySlot);
        return this;
    }

    public ScoreboardBuilder setLine(@NotNull final String line, final int score) {
        this.objective.getScore(line).setScore(score);
        return this;
    }

    public ScoreboardBuilder setTeam(@NotNull final String name) {
        this.scoreboard.registerNewTeam(name);
        return this;
    }

    public ScoreboardBuilder setTeam(@NotNull final String name, @NotNull final String prefix) {
        final Team team = this.scoreboard.registerNewTeam(name);
        team.setPrefix(prefix);
        return this;
    }

    public ScoreboardBuilder setTeam(@NotNull final String name, @NotNull final String prefix, @NotNull final String entry) {
        final Team team = this.scoreboard.registerNewTeam(name);
        team.setPrefix(prefix);
        team.addEntry(entry);
        return this;
    }


    public Scoreboard build() {
        return this.scoreboard;
    }

}
