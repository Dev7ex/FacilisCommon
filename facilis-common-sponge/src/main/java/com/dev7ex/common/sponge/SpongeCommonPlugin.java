package com.dev7ex.common.sponge;

import com.dev7ex.common.sponge.plugin.SpongePlugin;
import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.StartedEngineEvent;
import org.spongepowered.plugin.builtin.jvm.Plugin;

/**
 * @author Dev7ex
 * @since 29.08.2024
 */
@Plugin("faciliscommon")
public class SpongeCommonPlugin extends SpongePlugin {

    @Inject
    private Logger logger;

    @Listener
    public void handleServerStart(final StartedEngineEvent<Server> event) {
        this.logger.info("Successfully running ExamplePlugin!!!");
    }

}
