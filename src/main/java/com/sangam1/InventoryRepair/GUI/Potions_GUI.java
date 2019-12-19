package com.sangam1.InventoryRepair.GUI;

import java.util.ArrayList;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sangam1.InventoryRepair.Config.ConfigHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class Potions_GUI {

	private final Minecraft mc = Minecraft.getInstance();

	int guiPosX = mc.mainWindow.getScaledWidth();
	int guiPosY = mc.mainWindow.getScaledHeight() - ConfigHandler.LOOK_HUD_Y.getValue();

	private ArrayList<EffectInstance> potions = new ArrayList<EffectInstance>();

	//Stops the default tooltip from being displayed
	@SubscribeEvent
	public void onRenderTick(RenderGameOverlayEvent.Post event) {
		FontRenderer textRenderer = mc.fontRenderer;

		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL || !ConfigHandler.LOOK_HUD_ENABLED.getValue())
			return;

		if (ConfigHandler.LOOK_HUD_HIDE_IN_CHAT.getValue())
			if (mc.currentScreen instanceof ChatScreen)
				return;

		if (ConfigHandler.LOOK_HUD_ONLY_IN_FULLSCREEN.getValue())
			if (!Minecraft.getInstance().mainWindow.isFullscreen())
				return;

		float scale = (float) ConfigHandler.LOOK_HUD_SCALE.getValue();

		if(!mc.player.getActivePotionEffects().isEmpty())
			potions = new ArrayList<EffectInstance>(mc.player.getActivePotionEffects());

		GlStateManager.pushMatrix();		         
		GlStateManager.scalef(scale, scale, scale);		         
		for(int i = 0; i < potions.size(); i++) {		  
			EffectInstance effect = potions.get(i);
			String name = effect.getPotion().getDisplayName().getString();
			int tick_sec = Math.round(potions.get(i).getDuration()/20);
			int min = Math.round(tick_sec/60);
			int sec = Math.round(tick_sec - (min*60));
			String duration = name + " : " + String.format("%02d", min) + ":" + String.format("%02d", sec);
			textRenderer.drawStringWithShadow(duration, 3, 4 + (i*10), TextFormatting.GOLD.getColor());
		}
		GlStateManager.popMatrix();
	}
}
