package com.sangam1.InventoryRepair.Item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemAutoSmeltTool extends ItemBase {

	private boolean active;

	public ItemAutoSmeltTool(String name) {
		super(name);
		active = false;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if(active){
			
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
