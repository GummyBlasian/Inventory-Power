package com.sangam1.InventoryRepair.GUI;

import com.sangam1.InventoryRepair.ConfigHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Items;

public class GUIArmorDurability extends Gui {

	private static String HelmetDurability = "";
	private static String BodyDurability = "";
	private static String LegDurability = "";
	private static String BootDurability = "";

	public GUIArmorDurability(Minecraft mc) {
		if (ConfigHandler.enableBlockIdGUI) {
			ScaledResolution scaled = new ScaledResolution(mc);
			int width = scaled.getScaledWidth();
			int height = scaled.getScaledHeight();
			drawString(mc.fontRendererObj, HelmetDurability, width - mc.fontRendererObj.getStringWidth(HelmetDurability) - 5, height - 95, Integer.parseInt("FFAA00", 16));
			drawString(mc.fontRendererObj, BodyDurability, width - mc.fontRendererObj.getStringWidth(BodyDurability) - 5, height - 85, Integer.parseInt("FFAA00", 16));
			drawString(mc.fontRendererObj, LegDurability, width - mc.fontRendererObj.getStringWidth(LegDurability) - 5, height - 75, Integer.parseInt("FFAA00", 16));
			drawString(mc.fontRendererObj, BootDurability, width - mc.fontRendererObj.getStringWidth(BootDurability) - 5, height - 65, Integer.parseInt("FFAA00", 16));
		}
	}
	
	public static void setHelmet(String value){
		HelmetDurability = value;
	}
	
	public static void setBody(String value){
		BodyDurability = value;
	}
	
	public static void setLeg(String value){
		LegDurability = value;
	}
	
	public static void setBoot(String value){
		BootDurability = value;
	}
}
