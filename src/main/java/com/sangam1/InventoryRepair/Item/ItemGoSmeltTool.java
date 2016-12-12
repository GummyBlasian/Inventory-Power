package com.sangam1.InventoryRepair.Item;

import com.sangam1.InventoryRepair.GUI.Container.EntityGoFurnace;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemGoSmeltTool extends ItemBase {

	EntityGoFurnace furnace;
	private final boolean isBurning;
	private static boolean keepInventory;
	private boolean used;

	public ItemGoSmeltTool(String name) {
		super(name);
		this.setMaxStackSize(1);
		this.isBurning = false;
		this.used = false;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand playerIn) {
		if (world.isRemote) {
			return new ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));
		} else {
			if(!used || this.furnace == null){
				//System.out.println("Pew");
				this.furnace = new EntityGoFurnace();				
			}
			//System.out.println("Hello " + this.furnace == null);
			player.displayGUIChest(furnace);
			player.addStat(StatList.FURNACE_INTERACTION);
			return new ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));
		}
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World worldIn, Entity par3Entity, int par4, boolean par5) {

	}

	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		keepInventory = true;

		/*
		 * if (active) { worldIn.setBlockState(pos,
		 * Blocks.LIT_FURNACE.getDefaultState().withProperty(FACING,
		 * iblockstate.getValue(FACING)), 3); worldIn.setBlockState(pos,
		 * Blocks.LIT_FURNACE.getDefaultState().withProperty(FACING,
		 * iblockstate.getValue(FACING)), 3); } else {
		 * worldIn.setBlockState(pos,
		 * Blocks.FURNACE.getDefaultState().withProperty(FACING,
		 * iblockstate.getValue(FACING)), 3); worldIn.setBlockState(pos,
		 * Blocks.FURNACE.getDefaultState().withProperty(FACING,
		 * iblockstate.getValue(FACING)), 3); }
		 */

		keepInventory = false;

		if (tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

}
