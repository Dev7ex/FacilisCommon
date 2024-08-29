package com.dev7ex.common.bukkit.scoreboard.game;

/**
 * @author Dev7ex
 * @since 20.08.2024
 */

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * Lightweight packet-based scoreboard API for Bukkit plugins.
 * It can be safely used asynchronously as everything is at packet level.
 * <p>
 * The project is on <a href="https://github.com/MrMicky-FR/FastBoard">GitHub</a>.
 *
 * @author MrMicky
 * @version 2.1.3
 */
public abstract class GameScoreboardBase<T> {

    private static final Map<Class<?>, Field[]> PACKETS = new HashMap<>(8);
    protected static final String[] COLOR_CODES = Arrays.stream(ChatColor.values())
            .map(Object::toString)
            .toArray(String[]::new);
    private static final VersionType VERSION_TYPE;
    // Packets and components
    private static final Class<?> CHAT_COMPONENT_CLASS;
    private static final Class<?> CHAT_FORMAT_ENUM;
    private static final Object RESET_FORMATTING;
    private static final MethodHandle PLAYER_CONNECTION;
    private static final MethodHandle SEND_PACKET;
    private static final MethodHandle PLAYER_GET_HANDLE;
    private static final MethodHandle FIXED_NUMBER_FORMAT;
    // Scoreboard packets
    private static final GameScoreboardReflection.PacketConstructor PACKET_SB_OBJ;
    private static final GameScoreboardReflection.PacketConstructor PACKET_SB_DISPLAY_OBJ;
    private static final GameScoreboardReflection.PacketConstructor PACKET_SB_TEAM;
    private static final GameScoreboardReflection.PacketConstructor PACKET_SB_SERIALIZABLE_TEAM;
    private static final MethodHandle PACKET_SB_SET_SCORE;
    private static final MethodHandle PACKET_SB_RESET_SCORE;
    private static final boolean SCORE_OPTIONAL_COMPONENTS;
    // Scoreboard enums
    private static final Class<?> DISPLAY_SLOT_TYPE;
    private static final Class<?> ENUM_SB_HEALTH_DISPLAY;
    private static final Class<?> ENUM_SB_ACTION;
    private static final Object BLANK_NUMBER_FORMAT;
    private static final Object SIDEBAR_DISPLAY_SLOT;
    private static final Object ENUM_SB_HEALTH_DISPLAY_INTEGER;
    private static final Object ENUM_SB_ACTION_CHANGE;
    private static final Object ENUM_SB_ACTION_REMOVE;

