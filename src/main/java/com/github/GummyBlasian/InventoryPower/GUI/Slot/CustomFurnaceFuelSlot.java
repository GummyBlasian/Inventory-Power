package com.github.GummyBlasian.InventoryPower.GUI.Slot;

import com.github.GummyBlasian.InventoryPower.GUI.Container.PFContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraftforge.common.ForgeHooks;

public class CustomFurnaceFuelSlot extends Slot {

    private PFContainer container;

    public CustomFurnaceFuelSlot(PFContainer container,IInventory inventory, int slot, int x, int y) {
        super(inventory, slot, x, y);
        this.container = container;
    }

    public boolean isItemValid(ItemStack stack) {
        return AbstractFurnaceTileEntity.isFuel(stack) || isBucket(stack);
    }

    public int getItemStackLimit(ItemStack stack) {
        return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.getItem() == Items.BUCKET;
    }

    @Override
    public void onSlotChanged() {
        container.addBurn(ForgeHooks.getBurnTime(getStack()));
        decrStackSize(1);
        this.inventory.markDirty();
    }
}
