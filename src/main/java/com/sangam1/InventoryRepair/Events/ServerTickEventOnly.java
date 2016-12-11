package com.sangam1.InventoryRepair.Events;

import com.sangam1.InventoryRepair.ConfigHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ServerTickEventOnly {
	
	private static EntityPlayer player;
	
	public static void setPlayer(EntityPlayer players){
		player = players;
	}
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if (player != null && ConfigHandler.enableItemRepair) {
			if (!player.worldObj.isRemote) {
				double y = player.getHeldItemMainhand().getItemDamage();
				// System.out.println("Item damage " + y);
				double z = player.getHeldItemMainhand().getMaxDamage();
				int x = (int) (y / z * 100);
				if (x >= 10) {
					//System.out.println("World is Remote! Entered Tick!");
					int damageTimes = x / 10;
					for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
						if (player.getHeldItemMainhand().getItem().getIsRepairable(player.getHeldItemMainhand(),
								player.inventory.getStackInSlot(i))) {
							//System.out
							//		.println("Item can repair " + player.inventory.getStackInSlot(i).getDisplayName());
							player.inventory.decrStackSize(i, damageTimes);
							player.getHeldItemMainhand().setItemDamage(0);
							player.getHeldItemMainhand().setRepairCost(1);
							break;
						}
					}
				}
			}
		}
	}

}
