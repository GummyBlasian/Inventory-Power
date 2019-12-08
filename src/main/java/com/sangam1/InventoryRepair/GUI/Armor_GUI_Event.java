package com.sangam1.InventoryRepair.GUI;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sangam1.InventoryRepair.Config.ConfigHandler;
import com.sangam1.InventoryRepair.Events.Client.Armor_Durability;
import com.sangam1.InventoryRepair.Events.Client.Looking_At;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Armor_GUI_Event {
	
	 	private final Minecraft mc = Minecraft.getInstance();

		int guiPosX = mc.mainWindow.getScaledWidth();
	    int guiPosY = mc.mainWindow.getScaledHeight() - ConfigHandler.LOOK_HUD_Y.getValue();

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
		        
		         if (ConfigHandler.LOOK_HUD_BACKGROUND_ENABLED.getValue()) {
		         
		         GlStateManager.pushMatrix();

		         GlStateManager.disableLighting();
		         GlStateManager.enableAlphaTest();
		         
		         GlStateManager.alphaFunc(516, 0.1F);
		         GlStateManager.enableBlend();
		         GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		         
		         GlStateManager.pushMatrix();
		        
		         GlStateManager.scalef(scale, scale, scale);
	         
	         
	          	 GlStateManager.popMatrix();
		        
	          	 GlStateManager.disableAlphaTest();
	          	 RenderHelper.enableGUIStandardItemLighting();
		         
		         GlStateManager.popMatrix();
		         
		         }
		         
		         GlStateManager.pushMatrix();
		         
		         GlStateManager.scalef(scale, scale, scale);
	         
		         headGUI(textRenderer);
		         bodyGUI(textRenderer);
		         legGUI(textRenderer);
		         bootGUI(textRenderer);
		         
		         handGUI (textRenderer);
		         
		         GlStateManager.popMatrix();
	    }
	    
	    private void handGUI (FontRenderer textRenderer) {		
			String armor_head = Armor_Durability.getHand();
			String armor_head_durability = Armor_Durability.getHand_Durability();
	        
		    int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		    int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);
		    if(Armor_Durability.getHand_Icon() != null)
		    	mc.getItemRenderer().renderItemAndEffectIntoGUI(Armor_Durability.getHand_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY-20);
	        textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY - 15, TextFormatting.GOLD.getColor());
	        textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY - 5, TextFormatting.GOLD.getColor()); 
	        if (Looking_At.can_mine())
		        textRenderer.drawStringWithShadow("can mine", guiPosX - txtwidth_armor_head_durability, guiPosY , TextFormatting.GOLD.getColor()); 
	    }
	    
	    private void headGUI(FontRenderer textRenderer) {
			String armor_head = Armor_Durability.getHead();
			String armor_head_durability = Armor_Durability.getHead_Durability();
	        
		    int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		    int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);
		    mc.getItemRenderer().renderItemAndEffectIntoGUI(Armor_Durability.getHead_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY/2-45);
	        textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY/2 - 40, TextFormatting.GOLD.getColor());
	        textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY/2 - 30, TextFormatting.GOLD.getColor()); 
	    }
	    
	    private void bodyGUI(FontRenderer textRenderer) {
			String armor_head = Armor_Durability.getBody();
			String armor_head_durability = Armor_Durability.getBody_Durability();
	        
		    int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		    int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);
		    mc.getItemRenderer().renderItemAndEffectIntoGUI(Armor_Durability.getBody_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY/2-25);
	        textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY/2 - 20, TextFormatting.GOLD.getColor());
	        textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY/2 - 10, TextFormatting.GOLD.getColor()); 
	    }
	    
	    private void legGUI(FontRenderer textRenderer) {
			String armor_head = Armor_Durability.getLeg();
			String armor_head_durability = Armor_Durability.getLeg_Durability();
	        
		    int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		    int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);
		    mc.getItemRenderer().renderItemAndEffectIntoGUI(Armor_Durability.getLeg_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY/2-5);
	        textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY/2, TextFormatting.GOLD.getColor());
	        textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY/2 + 10, TextFormatting.GOLD.getColor()); 
	    }
	    
	    private void bootGUI(FontRenderer textRenderer) {
			String armor_head = Armor_Durability.getBoot();
			String armor_head_durability = Armor_Durability.getBoot_Durability();
	        
		    int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		    int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);
		    mc.getItemRenderer().renderItemAndEffectIntoGUI(Armor_Durability.getBoot_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY/2+20);
	        textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY/2 + 20, TextFormatting.GOLD.getColor());
	        textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY/2 + 30, TextFormatting.GOLD.getColor()); 
	    }


}
