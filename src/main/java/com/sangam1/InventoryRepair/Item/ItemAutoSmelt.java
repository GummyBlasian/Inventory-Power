package com.sangam1.InventoryRepair.Item;

import com.sangam1.InventoryRepair.Main;
import com.sangam1.InventoryRepair.References.StartupRef;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAutoSmelt extends Item {

	public ItemAutoSmelt() {
		super();

		this.setMaxDamage(0);
		this.setUnlocalizedName("ItemAutoSmelterTool");
		this.setCreativeTab(Main.sangam1ir);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return this.getUnlocalizedName();
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer stack, World playerIn, BlockPos worldIn, EnumHand pos, EnumFacing hand, float facing, float hitX, float hitY)
    {
		System.out.println("I was clicked!");
        return EnumActionResult.SUCCESS;
    }

}
