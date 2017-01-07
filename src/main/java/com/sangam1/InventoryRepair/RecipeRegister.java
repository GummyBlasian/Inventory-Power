package com.sangam1.InventoryRepair;

import com.sangam1.InventoryRepair.References.ItemsList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegister {

	public static void Register() {
		GameRegistry.addRecipe(new ItemStack(ItemsList.autosmelttool, 1), "A A", "BBB","A A", 'A', Blocks.COBBLESTONE, 'B', Items.COAL);
		GameRegistry.addRecipe(new ItemStack(ItemsList.gocraft, 1), "A A", " B ","A A", 'A', Blocks.PLANKS, 'B', Blocks.CRAFTING_TABLE);
		GameRegistry.addRecipe(new ItemStack(ItemsList.portablefurnace, 1), "A A", " B ","A A", 'A', Blocks.COBBLESTONE, 'B', Blocks.FURNACE);	
	}

}