    static {
        try {
            final MethodHandles.Lookup lookup = MethodHandles.lookup();

            if (GameScoreboardReflection.isRepackaged()) {
                VERSION_TYPE = VersionType.V1_17;
            } else if (GameScoreboardReflection.nmsOptionalClass(null, "ScoreboardServer$Action").isPresent()
                    || GameScoreboardReflection.nmsOptionalClass(null, "ServerScoreboard$Method").isPresent()) {
                VERSION_TYPE = VersionType.V1_13;
            } else if (GameScoreboardReflection.nmsOptionalClass(null, "IScoreboardCriteria$EnumScoreboardHealthDisplay").isPresent()
                    || GameScoreboardReflection.nmsOptionalClass(null, "ObjectiveCriteria$RenderType").isPresent()) {
                VERSION_TYPE = VersionType.V1_8;
            } else {
                VERSION_TYPE = VersionType.V1_7;
            }

            final String gameProtocolPackage = "network.protocol.game";
            final Class<?> craftPlayerClass = GameScoreboardReflection.obcClass("entity.CraftPlayer");
            final Class<?> entityPlayerClass = GameScoreboardReflection.nmsClass("server.level", "EntityPlayer", "ServerPlayer");
            final Class<?> playerConnectionClass = GameScoreboardReflection.nmsClass("server.network", "PlayerConnection", "ServerGamePacketListenerImpl");
            final Class<?> packetClass = GameScoreboardReflection.nmsClass("network.protocol", "Packet");
            final Class<?> packetSbObjClass = GameScoreboardReflection.nmsClass(gameProtocolPackage, "PacketPlayOutScoreboardObjective", "ClientboundSetObjectivePacket");
            final Class<?> packetSbDisplayObjClass = GameScoreboardReflection.nmsClass(gameProtocolPackage, "PacketPlayOutScoreboardDisplayObjective", "ClientboundSetDisplayObjectivePacket");
            final Class<?> packetSbScoreClass = GameScoreboardReflection.nmsClass(gameProtocolPackage, "PacketPlayOutScoreboardScore", "ClientboundSetScorePacket");
            final Class<?> packetSbTeamClass = GameScoreboardReflection.nmsClass(gameProtocolPackage, "PacketPlayOutScoreboardTeam", "ClientboundSetPlayerTeamPacket");
            final Class<?> sbTeamClass = VersionType.V1_17.isHigherOrEqual()
                    ? GameScoreboardReflection.innerClass(packetSbTeamClass, innerClass -> !innerClass.isEnum()) : null;
            final Field playerConnectionField = Arrays.stream(entityPlayerClass.getFields())
                    .filter(field -> field.getType().isAssignableFrom(playerConnectionClass))
                    .findFirst().orElseThrow(NoSuchFieldException::new);
            final Method sendPacketMethod = Stream.concat(
                            Arrays.stream(playerConnectionClass.getSuperclass().getMethods()),
                            Arrays.stream(playerConnectionClass.getMethods())
                    )
                    .filter(m -> m.getParameterCount() == 1 && m.getParameterTypes()[0] == packetClass)
                    .findFirst().orElseThrow(NoSuchMethodException::new);
            final Optional<Class<?>> displaySlotEnum = GameScoreboardReflection.nmsOptionalClass("world.scores", "DisplaySlot");
            CHAT_COMPONENT_CLASS = GameScoreboardReflection.nmsClass("network.chat", "IChatBaseComponent", "Component");
            CHAT_FORMAT_ENUM = GameScoreboardReflection.nmsClass(null, "EnumChatFormat", "ChatFormatting");
            DISPLAY_SLOT_TYPE = displaySlotEnum.orElse(int.class);
            RESET_FORMATTING = GameScoreboardReflection.enumValueOf(CHAT_FORMAT_ENUM, "RESET", 21);
            SIDEBAR_DISPLAY_SLOT = displaySlotEnum.isPresent() ? GameScoreboardReflection.enumValueOf(DISPLAY_SLOT_TYPE, "SIDEBAR", 1) : 1;
            PLAYER_GET_HANDLE = lookup.findVirtual(craftPlayerClass, "getHandle", MethodType.methodType(entityPlayerClass));
            PLAYER_CONNECTION = lookup.unreflectGetter(playerConnectionField);
            SEND_PACKET = lookup.unreflect(sendPacketMethod);
            PACKET_SB_OBJ = GameScoreboardReflection.findPacketConstructor(packetSbObjClass, lookup);
            PACKET_SB_DISPLAY_OBJ = GameScoreboardReflection.findPacketConstructor(packetSbDisplayObjClass, lookup);

            final Optional<Class<?>> numberFormat = GameScoreboardReflection.nmsOptionalClass("network.chat.numbers", "NumberFormat");
            final MethodHandle packetSbSetScore;
            MethodHandle packetSbResetScore = null;
            MethodHandle fixedFormatConstructor = null;
            Object blankNumberFormat = null;
            boolean scoreOptionalComponents = false;

            if (numberFormat.isPresent()) { // 1.20.3
                final Class<?> blankFormatClass = GameScoreboardReflection.nmsClass("network.chat.numbers", "BlankFormat");
                final Class<?> fixedFormatClass = GameScoreboardReflection.nmsClass("network.chat.numbers", "FixedFormat");
                final Class<?> resetScoreClass = GameScoreboardReflection.nmsClass(gameProtocolPackage, "ClientboundResetScorePacket");
                final MethodType scoreType = MethodType.methodType(void.class, String.class, String.class, int.class, CHAT_COMPONENT_CLASS, numberFormat.get());
                final MethodType scoreTypeOptional = MethodType.methodType(void.class, String.class, String.class, int.class, Optional.class, Optional.class);
                final MethodType removeScoreType = MethodType.methodType(void.class, String.class, String.class);
                final MethodType fixedFormatType = MethodType.methodType(void.class, CHAT_COMPONENT_CLASS);
                final Optional<Field> blankField = Arrays.stream(blankFormatClass.getFields()).filter(f -> f.getType() == blankFormatClass).findAny();
                // Fields are of type Optional in 1.20.5+
                final Optional<MethodHandle> optionalScorePacket = GameScoreboardReflection.optionalConstructor(packetSbScoreClass, lookup, scoreTypeOptional);
                fixedFormatConstructor = lookup.findConstructor(fixedFormatClass, fixedFormatType);
                packetSbSetScore = optionalScorePacket.isPresent() ? optionalScorePacket.get()
                        : lookup.findConstructor(packetSbScoreClass, scoreType);
                scoreOptionalComponents = optionalScorePacket.isPresent();
                packetSbResetScore = lookup.findConstructor(resetScoreClass, removeScoreType);
                blankNumberFormat = blankField.isPresent() ? blankField.get().get(null) : null;
            } else if (VersionType.V1_17.isHigherOrEqual()) {
                final Class<?> enumSbAction = GameScoreboardReflection.nmsClass("server", "ScoreboardServer$Action", "ServerScoreboard$Method");
                final MethodType scoreType = MethodType.methodType(void.class, enumSbAction, String.class, String.class, int.class);
                packetSbSetScore = lookup.findConstructor(packetSbScoreClass, scoreType);
            } else {
                packetSbSetScore = lookup.findConstructor(packetSbScoreClass, MethodType.methodType(void.class));
            }

            PACKET_SB_SET_SCORE = packetSbSetScore;
            PACKET_SB_RESET_SCORE = packetSbResetScore;
            PACKET_SB_TEAM = GameScoreboardReflection.findPacketConstructor(packetSbTeamClass, lookup);
            PACKET_SB_SERIALIZABLE_TEAM = sbTeamClass == null ? null : GameScoreboardReflection.findPacketConstructor(sbTeamClass, lookup);
            FIXED_NUMBER_FORMAT = fixedFormatConstructor;
            BLANK_NUMBER_FORMAT = blankNumberFormat;
            SCORE_OPTIONAL_COMPONENTS = scoreOptionalComponents;

            for (final Class<?> clazz : Arrays.asList(packetSbObjClass, packetSbDisplayObjClass, packetSbScoreClass, packetSbTeamClass, sbTeamClass)) {
                if (clazz == null) {
                    continue;
                }
                final Field[] fields = Arrays.stream(clazz.getDeclaredFields())
                        .filter(field -> !Modifier.isStatic(field.getModifiers()))
                        .toArray(Field[]::new);
                for (final Field field : fields) {
                    field.setAccessible(true);
                }
                PACKETS.put(clazz, fields);
            }

            if (VersionType.V1_8.isHigherOrEqual()) {
                final String enumSbActionClass = VersionType.V1_13.isHigherOrEqual()
                        ? "ScoreboardServer$Action"
                        : "PacketPlayOutScoreboardScore$EnumScoreboardAction";
                ENUM_SB_HEALTH_DISPLAY = GameScoreboardReflection.nmsClass("world.scores.criteria", "IScoreboardCriteria$EnumScoreboardHealthDisplay", "ObjectiveCriteria$RenderType");
                ENUM_SB_ACTION = GameScoreboardReflection.nmsClass("server", enumSbActionClass, "ServerScoreboard$Method");
                ENUM_SB_HEALTH_DISPLAY_INTEGER = GameScoreboardReflection.enumValueOf(ENUM_SB_HEALTH_DISPLAY, "INTEGER", 0);
                ENUM_SB_ACTION_CHANGE = GameScoreboardReflection.enumValueOf(ENUM_SB_ACTION, "CHANGE", 0);
                ENUM_SB_ACTION_REMOVE = GameScoreboardReflection.enumValueOf(ENUM_SB_ACTION, "REMOVE", 1);
            } else {
                ENUM_SB_HEALTH_DISPLAY = null;
                ENUM_SB_ACTION = null;
                ENUM_SB_HEALTH_DISPLAY_INTEGER = null;
                ENUM_SB_ACTION_CHANGE = null;
                ENUM_SB_ACTION_REMOVE = null;
            }
        } catch (final Throwable t) {
            throw new ExceptionInInitializerError(t);
        }
    }

