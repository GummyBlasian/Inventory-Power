package com.github.GummyBlasian.InventoryPower.Events.Client;

import com.github.GummyBlasian.InventoryPower.GUI.GUIToolDurability;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class OnTickEvent {

	// Display the item in the players main hand in a GUI
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().player;
		if (player != null) {			
			if (player.getHeldItemMainhand() != null) {
				if (!player.getHeldItemMainhand().toString().contains("tile.air")) {
					if (player.getHeldItemMainhand().getMaxDamage() == 0) {
						GUIToolDurability.setItemInHandText(player.getHeldItemMainhand().getDisplayName(), " ");
					} else {

						double y = player.getHeldItemMainhand().getItemDamage();
						double z = player.getHeldItemMainhand().getMaxDamage();
						int x = (int) (y / z * 100);
						GUIToolDurability.setItemInHandText(
								player.getHeldItemMainhand().getDisplayName(),
								y + "/" + z + " (" + Math.round(y / z * 100) + "% Damage)");
					}
				} else {
					GUIToolDurability.setItemInHandText(" ", " ");
				}
			}
		}

	}
}
