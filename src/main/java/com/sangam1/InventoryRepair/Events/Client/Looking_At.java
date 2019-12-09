package com.sangam1.InventoryRepair.Events.Client;

import java.util.List;

import com.google.common.collect.Lists;
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

public class Looking_At {
	
	private static final Minecraft mc = Minecraft.getInstance();
	private static final PlayerEntity p = mc.player;
	private static final World world = mc.world;

	private static RayTraceResult target;
	@SuppressWarnings("unused")
	private static ItemStack targetStack;
	private static String looking_at;
	private static String made_by;
	private static boolean can_mine;
	private static String harvest_level;

	public static String get_looking_at() {
		
		fire();
		
		looking_at = " ";
		made_by = " ";
		can_mine = false;
		harvest_level = " ";
		
		if (target != null && target.getType() == RayTraceResult.Type.BLOCK) {
			BlockPos abc = new BlockPos(target.getHitVec());
			BlockPos abc1 = new BlockPos(abc.getX(), abc.getY() - 1, abc.getZ());
			BlockState mcBlockState = world.getBlockState(abc);
			Block mcBlock = world.getBlockState(abc).getBlock();
			if(mcBlock == Blocks.AIR) {
				made_by = " ";
				return looking_at = " ";
			}
			if (mcBlock.toString().contains("double_plant")) {
				if (mcBlockState.toString().contains("half=upper")) {
					BlockState ad = world.getBlockState(abc1);
					looking_at = " " + ListOfSpecialBlocks.getFromList(ad.toString().split("variant=")[1].split("]")[0]);
					made_by = " " + mcBlock.getRegistryName().getNamespace();
				} else {
					looking_at = " " + ListOfSpecialBlocks.getFromList(mcBlockState.toString().split("variant=")[1].split("]")[0]);
					made_by = " " + mcBlock.getRegistryName().getNamespace();
				}

			} else if (mcBlock.toString().contains("water")) {
				looking_at = " " + ListOfSpecialBlocks.getFromList(mcBlock);
				made_by = " " + mcBlock.getRegistryName().getNamespace();
			} else if (mcBlock.toString().contains("lava")) {
				looking_at = " " + ListOfSpecialBlocks.getFromList(mcBlock);
				made_by = " " + mcBlock.getRegistryName().getNamespace();
			} else {
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

	private static void getHarvest_level(BlockPos abc) {
		harvest_level = Harvest_Level_API.get_level(world.getBlockState(abc).getHarvestLevel());
	}

	public static Entity getIdentifierEntity() {
        if (target == null || target.getType() != RayTraceResult.Type.ENTITY)
            return null;

        List<Entity> entities = Lists.newArrayList();

        Entity entity = ((EntityRayTraceResult) target).getEntity();

        return entities.size() > 0 ? entities.get(0) : entity;
    }
	
	private static void fire() {
		if (mc.objectMouseOver != null && mc.objectMouseOver.getType() == RayTraceResult.Type.ENTITY) {
			target = mc.objectMouseOver;
			targetStack = null;
			return;
		}
		if (mc.objectMouseOver != null && mc.objectMouseOver.getType() == RayTraceResult.Type.BLOCK) {
			//Main.LOGGER.info("set");
			if (Block.getValidBlockForPosition(world.getBlockState(new BlockPos(mc.objectMouseOver.getHitVec())), world, new BlockPos(mc.objectMouseOver.getHitVec())) != Blocks.AIR.getDefaultState()) {
				target = mc.objectMouseOver;
				targetStack = null;
				//Main.LOGGER.info("info");
				return;
			}
			target = mc.objectMouseOver;
			targetStack = null;
			//Main.LOGGER.info("hehe");
			return;
		}
		
		//Main.LOGGER.info("weee");
		Entity viewpoint = mc.getRenderViewEntity();
		
		if (viewpoint == null) {
			//Main.LOGGER.info("null1");
			return;
		}

		target = rayTrace(p, mc.playerController.getBlockReachDistance(), 0);

		if (target == null) {
			//Main.LOGGER.info("null2");
			return;
		}
	}

	@SuppressWarnings("unused")
	private static RayTraceResult rayTrace(Entity entity, double par1, float par3) {
		Vec3d vec3 = entity.getEyePosition(par3);
		Vec3d vec31 = entity.getLook(par3);
		Vec3d vec32 = vec3.add(par1, par1, par1);
		//Main.LOGGER.info(vec3);
		//Main.LOGGER.info(vec31);
		//Main.LOGGER.info(vec32);
		return entity.world.rayTraceBlocks(new RayTraceContext(vec3, vec32, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, entity));
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
