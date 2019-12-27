package com.sangam1.InventoryRepair.proxy;

import com.sangam1.InventoryRepair.Main;
import com.sangam1.InventoryRepair.GUI.Container.PCTContainer;
import com.sangam1.InventoryRepair.Screen.PCTScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
    	
       ScreenManager.registerFactory(PCTContainer.TYPE, PCTScreen::new);

       Main.LOGGER.info(Main.MODID +" : " + "Client proxy done!");
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}