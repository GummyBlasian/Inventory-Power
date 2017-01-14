package com.sangam1.InventoryRepair.GUI;

import com.sangam1.InventoryRepair.GUI.Container.PortableFurnaceContainer;
import com.sangam1.InventoryRepair.GUI.Inventory.FurnaceInventory;
import com.sangam1.InventoryRepair.Item.ItemPortableFurnace;
import com.sangam1.InventoryRepair.References.Keys;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUIPortableFurnace extends GuiContainer
{
    private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation("textures/gui/container/furnace.png");
    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final ItemStack stack;

    public GUIPortableFurnace(InventoryPlayer playerInv, ItemStack stack)
    {
        super(new PortableFurnaceContainer(playerInv, stack, new FurnaceInventory(stack)));
        this.playerInventory = playerInv;
        this.stack = stack;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = "Portable Furnace";
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(FURNACE_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        if (((ItemPortableFurnace)this.stack.getItem()).isBurning())
        {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = ((ItemPortableFurnace)this.stack.getItem()).getNBTInt(stack, Keys.PROG.key());
        int j = 200;
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = ((ItemPortableFurnace)this.stack.getItem()).getNBTInt(stack, Keys.BT.key());

        if (i == 0)
        {
            i = 200;
        }

        return ((ItemPortableFurnace)this.stack.getItem()).getNBTInt(stack, Keys.FT.key()) * pixels / i;
    }
}
