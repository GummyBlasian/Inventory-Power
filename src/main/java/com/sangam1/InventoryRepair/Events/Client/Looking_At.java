package com.sangam1.InventoryRepair.Events.Client;

import java.util.List;

import com.google.common.collect.Lists;
import com.sangam1.InventoryRepair.Main;
import com.sangam1.InventoryRepair.API.Harvest_Level_API;
import com.sangam1.InventoryRepair.API.ListOfSpecialBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class Looking_At {
	
	private static final Minecraft mc = Minecraft.getInstance();
	private static final PlayerEntity p = mc.player;
	private static World world;

	private static RayTraceResult target;
	@SuppressWarnings("unused")
	private static ItemStack targetStack;
	private static String looking_at;
	private static String made_by;
	private static boolean can_mine;
	private static String harvest_level;
	private static Biome biome;

	public static String get_looking_at() {
		world = mc.world;
		
		get_biome();
		
		fire();
		
		looking_at = " ";
		made_by = " ";
		can_mine = false;
		harvest_level = "";
		
		if(target == null)
			Main.LOGGER.info("null");
		else
			Main.LOGGER.info("target " + target.getType());
		
		if (target != null && target.getType() == RayTraceResult.Type.BLOCK) {
			BlockPos abc = new BlockPos(target.getHitVec());
			BlockPos abc1 = new BlockPos(abc.getX(), abc.getY() - 1, abc.getZ());
			BlockState mcBlockState = world.getBlockState(abc);
			Block mcBlock = world.getBlockState(abc).getBlock();
			Main.LOGGER.info("0 " + mcBlock.toString());
			if(mcBlock == Blocks.AIR) {
				made_by = " ";
				return looking_at = " ";
			}
			//Main.LOGGER.info("1 " + mcBlock.toString());
			if(mcBlock == Blocks.CAVE_AIR) {
				mcBlock = world.getBlockState(abc1).getBlock();
			}
			//Main.LOGGER.info("2 " + mcBlock.toString());
			looking_at = mcBlock.getNameTextComponent().getString();
			made_by = mcBlock.getRegistryName().getNamespace();
			getHarvest_level(mcBlock);
			if (mcBlock.toString().contains("double_plant") && looking_at == " ") {
				if (mcBlockState.toString().contains("half=upper")) {
					BlockState ad = world.getBlockState(abc1);
					looking_at = " " + ListOfSpecialBlocks.getFromList(ad.toString().split("variant=")[1].split("]")[0]);
					made_by = " " + mcBlock.getRegistryName().getNamespace();
				} else {
					looking_at = " " + ListOfSpecialBlocks.getFromList(mcBlockState.toString().split("variant=")[1].split("]")[0]);
					made_by = " " + mcBlock.getRegistryName().getNamespace();
				}

			} else if (mcBlock.toString().contains("water") && looking_at == " ") {
				looking_at = " " + ListOfSpecialBlocks.getFromList(mcBlock);
				made_by = " " + mcBlock.getRegistryName().getNamespace();
			} else if (mcBlock.toString().contains("lava") && looking_at == " ") {
				looking_at = " " + ListOfSpecialBlocks.getFromList(mcBlock);
				made_by = " " + mcBlock.getRegistryName().getNamespace();
			} else if (looking_at == " "){
				if (Armor_Durability.getHand_Icon() != null) {
					if (Armor_Durability.getHand_Icon().getToolTypes().contains(world.getBlockState(abc).getHarvestTool())) {
						if(Armor_Durability.getHand_Icon().canHarvestBlock(world.getBlockState(abc))) {
							can_mine = true;
						}
					}
				}
				getHarvest_level(abc);
				Item abcd = Item.BLOCK_TO_ITEM.get(world.getBlockState(abc).getBlock());
				ItemStack bcd = new ItemStack(abcd);
				if (abcd != null && !ItemStack.areItemStacksEqual(bcd, new ItemStack(Item.BLOCK_TO_ITEM.get(Blocks.AIR)))) {
					looking_at = " " + bcd.getDisplayName().getFormattedText();
					made_by = " " + mcBlock.getRegistryName().getNamespace();
				} else {
					looking_at = " " + ListOfSpecialBlocks.getFromList(mcBlock);
					made_by = " " + mcBlock.getRegistryName().getNamespace();
				}
			}			
		} else if (target != null && target.getType() == RayTraceResult.Type.ENTITY) {
			Entity en = target.getType() == RayTraceResult.Type.ENTITY ? getIdentifierEntity() : null;
			if (en != null) 
			{
			looking_at = " " + en.getDisplayName().getFormattedText();	
			made_by = " ";
			}
		} else {
			looking_at = " ";
		}
		return looking_at;
	}
	
	public static String get_biome_name() {
		return biome.getDisplayName().getFormattedText();
	}

	private static void get_biome() {
		biome = world.getBiome(mc.player.getPosition());
	}

	private static void getHarvest_level(BlockPos abc) {
		harvest_level = Harvest_Level_API.get_level(world.getBlockState(abc).getHarvestLevel());
	}
	
	private static void getHarvest_level(Block abc) {
		harvest_level = Harvest_Level_API.get_level(abc.getDefaultState().getHarvestLevel());
	}

	public static Entity getIdentifierEntity() {
        if (target == null || target.getType() != RayTraceResult.Type.ENTITY)
            return null;

        List<Entity> entities = Lists.newArrayList();

        Entity entity = ((EntityRayTraceResult) target).getEntity();

        return entities.size() > 0 ? entities.get(0) : entity;
    }
	
	private static void fire() {
		Main.LOGGER.info(mc.objectMouseOver.getType().toString());
		
		if (mc.objectMouseOver != null && mc.objectMouseOver.getType() == RayTraceResult.Type.ENTITY) {
			target = mc.objectMouseOver;
			targetStack = null;
			return;
		}
		if (mc.objectMouseOver != null && mc.objectMouseOver.getType() == RayTraceResult.Type.BLOCK) {
			if (Block.getValidBlockForPosition(world.getBlockState(new BlockPos(mc.objectMouseOver.getHitVec())), world, new BlockPos(mc.objectMouseOver.getHitVec())) != Blocks.AIR.getDefaultState()) {
				target = mc.objectMouseOver;
				targetStack = null;
				return;
			}
			target = mc.objectMouseOver;
			targetStack = null;
			return;
		}
		Entity viewpoint = mc.getRenderViewEntity();
		
		if (viewpoint == null) {
			return;
		}		

		target = rayTrace(p, mc.playerController.getBlockReachDistance(), 0);

		if (target == null) {
			return;
		}
	}

	private static RayTraceResult rayTrace(Entity entity, double par1, float par3) {
		
		final RayTraceContext.FluidMode FLUID_MODE = RayTraceContext.FluidMode.ANY;
        final RayTraceContext.BlockMode BLOCK_MODE = RayTraceContext.BlockMode.COLLIDER;
        final Vec3d EYES_POSITION = p.getEyePosition(1f);
        final Vec3d LOOK_DIRECTION = p.getLook(1f);
        float range = 10.0f;
        Vec3d endOfLook = EYES_POSITION.add(LOOK_DIRECTION.x * range, LOOK_DIRECTION.y * range, LOOK_DIRECTION.z * range);
        
        RayTraceResult targetedBlock = world.rayTraceBlocks(new RayTraceContext(EYES_POSITION, endOfLook, BLOCK_MODE, FLUID_MODE, p));

        Main.LOGGER.info(p.REACH_DISTANCE.getDefaultValue() + " " + world.getBlockState(new BlockPos(targetedBlock.getHitVec())).getBlock().toString());
        /*
		Vec3d vec3 = entity.getEyePosition(par3);
		@SuppressWarnings("unused")
		Vec3d vec31 = entity.getLook(par3);
		Vec3d vec32 = vec3.add(par1, par1, par1);
		
		return entity.world.rayTraceBlocks(new RayTraceContext(vec3, vec32, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, entity));
		*/
        return targetedBlock;
	}

	public static String get_made_by() {
		return made_by;
	}
	
	public static boolean can_mine() {
		return can_mine;
	}
	
	public static String get_harvest_level() {
		return harvest_level;
	}
}
