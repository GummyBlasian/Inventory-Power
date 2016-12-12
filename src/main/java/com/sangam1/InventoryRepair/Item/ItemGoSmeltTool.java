package com.sangam1.InventoryRepair.Item;

import com.sangam1.InventoryRepair.GUI.Container.EntityGoFurnace;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemGoSmeltTool extends ItemBase {
	
	EntityGoFurnace furnace;
    private final boolean isBurning;
    private static boolean keepInventory;

	public ItemGoSmeltTool(String name) {
		super(name);
		this.furnace = new EntityGoFurnace();
		this.setMaxStackSize(1);
		this.isBurning = false;
	}	

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand playerIn) {
		if (world.isRemote)
        {
			return new ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));
        }
        else
        {            
            if (player instanceof EntityPlayer)
            {
            	player.displayGUIChest(furnace);
            	player.addStat(StatList.FURNACE_INTERACTION);
            }
            return new ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));
        }
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World worldIn, Entity par3Entity, int par4, boolean par5) {
		
	}

}