    private final Player player;
    private final String id;

    private final List<T> lines = new ArrayList<>();
    private final List<T> scores = new ArrayList<>();
    private T title = this.emptyLine();

    private boolean deleted = false;

    /**
     * Creates a new FastBoard.
     *
     * @param player the owner of the scoreboard
     */
    protected GameScoreboardBase(final Player player) {
        this.player = Objects.requireNonNull(player, "player");
        this.id = "fb-" + Integer.toHexString(ThreadLocalRandom.current().nextInt());

        try {
            this.sendObjectivePacket(ObjectiveMode.CREATE);
            this.sendDisplayObjectivePacket();
        } catch (final Throwable t) {
            throw new RuntimeException("Unable to create scoreboard", t);
        }
    }

    /**
     * Get the scoreboard title.
     *
     * @return the scoreboard title
     */
    public T getTitle() {
        return this.title;
    }

    /**
     * Update the scoreboard title.
     *
     * @param title the new scoreboard title
     * @throws IllegalArgumentException if the title is longer than 32 chars on 1.12 or lower
     * @throws IllegalStateException    if {@link #delete()} was call before
     */
    public void updateTitle(final T title) {
        if (this.title.equals(Objects.requireNonNull(title, "title"))) {
            return;
        }

        this.title = title;

        try {
            this.sendObjectivePacket(ObjectiveMode.UPDATE);
        } catch (final Throwable t) {
            throw new RuntimeException("Unable to update scoreboard title", t);
        }
    }

