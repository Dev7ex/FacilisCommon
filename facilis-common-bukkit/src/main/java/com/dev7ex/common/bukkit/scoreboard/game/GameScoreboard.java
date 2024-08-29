package com.dev7ex.common.bukkit.scoreboard.game;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

/**
 * @author mrmicky
 * @see <a href="https://github.com/MrMicky-FR/FastBoard">https://github.com/MrMicky-FR/FastBoard</a>
 */
public class GameScoreboard extends GameScoreboardBase<Component> {

    private static final MethodHandle COMPONENT_METHOD;
    private static final Object EMPTY_COMPONENT;
    private static final boolean ADVENTURE_SUPPORT;

    static {
        ADVENTURE_SUPPORT = GameScoreboardReflection
                .optionalClass("io.papermc.paper.adventure.PaperAdventure")
                .isPresent();
        final MethodHandles.Lookup lookup = MethodHandles.lookup();

        try {
            if (ADVENTURE_SUPPORT) {
                final Class<?> paperAdventure = Class.forName("io.papermc.paper.adventure.PaperAdventure");
                final Method method = paperAdventure.getDeclaredMethod("asVanilla", Component.class);
                COMPONENT_METHOD = lookup.unreflect(method);
                EMPTY_COMPONENT = COMPONENT_METHOD.invoke(Component.empty());
            } else {
                final Class<?> craftChatMessageClass = GameScoreboardReflection.obcClass("util.CraftChatMessage");
                COMPONENT_METHOD = lookup.unreflect(craftChatMessageClass.getMethod("fromString", String.class));
                EMPTY_COMPONENT = Array.get(COMPONENT_METHOD.invoke(""), 0);
            }
        } catch (final Throwable t) {
            throw new ExceptionInInitializerError(t);
        }
    }

    /**
     * {@inheritDoc}
     */
    public GameScoreboard(final Player player) {
        super(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void sendLineChange(final int score) throws Throwable {
        final Component line = this.getLineByScore(score);

        this.sendTeamPacket(score, GameScoreboardBase.TeamMode.UPDATE, line, null);
    }

    @Override
    protected Object toMinecraftComponent(final Component component) throws Throwable {
        if (component == null) {
            return EMPTY_COMPONENT;
        }

        // If the server isn't running adventure natively, we convert the component to legacy text
        // and then to a Minecraft chat component
        if (!ADVENTURE_SUPPORT) {
            final String legacy = this.serializeLine(component);

            return Array.get(COMPONENT_METHOD.invoke(legacy), 0);
        }

        return COMPONENT_METHOD.invoke(component);
    }

    @Override
    protected String serializeLine(final Component value) {
        return LegacyComponentSerializer.legacySection().serialize(value);
    }

    @Override
    protected Component emptyLine() {
        return Component.empty();
    }
}
