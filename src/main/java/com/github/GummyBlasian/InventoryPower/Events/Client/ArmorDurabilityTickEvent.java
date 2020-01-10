package com.github.GummyBlasian.InventoryPower.Events.Client;

import com.github.GummyBlasian.InventoryPower.GUI.GUIArmorDurability;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ArmorDurabilityTickEvent {


	// Display the item in the players main hand in a GUI
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().player;
		Minecraft mc = Minecraft.getMinecraft();

		if (player != null) {			
			
			if (!player.inventory.armorItemInSlot(0).isEmpty()) {
				GUIArmorDurability.setHelmet("Boot: " + (player.inventory.armorItemInSlot(0).getMaxDamage() - player.inventory.armorItemInSlot(0).getItemDamage()) + "/" + player.inventory.armorItemInSlot(0).getMaxDamage());
			} else {
				GUIArmorDurability.setHelmet("");
			}
			if (!player.inventory.armorItemInSlot(1).isEmpty()) {
				GUIArmorDurability.setBody("Leg: " + (player.inventory.armorItemInSlot(1).getMaxDamage() - player.inventory.armorItemInSlot(1).getItemDamage()) + "/" + player.inventory.armorItemInSlot(1).getMaxDamage());
			} else {
				GUIArmorDurability.setBody("");
			}
			if (!player.inventory.armorItemInSlot(2).isEmpty()) {
				GUIArmorDurability.setLeg("Chest: " + (player.inventory.armorItemInSlot(2).getMaxDamage() - player.inventory.armorItemInSlot(2).getItemDamage()) + "/" + player.inventory.armorItemInSlot(2).getMaxDamage());
			} else {
				GUIArmorDurability.setLeg("");
			}
			if (!player.inventory.armorItemInSlot(3).isEmpty()) {
				GUIArmorDurability.setBoot("Helmet: " + (player.inventory.armorItemInSlot(3).getMaxDamage() - player.inventory.armorItemInSlot(3).getItemDamage()) + "/" + player.inventory.armorItemInSlot(3).getMaxDamage());
			} else {
				GUIArmorDurability.setBoot("");
			}
			
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.75f, 0.75f, 1.0f);
			
			GUIArmorDurability.drawGUI(mc.fontRenderer, mc);

			GlStateManager.scale(1f, 1f, 1.0f);
			GlStateManager.popMatrix();	
		}
	}

}
