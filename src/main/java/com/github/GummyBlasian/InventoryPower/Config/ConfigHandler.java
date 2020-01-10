package com.github.GummyBlasian.InventoryPower.Config;

import com.github.GummyBlasian.InventoryPower.References.StartupRef;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = StartupRef.MODID)
@Config.LangKey("sangamir.config.title")
public class ConfigHandler {

    // Settings
    @Comment("Allows GUI for displaying what a player is looking at. Disable if you don't want the GUI.")
    public static boolean enableBlockIdGUI = true;
    
    @Comment("Allows GUI for displaying what a player is holding. Disable if you don't want the GUI.")
    public static boolean enableItemIdGUI = true;
    
    @Comment("Allows item auto-repair. Disable if you don't want item auto-repair.")
    public static boolean enableItemRepair = true;
    
    @Comment("Allow portable crafting table. Disable if you don't want portable crafting table. Requires restart of client.")
    @RequiresMcRestart
    public static boolean enableGoCraft = true;
    
    
    @Mod.EventBusSubscriber(modid = StartupRef.MODID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(StartupRef.MODID)) {
				ConfigManager.sync(StartupRef.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
