package com.sangam1.InventoryRepair;

import com.sangam1.InventoryRepair.Item.ItemAutoSmeltTool;
import com.sangam1.InventoryRepair.Item.ItemModelProvider;
import com.sangam1.InventoryRepair.References.ItemsList;
import com.sangam1.InventoryRepair.References.StartupRef;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {

	public static void init() {
		ItemsList.autosmelttool = register(
				new ItemAutoSmeltTool(StartupRef.MODID + ".autosmelttool").setCreativeTab(Main.creativeTab));
	}

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof ItemModelProvider) {
			((ItemModelProvider) item).registerItemModel(item);
		}

		return item;
	}
}