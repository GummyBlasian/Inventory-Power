package com.github.GummyBlasian.InventoryPower.GUI.Slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;

public class CustomFurnaceResultSlot extends FurnaceResultSlot {

    private final PlayerEntity player;
    private int removeCount;

    public CustomFurnaceResultSlot(PlayerEntity player, IInventory inventoryIn, int slot, int x, int y) {
        super(player, inventoryIn, slot, x, y);
        this.player = player;
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    protected void onCrafting(ItemStack stack, int amount) {
        removeCount += amount;
        this.onCrafting(stack);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    protected void onCrafting(ItemStack stack) {
        System.out.println("called " + stack.toString() + this.removeCount);
        stack.onCrafting(this.player.world, this.player, this.removeCount);

        this.removeCount = 0;
        net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerSmeltedEvent(this.player, stack);
    }
}
