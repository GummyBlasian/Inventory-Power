package com.sangam1.InventoryRepair.Events;

import com.sangam1.InventoryRepair.GUI.GUIToolDurability;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class OnTickEvent {

	// Display the item in the players main hand in a GUI
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().thePlayer;
		if (player != null) {			
			if (player.getHeldItemMainhand() != null) {
				if (!player.getHeldItemMainhand().toString().contains("tile.air")) {
					if (player.getHeldItemMainhand().getMaxDamage() == 0) {
						GUIToolDurability.setItemInHandText(player.getHeldItemMainhand().getDisplayName(), " ");
					} else {

						double y = player.getHeldItemMainhand().getItemDamage();
						//System.out.println("Item damage " + y);
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
