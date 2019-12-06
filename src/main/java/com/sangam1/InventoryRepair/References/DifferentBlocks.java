package com.sangam1.InventoryRepair.References;

import com.sangam1.InventoryRepair.API.ListOfSpecialBlocks;

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
		ListOfSpecialBlocks.addToList(Blocks.IRON_TRAPDOOR, "Iron Trapdoor");
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
		ListOfSpecialBlocks.addToList(Blocks.MELON, "Melon");
		ListOfSpecialBlocks.addToList(Blocks.MELON_STEM, "Melon Stem");
		ListOfSpecialBlocks.addToList(Blocks.PUMPKIN, "Pumpkin");
		ListOfSpecialBlocks.addToList(Blocks.PUMPKIN_STEM, "Pumpkin Stem");
		ListOfSpecialBlocks.addToList(Blocks.POTATOES, "Potatoes");
		ListOfSpecialBlocks.addToList(Blocks.NETHER_WART, "Nether Wart");
	}

	public static void addBlockWater() {
		ListOfSpecialBlocks.addToList(Block.getStateById(8), "Flowing Water");
		ListOfSpecialBlocks.addToList(Blocks.WATER, "Water");
	}

	public static void addBlockLava() {
		ListOfSpecialBlocks.addToList(Block.getStateById(10), "Flowing Lava");
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