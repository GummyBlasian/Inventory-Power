package com.sangam1.InventoryRepair.Item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAutoSmeltTool extends ItemBase{
	
	private boolean active;

	public ItemAutoSmeltTool(String name) {
		super(name);
		active = false;
	}
	
	/* Doesn't work any more
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		System.out.println("Item clicked");
		return item;		
	}
	*/
	
	
}
