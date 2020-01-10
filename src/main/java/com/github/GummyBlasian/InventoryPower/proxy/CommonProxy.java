package com.github.GummyBlasian.InventoryPower.proxy;

import com.github.GummyBlasian.InventoryPower.GUI.RenderGUIHandler;
import com.github.GummyBlasian.InventoryPower.GUI.Container.GoCraftContainer;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
	
	public RenderGUIHandler guiHandler = new RenderGUIHandler();
	
	public void registerItemRenderer(Item item, int meta, String id) {

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 20){
			return new GoCraftContainer(player.inventory, world, player.getPosition());
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 20){
			return new GuiCrafting(player.inventory, world);
		}
		return null;
	}
	
}
