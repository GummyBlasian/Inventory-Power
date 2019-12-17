package com.sangam1.InventoryRepair.GUI.Inventory;

import com.sangam1.InventoryRepair.GUI.Container.PCT_Container;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;

public class PCT_Inventory extends CraftingInventory {

	private final PCT_Container container;

	public PCT_Inventory(final PCT_Container eventHandlerIn) {
		super(eventHandlerIn, 3, 3);
		container = eventHandlerIn;
	}

	@Override
	public int getSizeInventory() {
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(final int index) {
		return container.getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(final int index, final int count) {
		final ItemStack stack = container.decrStackSize(index, count);
		return stack;
	}

	@Override
	public void setInventorySlotContents(final int index, final ItemStack stack) {
		container.setInventorySlotContents(index, stack);
	}

	public void changed() {
		container.onCraftMatrixChanged(this);
	}
}