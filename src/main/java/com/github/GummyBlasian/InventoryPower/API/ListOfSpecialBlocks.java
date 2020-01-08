package com.github.GummyBlasian.InventoryPower.API;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraftforge.common.extensions.IForgeBlockState;

public class ListOfSpecialBlocks {

	private static Map<Object, String> all = new HashMap<Object, String>();

	/**
     * Adds the info for the block that a player is looking at to be properly named. (String version)
     */
	public static void addToList(String input, String output) {
		all.put(input, output);
	}

	/**
     * Adds the info for the block that a player is looking at to be properly named. (Block version)
     */
	public static void addToList(Block input, String output) {
		all.put(input, output);
	}

	/**
     * Adds the info for the block that a player is looking at to be properly named. (IBlockState version)
     */
	public static void addToList(BlockState input, String output) {
		all.put(input, output);
	}

	public static String getFromList(String key) {
		return all.get(key);
	}

	public static String getFromList(Block block) {
		return all.get(block);
	}

	public static String getFromList(IForgeBlockState blockState) {
		return all.get(blockState);
	}
}
