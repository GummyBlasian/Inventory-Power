package com.github.GummyBlasian.InventoryPower.Events.Client;

import com.github.GummyBlasian.InventoryPower.API.HarvestLevelAPI;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;

/*
 * Big thanks to williewillus for all his help!!!
 */
public class LookingAt {

	private static final Minecraft mc = Minecraft.getInstance();
	private static World world;

	private static String lookingAt;
	private static String madeBy;
	private static boolean canMine;
	private static String harvestLevel;
	private static Biome biome;
	private static Dimension dimension;
	private static boolean isRaining;

	public static void get_data() {
		world = mc.world;

		lookingAt = " ";
		madeBy = " ";
		canMine = false;
		harvestLevel = "";
		dimension = null;

		getDataMC();
		fire();
	}

	private static void fire() {
		if (Minecraft.getInstance().objectMouseOver.getType() == RayTraceResult.Type.BLOCK) {
			BlockState state = world.getBlockState(((BlockRayTraceResult) Minecraft.getInstance().objectMouseOver).getPos());
			Block block = state.getBlock();
			lookingAt = block.getNameTextComponent().getFormattedText();
			madeBy = block.getRegistryName().getNamespace();
			getHarvestLevelMC(state);
			if(ArmorDurability.getHand_Icon() != null) {
				if(ArmorDurability.getHand_Icon().canHarvestBlock(state)) {
					canMine = true;
				}
			}
		}

		if (Minecraft.getInstance().objectMouseOver.getType() == RayTraceResult.Type.ENTITY) {
			Entity entity = ((EntityRayTraceResult) Minecraft.getInstance().objectMouseOver).getEntity();
			lookingAt = entity.getDisplayName().getFormattedText();
			harvestLevel = "";
		}

		if (Minecraft.getInstance().objectMouseOver.getType() == RayTraceResult.Type.MISS) {
			Block block = world.getBlockState(((BlockRayTraceResult) Minecraft.getInstance().objectMouseOver).getPos()).getBlock();
			if(!block.getTranslationKey().matches(Blocks.AIR.getTranslationKey())) {
				if(!block.getTranslationKey().matches(Blocks.CAVE_AIR.getTranslationKey())) {
					lookingAt = block.getNameTextComponent().getFormattedText();
					madeBy = block.getRegistryName().getNamespace();
					harvestLevel = "";
				}
			}

		}		

	}

	private static void getDataMC() {
		biome = world.getBiome(mc.player.getPosition());
		dimension = world.getDimension();
		isRaining = world.isRaining();
	}

	@SuppressWarnings("unused")
	private static void getHarvestLevelMC(Block block) {
		harvestLevel = HarvestLevelAPI.getLevel(block.getDefaultState().getHarvestLevel());
	}

	private static void getHarvestLevelMC(BlockState state) {
		harvestLevel = HarvestLevelAPI.getLevel(state.getHarvestLevel());
	}


	//All getters
	public static String getLookingAt() {
		return lookingAt;
	}

	public static String getMadeBy() {
		return madeBy;
	}

	public static boolean isCanMine() {
		return canMine;
	}

	public static String getHarvestLevel() {
		return harvestLevel;
	}

	public static String getBiome() {
		return biome.getDisplayName().getFormattedText();
	}

	public static Dimension getDimension() {
		return dimension;
	}

	public static String isRaining() {
		if(isRaining)
			return "Raining";
		else
			return "Clear";
	}

}
