package com.sangam1.InventoryRepair.proxy;

import com.sangam1.InventoryRepair.GUI.RenderGUIHandler;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
	
	public RenderGUIHandler guiHandler = new RenderGUIHandler();
	
	public void registerItemRenderer(Item item, int meta, String id) {

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 10){
			return new ContainerWorkbench(player.inventory, world, player.getPosition());
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 3){
			return new GuiCrafting(player.inventory, world);
		} else{
			return null;			
		}
	}
	
}
