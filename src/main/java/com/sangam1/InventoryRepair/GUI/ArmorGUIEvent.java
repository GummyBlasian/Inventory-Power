package com.sangam1.InventoryRepair.GUI;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sangam1.InventoryRepair.Config.ConfigHandler;
import com.sangam1.InventoryRepair.GUI.Handlers.ArmorGUIHandler;

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
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();		        
			GlStateManager.scalef(scale, scale, scale);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			GlStateManager.disableAlphaTest();
			RenderHelper.enableGUIStandardItemLighting();
			GlStateManager.popMatrix();

		}

		GlStateManager.pushMatrix();
		GlStateManager.scalef(scale, scale, scale);	         
		ArmorGUIHandler.headGUI(textRenderer, mc, guiPosX, guiPosY);
		ArmorGUIHandler.bodyGUI(textRenderer, mc, guiPosX, guiPosY);
		ArmorGUIHandler.legGUI(textRenderer, mc, guiPosX, guiPosY);
		ArmorGUIHandler.bootGUI(textRenderer, mc, guiPosX, guiPosY);		         
		ArmorGUIHandler.handGUI (textRenderer, mc, guiPosX, guiPosY);
		GlStateManager.popMatrix();
	}

}
