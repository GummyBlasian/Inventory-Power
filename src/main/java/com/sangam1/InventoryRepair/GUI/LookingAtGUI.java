package com.sangam1.InventoryRepair.GUI;

import java.util.Random;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sangam1.InventoryRepair.Main;
import com.sangam1.InventoryRepair.Config.IRConfig;
import com.sangam1.InventoryRepair.Events.Client.LookingAt;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiUtils;

@OnlyIn(Dist.CLIENT)
public class LookingAtGUI{

	private final Minecraft mc = Minecraft.getInstance();

	private static final Random random = new Random();

	private int guiPosX = mc.mainWindow.getScaledWidth();
	private int guiPosY = mc.mainWindow.getScaledHeight() - 20;

	private long hours = 0;
	private long mins = 0;
	private long days = 0;
	private String amOrPm = " AM";

	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onRenderTick(RenderGameOverlayEvent.Pre event) {

		FontRenderer textRenderer = mc.fontRenderer;

		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL)
			return;

		if (mc.currentScreen instanceof ChatScreen)
			return;

		LookingAt.get_data();

		String looking_at = LookingAt.getLookingAt();
		String made_by = LookingAt.getMadeBy();
		String harvest_level = "Harvest level: " + LookingAt.getHarvestLevel();
		String biome = "Biome: " + LookingAt.getBiome();
		String can_mine = "Can mine: " + LookingAt.isCanMine();
		String dimension= "Dimension: " + LookingAt.getDimension().getType().getRegistryName().toString();
		String weather = "Weather: " + LookingAt.isRaining();
		
		getTime();

		String day =  "Day: " + days;
		String time = String.format("%02d", hours) + ":" + String.format("%02d", mins) + amOrPm;
		
		if (Minecraft.getInstance().world.dimension.getType().getId() == 1) {
			String alt_text = "";
			for (int i = 0; i < looking_at.length(); i++) {
				alt_text = alt_text.concat(Character.isDigit(looking_at.charAt(i)) ? ""+random.nextInt(10) : ""+looking_at.charAt(i));
			}

			looking_at = alt_text;
		}

		int txt1width = mc.fontRenderer.getStringWidth(looking_at);
		int txt2width = mc.fontRenderer.getStringWidth(made_by);
		int txt3width = mc.fontRenderer.getStringWidth(harvest_level);	   
		int txt4width = mc.fontRenderer.getStringWidth(biome);
		int txt5width = mc.fontRenderer.getStringWidth(dimension);
		int txt6width = mc.fontRenderer.getStringWidth(weather);

		float scale = (float) 1;

		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.enableAlphaTest();	         
		GlStateManager.alphaFunc(516, 0.1F);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.popMatrix();

		if (IRConfig.TimeHUD) {
			GlStateManager.pushMatrix();
			GlStateManager.scalef(scale, scale, scale);	        
			mc.getTextureManager().bindTexture(new ResourceLocation(Main.MODID, "textures/gui/clock_hud_rect.png"));//Render Background
			GlStateManager.color3f(1.0F, 1.0F, 1.0F);
			GuiUtils.drawTexturedModalRect(0, guiPosY-10, 0, 0, 90, 28, 0F); 
			mc.getItemRenderer().renderItemAndEffectIntoGUI(Main.Clock, 5, guiPosY-4);//Render Clock
			GlStateManager.popMatrix();
			
			GlStateManager.pushMatrix();
			GlStateManager.scalef(0.8f, 0.8f, scale);	
			textRenderer.drawStringWithShadow(day, 30, (float) ((guiPosY*1.25) - 3), TextFormatting.GOLD.getColor()); //Day
			textRenderer.drawStringWithShadow(time, 30, (float) ((guiPosY*1.25) + 7), TextFormatting.GOLD.getColor()); //Time
			GlStateManager.popMatrix();
		}

		GlStateManager.pushMatrix();	        
		GlStateManager.disableAlphaTest();
		RenderHelper.enableGUIStandardItemLighting();	         
		GlStateManager.popMatrix();

		if(IRConfig.LookingAtHUD) {
			GlStateManager.pushMatrix();	         
			GlStateManager.scalef(scale, scale, scale);
			textRenderer.drawStringWithShadow(looking_at, guiPosX/2 - txt1width/2, 2, TextFormatting.GOLD.getColor()); //Looking at
			textRenderer.drawStringWithShadow(made_by, guiPosX/2 - txt2width/2, 12, TextFormatting.GOLD.getColor()); //Made by
			GlStateManager.popMatrix();
		}

		if(IRConfig.DataHUD) {
			GlStateManager.pushMatrix();
			GlStateManager.scalef(0.8f, 0.8f, scale);
			textRenderer.drawStringWithShadow(harvest_level, 4, (float) ((guiPosY*1.25)/2 - 2), TextFormatting.GOLD.getColor()); //Harvest level
			textRenderer.drawStringWithShadow(biome, 4, (float)(guiPosY*1.25)/2 + 10, TextFormatting.GOLD.getColor()); //Biome
			textRenderer.drawStringWithShadow(can_mine, 4, (float) ((guiPosY*1.25)/2 + 22), TextFormatting.GOLD.getColor()); //Can Mine
			textRenderer.drawStringWithShadow(dimension, 4, (float) ((guiPosY*1.25)/2 + 34), TextFormatting.GOLD.getColor()); //Dimension
			textRenderer.drawStringWithShadow(weather, 4, (float) ((guiPosY*1.25)/2 + 46), TextFormatting.GOLD.getColor()); //Weather
			GlStateManager.popMatrix();
		}

		GlStateManager.pushMatrix();
		GlStateManager.scalef(1, 1, 1);
		GlStateManager.popMatrix();
	}

	private void getTime() {
		long game_time = 0;
		if(mc.world != null)
			game_time = mc.world.getGameTime();

		long sec_tick = game_time;
		long min_tick = sec_tick/60;
		long hour_tick = min_tick/60;
		long day_tick = hour_tick/24;

		long hours = hour_tick - (day_tick*24);
		long mins = min_tick - (hour_tick*60);

		if (hours <= 12) {
			this.hours = hours;
			this.mins = mins;
			this.amOrPm = " PM";
		} else {
			this.days = day_tick + 1;
			this.hours = hours - 12;
			this.mins = mins;
			this.amOrPm = " AM";
		}
	}


}
