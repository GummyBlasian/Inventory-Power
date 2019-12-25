package com.sangam1.InventoryRepair.Screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sangam1.InventoryRepair.Main;
import com.sangam1.InventoryRepair.GUI.Container.PCTContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PCTScreen extends ContainerScreen<PCTContainer>{

    private ResourceLocation GUI = new ResourceLocation(Main.MODID, "textures/gui/gocraft.png");

	public PCTScreen(PCTContainer container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
	}

}
