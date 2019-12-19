package com.sangam1.InventoryRepair.Events.Client;

import com.sangam1.InventoryRepair.API.Harvest_Level_API;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

/*
 * Big thanks to williewillus for all his help!!!
 */
public class Looking_At {

	private static final Minecraft mc = Minecraft.getInstance();
	private static World world;

	private static String lookingAt;
	private static String madeBy;
	private static boolean canMine;
	private static String harvestLevel;
	private static Biome biome;

	public static void get_data() {
		world = mc.world;

		lookingAt = " ";
		madeBy = " ";
		canMine = false;
		harvestLevel = "";

		getBiomeMC();
		fire();
	}

	private static void fire() {
		if (Minecraft.getInstance().objectMouseOver.getType() == RayTraceResult.Type.BLOCK) {
			Block block = world.getBlockState(((BlockRayTraceResult) Minecraft.getInstance().objectMouseOver).getPos()).getBlock();
			lookingAt = block.getNameTextComponent().getFormattedText();
			madeBy = block.getRegistryName().getNamespace();
			getHarvestLevelMC(block);
			if(Armor_Durability.getHand_Icon() != null) {
				if(Armor_Durability.getHand_Icon().canHarvestBlock(world.getBlockState(((BlockRayTraceResult) Minecraft.getInstance().objectMouseOver).getPos()))) {
					canMine = true;
				}
			}
		}
		
		if (Minecraft.getInstance().objectMouseOver.getType() == RayTraceResult.Type.ENTITY) {
			Entity entity = ((EntityRayTraceResult) Minecraft.getInstance().objectMouseOver).getEntity();
			lookingAt = entity.getDisplayName().getFormattedText();
		}
		
		if (Minecraft.getInstance().objectMouseOver.getType() == RayTraceResult.Type.MISS) {
			Block block = world.getBlockState(((BlockRayTraceResult) Minecraft.getInstance().objectMouseOver).getPos()).getBlock();
			if(block != Blocks.AIR || block != Blocks.CAVE_AIR) {
				lookingAt = block.getNameTextComponent().getFormattedText();
				madeBy = block.getRegistryName().getNamespace();
			}
		}		
		
	}
	
	private static void getBiomeMC() {
		biome = world.getBiome(mc.player.getPosition());
	}
	
	private static void getHarvestLevelMC(Block abc) {
		harvestLevel = Harvest_Level_API.get_level(abc.getDefaultState().getHarvestLevel());
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

}
