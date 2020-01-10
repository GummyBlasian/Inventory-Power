package com.github.GummyBlasian.InventoryPower.GUI;

import org.lwjgl.opengl.GL11;

import com.github.GummyBlasian.InventoryPower.Config.ConfigHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.BlockPos;

public class GUIToolDurability extends Gui {

	private static String ItemInHandTextName = "";
	private static String ItemInHandTextDamage = "";
	@SuppressWarnings("unused")
	private static String ItemInHandTextRepair = "";

	@SuppressWarnings("unused")
	public GUIToolDurability(Minecraft mc) {
		if (ConfigHandler.enableItemIdGUI) {
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glScalef(0.75f, 0.75f, 0.75f);
			
			ScaledResolution scaled = new ScaledResolution(mc);
			int width = scaled.getScaledWidth();
			int height = scaled.getScaledHeight();
			
			drawString(mc.fontRenderer, ItemInHandTextName, 4, height - 65, Integer.parseInt("FFAA00", 16));
			drawString(mc.fontRenderer, ItemInHandTextDamage, 4, height - 55, Integer.parseInt("FFAA00", 16));
			drawString(mc.fontRenderer, mc.world.getBiome(new BlockPos(mc.player)).getBiomeName(), 4, height - 45, Integer.parseInt("FFAA00", 16));
			drawString(mc.fontRenderer, getTimeClockDay(mc), 4, height - 35, Integer.parseInt("FFAA00", 16));
			drawString(mc.fontRenderer, getTimeClockHour(mc), 4, height - 25, Integer.parseInt("FFAA00", 16));
			drawString(mc.fontRenderer, "Player Health: " + mc.player.getHealth(), 4, height - 15, Integer.parseInt("FFAA00", 16));		
			drawString(mc.fontRenderer, "Player Food: " + mc.player.getFoodStats().getFoodLevel(), 4, height - 5, Integer.parseInt("FFAA00", 16));	
			
			GL11.glPopMatrix();
		}
	}
	
	public static String getTimeClockDay(Minecraft mc){
		long totalTime = mc.world.getTotalWorldTime();
		float val = Float.valueOf(totalTime);
		float timeDay = val/24000;
		return "Day: " + (int)Math.floor((int)timeDay);
	}
	
	@SuppressWarnings("unused")
	public static String getTimeClockHour(Minecraft mc){
		long totalTime = mc.world.getTotalWorldTime();
		float val = Float.valueOf(totalTime);
		float timeSec = val/0.27f;
		float timeMin = val/16.6f;
		float timeHour = val/1000;
		float timeDay = val/24000;
		float timeChangeHour = timeHour - (((int) timeDay)*(24000/1000));
		float timeChangeMin = timeMin - (((int) timeHour)*(1000/16.6f));// - (((int) timeDay)*(24000/1000));
		int timeHourCorrect = 0;
		if(timeChangeHour >= 13){
			timeHourCorrect = (((int) timeChangeHour) - 12);
		}
		//System.out.println(((int) timeChangeHour) + " " + ((int) timeHourCorrect));
		if(timeChangeHour > 13){
			return "Time: " + (int)timeHourCorrect + ":" + String.format("%02d", ((int) timeChangeMin)) + " PM";
		} else {
			return "Time: " + (int)timeChangeHour + ":" + String.format("%02d", ((int) timeChangeMin)) + " AM";
		}
	}

	public static void setItemInHandText(String name, String damage) {
		ItemInHandTextName = name;
		ItemInHandTextDamage = damage;
	}
}
