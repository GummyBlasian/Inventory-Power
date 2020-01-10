package com.github.GummyBlasian.InventoryPower.References;

import com.github.GummyBlasian.InventoryPower.API.ListOfSpecialBlocks;

import net.minecraft.init.Blocks;

public class DifferentBlocks {

	public static void init() {
		addBlockWater();
		addBlockLava();
		addFlowerName();
		addCropsName();
		addDoorName();
	}

	public static void addDoorName() {
		ListOfSpecialBlocks.addToList(Blocks.IRON_TRAPDOOR, "Iron Trapdoor");
		ListOfSpecialBlocks.addToList(Blocks.TRAPDOOR, "Trapdoor");
		ListOfSpecialBlocks.addToList(Blocks.ACACIA_DOOR, "Acacia Door");
		ListOfSpecialBlocks.addToList(Blocks.BIRCH_DOOR, "Birch Door");
		ListOfSpecialBlocks.addToList(Blocks.DARK_OAK_DOOR, "Dark Oak Door");
		ListOfSpecialBlocks.addToList(Blocks.IRON_DOOR, "Iron Door");
		ListOfSpecialBlocks.addToList(Blocks.JUNGLE_DOOR, "Jungle Door");
		ListOfSpecialBlocks.addToList(Blocks.OAK_DOOR, "Oak Door");
		ListOfSpecialBlocks.addToList(Blocks.SPRUCE_DOOR, "Spruce Door");
	}

	public static void addCropsName() {
		ListOfSpecialBlocks.addToList(Blocks.WHEAT, "Wheat");
		ListOfSpecialBlocks.addToList(Blocks.CARROTS, "Carrots");
		ListOfSpecialBlocks.addToList(Blocks.BEETROOTS, "Beetroots");
		ListOfSpecialBlocks.addToList(Blocks.COCOA, "Cocoa");
		ListOfSpecialBlocks.addToList(Blocks.MELON_BLOCK, "Melon");
		ListOfSpecialBlocks.addToList(Blocks.MELON_STEM, "Melon Stem");
		ListOfSpecialBlocks.addToList(Blocks.PUMPKIN, "Pumpkin");
		ListOfSpecialBlocks.addToList(Blocks.PUMPKIN_STEM, "Pumpkin Stem");
		ListOfSpecialBlocks.addToList(Blocks.POTATOES, "Potatoes");
		ListOfSpecialBlocks.addToList(Blocks.NETHER_WART, "Nether Wart");
	}

	public static void addBlockWater() {
		ListOfSpecialBlocks.addToList(Blocks.FLOWING_WATER, "Flowing Water");
		ListOfSpecialBlocks.addToList(Blocks.WATER, "Water");
	}

	public static void addBlockLava() {
		ListOfSpecialBlocks.addToList(Blocks.FLOWING_LAVA, "Flowing Lava");
		ListOfSpecialBlocks.addToList(Blocks.LAVA, "Lava");
	}

	public static void addFlowerName() {
		ListOfSpecialBlocks.addToList("sunflower", "Sunflower");
		ListOfSpecialBlocks.addToList("syringa", "Lilac");
		ListOfSpecialBlocks.addToList("double_grass", "Double Tallgrass");
		ListOfSpecialBlocks.addToList("double_fern", "Large Fern");
		ListOfSpecialBlocks.addToList("double_rose", "Rose Bush");
		ListOfSpecialBlocks.addToList("peony", "Peony");
	}
}
