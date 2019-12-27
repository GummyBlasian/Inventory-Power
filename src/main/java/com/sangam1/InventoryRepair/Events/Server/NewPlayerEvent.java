package com.sangam1.InventoryRepair.Events.Server;

import com.sangam1.InventoryRepair.Config.IRConfig;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NewPlayerEvent {
	public static PlayerEntity player;

	@SubscribeEvent
	public void EntityJoinWorldEvent(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			player = (PlayerEntity) event.getEntity();
			if (!player.world.isRemote && IRConfig.AutoRepair) { // && ConfigHandler.enableItemRepair
				AutoRepairEvent.setPlayer(player);
				double y = player.getHeldItemMainhand().getDamage();
				//System.out.println("Item damage " + y);
				double z = player.getHeldItemMainhand().getMaxDamage();
				int x = (int) (y / z * 100);
				if (x >= 10) {
					//System.out.println("World is Remote!");
					int damageTimes = x / 10;
					for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
						if (player.getHeldItemMainhand().getItem().getIsRepairable(player.getHeldItemMainhand(),
								player.inventory.getStackInSlot(i))) {
							//System.out.println("Item can repair " + player.inventory.getStackInSlot(i).getDisplayName());
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
}
