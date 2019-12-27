package com.sangam1.InventoryRepair.GUI.Handlers;

import com.sangam1.InventoryRepair.Events.Client.ArmorDurability;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class SmallIconArmorGUIHandler {

	public static void headGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		ArmorDurability.getAll();
		if(ArmorDurability.getHead_Icon() != null) {
			String armor_head_durability = ArmorDurability.getHead_Durability();

			int txtwidth_armor_head_durability = mc.fontRenderer.getStringWidth(armor_head_durability);
			mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getHead_Icon(), (int) (guiPosX - (txtwidth_armor_head_durability * (0.7/0.5)) - 18), guiPosY);
		}
	}

	public static void bodyGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		ArmorDurability.getAll();
		if(ArmorDurability.getBody_Icon() != null) {
			String armor_body_durability = ArmorDurability.getBody_Durability();

			int txtwidth_armor_body_durability = mc.fontRenderer.getStringWidth(armor_body_durability);
			mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getBody_Icon(), (int) (guiPosX - (txtwidth_armor_body_durability * (0.7/0.5)) - 18), guiPosY);
		}
	}

	public static void legGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		ArmorDurability.getAll();
		if(ArmorDurability.getLeg_Icon() != null) {
			String armor_leg_durability = ArmorDurability.getLeg_Durability();

			int txtwidth_armor_leg_durability = mc.fontRenderer.getStringWidth(armor_leg_durability);
			mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getLeg_Icon(), (int) (guiPosX - (txtwidth_armor_leg_durability * (0.7/0.5)) - 18), guiPosY);
		}
	}

	public static void bootGUI(FontRenderer textRenderer, Minecraft mc, int guiPosX, int guiPosY) {
		ArmorDurability.getAll();
		if(ArmorDurability.getBoot_Icon() != null) {
			String armor_boot_durability = ArmorDurability.getBoot_Durability();

			int txtwidth_armor_boot_durability = mc.fontRenderer.getStringWidth(armor_boot_durability);
			mc.getItemRenderer().renderItemAndEffectIntoGUI(ArmorDurability.getBoot_Icon(), (int) ((guiPosX - txtwidth_armor_boot_durability * (0.7/0.5)) - 18), guiPosY);
		}
	}

}
