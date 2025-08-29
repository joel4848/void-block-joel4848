package net.voidblock.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class VoidBlockConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("voidblock.json");

    private static VoidBlockConfig INSTANCE;

    public boolean enableRedstoneControl = false;

    public static VoidBlockConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = load();
        }
        return INSTANCE;
    }

    private static VoidBlockConfig load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH);
                return GSON.fromJson(json, VoidBlockConfig.class);
            } catch (IOException e) {
                System.err.println("Failed to load VoidBlock config: " + e.getMessage());
            }
        }

        // Create default config
        VoidBlockConfig config = new VoidBlockConfig();
        config.save();
        return config;
    }

    public void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH, GSON.toJson(this));
        } catch (IOException e) {
            System.err.println("Failed to save VoidBlock config: " + e.getMessage());
        }
    }
}