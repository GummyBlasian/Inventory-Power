package com.sangam1.InventoryRepair.Events.Server;

import com.sangam1.InventoryRepair.Main;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Auto_Repair_Event {
	
    @SubscribeEvent
    public void onRenderTick(TickEvent.PlayerTickEvent event) {
    	if(Minecraft.getInstance() != null) {
    		if(Minecraft.getInstance().world != null) {
    			if (!Minecraft.getInstance().world.isRemote) {
    				Auto_Repair.check_repair();
    				if(Auto_Repair.can_repair()) {
    					Main.LOGGER.info("Can!");
    					Auto_Repair.set_can_repair(false);
    					int damage = Auto_Repair.inventory().player.getHeldItemMainhand().getDamage();
    					Auto_Repair.inventory().player.getHeldItemMainhand().setDamage(damage + Auto_Repair.repair_amount());
    					Auto_Repair.inventory().decrStackSize(Auto_Repair.repair_slot(), 1);
    				} else {
    					Main.LOGGER.info("Cant!");
    				}
    			}
    		}
    	}
    }
}
