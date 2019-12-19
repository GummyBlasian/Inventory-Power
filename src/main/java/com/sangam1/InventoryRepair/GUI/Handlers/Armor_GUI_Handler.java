package com.sangam1.InventoryRepair.GUI.Handlers;

import com.sangam1.InventoryRepair.Events.Client.Armor_Durability;
import com.sangam1.InventoryRepair.Events.Client.Looking_At;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class Armor_GUI_Handler {

	public static void handGUI (FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {		
		String armor_head = Armor_Durability.getHand();
		String armor_head_durability = Armor_Durability.getHand_Durability();

		int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);
		int txtwidth_can_mine = mc.fontRenderer.getStringWidth("can mine");
		if(Armor_Durability.getHand_Icon() != null)
			mc.getItemRenderer().renderItemAndEffectIntoGUI(Armor_Durability.getHand_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY-20);
		textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY - 17, TextFormatting.GOLD.getColor());
		textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY - 7, TextFormatting.GOLD.getColor()); 
		if (Looking_At.isCanMine())
			textRenderer.drawStringWithShadow("can mine", guiPosX - txtwidth_can_mine, guiPosY + 5 , TextFormatting.GOLD.getColor()); 
	}

	public static void headGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		String armor_head = Armor_Durability.getHead();
		String armor_head_durability = Armor_Durability.getHead_Durability();

		int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);
		if(Armor_Durability.getHead_Icon() != null)
			mc.getItemRenderer().renderItemAndEffectIntoGUI(Armor_Durability.getHead_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY/2-45);
		textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY/2 - 40, TextFormatting.GOLD.getColor());
		textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY/2 - 30, TextFormatting.GOLD.getColor()); 
	}

	public static void bodyGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		String armor_head = Armor_Durability.getBody();
		String armor_head_durability = Armor_Durability.getBody_Durability();

		int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);
		if(Armor_Durability.getBody_Icon() != null)
			mc.getItemRenderer().renderItemAndEffectIntoGUI(Armor_Durability.getBody_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY/2-25);
		textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY/2 - 20, TextFormatting.GOLD.getColor());
		textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY/2 - 10, TextFormatting.GOLD.getColor()); 
	}

	public static void legGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		String armor_head = Armor_Durability.getLeg();
		String armor_head_durability = Armor_Durability.getLeg_Durability();

		int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);
		if(Armor_Durability.getLeg_Icon() != null)
			mc.getItemRenderer().renderItemAndEffectIntoGUI(Armor_Durability.getLeg_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY/2-5);
		textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY/2, TextFormatting.GOLD.getColor());
		textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY/2 + 10, TextFormatting.GOLD.getColor()); 
	}

	public static void bootGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		String armor_head = Armor_Durability.getBoot();
		String armor_head_durability = Armor_Durability.getBoot_Durability();

		int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);
		if(Armor_Durability.getBoot_Icon() != null)
			mc.getItemRenderer().renderItemAndEffectIntoGUI(Armor_Durability.getBoot_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY/2+20);
		textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY/2 + 20, TextFormatting.GOLD.getColor());
		textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY/2 + 30, TextFormatting.GOLD.getColor()); 
	}

}
