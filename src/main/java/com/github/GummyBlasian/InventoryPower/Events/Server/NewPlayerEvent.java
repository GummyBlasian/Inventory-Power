package com.github.GummyBlasian.InventoryPower.Events.Server;

import com.github.GummyBlasian.InventoryPower.Config.IRConfig;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NewPlayerEvent {
	public static PlayerEntity player;

	@SubscribeEvent
	public void EntityJoinWorldEvent(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			player = (PlayerEntity) event.getEntity();
			if (!player.world.isRemote && IRConfig.AutoRepair) {
				AutoRepairEvent.setPlayer(player);
				double y = player.getHeldItemMainhand().getDamage();
				double z = player.getHeldItemMainhand().getMaxDamage();
				int x = (int) (y / z * 100);
				if (x >= 10) {
					int damageTimes = x / 10;
					for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
						if (player.getHeldItemMainhand().getItem().getIsRepairable(player.getHeldItemMainhand(),
								player.inventory.getStackInSlot(i))) {
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
