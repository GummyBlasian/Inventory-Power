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
					// Testing :
					// System.out.println(player.getHeldItemMainhand().toString()
					// + " " +
					// player.getHeldItemMainhand().getMaxItemUseDuration() + "
					// " +
					// player.getHeldItemMainhand().getItem().getUnlocalizedName()
					// + " " + player.getHeldItemMainhand().getMaxDamage() + " "
					// + player.getHeldItemMainhand().getItemDamage());
					// System.out.println(player.inventory.getCurrentItem() + "
					// " + player.inventory.currentItem);
					// System.out.println(player.inventory.getStackInSlot(player.inventory.currentItem));
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
						// if(player.getHeldItemMainhand().getRepairCost() !=
						// 0){
						/*
						if (x >= 20) {
							ItemStack itemFixed = ServerTickEvent.repairTool(player, player.getHeldItemMainhand(), player.inventory, x);
							if(itemFixed != null){
								System.out.println("FIXED!" + itemFixed.getItemDamage() + player.inventory.inventoryChanged);
								player.inventory.removeStackFromSlot(player.inventory.currentItem);
								player.inventory.inventoryChanged = true;
								System.out.println("There is in hand " + player.inventory.getCurrentItem().getDisplayName());
								player.inventory.setInventorySlotContents(player.inventory.currentItem, itemFixed);
								player.inventory.inventoryChanged = true;
								System.out.println("Item is now " + player.inventory.getCurrentItem().getDisplayName() + " " + player.inventory.getCurrentItem().getItemDamage());
								player.inventory.inventoryChanged = false;
								NBTTagCompound tagz = player.inventory.getCurrentItem().getTagCompound();
								if(tagz != null){
									System.out.println("NOT NULL");
									System.out.println("HERE! " + (float)tagz.getShort("Damage") + " " + tagz.getByte("Count"));
									player.inventory.getCurrentItem().getTagCompound().setShort("Damage", (short) 0);
								}
							}						
						}
						*/

						// System.out.println(ItemTool.REGISTRY.getKeys().size());
						// System.out.println(Item.ToolMaterial.values().length);
					}
					// old code: " " +
					// Item.REGISTRY.getNameForObject(player.getHeldItemMainhand().getItem())
				} else {
					GUIToolDurability.setItemInHandText(" ", " ");
				}
			}
		}

	}
}