    /**
     * Get the scoreboard lines.
     *
     * @return the scoreboard lines
     */
    public List<T> getLines() {
        return new ArrayList<>(this.lines);
    }

    /**
     * Get the specified scoreboard line.
     *
     * @param line the line number
     * @return the line
     * @throws IndexOutOfBoundsException if the line is higher than {@code size}
     */
    public T getLine(final int line) {
        this.checkLineNumber(line, true, false);

        return this.lines.get(line);
    }

    /**
     * Get how a specific line's score is displayed. On 1.20.2 or below, the value returned isn't used.
     *
     * @param line the line number
     * @return the text of how the line is displayed
     * @throws IndexOutOfBoundsException if the line is higher than {@code size}
     */
    public Optional<T> getScore(final int line) {
        this.checkLineNumber(line, true, false);

        return Optional.ofNullable(this.scores.get(line));
    }

    /**
     * Update a single scoreboard line.
     *
     * @param line the line number
     * @param text the new line text
     * @throws IndexOutOfBoundsException if the line is higher than {@link #size() size() + 1}
     */
    public synchronized void updateLine(final int line, final T text) {
        this.updateLine(line, text, null);
    }

    /**
     * Update a single scoreboard line including how its score is displayed.
     * The score will only be displayed on 1.20.3 and higher.
     *
     * @param line      the line number
     * @param text      the new line text
     * @param scoreText the new line's score, if null will not change current value
     * @throws IndexOutOfBoundsException if the line is higher than {@link #size() size() + 1}
     */
    public synchronized void updateLine(final int line, final T text, final T scoreText) {
        this.checkLineNumber(line, false, false);

        try {
            if (line < this.size()) {
                this.lines.set(line, text);
                this.scores.set(line, scoreText);

                this.sendLineChange(this.getScoreByLine(line));

                if (this.customScoresSupported()) {
                    this.sendScorePacket(this.getScoreByLine(line), ScoreboardAction.CHANGE);
                }

                return;
            }

            final List<T> newLines = new ArrayList<>(this.lines);
            final List<T> newScores = new ArrayList<>(this.scores);

            if (line > this.size()) {
                for (int i = this.size(); i < line; i++) {
                    newLines.add(this.emptyLine());
                    newScores.add(null);
                }
            }

            newLines.add(text);
            newScores.add(scoreText);

            this.updateLines(newLines, newScores);
        } catch (final Throwable t) {
            throw new RuntimeException("Unable to update scoreboard lines", t);
        }
    }

