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
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Potions_GUI {
	
	private final Minecraft mc = Minecraft.getInstance();

	int guiPosX = mc.mainWindow.getScaledWidth();
    int guiPosY = mc.mainWindow.getScaledHeight() - ConfigHandler.LOOK_HUD_Y.getValue();
    
    private ArrayList<EffectInstance> potions = new ArrayList<EffectInstance>();
    
    @SubscribeEvent
    public void onRenderTickPre(RenderGameOverlayEvent.Pre event) {
    	if (event.getType() == ElementType.POTION_ICONS)
    		event.setCanceled(true);
    }
    
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
		         /*
		         for(int i = 0; i < potions.size(); i++) {		        	
		        	String potion_info = potions.get(i).getEffectName() + " " + potions.get(i).getDuration();
		        	int a = mc.fontRenderer.getStringWidth(potion_info);
		        	textRenderer.drawStringWithShadow(potion_info, a, 4 + (i*10), TextFormatting.GOLD.getColor());
		         }
		         */
		         GlStateManager.popMatrix();
    }
}
