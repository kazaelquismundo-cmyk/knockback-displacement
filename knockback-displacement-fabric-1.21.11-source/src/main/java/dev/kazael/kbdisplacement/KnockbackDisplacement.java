package dev.kazael.kbdisplacement;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnockbackDisplacement implements ModInitializer {
    public static final String MOD_ID = "knockback_displacement";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        KnockbackConfig.load();
        LOGGER.info("Knockback Displacement loaded");
    }
}
