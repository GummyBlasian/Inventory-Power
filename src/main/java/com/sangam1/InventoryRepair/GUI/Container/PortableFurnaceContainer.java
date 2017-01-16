package com.sangam1.InventoryRepair.GUI.Container;

import java.util.List;

import com.google.common.collect.Lists;
import com.sangam1.InventoryRepair.GUI.Slots.FurnaceFuelSlots;
import com.sangam1.InventoryRepair.GUI.Slots.FurnaceOutputSlots;
import com.sangam1.InventoryRepair.GUI.Slots.FurnaceSlots;
import com.sangam1.InventoryRepair.Item.ItemPortableFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PortableFurnaceContainer extends Container
{
    private final IInventory tileFurnace;
	private int cookTime;
    private int totalCookTime;
    private int furnaceBurnTime;
    private int currentItemBurnTime;
    public List<FurnaceSlots> inventorySlots = Lists.<FurnaceSlots>newArrayList();
    private final ItemStack stack;

    public PortableFurnaceContainer(InventoryPlayer playerInventory, ItemStack stack, IInventory furnace)
    {
    	this.tileFurnace = furnace;
        this.stack = stack;
        this.addSlotToContainer(new FurnaceSlots((ItemPortableFurnace)stack.getItem(), 0, 56, 17));
        this.addSlotToContainer(new FurnaceFuelSlots((ItemPortableFurnace)stack.getItem(), 1, 56, 53));
        this.addSlotToContainer(new FurnaceOutputSlots(playerInventory.player, (ItemPortableFurnace)stack.getItem(), 2, 116, 35));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }
    
    private FurnaceSlots addSlotToContainer(FurnaceSlots slotIn)
    {
        slotIn.slotNumber = this.inventorySlots.size();
        this.inventorySlots.add(slotIn);
        this.inventoryItemStacks.add(ItemStack.field_190927_a);
        return slotIn;
    }

    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileFurnace);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);

            if (this.cookTime != ((ItemPortableFurnace)this.stack.getItem()).getField(2))
            {
                icontainerlistener.sendProgressBarUpdate(this, 2, ((ItemPortableFurnace)this.stack.getItem()).getField(2));
            }

            if (this.furnaceBurnTime != ((ItemPortableFurnace)this.stack.getItem()).getField(0))
            {
                icontainerlistener.sendProgressBarUpdate(this, 0, ((ItemPortableFurnace)this.stack.getItem()).getField(0));
            }

            if (this.currentItemBurnTime != ((ItemPortableFurnace)this.stack.getItem()).getField(1))
            {
                icontainerlistener.sendProgressBarUpdate(this, 1, ((ItemPortableFurnace)this.stack.getItem()).getField(1));
            }

            if (this.totalCookTime != ((ItemPortableFurnace)this.stack.getItem()).getField(3))
            {
                icontainerlistener.sendProgressBarUpdate(this, 3, ((ItemPortableFurnace)this.stack.getItem()).getField(3));
            }
        }

        this.cookTime = ((ItemPortableFurnace)this.stack.getItem()).getField(2);
        this.furnaceBurnTime = ((ItemPortableFurnace)this.stack.getItem()).getField(0);
        this.currentItemBurnTime = ((ItemPortableFurnace)this.stack.getItem()).getField(1);
        this.totalCookTime = ((ItemPortableFurnace)this.stack.getItem()).getField(3);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
    	((ItemPortableFurnace)this.stack.getItem()).setField(id, data);
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
        ItemStack itemstack = ItemStack.field_190927_a;
        FurnaceSlots slot = (FurnaceSlots)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return ItemStack.field_190927_a;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (!FurnaceRecipes.instance().getSmeltingResult(itemstack1).func_190926_b())
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return ItemStack.field_190927_a;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return ItemStack.field_190927_a;
            }

            if (itemstack1.func_190926_b())
            {
                slot.putStack(ItemStack.field_190927_a);
            }
            else
            {
                slot.onSlotChanged(itemstack);
            }

            if (itemstack1.func_190916_E() == itemstack.func_190916_E())
            {
                return ItemStack.field_190927_a;
            }

            slot.func_190901_a(playerIn, itemstack1);
        }

        return itemstack;
    }
}