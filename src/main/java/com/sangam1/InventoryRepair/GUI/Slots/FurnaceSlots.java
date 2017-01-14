package com.sangam1.InventoryRepair.GUI.Slots;

import javax.annotation.Nullable;

import com.sangam1.InventoryRepair.GUI.Inventory.FurnaceInventory;
import com.sangam1.InventoryRepair.Item.ItemPortableFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FurnaceSlots extends Slot
{
    /** The index of the slot in the inventory. */
    private final int slotIndex;
    /** The inventory we want to extract a slot from. */
    //public final IInventory inventory;
    /** the id of the slot(also the index in the inventory arraylist) */
    public int slotNumber;
    /** display position of the inventory slot on the screen x axis */
    public int xDisplayPosition;
    /** display position of the inventory slot on the screen y axis */
    public int yDisplayPosition;
    public final ItemPortableFurnace stack;

    public FurnaceSlots(ItemPortableFurnace itemPortableFurnace, int index, int xPosition, int yPosition)
    {
    	super(new FurnaceInventory(new ItemStack(itemPortableFurnace)), index, xPosition, yPosition);
        //this.inventory = inventoryIn;
        this.stack = itemPortableFurnace;
        this.slotIndex = index;
        this.xDisplayPosition = xPosition;
        this.yDisplayPosition = yPosition;
    }

    /**
     * if par2 has more items than par1, onCrafting(item,countIncrease) is called
     */
    public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_)
    {
        int i = p_75220_2_.func_190916_E() - p_75220_1_.func_190916_E();

        if (i > 0)
        {
            this.onCrafting(p_75220_2_, i);
        }
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    protected void onCrafting(ItemStack stack, int amount)
    {
    }

    protected void func_190900_b(int p_190900_1_)
    {
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    protected void onCrafting(ItemStack stack)
    {
    }

    public ItemStack func_190901_a(EntityPlayer p_190901_1_, ItemStack p_190901_2_)
    {
        this.onSlotChanged(p_190901_2_);
        return p_190901_2_;
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return true;
    }

    /**
     * Helper fnct to get the stack in the slot.
     */
    public ItemStack getStack()
    {
        return stack.getStackInSlot(this.slotIndex);
    }

    /**
     * Returns if this slot contains a stack.
     */
    public boolean getHasStack()
    {
        return !this.getStack().func_190926_b();
    }

    /**
     * Helper method to put a stack in the slot.
     */
    public void putStack(ItemStack stack)
    {
        this.stack.setFurnaceStackSlot(this.slotIndex, stack);
        this.onSlotChanged(stack);
    }

    /**
     * Called when the stack in a Slot changes
     */
    public void onSlotChanged(ItemStack stack)
    {
    	this.stack.setFurnaceStackSlot(this.slotIndex, stack);
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
     * of armor slots)
     */
    public int getSlotStackLimit()
    {
        return 64;
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return this.getSlotStackLimit();
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    public String getSlotTexture()
    {
        return backgroundName;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int amount)
    {
        return this.stack.decrStackSize(this.slotIndex, amount);
    }


    public boolean isHere(IInventory inv, int slotIn)
    {
        return true;
    }

    /**
     * Return whether this slot's stack can be taken from this slot.
     */
    public boolean canTakeStack(EntityPlayer playerIn)
    {
        return true;
    }

    /**
     * Actualy only call when we want to render the white square effect over the slots. Return always True, except for
     * the armor slot of the Donkey/Mule (we can't interact with the Undead and Skeleton horses)
     */
    @SideOnly(Side.CLIENT)
    public boolean canBeHovered()
    {
        return true;
    }

    /*========================================= FORGE START =====================================*/
    protected String backgroundName = null;
    protected net.minecraft.util.ResourceLocation backgroundLocation = null;
    protected Object backgroundMap;
    /**
     * Gets the path of the texture file to use for the background image of this slot when drawing the GUI.
     * @return The resource location for the background image
     */
    @SideOnly(Side.CLIENT)
    public net.minecraft.util.ResourceLocation getBackgroundLocation()
    {
        return (backgroundLocation == null ? net.minecraft.client.renderer.texture.TextureMap.LOCATION_BLOCKS_TEXTURE : backgroundLocation);
    }

    /**
     * Sets the texture file to use for the background image of the slot when it's empty.
     * @param texture the resourcelocation for the texture
     */
    @SideOnly(Side.CLIENT)
    public void setBackgroundLocation(net.minecraft.util.ResourceLocation texture)
    {
        this.backgroundLocation = texture;
    }

    /**
     * Sets which icon index to use as the background image of the slot when it's empty.
     * @param name The icon to use, null for none
     */
    public void setBackgroundName(String name)
    {
        this.backgroundName = name;
    }

    @SideOnly(Side.CLIENT)
    public net.minecraft.client.renderer.texture.TextureAtlasSprite getBackgroundSprite()
    {
        String name = getSlotTexture();
        return name == null ? null : getBackgroundMap().getAtlasSprite(name);
    }

    @SideOnly(Side.CLIENT)
    protected net.minecraft.client.renderer.texture.TextureMap getBackgroundMap()
    {
        if (backgroundMap == null) backgroundMap = net.minecraft.client.Minecraft.getMinecraft().getTextureMapBlocks();
        return (net.minecraft.client.renderer.texture.TextureMap)backgroundMap;
    }

    /**
     * Retrieves the index in the inventory for this slot, this value should typically not
     * be used, but can be useful for some occasions.
     *
     * @return Index in associated inventory for this slot.
     */
    public int getSlotIndex()
    {
        /** The index of the slot in the inventory. */
        return slotIndex;
    }


    public boolean isSameInventory(FurnaceSlots other)
    {
        //return this.inventory == other.inventory;
    	return true;
    }
    /*========================================= FORGE END =====================================*/
}