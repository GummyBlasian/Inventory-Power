package com.sangam1.InventoryRepair.Events.Server;

import com.sangam1.InventoryRepair.Main;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Auto_Repair_Event {

	@SubscribeEvent
	public void onRenderTick(RenderGameOverlayEvent.Post event) {
		if (Minecraft.getInstance().world.isRemote) {
			//Main.LOGGER.info("sending check");
			Auto_Repair.check_repair();
			if(Auto_Repair.can_repair() && getRepair()) {
				Main.LOGGER.info("SET: " + Auto_Repair.repair_slot());
				Auto_Repair.set_can_repair(false);
				int damage = Auto_Repair.inventory().player.getHeldItemMainhand().getDamage();
				//Auto_Repair.inventory().player.getHeldItemMainhand().setDamage(damage - Auto_Repair.repair_amount());
				//Auto_Repair.inventory().decrStackSize(Auto_Repair.repair_slot(), 1);
			}
		}
	}  
	/*
	@SubscribeEvent
	public void onServerTick1(TickEvent.ServerTickEvent event) {
		if (Minecraft.getInstance().world.isRemote) {
			//Main.LOGGER.info("sending check");
			Auto_Repair.check_repair();
			if(Auto_Repair.can_repair() && getRepair()) {
				Main.LOGGER.info("Can!");
				Auto_Repair.set_can_repair(false);
				int damage = Auto_Repair.inventory().player.getHeldItemMainhand().getDamage();
				Auto_Repair.inventory().player.getHeldItemMainhand().setDamage(damage - Auto_Repair.repair_amount());
				Auto_Repair.inventory().decrStackSize(Auto_Repair.repair_slot(), 1);
			}
		}
	}  
	*/
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if(Minecraft.getInstance().world != null) {
			if (!Minecraft.getInstance().world.isRemote) {
				PlayerEntity player = Minecraft.getInstance().player;
				double y = player.getHeldItemMainhand().getDamage();
				Main.LOGGER.info("SERVER: Item damage " + y);
				double z = player.getHeldItemMainhand().getMaxDamage();
				int x = (int) (y / z * 100);
				if (x >= 10) {
					Main.LOGGER.info("SERVER: World is Remote! Entered Tick!");
					int damageTimes = x / 10;
					for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
						if (player.getHeldItemMainhand().getItem().getIsRepairable(player.getHeldItemMainhand(),
								player.inventory.getStackInSlot(i))) {
							Main.LOGGER.info("SERVER: Item can repair " + player.inventory.getStackInSlot(i).getDisplayName());
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
	
	private boolean getRepair() {
		return Minecraft.getInstance().player.getHeldItemMainhand().getItem().isRepairable(Auto_Repair.repair_with());
	}
}
