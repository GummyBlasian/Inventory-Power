package com.github.GummyBlasian.InventoryPower.GUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public class GUIArmorDurability{

	private static String HelmetDurability = "";
	private static String BodyDurability = "";
	private static String LegDurability = "";
	private static String BootDurability = "";

	public static void drawGUI(FontRenderer textRenderer, Minecraft mc) {
		ScaledResolution scaled = new ScaledResolution(mc);
		int width = (int) (scaled.getScaledWidth()*1.33);
		int height = (int) (scaled.getScaledHeight()*1.33);
		textRenderer.drawStringWithShadow(HelmetDurability, width - mc.fontRenderer.getStringWidth(HelmetDurability) - 5, height - 10, Integer.parseInt("FFAA00", 16));
		textRenderer.drawStringWithShadow(BodyDurability, width - mc.fontRenderer.getStringWidth(BodyDurability) - 5, height - 20, Integer.parseInt("FFAA00", 16));
		textRenderer.drawStringWithShadow(LegDurability, width - mc.fontRenderer.getStringWidth(LegDurability) - 5, height - 30, Integer.parseInt("FFAA00", 16));
		textRenderer.drawStringWithShadow(BootDurability, width - mc.fontRenderer.getStringWidth(BootDurability) - 5, height - 40, Integer.parseInt("FFAA00", 16));

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
