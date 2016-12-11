package com.sangam1.InventoryRepair.GUI;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.client.GuiNotification;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderGUIHandler {
	
	//Stop GUI
	@SubscribeEvent
	public void onRenderGUI(RenderGameOverlayEvent.Pre event){
		if(event.getType() == ElementType.TEXT) event.setCanceled(true);
	}

	//Create GUI
	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event)
	{
		if (event.getType() != ElementType.EXPERIENCE) return;
		new GUIToolDurability(Minecraft.getMinecraft());
		new GUILookAt(Minecraft.getMinecraft());
	}
	
}
