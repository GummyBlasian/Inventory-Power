package com.sangam1.InventoryRepair.Item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAutoSmeltTool extends ItemBase{
	
	private boolean active;

	public ItemAutoSmeltTool(String name) {
		super(name);
		active = false;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn)
    {
		System.out.println("Click!");
        return new ActionResult(EnumActionResult.PASS, worldIn.getHeldItem(playerIn));
    }
	
	
}