    /**
     * Remove a scoreboard line.
     *
     * @param line the line number
     */
    public synchronized void removeLine(final int line) {
        this.checkLineNumber(line, false, false);

        if (line >= this.size()) {
            return;
        }

        final List<T> newLines = new ArrayList<>(this.lines);
        final List<T> newScores = new ArrayList<>(this.scores);
        newLines.remove(line);
        newScores.remove(line);
        this.updateLines(newLines, newScores);
    }

    /**
     * Update all the scoreboard lines.
     *
     * @param lines the new lines
     * @throws IllegalArgumentException if one line is longer than 30 chars on 1.12 or lower
     * @throws IllegalStateException    if {@link #delete()} was call before
     */
    public void updateLines(final T... lines) {
        this.updateLines(Arrays.asList(lines));
    }

    /**
     * Update the lines of the scoreboard
     *
     * @param lines the new scoreboard lines
     * @throws IllegalArgumentException if one line is longer than 30 chars on 1.12 or lower
     * @throws IllegalStateException    if {@link #delete()} was call before
     */
    public synchronized void updateLines(final Collection<T> lines) {
        this.updateLines(lines, null);
    }

    /**
     * Update the lines and how their score is displayed on the scoreboard.
     * The scores will only be displayed for servers on 1.20.3 and higher.
     *
     * @param lines  the new scoreboard lines
     * @param scores the set for how each line's score should be, if null will fall back to default (blank)
     * @throws IllegalArgumentException if one line is longer than 30 chars on 1.12 or lower
     * @throws IllegalArgumentException if lines and scores are not the same size
     * @throws IllegalStateException    if {@link #delete()} was call before
     */
    public synchronized void updateLines(final Collection<T> lines, final Collection<T> scores) {
        Objects.requireNonNull(lines, "lines");
        this.checkLineNumber(lines.size(), false, true);

        if (scores != null && scores.size() != lines.size()) {
            throw new IllegalArgumentException("The size of the scores must match the size of the board");
        }

        final List<T> oldLines = new ArrayList<>(this.lines);
        this.lines.clear();
        this.lines.addAll(lines);

        final List<T> oldScores = new ArrayList<>(this.scores);
        this.scores.clear();
        this.scores.addAll(scores != null ? scores : Collections.nCopies(lines.size(), null));

        final int linesSize = this.lines.size();

        try {
            if (oldLines.size() != linesSize) {
                final List<T> oldLinesCopy = new ArrayList<>(oldLines);

                if (oldLines.size() > linesSize) {
                    for (int i = oldLinesCopy.size(); i > linesSize; i--) {
                        this.sendTeamPacket(i - 1, TeamMode.REMOVE);
                        this.sendScorePacket(i - 1, ScoreboardAction.REMOVE);
                        oldLines.remove(0);
                    }
                } else {
                    for (int i = oldLinesCopy.size(); i < linesSize; i++) {
                        this.sendScorePacket(i, ScoreboardAction.CHANGE);
                        this.sendTeamPacket(i, TeamMode.CREATE, null, null);
                    }
                }
            }

            for (int i = 0; i < linesSize; i++) {
                if (!Objects.equals(this.getLineByScore(oldLines, i), this.getLineByScore(i))) {
                    this.sendLineChange(i);
                }
                if (!Objects.equals(this.getLineByScore(oldScores, i), this.getLineByScore(this.scores, i))) {
                    this.sendScorePacket(i, ScoreboardAction.CHANGE);
                }
            }
        } catch (final Throwable t) {
            throw new RuntimeException("Unable to update scoreboard lines", t);
        }
    }

