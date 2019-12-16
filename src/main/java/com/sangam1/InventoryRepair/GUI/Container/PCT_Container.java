package com.sangam1.InventoryRepair.GUI.Container;

import com.sangam1.InventoryRepair.References.ContainerList;
import com.sangam1.InventoryRepair.References.ItemList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class PCT_Container extends Container{

    @SuppressWarnings("unused")
	private PlayerEntity playerEntity;
    @SuppressWarnings("unused")
	private IItemHandler playerInventory;
    @SuppressWarnings("unused")
	private World world;

	public PCT_Container(int window_id, World world, PlayerEntity player, PlayerInventory playerInv) {
		super(ContainerList.PCT_CONTAINER, window_id);
		this.playerEntity = player;
		this.playerInventory = new InvWrapper(playerInv);
		this.world = world;
		
		layoutPlayerInventorySlots(playerInv);
		
	}
	
    @Override
    public ContainerType<?> getType() {
        return ContainerList.PCT_CONTAINER;
    }

	public PCT_Container(int windowId, PlayerInventory playerInv, PacketBuffer data) {
		this(windowId, playerInv.player.world, playerInv.player,playerInv);
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return player.getHeldItemMainhand().getItem() == ItemList.gocraft;
		//return true;
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
                if (!mergeItemStack(itemstack1, countSlots + 1, this.inventorySlots.size() - 9, false) && !mergeItemStack(itemstack1, this.inventorySlots.size() - 9, this.inventorySlots.size(), true)) {
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
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new Slot(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }
	
	private int addSlotBox(PlayerInventory handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

	private void layoutPlayerInventorySlots(PlayerInventory playerInv) {
		// Crafting Grid
		
		
		int leftCol = 8, topRow = 84;
        // Player inventory
        addSlotBox(playerInv, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInv, 0, leftCol, topRow, 9, 18);
    }
	
	/*
	private int addCraftingSlot(PlayerInventory handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new CraftingSlot(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }
    */
	
}
