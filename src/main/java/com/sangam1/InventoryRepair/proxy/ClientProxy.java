package com.sangam1.InventoryRepair.proxy;

import com.sangam1.InventoryRepair.References.ContainerList;
import com.sangam1.InventoryRepair.Screen.PCT_Screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
       // ScreenManager.registerFactory(ContainerList.PCT_CONTAINER, PCT_Screen::new);
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