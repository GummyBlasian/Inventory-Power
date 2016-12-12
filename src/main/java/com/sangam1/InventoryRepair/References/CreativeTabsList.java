package com.sangam1.InventoryRepair.References;

import com.sangam1.InventoryRepair.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabsList extends CreativeTabs{

	public CreativeTabsList() {
		super(StartupRef.MODID);
		//setBackgroundImageName("tutorialmod.png");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.ingotCopper); //shown icon on creative tab
	}
	
	 @Override
	 public boolean hasSearchBar() {
		 return true; // return false if you don't want search bar
	 }

}
