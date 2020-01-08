package com.github.GummyBlasian.InventoryPower.proxy;

import com.github.GummyBlasian.InventoryPower.Main;
import com.github.GummyBlasian.InventoryPower.GUI.Container.PCTContainer;
import com.github.GummyBlasian.InventoryPower.Screen.PCTScreen;

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