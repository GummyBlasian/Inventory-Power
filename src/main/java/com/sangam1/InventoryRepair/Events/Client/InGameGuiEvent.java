package com.sangam1.InventoryRepair.Events.Client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class InGameGuiEvent {

	@SubscribeEvent
	public void onRenderTickPre(RenderGameOverlayEvent.Pre event) {
		Minecraft.getInstance().gameSettings.heldItemTooltips = false;		
		if (event.getType() == ElementType.POTION_ICONS)
			event.setCanceled(true);
	}
	
}
