package com.github.GummyBlasian.InventoryPower.GUI;

import java.util.ArrayList;

import com.github.GummyBlasian.InventoryPower.Config.IRConfig;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class PotionsGUI {

	private final Minecraft mc = Minecraft.getInstance();

	int guiPosX = mc.func_228018_at_().getScaledWidth(); //mainWindow
	int guiPosY = mc.func_228018_at_().getScaledHeight() - 20; //mainWindow

	private ArrayList<EffectInstance> potions = new ArrayList<EffectInstance>();

	@SubscribeEvent
	public void onRenderTick(RenderGameOverlayEvent.Post event) {
		FontRenderer textRenderer = mc.fontRenderer;

		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL && !IRConfig.PotionsHUD)
			return;

		if (mc.currentScreen instanceof ChatScreen)
			return;
		
		if(mc.player.getActivePotionEffects().isEmpty())
			return;
		
		guiPosX = mc.func_228018_at_().getScaledWidth(); //mainWindow
		guiPosY = mc.func_228018_at_().getScaledHeight() - 20; //mainWindow
    
		float scale = (float) 1;

		potions = new ArrayList<EffectInstance>(mc.player.getActivePotionEffects());
         
		for(int i = 0; i < potions.size(); i++) {		  
			RenderSystem.pushMatrix();		         
			RenderSystem.scalef(scale, scale, scale);		
			EffectInstance effect = potions.get(i);
			String name = effect.getPotion().getDisplayName().getString();
			int tick_sec = Math.round(potions.get(i).getDuration()/20);
			int min = Math.round(tick_sec/60);
			int sec = Math.round(tick_sec - (min*60));
			String duration = name + " : " + String.format("%02d", min) + ":" + String.format("%02d", sec);
			textRenderer.drawStringWithShadow(duration, 3, 4 + (i*10), TextFormatting.GOLD.getColor());
			RenderSystem.popMatrix();
		}

		RenderSystem.pushMatrix();
		RenderSystem.scalef(1, 1, 1);
		RenderSystem.popMatrix();
	}
}
