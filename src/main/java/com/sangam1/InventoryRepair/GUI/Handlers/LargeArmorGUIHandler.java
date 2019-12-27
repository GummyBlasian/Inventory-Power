package com.sangam1.InventoryRepair.GUI.Handlers;

import com.sangam1.InventoryRepair.Events.Client.ArmorDurability;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class LargeArmorGUIHandler {

	public static void handGUI (FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		ArmorDurability.getAll();
		String armor_head = ArmorDurability.getHand();
		String armor_head_durability = "";
		if(ArmorDurability.getHand_Icon() != null) {	
			if (ArmorDurability.getHand_Icon().getMaxDamage() != 0)
				armor_head_durability = ArmorDurability.getHand_Durability();

			int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
			int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);

			mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getHand_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY-35);
			textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY - 32, TextFormatting.GOLD.getColor());
			textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY - 22, TextFormatting.GOLD.getColor()); 
		}
	}

	public static void headGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		ArmorDurability.getAll();
		String armor_head = ArmorDurability.getHead();
		String armor_head_durability = ArmorDurability.getHead_Durability();

		int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);

		if(ArmorDurability.getHead_Icon() != null) {
			mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getHead_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY/2-45);
			textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY/2 - 40, TextFormatting.GOLD.getColor());
			textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY/2 - 30, TextFormatting.GOLD.getColor()); 
		}
	}

	public static void bodyGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		ArmorDurability.getAll();
		String armor_head = ArmorDurability.getBody();
		String armor_head_durability = ArmorDurability.getBody_Durability();

		int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);

		if(ArmorDurability.getBody_Icon() != null) {
			mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getBody_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY/2-25);
			textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY/2 - 20, TextFormatting.GOLD.getColor());
			textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY/2 - 10, TextFormatting.GOLD.getColor()); 
		}
	}

	public static void legGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		ArmorDurability.getAll();
		String armor_head = ArmorDurability.getLeg();
		String armor_head_durability = ArmorDurability.getLeg_Durability();

		int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);

		if(ArmorDurability.getLeg_Icon() != null) {
			mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getLeg_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY/2-5);
			textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY/2, TextFormatting.GOLD.getColor());
			textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY/2 + 10, TextFormatting.GOLD.getColor()); 
		}
	}

	public static void bootGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		ArmorDurability.getAll();
		String armor_head = ArmorDurability.getBoot();
		String armor_head_durability = ArmorDurability.getBoot_Durability();

		int txtwidth_armor_head = mc.fontRenderer.getStringWidth(armor_head);
		int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);

		if(ArmorDurability.getBoot_Icon() != null) {	
			mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getBoot_Icon(), guiPosX - txtwidth_armor_head - 20, guiPosY/2+20);
			textRenderer.drawStringWithShadow(armor_head, guiPosX - txtwidth_armor_head, guiPosY/2 + 20, TextFormatting.GOLD.getColor());
			textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY/2 + 30, TextFormatting.GOLD.getColor()); 
		}
	}

}
