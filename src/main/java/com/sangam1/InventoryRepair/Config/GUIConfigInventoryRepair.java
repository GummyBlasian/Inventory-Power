package com.sangam1.InventoryRepair.Config;

import com.sangam1.InventoryRepair.ConfigHandler;
import com.sangam1.InventoryRepair.References.StartupRef;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class GUIConfigInventoryRepair extends GuiConfig {
	public GUIConfigInventoryRepair(GuiScreen parent) {
		super(parent,
				new ConfigElement(ConfigHandler.config.getCategory("gui")).getChildElements(),
				StartupRef.MODID, false, false, "Play InventoryRepair Your Way!");
		titleLine2 = StartupRef.configDir.getAbsolutePath();
	}

	@Override
	public void initGui() {
		// You can add buttons and initialize fields here
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		// You can do things like create animations, draw additional elements,
		// etc. here
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		// You can process any additional buttons you may have added here
		super.actionPerformed(button);
	}
}