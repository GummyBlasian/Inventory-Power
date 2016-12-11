package com.sangam1.InventoryRepair.Events;

import com.sangam1.InventoryRepair.ConfigHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

//@SideOnly(Side.SERVER)
public class ServerTickEvent {
	
	public static EntityPlayer player;

	@SubscribeEvent
	public void EntityJoinWorldEvent(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			player = (EntityPlayer) event.getEntity();
			if (!player.worldObj.isRemote && ConfigHandler.enableItemRepair) {
				ServerTickEventOnly.setPlayer(player);
				double y = player.getHeldItemMainhand().getItemDamage();
				// System.out.println("Item damage " + y);
				double z = player.getHeldItemMainhand().getMaxDamage();
				int x = (int) (y / z * 100);
				if (x >= 10) {
					System.out.println("World is Remote!");
					int damageTimes = x / 10;
					for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
						if (player.getHeldItemMainhand().getItem().getIsRepairable(player.getHeldItemMainhand(),
								player.inventory.getStackInSlot(i))) {
							System.out
									.println("Item can repair " + player.inventory.getStackInSlot(i).getDisplayName());
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


	/*
	 * public static ItemStack repairTool(EntityPlayer player, ItemStack item,
	 * InventoryPlayer inventory, int damagePercent) {
	 * if(!player.worldObj.isRemote){ System.out.println("World is Remote!");
	 * int damageTimes = damagePercent/20; for(int i = 0; i <
	 * inventory.getSizeInventory(); i ++){
	 * if(item.getItem().getIsRepairable(item, inventory.getStackInSlot(i))){
	 * System.out.println("Item can repair " +
	 * inventory.getStackInSlot(i).getDisplayName()); inventory.decrStackSize(i,
	 * damageTimes); item.setItemDamage(0); item.setRepairCost(1); break; } }
	 * return item; } return null; }
	 */

}
