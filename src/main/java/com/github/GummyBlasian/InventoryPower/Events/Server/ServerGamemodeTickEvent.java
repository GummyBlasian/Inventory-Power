package com.github.GummyBlasian.InventoryPower.Events.Server;

import com.github.GummyBlasian.InventoryPower.Config.ConfigHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ServerGamemodeTickEvent {
	
	private static EntityPlayer player;
	private static GameType gamemode;
	private static boolean change = false;
	
	public static void setPlayerMode(EntityPlayer players, GameType mode){
		player = players;
		gamemode = mode;
		change = true;
	}
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if (player != null && ConfigHandler.enableItemRepair) {
			if (!player.world.isRemote && change) {
				System.out.println("entered");
				player.setGameType(gamemode);
				change = false;
			}
		}
	}

}
