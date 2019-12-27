package com.sangam1.InventoryRepair.Events.Client;

import java.lang.reflect.Field;
import java.util.List;

import com.sangam1.InventoryRepair.Main;
import com.sangam1.InventoryRepair.Events.Server.ServerGamemodeTickEvent;
import com.sangam1.InventoryRepair.GUI.ButtonGUICustom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.world.GameType;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class RenderModeButtonsTickEvent {
	
	@SubscribeEvent
	public void guiOpenEvent(GuiOpenEvent event){
		System.out.println(event.getGui());
		if(event.getGui() instanceof GuiInventory){
			event.setCanceled(true);
			Minecraft.getMinecraft().thePlayer.openGui(Main.instance, 21, Minecraft.getMinecraft().theWorld, (int) Minecraft.getMinecraft().thePlayer.posX, (int) Minecraft.getMinecraft().thePlayer.posY, (int) Minecraft.getMinecraft().thePlayer.posZ);
		}
	}
	
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		try
		{
			GuiScreen activeGUI = Minecraft.getMinecraft().currentScreen;

			if(activeGUI != null && activeGUI instanceof GuiInventory) //whatever GUI you want to add a/multiple button/s to
			{
				Field field = GuiScreen.class.getDeclaredField("buttonList");
				field.setAccessible(true);

				List buttonList = (List) field.get(activeGUI);
				//System.out.println("Size: " + buttonList.size());
				if(buttonList.size() < 1) //this has to be one smaller than the number of buttons it usually has, else you will keep adding your button over and oever again
				{
					buttonList.add(new ButtonGUICustom(1, 2, 5, Minecraft.getMinecraft().fontRendererObj.getStringWidth("Creative Mode") + 5,40, "Creative Mode", GameType.CREATIVE)); //you have to make a class extending GuiButton to add function to your button
				}
			}
			if(activeGUI != null && activeGUI instanceof GuiContainerCreative) //whatever GUI you want to add a/multiple button/s to
			{
				Field field = GuiScreen.class.getDeclaredField("buttonList");
				field.setAccessible(true);

				List buttonList = (List) field.get(activeGUI);
				//System.out.println("Size: " + buttonList.size());

				if(buttonList.size() < 3) //this has to be one smaller than the number of buttons it usually has, else you will keep adding your button over and oever again
				{
					buttonList.add(new GuiButton(1, 2, 5, Minecraft.getMinecraft().fontRendererObj.getStringWidth("Survival Mode") + 5, 40, "Survival Mode"){
						@Override
						public void mouseReleased(int p_146118_1_, int p_146118_2_) {
							ServerGamemodeTickEvent.setPlayerMode(Minecraft.getMinecraft().thePlayer, GameType.SURVIVAL);
						}
					});
				}
			}
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
