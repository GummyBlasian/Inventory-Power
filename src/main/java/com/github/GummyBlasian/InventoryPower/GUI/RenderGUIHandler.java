package com.github.GummyBlasian.InventoryPower.GUI;

import com.github.GummyBlasian.InventoryPower.GUI.Container.GoCraftContainer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class RenderGUIHandler implements IGuiHandler{

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
		//new GUIArmorDurability(Minecraft.getMinecraft());
	}

	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 0) {
			return new GoCraftContainer(player.inventory, world, player.getPosition());
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int handId, int unused1, int unused2) {
		if (ID == 0) {
			return new GUIPortableCraftingTable( new GoCraftContainer(player.inventory, world, player.getPosition()));
		}
		return unused2;		 
	}

}
