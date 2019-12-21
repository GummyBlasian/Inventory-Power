package com.sangam1.InventoryRepair.Events.Server;

import com.sangam1.InventoryRepair.Main;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Auto_Repair_Event {

	@SubscribeEvent
	public void onRenderTick(RenderGameOverlayEvent.Post event) {
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
	
	private boolean getRepair() {
		return Minecraft.getInstance().player.getHeldItemMainhand().getItem().isRepairable(Auto_Repair.repair_with());
	}
}
