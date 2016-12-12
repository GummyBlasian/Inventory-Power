package com.sangam1.InventoryRepair.Item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemAutoSmeltTool extends ItemBase {

	private boolean active;
	private int burnTimeLeft;

	public ItemAutoSmeltTool(String name) {
		super(name);
		active = false;
		burnTimeLeft = 0;
		this.setMaxStackSize(1);
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World world, Entity par3Entity, int par4, boolean par5) {
		EntityPlayer player = (EntityPlayer) par3Entity;
		if (player == null || world.isRemote)
			return;
		System.out.println("Hello");
		InventoryPlayer inventory = player.inventory;
		if (active) {
			if (burnTimeLeft == 0) {
				for (int i = 0; i < inventory.getSizeInventory(); i++) {
					if (inventory.getStackInSlot(i).getItem() == Items.COAL) {

						return;
					}
				}
			}
			for (int i = 0; i < inventory.getSizeInventory(); i++) {
				if (inventory.getStackInSlot(i) == new ItemStack(Blocks.IRON_ORE)) {
					ItemStack smelt = inventory.getStackInSlot(i);
					inventory.removeStackFromSlot(i);
					for(int a = 0; a < smelt.getItemDamage(); i ++){
						inventory.addItemStackToInventory(FurnaceRecipes.instance().getSmeltingResult(smelt));
					}
					
					return;
				}
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn) {
		System.out.println("Click!");
		if (active) {
			active = false;
		} else {
			active = true;
		}
		return new ActionResult(EnumActionResult.PASS, worldIn.getHeldItem(playerIn));
	}

}
