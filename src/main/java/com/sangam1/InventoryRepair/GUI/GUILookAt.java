package com.sangam1.InventoryRepair.GUI;

import com.sangam1.InventoryRepair.ConfigHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class GUILookAt extends Gui {

	private static String ItemLookingTextName = "";

	public GUILookAt(Minecraft mc) {
		if (ConfigHandler.enableBlockIdGUI) {
			ScaledResolution scaled = new ScaledResolution(mc);
			int width = scaled.getScaledWidth();
			int height = scaled.getScaledHeight();
			drawString(mc.fontRendererObj, ItemLookingTextName, (width / 2) - (mc.fontRendererObj.getStringWidth(ItemLookingTextName)/2), 20, Integer.parseInt("FFAA00", 16));
		}
	}

	public static void setItemInHandText(String looking) {
		ItemLookingTextName = looking;
	}
}
