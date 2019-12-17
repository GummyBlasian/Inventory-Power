package com.sangam1.InventoryRepair.GUI.Container;

import java.util.HashMap;
import java.util.Map;

import com.sangam1.InventoryRepair.GUI.Inventory.PCT_Inventory;
import com.sangam1.InventoryRepair.References.ContainerList;
import com.sangam1.InventoryRepair.References.ItemList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

public class PCT_Container extends Container {

	private PlayerEntity playerEntity;
	private PCT_Inventory crafting_matrix;
	private CraftResultInventory craft_result;
	private Map<Integer, Slot> all_slots;

	public PCT_Container(int window_id, World world, PlayerEntity player, PlayerInventory playerInv) {
		super(ContainerList.PCT_CONTAINER, window_id);
		this.playerEntity = player;
		all_slots = new HashMap<Integer, Slot>();
		crafting_matrix = new PCT_Inventory(this);
		craft_result = new CraftResultInventory();
		layoutPlayerInventorySlots(playerInv);

	}

	@Override
	public ContainerType<?> getType() {
		return ContainerList.PCT_CONTAINER;
	}

	public PCT_Container(int windowId, PlayerInventory playerInv, PacketBuffer data) {
		this(windowId, playerInv.player.world, playerInv.player, playerInv);
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return player.getHeldItemMainhand().getItem() == ItemList.gocraft;
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			int countSlots = 27;
			if (index < countSlots) {
				if (!mergeItemStack(itemstack1, countSlots + 1, this.inventorySlots.size() - 9, false)
						&& !mergeItemStack(itemstack1, this.inventorySlots.size() - 9, this.inventorySlots.size(),
								true)) {
					return ItemStack.EMPTY;
				}
			} else if (!mergeItemStack(itemstack1, 0, countSlots, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}

	private int addSlotRange(PlayerInventory handler, int index, int x, int y, int amount, int dx) {
		for (int i = 0; i < amount; i++) {
			Slot a = new Slot(handler, index, x, y);
			all_slots.put(index, a);
			addSlot(a);
			x += dx;
			index++;
		}
		return index;
	}

	private int addSlotBox(PlayerInventory handler, int index, int x, int y, int horAmount, int dx, int verAmount,
			int dy) {
		for (int j = 0; j < verAmount; j++) {
			index = addSlotRange(handler, index, x, y, horAmount, dx);
			y += dy;
		}
		return index;
	}	
	
	private int addCraftingSlot(PCT_Inventory handler, int index, int x, int y, int dx, int dy) { 
		for(int a = 0; a<3;a++) {
			for (int i = 0 ; i < 3 ; i++) { 
				Slot b = new Slot(handler, index, x, y);
				all_slots.put(index, b);
			 	addSlot(b);
			 	x += dx; index++;
		 	}
			y += dy;
		}
		return index; 
	 }

	private void layoutPlayerInventorySlots(PlayerInventory playerInv) {
		// Crafting Grid
		addCraftingSlot(crafting_matrix,45,20,20,18,18);

		addSlot(new CraftingResultSlot(playerEntity, crafting_matrix, craft_result, 0, 124, 35));
		
		int leftCol = 8, topRow = 84;
		// Player inventory
		addSlotBox(playerInv, 9, leftCol, topRow, 9, 18, 3, 18);

		// Hotbar
		topRow += 58;
		addSlotRange(playerInv, 0, leftCol, topRow, 9, 18);
	}


	public ItemStack getStackInSlot(int index) {
		if(all_slots.containsKey(index))
			return all_slots.get(index).getStack();
		return ItemStack.EMPTY;
	}

	public ItemStack decrStackSize(int index, int count) {
		if(all_slots.containsKey(index)) {
			all_slots.get(index).getStack().setCount(all_slots.get(index).getStack().getCount()-count);
			return getStackInSlot(index);
		}
		return ItemStack.EMPTY;
	}

	public void setInventorySlotContents(int index, ItemStack stack) {
		if(all_slots.containsKey(index)) {
			Slot slot = all_slots.get(index);
			slot.putStack(stack);
			all_slots.replace(index, slot);
		}
	}
	 

}
