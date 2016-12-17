package com.sangam1.InventoryRepair.GUI.Slots;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FurnaceSlots extends Slot {
	/** The index of the slot in the inventory. */
	private final int slotIndex;
	/** The inventory we want to extract a slot from. */
	public final IInventory inventory;
	/** the id of the slot(also the index in the inventory arraylist) */
	public int slotNumber;
	/** display position of the inventory slot on the screen x axis */
	public int xDisplayPosition;
	/** display position of the inventory slot on the screen y axis */
	public int yDisplayPosition;

	public FurnaceSlots(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, yPosition, yPosition, yPosition);
		this.inventory = inventoryIn;
		this.slotIndex = index;
		this.xDisplayPosition = xPosition;
		this.yDisplayPosition = yPosition;
	}

	/**
	 * Called when the stack in a Slot changes
	 */
	@Override
	public void onSlotChanged() {
		// this.inventory.markDirty();
	}

}