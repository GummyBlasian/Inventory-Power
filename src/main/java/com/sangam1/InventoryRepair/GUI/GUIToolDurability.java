package com.sangam1.InventoryRepair.GUI;

import com.sangam1.InventoryRepair.ConfigHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class GUIToolDurability extends Gui {

	private static String ItemInHandTextName = "";
	private static String ItemInHandTextDamage = "";
	private static String ItemInHandTextRepair = "";

	public GUIToolDurability(Minecraft mc) {
		if (ConfigHandler.enableItemIdGUI) {
			ScaledResolution scaled = new ScaledResolution(mc);
			int width = scaled.getScaledWidth();
			int height = scaled.getScaledHeight();
			drawString(mc.fontRendererObj, ItemInHandTextName, 4, height - 25, Integer.parseInt("FFAA00", 16));
			drawString(mc.fontRendererObj, ItemInHandTextDamage, 4, height - 15, Integer.parseInt("FFAA00", 16));
			// drawCenteredString(mc.fontRendererObj, ItemInHandText, width / 2,
			// (height / 2) - 4, Integer.parseInt("FFAA00", 16));
		}
	}

	public static void setItemInHandText(String name, String damage) {
		ItemInHandTextName = name;
		ItemInHandTextDamage = damage;
	}
}
