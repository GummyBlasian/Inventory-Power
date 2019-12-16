package com.sangam1.InventoryRepair.proxy;

import com.sangam1.InventoryRepair.Main;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {

    @Override
    public void init() {
        Main.LOGGER.info(Main.MODID +" : " + "Server Proxy done!");
    }

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public PlayerEntity getClientPlayer() {
        throw new IllegalStateException("Only run this on the client!");
    }
}