    /**
     * Update how a specified line's score is displayed on the scoreboard. A null value will reset the displayed
     * text back to default. The scores will only be displayed for servers on 1.20.3 and higher.
     *
     * @param line the line number
     * @param text the text to be displayed as the score. if null, no score will be displayed
     * @throws IllegalArgumentException if the line number is not in range
     * @throws IllegalStateException    if {@link #delete()} was call before
     */
    public synchronized void updateScore(final int line, final T text) {
        this.checkLineNumber(line, true, false);

        this.scores.set(line, text);

        try {
            if (this.customScoresSupported()) {
                this.sendScorePacket(this.getScoreByLine(line), ScoreboardAction.CHANGE);
            }
        } catch (final Throwable e) {
            throw new RuntimeException("Unable to update line score", e);
        }
    }

    /**
     * Reset a line's score back to default (blank). The score will only be displayed for servers on 1.20.3 and higher.
     *
     * @param line the line number
     * @throws IllegalArgumentException if the line number is not in range
     * @throws IllegalStateException    if {@link #delete()} was call before
     */
    public synchronized void removeScore(final int line) {
        this.updateScore(line, null);
    }

    /**
     * Update how all lines' scores are displayed. A value of null will reset the displayed text back to default.
     * The scores will only be displayed for servers on 1.20.3 and higher.
     *
     * @param texts the set of texts to be displayed as the scores
     * @throws IllegalArgumentException if the size of the texts does not match the current size of the board
     * @throws IllegalStateException    if {@link #delete()} was call before
     */
    public synchronized void updateScores(final T... texts) {
        this.updateScores(Arrays.asList(texts));
    }

    /**
     * Update how all lines' scores are displayed.  A null value will reset the displayed
     * text back to default (blank). Only available on 1.20.3+ servers.
     *
     * @param texts the set of texts to be displayed as the scores
     * @throws IllegalArgumentException if the size of the texts does not match the current size of the board
     * @throws IllegalStateException    if {@link #delete()} was call before
     */
    public synchronized void updateScores(final Collection<T> texts) {
        Objects.requireNonNull(texts, "texts");

        if (this.scores.size() != this.lines.size()) {
            throw new IllegalArgumentException("The size of the scores must match the size of the board");
        }

        final List<T> newScores = new ArrayList<>(texts);
        for (int i = 0; i < this.scores.size(); i++) {
            if (Objects.equals(this.scores.get(i), newScores.get(i))) {
                continue;
            }

            this.scores.set(i, newScores.get(i));

            try {
                if (this.customScoresSupported()) {
                    this.sendScorePacket(this.getScoreByLine(i), ScoreboardAction.CHANGE);
                }
            } catch (final Throwable e) {
                throw new RuntimeException("Unable to update scores", e);
            }
        }
    }

    /**
     * Get the player who has the scoreboard.
     *
     * @return current player for this FastBoard
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Get the scoreboard id.
     *
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get if the scoreboard is deleted.
     *
     * @return true if the scoreboard is deleted
     */
    public boolean isDeleted() {
        return this.deleted;
    }

    /**
     * Get if the server supports custom scoreboard scores (1.20.3+ servers only).
     *
     * @return true if the server supports custom scores
     */
    public boolean customScoresSupported() {
        return BLANK_NUMBER_FORMAT != null;
    }

    /**
     * Get the scoreboard size (the number of lines).
     *
     * @return the size
     */
    public int size() {
        return this.lines.size();
    }

    /**
     * Delete this FastBoard, and will remove the scoreboard for the associated player if he is online.
     * After this, all uses of {@link #updateLines} and {@link #updateTitle} will throw an {@link IllegalStateException}
     *
     * @throws IllegalStateException if this was already call before
     */
    public void delete() {
        try {
            for (int i = 0; i < this.lines.size(); i++) {
                this.sendTeamPacket(i, TeamMode.REMOVE);
            }

            this.sendObjectivePacket(ObjectiveMode.REMOVE);
        } catch (final Throwable t) {
            throw new RuntimeException("Unable to delete scoreboard", t);
        }

        this.deleted = true;
    }

    protected abstract void sendLineChange(int score) throws Throwable;

    protected abstract Object toMinecraftComponent(T value) throws Throwable;

    protected abstract String serializeLine(T value);

    protected abstract T emptyLine();

