package com.sangam1.InventoryRepair.GUI.Slots;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FurnaceSlots extends Slot {

	public FurnaceSlots(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, yPosition, yPosition, yPosition);
	}

	/**
	 * Called when the stack in a Slot changes
	 */
	@Override
	public void onSlotChanged() {
		// this.inventory.markDirty();
	}

}