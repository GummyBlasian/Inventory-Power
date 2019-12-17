package com.sangam1.InventoryRepair.References;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroup_IRGroup extends ItemGroup
{
	public ItemGroup_IRGroup() 
	{
		super("S1IR");
	}

	@Override
	public ItemStack createIcon() 
	{
		return new ItemStack(ItemList.gocraft);
	}
}