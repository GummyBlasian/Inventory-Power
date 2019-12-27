package com.sangam1.InventoryRepair;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

    public static Configuration config;

    // Settings
    public static boolean enableBlockIdGUI;
    public static boolean enableItemIdGUI;
    public static boolean enableItemRepair;
    public static boolean enableGoCraft;

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        String category;

        category = "gui";
        config.addCustomCategoryComment(category, "GUI Settings");
        enableBlockIdGUI = config.getBoolean("enableBlockIdGUI", category, true, "Allows GUI for displaying what a player is looking at. Disable if you don't want the GUI.");
        enableItemIdGUI = config.getBoolean("enableItemIdGUI", category, true, "Allows GUI for displaying what a player is holding. Disable if you don't want the GUI.");
        enableItemRepair = config.getBoolean("enableItemRepair", category, true, "Allows item auto-repair. Disable if you don't want item auto-repair.");
        enableGoCraft = config.getBoolean("enableGoCraft", category, true, "Allow portable crafting table. Disable if you don't want portable crafting table. Requires restart of client.");

        config.save();
    }
}
