package com.sangam1.InventoryRepair.Events.Server;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AutoRepairEvent {

	private static PlayerEntity player;
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if(player != null) {
			if (!player.world.isRemote) {
				double y = player.getHeldItemMainhand().getDamage();
				//System.out.println("SERVER: Item damage " + y);
				double z = player.getHeldItemMainhand().getMaxDamage();
				int x = (int) (y / z * 100);
				if (x >= 10) {
					//Main.LOGGER.info("SERVER: World is Remote! Entered Tick!");
					int damageTimes = x / 10;
					for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
						if (player.getHeldItemMainhand().getItem().getIsRepairable(player.getHeldItemMainhand(),
								player.inventory.getStackInSlot(i))) {
							//Main.LOGGER.info("SERVER: Item can repair " + player.inventory.getStackInSlot(i).getDisplayName());
							player.inventory.decrStackSize(i, damageTimes);
							player.getHeldItemMainhand().setDamage(0);
							player.getHeldItemMainhand().setRepairCost(1);
							break;
						}
					}
				}
			}
		}
		
	}

	public static void setPlayer(PlayerEntity player) {
		AutoRepairEvent.player = player;
	}
}
