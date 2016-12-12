package com.sangam1.InventoryRepair;

import com.sangam1.InventoryRepair.Item.ItemBase;
import com.sangam1.InventoryRepair.Item.ItemModelProvider;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {
	public static ItemBase ingotCopper;

	public static void init() {
		ingotCopper = register(new ItemBase("ingotCopper").setCreativeTab(CreativeTabs.MATERIALS));
	}

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof ItemModelProvider) {
			((ItemModelProvider) item).registerItemModel(item);
		}

		return item;
	}
}