package com.sangam1.InventoryRepair.GUI.Handlers;

import com.sangam1.InventoryRepair.Events.Client.ArmorDurability;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class SmallArmorGUIHandler {

	public static void headGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		if(ArmorDurability.getHead_Icon() != null) {
			String armor_head_durability = ArmorDurability.getHead_Durability();

			int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);
			//mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getHead_Icon(), guiPosX - txtwidth_armor_head_durability - 15, guiPosY);
			textRenderer.drawStringWithShadow(armor_head_durability, guiPosX - txtwidth_armor_head_durability, guiPosY - 2, TextFormatting.GOLD.getColor()); 
		}
	}

	public static void bodyGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		if(ArmorDurability.getBody_Icon() != null) {
			String armor_body_durability = ArmorDurability.getBody_Durability();

			int txtwidth_armor_body_durability = mc.fontRenderer.getStringWidth(armor_body_durability);
			//mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getBody_Icon(), guiPosX - txtwidth_armor_body_durability - 15, guiPosY);
			textRenderer.drawStringWithShadow(armor_body_durability, guiPosX - txtwidth_armor_body_durability, guiPosY - 2, TextFormatting.GOLD.getColor()); 
		}
	}

	public static void legGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		if(ArmorDurability.getLeg_Icon() != null) {
			String armor_leg_durability = ArmorDurability.getLeg_Durability();

			int txtwidth_armor_leg_durability = mc.fontRenderer.getStringWidth(armor_leg_durability);
			//mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getLeg_Icon(), guiPosX - txtwidth_armor_leg_durability - 15, guiPosY);
			textRenderer.drawStringWithShadow(armor_leg_durability, guiPosX - txtwidth_armor_leg_durability, guiPosY - 2, TextFormatting.GOLD.getColor()); 
		}
	}

	public static void bootGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		if(ArmorDurability.getBoot_Icon() != null) {
			String armor_boot_durability = ArmorDurability.getBoot_Durability();

			int txtwidth_armor_boot_durability = mc.fontRenderer.getStringWidth(armor_boot_durability);
			//mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getBoot_Icon(), guiPosX - txtwidth_armor_boot_durability - 15, guiPosY);
			textRenderer.drawStringWithShadow(armor_boot_durability, guiPosX - txtwidth_armor_boot_durability - 2, guiPosY, TextFormatting.GOLD.getColor()); 
		}
	}

}
