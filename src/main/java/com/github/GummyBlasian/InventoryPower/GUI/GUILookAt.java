package com.github.GummyBlasian.InventoryPower.GUI;

import com.github.GummyBlasian.InventoryPower.Config.ConfigHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class GUILookAt extends Gui {

	private static String ItemLookingTextName = "";

	@SuppressWarnings("unused")
	public GUILookAt(Minecraft mc) {
		if (ConfigHandler.enableBlockIdGUI) {
			ScaledResolution scaled = new ScaledResolution(mc);
			int width = scaled.getScaledWidth();
			int height = scaled.getScaledHeight();
			drawString(mc.fontRenderer, ItemLookingTextName, (width / 2) - (mc.fontRenderer.getStringWidth(ItemLookingTextName)/2), 10, Integer.parseInt("FFAA00", 16));
		}
	}

	public static void setItemInHandText(String looking) {
		ItemLookingTextName = looking;
	}
}
