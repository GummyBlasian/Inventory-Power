package com.sangam1.InventoryRepair.proxy;

import com.sangam1.InventoryRepair.References.StartupRef;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(StartupRef.MODID + ":" + id, "inventory"));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z)
	{
		if (ID == 3){
			return new GuiCrafting(player.inventory, world);
		} else{
			return null;			
		}
	}

}
