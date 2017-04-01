package com.sangam1.InventoryRepair.Events;

import com.sangam1.InventoryRepair.GUI.GUIArmorDurability;
import com.sangam1.InventoryRepair.GUI.GUIToolDurability;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ArmorDurabilityTickEvent {
	
	// Display the item in the players main hand in a GUI
		@SubscribeEvent
		public void onRenderTick(TickEvent.RenderTickEvent event) {
			EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().thePlayer;
			if (player != null) {
					if (!player.inventory.armorItemInSlot(0).toString().contains("tile.air")) {
						System.out.println(player.inventory.armorItemInSlot(0).toString());
						GUIArmorDurability.setHelmet(player.inventory.armorItemInSlot(0).getItemDamage() + "/" + player.inventory.armorItemInSlot(0).getMaxDamage());
					}
					if (!player.inventory.armorItemInSlot(1).toString().contains("tile.air")) {
						System.out.println(player.inventory.armorItemInSlot(1).toString());
						GUIArmorDurability.setHelmet(player.inventory.armorItemInSlot(1).getItemDamage() + "/" + player.inventory.armorItemInSlot(1).getMaxDamage());
					}
					if (!player.inventory.armorItemInSlot(2).toString().contains("tile.air")) {
						System.out.println(player.inventory.armorItemInSlot(2).toString());
						GUIArmorDurability.setHelmet(player.inventory.armorItemInSlot(2).getItemDamage() + "/" + player.inventory.armorItemInSlot(2).getMaxDamage());
					}
					if (!player.inventory.armorItemInSlot(3).toString().contains("tile.air")) {
						System.out.println(player.inventory.armorItemInSlot(3).toString());
						GUIArmorDurability.setHelmet(player.inventory.armorItemInSlot(3).getItemDamage() + "/" + player.inventory.armorItemInSlot(3).getMaxDamage());
					}
			}
		}

}
