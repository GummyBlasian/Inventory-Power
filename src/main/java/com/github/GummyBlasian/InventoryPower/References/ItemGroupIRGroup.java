package com.github.GummyBlasian.InventoryPower.References;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupIRGroup extends ItemGroup
{
	public ItemGroupIRGroup() 
	{
		super("S1IR");
	}

	@Override
	public ItemStack createIcon() 
	{
		return new ItemStack(ItemList.portableCrafting);
	}
}