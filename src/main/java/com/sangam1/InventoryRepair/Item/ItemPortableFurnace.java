package com.sangam1.InventoryRepair.Item;

import java.util.List;

import com.sangam1.InventoryRepair.Main;
import com.sangam1.InventoryRepair.API.ReferenceMethods;
import com.sangam1.InventoryRepair.References.Keys;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPortableFurnace extends ItemBase{
	
	private int burnTimer;

	public ItemPortableFurnace(String name) {
		super(name);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
		if(stack == null) return;
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		this.burnTimer = 0;
		this.saveNBT(stack, this.burnTimer, Keys.BT.key());
		this.saveNBT(stack, "0:" + 0, Keys.S1S.key());
		this.saveNBT(stack, 1, Keys.S1SN.key());
		this.saveNBT(stack, "0:" + 0, Keys.S2S.key());
		this.saveNBT(stack, 1, Keys.S2SN.key());
		this.saveNBT(stack, "0:" + 173, Keys.S3S.key());
		this.saveNBT(stack, 10, Keys.S3SN.key());
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		if(stack == null) return;
		if(stack.getTagCompound() == null) return;
		if(stack.getTagCompound().hasKey(Keys.BT.key())){
			tooltip.add("Burn Time Left: " + this.burnTimer);			
		}
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand playerIn) {
		if (!world.isRemote) {
			player.openGui(Main.instance, 21, world, (int) player.posX, (int) player.posY, (int) player.posZ);
			return new ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));
		}
		return new ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));
	}

	public void saveNBT(ItemStack stack,NBTTagCompound nbt){		
		stack.getTagCompound().merge(nbt);;
	}
	
	public void saveNBT(ItemStack stack, int value, String key){		
		stack.getTagCompound().setInteger(key, value);
	}
	
	public void saveNBT(ItemStack stack, String value, String key){		
		stack.getTagCompound().setString(key, value);
	}
	
	public NBTTagCompound getNBTTag(ItemStack stack){
		return stack.getTagCompound();
	}
	
	public int getNBTInt(ItemStack stack, String key){
		return stack.getTagCompound().getInteger(key);
	}
	
	public String getNBTString(ItemStack stack, String key){
		return stack.getTagCompound().getString(key);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5) {
		if(stack == null) return;
		if(!stack.hasTagCompound()) return;
		if(this.burnTimer == 0){
			String info = this.getNBTString(stack, Keys.S3S.key());
			System.out.println(info);
			//System.out.println(info + " info " + Integer.valueOf(info)); 
			//System.out.println(sizeS + " sizeS " + Integer.valueOf(sizeS)); 			
			int size = this.getNBTInt(stack, Keys.S3SN.key());
			int id = Integer.valueOf(info.split(":")[1]);
			int type = Integer.valueOf(info.split(":")[0]);
			if(size == 0) return;
			switch(type){
			case 0:
				if(ReferenceMethods.isItemFuel(new ItemStack(Block.getBlockById(id), 1))){
					this.burnTimer = ReferenceMethods.getItemBurnTime(new ItemStack(Block.getBlockById(id), 1));	
					//this.saveNBT(stack, size--, Keys.S3SN.key());
				}
				break;
			case 1:	
				if(ReferenceMethods.isItemFuel(new ItemStack(Item.getItemById(id), 1))){
					this.burnTimer = ReferenceMethods.getItemBurnTime(new ItemStack(Item.getItemById(id), 1));
					//this.saveNBT(stack, size--, Keys.S3SN.key());
				}
				break;
			}
		}
		if(this.burnTimer > 0){
			this.burnTimer--;
		}
	}
}
