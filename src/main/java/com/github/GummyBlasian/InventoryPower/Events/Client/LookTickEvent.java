package com.github.GummyBlasian.InventoryPower.Events.Client;

import com.github.GummyBlasian.InventoryPower.API.ListOfSpecialBlocks;
import com.github.GummyBlasian.InventoryPower.GUI.GUILookAt;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class LookTickEvent {
	private Minecraft mc;
	private RayTraceResult target;
	@SuppressWarnings("unused")
	private ItemStack targetStack;

	// Display what is being looked at
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (player != null && Minecraft.getMinecraft().world != null) {
			mc = Minecraft.getMinecraft();
			fire();
			if (target != null && target.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos abc = target.getBlockPos();
				BlockPos abc1 = new BlockPos(abc.getX(), abc.getY() - 1, abc.getZ());
				IBlockState mcBlockState = Minecraft.getMinecraft().world.getBlockState(abc);
				Block mcBlock = Minecraft.getMinecraft().world.getBlockState(abc).getBlock();
				System.out.println();
				if (mcBlock.toString().contains("double_plant")) {
					if (mcBlockState.toString().contains("half=upper")) {
						IBlockState ad = Minecraft.getMinecraft().world.getBlockState(abc1);
						GUILookAt.setItemInHandText(" "
								+ ListOfSpecialBlocks.getFromList(ad.toString().split("variant=")[1].split("]")[0]));
					} else {
						GUILookAt.setItemInHandText(" " + ListOfSpecialBlocks
								.getFromList(mcBlockState.toString().split("variant=")[1].split("]")[0]));
					}

				} else if (mcBlock.toString().contains("water")) {
					GUILookAt.setItemInHandText(" " + ListOfSpecialBlocks.getFromList(mcBlock));
				} else if (mcBlock.toString().contains("lava")) {
					GUILookAt.setItemInHandText(" " + ListOfSpecialBlocks.getFromList(mcBlock));
				} else {
					Item abcd = Item.getItemFromBlock(Minecraft.getMinecraft().world.getBlockState(abc).getBlock());
					ItemStack bcd = new ItemStack(abcd);
					if (abcd != null
							&& !ItemStack.areItemStacksEqual(bcd, new ItemStack(Item.getItemFromBlock(Blocks.AIR)))) {
						GUILookAt.setItemInHandText(" " + bcd.getDisplayName());
					} else {
						GUILookAt.setItemInHandText(" " + ListOfSpecialBlocks.getFromList(mcBlock));
					}
				}
			} else if (target != null && target.typeOfHit == RayTraceResult.Type.ENTITY) {
				GUILookAt.setItemInHandText(" " + EntityList.getEntityString(target.entityHit));
			} else {
				GUILookAt.setItemInHandText(" ");
			}
		} else {
			GUILookAt.setItemInHandText(" ");

		}
	}

	@SuppressWarnings("unused")
	private EntityLivingBase getTarget(float par1, double distance) {
		Minecraft mc = Minecraft.getMinecraft();
		return null;
	}

	private void fire() {
		if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY) {
			this.target = mc.objectMouseOver;
			this.targetStack = null;
			return;
		}

		Entity viewpoint = mc.getRenderViewEntity();
		if (viewpoint == null)
			return;

		this.target = this.rayTrace(viewpoint, mc.playerController.getBlockReachDistance(), 0);

		if (this.target == null)
			return;
	}

	private RayTraceResult rayTrace(Entity entity, double par1, float par3) {
		Vec3d vec3 = entity.getPositionEyes(par3);
		Vec3d vec31 = entity.getLook(par3);
		Vec3d vec32 = vec3.addVector(vec31.x * par1, vec31.y * par1, vec31.z * par1);

		return entity.world.rayTraceBlocks(vec3, vec32, true);
	}

}
