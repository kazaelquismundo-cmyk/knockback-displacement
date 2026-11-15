package dev.kazael.kbdisplacement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class KnockbackConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("knockback-displacement.json");

    public boolean enabled = true;
    public double horizontalMultiplier = 1.0;
    public double verticalMultiplier = 1.0;

    private static KnockbackConfig INSTANCE = new KnockbackConfig();

    public static KnockbackConfig get() { return INSTANCE; }

    public static void load() {
        try {
            if (Files.exists(FILE)) {
                KnockbackConfig loaded = GSON.fromJson(Files.readString(FILE), KnockbackConfig.class);
                if (loaded != null) INSTANCE = loaded;
            } else save();
            INSTANCE.clamp();
        } catch (Exception e) {
            KnockbackDisplacement.LOGGER.error("Could not load config; using defaults", e);
            INSTANCE = new KnockbackConfig();
        }
    }

    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, GSON.toJson(INSTANCE));
        } catch (IOException e) {
            KnockbackDisplacement.LOGGER.error("Could not save config", e);
        }
    }

    private void clamp() {
        horizontalMultiplier = Math.max(0.0, Math.min(5.0, horizontalMultiplier));
        verticalMultiplier = Math.max(0.0, Math.min(5.0, verticalMultiplier));
    }

    public void changeHorizontal(double amount) {
        horizontalMultiplier = Math.max(0.0, Math.min(5.0, horizontalMultiplier + amount));
        save();
    }

    public void changeVertical(double amount) {
        verticalMultiplier = Math.max(0.0, Math.min(5.0, verticalMultiplier + amount));
        save();
    }
}
