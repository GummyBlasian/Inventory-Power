package com.github.GummyBlasian.InventoryPower.References;

import static com.github.GummyBlasian.InventoryPower.API.ListOfSpecialBlocks.addToList;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class DifferentBlocks {

	public static void init() {
		addBlockWater();
		addBlockLava();
		addFlowerName();
		addCropsName();
		addDoorName();
	}

	public static void addDoorName() {
		addToList(Blocks.IRON_TRAPDOOR, "Iron Trapdoor");
		addToList(Blocks.ACACIA_DOOR, "Acacia Door");
		addToList(Blocks.BIRCH_DOOR, "Birch Door");
		addToList(Blocks.DARK_OAK_DOOR, "Dark Oak Door");
		addToList(Blocks.IRON_DOOR, "Iron Door");
		addToList(Blocks.JUNGLE_DOOR, "Jungle Door");
		addToList(Blocks.OAK_DOOR, "Oak Door");
		addToList(Blocks.SPRUCE_DOOR, "Spruce Door");
	}

	public static void addCropsName() {
		addToList(Blocks.WHEAT, "Wheat");
		addToList(Blocks.CARROTS, "Carrots");
		addToList(Blocks.BEETROOTS, "Beetroots");
		addToList(Blocks.COCOA, "Cocoa");
		addToList(Blocks.MELON, "Melon");
		addToList(Blocks.MELON_STEM, "Melon Stem");
		addToList(Blocks.PUMPKIN, "Pumpkin");
		addToList(Blocks.PUMPKIN_STEM, "Pumpkin Stem");
		addToList(Blocks.POTATOES, "Potatoes");
		addToList(Blocks.NETHER_WART, "Nether Wart");
	}

	public static void addBlockWater() {
		addToList(Block.getStateById(8), "Flowing Water");
		addToList(Blocks.WATER, "Water");
	}

	public static void addBlockLava() {
		addToList(Block.getStateById(10), "Flowing Lava");
		addToList(Blocks.LAVA, "Lava");
	}

	public static void addFlowerName() {
		addToList("sunflower", "Sunflower");
		addToList("syringa", "Lilac");
		addToList("double_grass", "Double Tallgrass");
		addToList("double_fern", "Large Fern");
		addToList("double_rose", "Rose Bush");
		addToList("peony", "Peony");
	}
}