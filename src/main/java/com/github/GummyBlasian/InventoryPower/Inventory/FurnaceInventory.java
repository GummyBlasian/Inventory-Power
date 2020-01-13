package com.github.GummyBlasian.InventoryPower.Inventory;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.util.NonNullList;

import java.util.Iterator;

public class FurnaceInventory extends CraftingInventory {

    private final NonNullList<ItemStack> stackList;
    private final int width;
    private final int height;
    private final Container container;

    public FurnaceInventory(Container container, int x, int y) {
        super(container, x, y);
        this.stackList = NonNullList.withSize(x * y, ItemStack.EMPTY);
        this.container = container;
        this.width = x;
        this.height = y;
    }

    @Override
    public int getSizeInventory() {
        return this.stackList.size();
    }

    @Override
    public boolean isEmpty() {
        Iterator var1 = this.stackList.iterator();

        ItemStack stack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            stack = (ItemStack)var1.next();
        } while(stack.isEmpty());

        return false;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return slot >= this.getSizeInventory() ? ItemStack.EMPTY : this.stackList.get(slot);
    }

    @Override
    public ItemStack removeStackFromSlot(int slot) {
        return ItemStackHelper.getAndRemove(this.stackList, slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int size) {
        ItemStack lvt_3_1_ = ItemStackHelper.getAndSplit(this.stackList, slot, size);
        if (!lvt_3_1_.isEmpty()) {
            this.container.onCraftMatrixChanged(this);
        }

        return lvt_3_1_;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.stackList.set(slot, stack);
        this.container.onCraftMatrixChanged(this);
    }

    @Override
    public void markDirty() {
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        this.stackList.clear();
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        Iterator var2 = this.stackList.iterator();

        while(var2.hasNext()) {
            ItemStack lvt_3_1_ = (ItemStack)var2.next();
            helper.accountPlainStack(lvt_3_1_);
        }

    }

}
