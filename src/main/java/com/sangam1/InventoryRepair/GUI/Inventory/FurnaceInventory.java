package com.sangam1.InventoryRepair.GUI.Inventory;

import com.sangam1.InventoryRepair.Item.ItemPortableFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class FurnaceInventory implements IInventory {
	
	private final ItemPortableFurnace stack;
	private final ItemStack stackFurnace;
	private String name = "Portable Furnace";
	
	public FurnaceInventory(ItemStack stack){
		this.stackFurnace = stack;
		this.stack = (ItemPortableFurnace)stack.getItem();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	@Override
	public int getSizeInventory() {
		return 64;
	}

	@Override
	public boolean func_191420_l() {
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return stack.getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return stack.decrStackSize(index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return stack.removeStackFromSlot(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {	}

	@Override
	public void closeInventory(EntityPlayer player) {	
		stack.updateAllNBT(stackFurnace);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {	}
	
}