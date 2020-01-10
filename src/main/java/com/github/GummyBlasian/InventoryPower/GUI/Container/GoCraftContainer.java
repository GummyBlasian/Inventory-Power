package com.github.GummyBlasian.InventoryPower.GUI.Container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GoCraftContainer extends Container
{
    /** The crafting matrix inventory (3x3). */
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public InventoryCraftResult craftResult = new InventoryCraftResult();
    private final World world;
    /** Position of the workbench */
    @SuppressWarnings("unused")
	private final BlockPos pos;

    public GoCraftContainer(InventoryPlayer playerInventory, World worldIn, BlockPos posIn)
    {
        this.world = worldIn;
        this.pos = posIn;
        this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 35));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for (int k = 0; k < 3; ++k)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for (int l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(playerInventory, l, 8 + l * 18, 142));
        }

        this.onCraftMatrixChanged(this.craftMatrix);
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(final IInventory inventoryIn)
    {
        //this.craftResult.setInventorySlotContents(0, CraftingManager.findMatchingRecipe(this.craftMatrix, this.worldObj).getRecipeOutput());
    	craftResult.setInventorySlotContents(0, CraftingManager.findMatchingResult(craftMatrix, world));
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!this.world.isRemote)
        {
            for (int i = 0; i < 9; ++i)
            {
                ItemStack itemstack = this.craftMatrix.removeStackFromSlot(i);

                if (!itemstack.isEmpty())
                {
                    playerIn.dropItem(itemstack, false);
                }
            }
        }
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0)
            {
                itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);

                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index >= 10 && index < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (index >= 37 && index < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

            if (index == 0)
            {
                playerIn.dropItem(itemstack2, false);
            }
        }

        return itemstack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    public boolean canMergeSlot(ItemStack stack, Slot slotIn)
    {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }
    
    // GUI/slot setup helpers

    /**
     * Returns the size of the top border in pixels.
     */
    public int getBorderTop() {
        return 17;
    }

    /**
     * Returns the size of the side border in pixels.
     */
    public int getBorderSide() {
        return 7;
    }

    /**
     * Returns the size of the bottom border in pixels.
     */
    public int getBorderBottom() {
        return 7;
    }

    /**
     * Returns the space between container and player inventory in pixels.
     */
    public int getBufferInventory() {
        return 13;
    }

    /**
     * Returns the space between player inventory and hotbar in pixels.
     */
    public int getBufferHotbar() {
        return 4;
    }

    /**
     * Returns the total width of the container in pixels.
     */
    public int getWidth() {
        return Math.max(4, 9) * 18 + getBorderSide() * 2;
    }

    /**
     * Returns the total height of the container in pixels.
     */
    public int getHeight() {
        return getBorderTop() + (9 * 18) + getBufferInventory() + (4 * 18) + getBufferHotbar() + getBorderBottom();
    }

    /**
     * Returns the size of the container's width, only the inventory/slots, not the border, in pixels.
     */
    public int getContainerInvWidth() {
        return 4 * 18;
    }

    /**
     * Returns the size of the container's height, only the inventory/slots, not the border, in pixels.
     */
    public int getContainerInvHeight() {
        return 9 * 18;
    }

    /**
     * Returns the size of the x offset for the backpack container in pixels.
     */
    public int getContainerInvXOffset() {
        return getBorderSide() + Math.max(0, (getPlayerInvWidth() - getContainerInvWidth()) / 2);
    }

    /**
     * Returns the size of the x offset for the player's inventory in pixels.
     */
    public int getPlayerInvXOffset() {
        return getBorderSide() + Math.max(0, (getContainerInvWidth() - getPlayerInvWidth()) / 2);
    }

    /**
     * Returns the size of the player's inventory width, not including the borders, in pixels.
     */
    public int getPlayerInvWidth() {
        return 9 * 18;
    }

    /**
     * Returns the size of the player's inventory height, including the hotbar, in pixels.
     */
    public int getPlayerInvHeight() {
        return 4 * 18 + getBufferHotbar();
    }

	public String getName() {
		return "Portable Crafting Table";
	}
}
