package com.sangam1.InventoryRepair.GUI;

import com.sangam1.InventoryRepair.Events.Server.ServerGamemodeTickEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.world.GameType;

public class ButtonGUICustom extends GuiButton{
	
	private GameType type;

	public ButtonGUICustom(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, GameType mode) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		type = mode;
	}
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		ServerGamemodeTickEvent.setPlayerMode(Minecraft.getMinecraft().thePlayer, type);
		return true;
	}

}
