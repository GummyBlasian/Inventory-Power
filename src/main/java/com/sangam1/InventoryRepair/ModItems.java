package com.sangam1.InventoryRepair;

import com.sangam1.InventoryRepair.Item.ItemAutoSmelt;
import com.sangam1.InventoryRepair.References.ItemsList;
import com.sangam1.InventoryRepair.References.StartupRef;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {

	public static void createItems() {
		setupItems();
		GameRegistry.register(ItemsList.ItemAutoSmelter);
	}

	private static void setupItems() {
		ItemsList.ItemAutoSmelter.setRegistryName(new ResourceLocation(StartupRef.MODID , "ItemAutoSmelterTool"));
	}
}