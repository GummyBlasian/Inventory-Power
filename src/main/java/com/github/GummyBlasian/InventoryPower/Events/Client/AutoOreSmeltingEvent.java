package com.github.GummyBlasian.InventoryPower.Events.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoOreSmeltingEvent {

	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().player;
		if (player != null) {
			if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().toString().contains("tile.air")) {
				ItemStack held = player.getHeldItemMainhand();
				InventoryPlayer playerInv = player.inventory;
				if (held.getItem() instanceof ItemPickaxe) {
					if(held.serializeNBT().getInteger("BurnTimeLeft") == 0){
						for (int i = 0; i < playerInv.getSizeInventory(); i++) {
							if (playerInv.getStackInSlot(i).getItem() == Items.COAL) {
								held.serializeNBT().setInteger("BurnTimeLeft",
										TileEntityFurnace.getItemBurnTime(playerInv.getStackInSlot(i)));
								break;
							}
						}						
					}
				}
			}
		}
	}

}