    private void checkLineNumber(final int line, final boolean checkInRange, final boolean checkMax) {
        if (line < 0) {
            throw new IllegalArgumentException("Line number must be positive");
        }

        if (checkInRange && line >= this.lines.size()) {
            throw new IllegalArgumentException("Line number must be under " + this.lines.size());
        }

        if (checkMax && line >= COLOR_CODES.length - 1) {
            throw new IllegalArgumentException("Line number is too high: " + line);
        }
    }

    protected int getScoreByLine(final int line) {
        return this.lines.size() - line - 1;
    }

    protected T getLineByScore(final int score) {
        return this.getLineByScore(this.lines, score);
    }

    protected T getLineByScore(final List<T> lines, final int score) {
        return score < lines.size() ? lines.get(lines.size() - score - 1) : null;
    }

    protected void sendObjectivePacket(final ObjectiveMode mode) throws Throwable {
        final Object packet = PACKET_SB_OBJ.invoke();

        this.setField(packet, String.class, this.id);
        this.setField(packet, int.class, mode.ordinal());

        if (mode != ObjectiveMode.REMOVE) {
            this.setComponentField(packet, this.title, 1);
            this.setField(packet, Optional.class, Optional.empty()); // Number format for 1.20.5+, previously nullable

            if (VersionType.V1_8.isHigherOrEqual()) {
                this.setField(packet, ENUM_SB_HEALTH_DISPLAY, ENUM_SB_HEALTH_DISPLAY_INTEGER);
            }
        } else if (VERSION_TYPE == VersionType.V1_7) {
            this.setField(packet, String.class, "", 1);
        }

        this.sendPacket(packet);
    }

    protected void sendDisplayObjectivePacket() throws Throwable {
        final Object packet = PACKET_SB_DISPLAY_OBJ.invoke();

        this.setField(packet, DISPLAY_SLOT_TYPE, SIDEBAR_DISPLAY_SLOT); // Position
        this.setField(packet, String.class, this.id); // Score Name

        this.sendPacket(packet);
    }

    protected void sendScorePacket(final int score, final ScoreboardAction action) throws Throwable {
        if (VersionType.V1_17.isHigherOrEqual()) {
            this.sendModernScorePacket(score, action);
            return;
        }

        final Object packet = PACKET_SB_SET_SCORE.invoke();

        this.setField(packet, String.class, COLOR_CODES[score], 0); // Player Name

        if (VersionType.V1_8.isHigherOrEqual()) {
            final Object enumAction = action == ScoreboardAction.REMOVE
                    ? ENUM_SB_ACTION_REMOVE : ENUM_SB_ACTION_CHANGE;
            this.setField(packet, ENUM_SB_ACTION, enumAction);
        } else {
            this.setField(packet, int.class, action.ordinal(), 1); // Action
        }

        if (action == ScoreboardAction.CHANGE) {
            this.setField(packet, String.class, this.id, 1); // Objective Name
            this.setField(packet, int.class, score); // Score
        }

        this.sendPacket(packet);
    }

    private void sendModernScorePacket(final int score, final ScoreboardAction action) throws Throwable {
        final String objName = COLOR_CODES[score];
        final Object enumAction = action == ScoreboardAction.REMOVE
                ? ENUM_SB_ACTION_REMOVE : ENUM_SB_ACTION_CHANGE;

        if (PACKET_SB_RESET_SCORE == null) { // Pre 1.20.3
            this.sendPacket(PACKET_SB_SET_SCORE.invoke(enumAction, this.id, objName, score));
            return;
        }

        if (action == ScoreboardAction.REMOVE) {
            this.sendPacket(PACKET_SB_RESET_SCORE.invoke(objName, this.id));
            return;
        }

        final T scoreFormat = this.getLineByScore(this.scores, score);
        final Object format = scoreFormat != null
                ? FIXED_NUMBER_FORMAT.invoke(this.toMinecraftComponent(scoreFormat))
                : BLANK_NUMBER_FORMAT;
        final Object scorePacket = SCORE_OPTIONAL_COMPONENTS
                ? PACKET_SB_SET_SCORE.invoke(objName, this.id, score, Optional.empty(), Optional.of(format))
                : PACKET_SB_SET_SCORE.invoke(objName, this.id, score, null, format);

        this.sendPacket(scorePacket);
    }

