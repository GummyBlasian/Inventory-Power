package com.github.GummyBlasian.InventoryPower.GUI;

import com.github.GummyBlasian.InventoryPower.Config.IRConfig;
import com.github.GummyBlasian.InventoryPower.Events.Client.ArmorDurability;
import com.github.GummyBlasian.InventoryPower.GUI.Handlers.LargeArmorGUIHandler;
import com.github.GummyBlasian.InventoryPower.GUI.Handlers.SmallArmorGUIHandler;
import com.github.GummyBlasian.InventoryPower.GUI.Handlers.SmallIconArmorGUIHandler;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

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

	int guiPosX = mc.func_228018_at_().getScaledWidth(); //mainWindow
	int guiPosY = mc.func_228018_at_().getScaledHeight() - 20; //mainWindow

	@SubscribeEvent
	public void onRenderTick(RenderGameOverlayEvent.Post event) {

		FontRenderer textRenderer = mc.fontRenderer;

		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL || !IRConfig.ArmorHud)
			return;

		if (mc.currentScreen instanceof ChatScreen)
      return;
		
		guiPosX = mc.func_228018_at_().getScaledWidth(); //mainWindow
		guiPosY = mc.func_228018_at_().getScaledHeight() - 20; //mainWindow

		float scale = (float) 1;
		
		ArmorDurability.getData();
		
		RenderSystem.pushMatrix();
		RenderSystem.disableLighting();
		RenderSystem.enableAlphaTest();		         
		RenderSystem.alphaFunc(516, 0.1F);
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		RenderSystem.popMatrix();

		RenderSystem.pushMatrix();		        
		RenderSystem.scalef(scale, scale, scale);
		RenderSystem.popMatrix();

		RenderSystem.pushMatrix();
		RenderSystem.disableAlphaTest();
		RenderHelper.func_227784_d_(); // .enableGUIStandardItemLighting();
		RenderSystem.popMatrix();

		RenderSystem.pushMatrix();
		RenderSystem.scalef(scale, scale, scale);	 		         
		LargeArmorGUIHandler.handGUI (textRenderer, mc, guiPosX, guiPosY);
		RenderSystem.popMatrix();

		if(IRConfig.LargeArmorHUD) {
			
			ArmorDurability.getData();
			
			RenderSystem.pushMatrix();
			RenderSystem.scalef(scale, scale, scale);	         
			LargeArmorGUIHandler.headGUI(textRenderer, mc, guiPosX, guiPosY);
			LargeArmorGUIHandler.bodyGUI(textRenderer, mc, guiPosX, guiPosY);
			LargeArmorGUIHandler.legGUI(textRenderer, mc, guiPosX, guiPosY);
			LargeArmorGUIHandler.bootGUI(textRenderer, mc, guiPosX, guiPosY);
			RenderSystem.popMatrix();
		}

		if(IRConfig.SmallArmorHud) {

			ArmorDurability.getData();
			
			//Text
			int guiPosYScaled = (int) (guiPosY*1.43);
			int guiPosXScaled = (int) (guiPosX*1.43);
			RenderSystem.pushMatrix();
			RenderSystem.scalef(0.7f, 0.7f, scale);	         
			SmallArmorGUIHandler.headGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled - 8);
			SmallArmorGUIHandler.bodyGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled + 2);
			SmallArmorGUIHandler.legGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled + 12);
			SmallArmorGUIHandler.bootGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled + 20);			
			RenderSystem.popMatrix();

			//Icons
			guiPosYScaled = (int) (guiPosY*2);
			guiPosXScaled = (int) (guiPosX*2);
			RenderSystem.pushMatrix();
			RenderSystem.scalef(0.5f, 0.5f, scale);	         
			SmallIconArmorGUIHandler.headGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled - 18);
			SmallIconArmorGUIHandler.bodyGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled - 4);
			SmallIconArmorGUIHandler.legGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled + 12);
			SmallIconArmorGUIHandler.bootGUI(textRenderer, mc, guiPosXScaled, guiPosYScaled + 25);			
			RenderSystem.popMatrix();
		}

		RenderSystem.pushMatrix();
		RenderSystem.scalef(1, 1, 1);
		RenderSystem.popMatrix();
	}

}
