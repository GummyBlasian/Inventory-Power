package com.sangam1.InventoryRepair.Events.Server;

import com.sangam1.InventoryRepair.Main;
import com.sangam1.InventoryRepair.Events.Client.Armor_Durability;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Auto_Repair {

	private static ItemStack itemstack;
	private static Item item;
	private static PlayerInventory playerInv;

	private static Boolean can_repair = false;
	private static int repair_slot;
	private static ItemStack repair_with;
	private static int repair_percentage;

	public static void check_repair() {
		//if(can_repair) {
		//Main.LOGGER.info("lets try");
		itemstack = Armor_Durability.getHand_Icon();
		if(itemstack != null) {
			item = itemstack.getItem();
			playerInv= Minecraft.getInstance().player.inventory;

			int i = 0;
			double max = itemstack.getMaxDamage();
			double current = itemstack.getDamage();
			double x =current/max;
			repair_percentage = (int) Math.rint(max * 0.1);
			if(x > 0.1) {
				for(i = 0; i < playerInv.getSizeInventory(); i ++) {
					if(item.isRepairable(playerInv.getStackInSlot(i)) && playerInv.getStackInSlot(i) != new ItemStack(Items.AIR)) {
						can_repair = true;
						repair_slot = i;
						Main.LOGGER.info("a " + i);
						Main.LOGGER.info("b " + (repair_with = playerInv.getStackInSlot(repair_slot)));
						break;
					}
				}
				if(can_repair) {
					Main.LOGGER.info("c " + playerInv.getStackInSlot(repair_slot));
					Main.LOGGER.info("d " + repair_slot);
					Main.LOGGER.info("e " + repair_with);
					return;
				}
			}
		}
		can_repair = false;
		repair_slot = 0;
		repair_with = null;
	}

	public static void set_can_repair(boolean set) {
		can_repair = false;
	}

	public static int repair_amount() {
		return repair_percentage;
	}

	public static PlayerInventory inventory() {
		return playerInv;
	}

	public static Boolean can_repair() {
		return can_repair;
	}

	public static int repair_slot() {
		return repair_slot;
	}

	public static ItemStack repair_with() {
		return repair_with;
	}
}
