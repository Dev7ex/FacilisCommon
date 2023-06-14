package com.dev7ex.common.bukkit.scoreboard;

import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dev7ex
 * @since 10.06.2023
 */
@Getter(AccessLevel.PUBLIC)
public class Sidebar {

    private final String name;
    private final String criteria;
    private final String displayName;
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final SidebarType type;

    public Sidebar(final String name, final String criteria, final String displayName, final SidebarType type) {
        this.name = name;
        this.criteria = criteria;
        this.displayName = displayName;
        this.type = type;

        if(this.type == SidebarType.MAIN) {
            this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        } else {
            this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        }

        if(!this.scoreboard.getObjectives().isEmpty()) {
            this.scoreboard.getObjectives().forEach(Objective::unregister);
        }

        this.objective = this.scoreboard.registerNewObjective(name, criteria, displayName);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public final void setLines(final String... lines) {
        final List<String> lineList = Arrays.asList(lines);

        for(int i = 0; i < lineList.size(); i++) {
            this.objective.getScore(lines[i]).setScore(i);
        }
    }

    public final void setLine(final String line, final int score) {
        this.objective.getScore(line).setScore(score);
    }

    public final Team registerNewTeam(final String name) {
        return this.scoreboard.registerNewTeam(name);
    }

    public final Team registerNewTeam(final String name, final String suffix, final String entry) {
        final Team newTeam = this.scoreboard.registerNewTeam(name);
        newTeam.setSuffix(suffix);
        newTeam.addEntry(entry);

        return newTeam;
    }

    public final Team registerNewTeam(final String name, final String prefix, final String suffix, final String entry) {
        final Team newTeam = this.scoreboard.registerNewTeam(name);
        newTeam.setPrefix(prefix);
        newTeam.setSuffix(suffix);
        newTeam.addEntry(entry);

        return newTeam;
    }

    public final Team getTeam(final String name) {
        return this.scoreboard.getTeam(name);
    }

    public final void updateTeam(final String team, final String prefix, final String suffix) {
        if(prefix == null) {
            this.getTeam(team).setSuffix(suffix);
            return;
        }

        if(suffix == null) {
            this.getTeam(team).setPrefix(prefix);
            return;
        }
        this.getTeam(team).setPrefix(prefix);
        this.getTeam(team).setSuffix(suffix);
    }

    public final void remove() {
        if(!this.scoreboard.getObjectives().isEmpty()) {
            this.scoreboard.getObjectives().forEach(Objective::unregister);
        }

        if(!this.scoreboard.getTeams().isEmpty()) {
            this.scoreboard.getTeams().forEach(Team::unregister);
        }

        if(this.scoreboard.getObjective(DisplaySlot.SIDEBAR) != null) {
            this.scoreboard.getObjective(DisplaySlot.SIDEBAR).unregister();
        }
    }

    public final void set(final Player player) {
        player.setScoreboard(this.scoreboard);
    }

    public enum SidebarType {
        MAIN,
        NEW;
    }

}
