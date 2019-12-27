package com.sangam1.InventoryRepair.GUI;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sangam1.InventoryRepair.Main;
import com.sangam1.InventoryRepair.Config.IRConfig;
import com.sangam1.InventoryRepair.GUI.Handlers.LargeArmorGUIHandler;
import com.sangam1.InventoryRepair.GUI.Handlers.SmallArmorGUIHandler;
import com.sangam1.InventoryRepair.GUI.Handlers.SmallIconArmorGUIHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ArmorGUIEvent {

	private final Minecraft mc = Minecraft.getInstance();

	int guiPosX = mc.mainWindow.getScaledWidth();
	int guiPosY = mc.mainWindow.getScaledHeight() - 20;

	@SubscribeEvent
	public void onRenderTick(RenderGameOverlayEvent.Post event) {

		FontRenderer textRenderer = mc.fontRenderer;

		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL || !IRConfig.ArmorHud)
			return;

		if (mc.currentScreen instanceof ChatScreen)
			return;

		float scale = (float) 1;
		Main.LOGGER.info("entered!");
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.enableAlphaTest();		         
		GlStateManager.alphaFunc(516, 0.1F);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();		        
		GlStateManager.scalef(scale, scale, scale);
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.disableAlphaTest();
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.scalef(scale, scale, scale);	 		         
		LargeArmorGUIHandler.handGUI (textRenderer, mc, guiPosX, guiPosY);
		GlStateManager.popMatrix();

		if(IRConfig.LargeArmorHUD) {
			Main.LOGGER.info("Large entered!");
			GlStateManager.pushMatrix();
			GlStateManager.scalef(scale, scale, scale);	         
			LargeArmorGUIHandler.headGUI(textRenderer, mc, guiPosX, guiPosY);
			LargeArmorGUIHandler.bodyGUI(textRenderer, mc, guiPosX, guiPosY);
			LargeArmorGUIHandler.legGUI(textRenderer, mc, guiPosX, guiPosY);
			LargeArmorGUIHandler.bootGUI(textRenderer, mc, guiPosX, guiPosY);
			GlStateManager.popMatrix();
		}

		if(IRConfig.SmallArmorHud) {
			Main.LOGGER.info("Small entered!");
			//Text
			int guiPosYScaled = (int) (guiPosY*1.43);
			int guiPosXScaled = (int) (guiPosX*1.43);
			GlStateManager.pushMatrix();
			GlStateManager.scalef(0.7f, 0.7f, scale);	         
			SmallArmorGUIHandler.headGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled - 8);
			SmallArmorGUIHandler.bodyGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled + 2);
			SmallArmorGUIHandler.legGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled + 12);
			SmallArmorGUIHandler.bootGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled + 20);			
			GlStateManager.popMatrix();
			
			//Icons
			guiPosYScaled = (int) (guiPosY*2);
			guiPosXScaled = (int) (guiPosX*2);
			GlStateManager.pushMatrix();
			GlStateManager.scalef(0.5f, 0.5f, scale);	         
			SmallIconArmorGUIHandler.headGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled - 18);
			SmallIconArmorGUIHandler.bodyGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled - 4);
			SmallIconArmorGUIHandler.legGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled + 12);
			SmallIconArmorGUIHandler.bootGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled + 25);			
			GlStateManager.popMatrix();
		}

		GlStateManager.pushMatrix();
		GlStateManager.scalef(1, 1, 1);
		GlStateManager.popMatrix();
	}

}
