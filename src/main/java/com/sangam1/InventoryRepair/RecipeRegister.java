package com.sangam1.InventoryRepair;

import com.sangam1.InventoryRepair.References.ItemsList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegister {

	public static void Register() {
		GameRegistry.addRecipe(new ItemStack(ItemsList.ItemAutoSmelter, 1), "A A", "BBB","A A", 'A', Blocks.COBBLESTONE, 'B', Items.COAL);
	}

}
