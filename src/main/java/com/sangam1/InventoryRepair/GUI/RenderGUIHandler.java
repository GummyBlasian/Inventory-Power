package com.sangam1.InventoryRepair.GUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderGUIHandler {

	// Stop GUI
	@SubscribeEvent
	public void onRenderGUI(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == ElementType.TEXT)
			event.setCanceled(true);
	}

	// Create GUI
	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event) {
		if (event.getType() != ElementType.EXPERIENCE)
			return;
		new GUIToolDurability(Minecraft.getMinecraft());
		new GUILookAt(Minecraft.getMinecraft());
		new GUIArmorDurability(Minecraft.getMinecraft());
	}

	public Object getServerGuiElement(int iD, EntityPlayer player, World world, int x, int y, int z) {
		if (iD == 20) {
			return new GuiCrafting(player.inventory, world);
		}
		return null;
	}

}
