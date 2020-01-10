package com.github.GummyBlasian.InventoryPower.GUI;

import com.github.GummyBlasian.InventoryPower.GUI.Container.GoCraftContainer;
import com.github.GummyBlasian.InventoryPower.References.StartupRef;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GUIPortableCraftingTable extends GuiContainer {	

	private static final ResourceLocation gui = new ResourceLocation(StartupRef.MODID, "textures/gui/portable_crafting.png");

	private GoCraftContainer container;

	public GUIPortableCraftingTable(GoCraftContainer inventorySlotsIn) {
		super(inventorySlotsIn);
		container = inventorySlotsIn;	
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(container.getName(), container.getBorderSide() + 1, 6, 0x404040);
		int invTitleX = container.getPlayerInvXOffset() + 1;
		int invTitleY = container.getBorderTop() + container.getContainerInvHeight() + 3;
		fontRenderer.drawString(I18n.format("container.inventory"), invTitleX, invTitleY, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int relX = (this.width - this.xSize) / 2;
		int relY = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(relX, relY, 0, 0, this.xSize, this.ySize);
	}


}
