package com.github.GummyBlasian.InventoryPower.GUI.Slot;

import com.github.GummyBlasian.InventoryPower.GUI.Container.PFContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class FurnaceInputSlot extends Slot {

    private final int slotIndex;
    public final IInventory inventory;
    public int slotNumber;
    public final int xPos;
    public final int yPos;
    private final PFContainer container;

    public FurnaceInputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition, PFContainer container) {
        super(inventoryIn, index, xPosition, yPosition);
        this.inventory = inventoryIn;
        this.slotIndex = index;
        this.xPos = xPosition;
        this.yPos = yPosition;
        this.container = container;
    }

    @Override
    public void putStack(ItemStack stack) {
        System.out.println("setting");
        this.inventory.setInventorySlotContents(this.slotIndex, stack);
        this.onSlotChanged();
    }

    @Override
    public void onSlotChanged() {
        System.out.println(this.getSlotIndex() + " changed to " + this.getStack().toString());
        this.container.onCraftMatrixChanged(this.inventory); // this should call and request to see if can smelt.
        this.inventory.markDirty();
    }

    @Override
    public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_) {
        System.out.println(this.getSlotIndex() + " change to " + this.getStack().toString());
        int i = p_75220_2_.getCount() - p_75220_1_.getCount();
        if (i > 0) {
            this.onCrafting(p_75220_2_, i);
        }
    }

    @Override
    public ItemStack getStack() {
        return this.inventory.getStackInSlot(this.slotIndex);
    }
}
