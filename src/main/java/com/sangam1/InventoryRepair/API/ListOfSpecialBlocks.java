package com.sangam1.InventoryRepair.API;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class ListOfSpecialBlocks {

	private static Map<Object, String> all = new HashMap<Object, String>();

	public static void addToList(String input, String output) {
		all.put(input, output);
	}

	public static void addToList(Block input, String output) {
		all.put(input, output);
	}

	public static void addToList(IBlockState input, String output) {
		all.put(input, output);
	}

	public static String getFromList(String key) {
		return all.get(key);
	}

	public static String getFromList(Block block) {
		return all.get(block);
	}

	public static String getFromList(IBlockState blockState) {
		return all.get(blockState);
	}
}
