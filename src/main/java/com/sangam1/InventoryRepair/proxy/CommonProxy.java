package com.sangam1.InventoryRepair.proxy;

import com.sangam1.InventoryRepair.GUI.GUIPortableFurnace;
import com.sangam1.InventoryRepair.GUI.RenderGUIHandler;
import com.sangam1.InventoryRepair.GUI.Container.GoCraftContainer;
import com.sangam1.InventoryRepair.GUI.Container.PortableFurnaceContainer;
import com.sangam1.InventoryRepair.Item.ItemGoSmeltTool;

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
			//System.out.println("Hello ServerGUI");
			return new GoCraftContainer(player.inventory, world, player.getPosition());
		} else if(ID == 21){
			//System.out.println("Hello ServerGUI");
	    	ItemGoSmeltTool furnace = (ItemGoSmeltTool)player.inventory.getCurrentItem().getItem();
			return new PortableFurnaceContainer(player.inventory, furnace.getInventory());
		}
		//System.out.println("BYE ServerGUI");
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 20){
			//System.out.println("Hello ClientGUI");
			return new GuiCrafting(player.inventory, world);
		} else if(ID == 21){
	    	ItemGoSmeltTool furnace = (ItemGoSmeltTool)player.inventory.getCurrentItem().getItem();
	    	return new GUIPortableFurnace(player.inventory, furnace.getInventory());
		}
		//System.out.println("BYE ClientGUI");
		return null;
	}
	
}