    protected void sendTeamPacket(final int score, final TeamMode mode) throws Throwable {
        this.sendTeamPacket(score, mode, null, null);
    }

    protected void sendTeamPacket(final int score, final TeamMode mode, final T prefix, final T suffix)
            throws Throwable {
        if (mode == TeamMode.ADD_PLAYERS || mode == TeamMode.REMOVE_PLAYERS) {
            throw new UnsupportedOperationException();
        }

        final Object packet = PACKET_SB_TEAM.invoke();

        this.setField(packet, String.class, this.id + ':' + score); // Team name
        this.setField(packet, int.class, mode.ordinal(), VERSION_TYPE == VersionType.V1_8 ? 1 : 0); // Update mode

        if (mode == TeamMode.REMOVE) {
            this.sendPacket(packet);
            return;
        }

        if (VersionType.V1_17.isHigherOrEqual()) {
            final Object team = PACKET_SB_SERIALIZABLE_TEAM.invoke();
            // Since the packet is initialized with null values, we need to change more things.
            this.setComponentField(team, null, 0); // Display name
            this.setField(team, CHAT_FORMAT_ENUM, RESET_FORMATTING); // Color
            this.setComponentField(team, prefix, 1); // Prefix
            this.setComponentField(team, suffix, 2); // Suffix
            this.setField(team, String.class, "always", 0); // Visibility
            this.setField(team, String.class, "always", 1); // Collisions
            this.setField(packet, Optional.class, Optional.of(team));
        } else {
            this.setComponentField(packet, prefix, 2); // Prefix
            this.setComponentField(packet, suffix, 3); // Suffix
            this.setField(packet, String.class, "always", 4); // Visibility for 1.8+
            this.setField(packet, String.class, "always", 5); // Collisions for 1.9+
        }

        if (mode == TeamMode.CREATE) {
            this.setField(packet, Collection.class, Collections.singletonList(COLOR_CODES[score])); // Players in the team
        }

        this.sendPacket(packet);
    }

    private void sendPacket(final Object packet) throws Throwable {
        if (this.deleted) {
            throw new IllegalStateException("This FastBoard is deleted");
        }

        if (this.player.isOnline()) {
            final Object entityPlayer = PLAYER_GET_HANDLE.invoke(this.player);
            final Object playerConnection = PLAYER_CONNECTION.invoke(entityPlayer);
            SEND_PACKET.invoke(playerConnection, packet);
        }
    }

    private void setField(final Object object, final Class<?> fieldType, final Object value)
            throws ReflectiveOperationException {
        this.setField(object, fieldType, value, 0);
    }

    private void setField(final Object packet, final Class<?> fieldType, final Object value, final int count)
            throws ReflectiveOperationException {
        int i = 0;
        for (final Field field : PACKETS.get(packet.getClass())) {
            if (field.getType() == fieldType && count == i++) {
                field.set(packet, value);
            }
        }
    }

    private void setComponentField(final Object packet, final T value, final int count) throws Throwable {
        if (!VersionType.V1_13.isHigherOrEqual()) {
            final String line = value != null ? this.serializeLine(value) : "";
            this.setField(packet, String.class, line, count);
            return;
        }

        int i = 0;
        for (final Field field : PACKETS.get(packet.getClass())) {
            if ((field.getType() == String.class || field.getType() == CHAT_COMPONENT_CLASS) && count == i++) {
                field.set(packet, this.toMinecraftComponent(value));
            }
        }
    }

    public enum ObjectiveMode {
        CREATE, REMOVE, UPDATE
    }

    public enum TeamMode {
        CREATE, REMOVE, UPDATE, ADD_PLAYERS, REMOVE_PLAYERS
    }

    public enum ScoreboardAction {
        CHANGE, REMOVE
    }

    enum VersionType {
        V1_7, V1_8, V1_13, V1_17;

        public boolean isHigherOrEqual() {
            return VERSION_TYPE.ordinal() >= this.ordinal();
        }
    }

}
