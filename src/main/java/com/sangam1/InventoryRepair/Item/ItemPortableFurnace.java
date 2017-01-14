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
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPortableFurnace extends ItemBase{
	
	private int defaultTimer;
	private int burnTimer;
	private NonNullList<ItemStack> furnaceItemStacks = NonNullList.<ItemStack>func_191197_a(3,
			ItemStack.field_190927_a);
	private int progress;

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
		this.defaultTimer = 0;
		this.progress = 0;
		this.saveNBT(stack, this.burnTimer, Keys.BT.key());
		this.saveNBT(stack, "0:" + 0, Keys.S1S.key());
		this.saveNBT(stack, 1, Keys.S1SN.key());
		this.saveNBT(stack, "0:" + 0, Keys.S2S.key());
		this.saveNBT(stack, 1, Keys.S2SN.key());
		this.saveNBT(stack, "0:" + 173, Keys.S3S.key());
		this.saveNBT(stack, 10, Keys.S3SN.key());
		furnaceItemStacks.add(0, new ItemStack(Blocks.AIR));
		furnaceItemStacks.add(1, new ItemStack(Blocks.AIR));
		furnaceItemStacks.add(2, new ItemStack(Blocks.AIR));
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		if(stack == null) return;
		if(stack.getTagCompound() == null) return;
		if(stack.getTagCompound().hasKey(Keys.BT.key())){
			tooltip.add("Burn time left: " + this.burnTimer);
		}
		if(!"0".equals(this.getNBTString(stack, Keys.S3S.key()).split(":")[1])){
			tooltip.add("Item being burned: " + this.burnTimer);
		}
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand playerIn) {
		if (!world.isRemote) {
			player.openGui(Main.instance, 22, world, (int) player.posX, (int) player.posY, (int) player.posZ);
			return new ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));
		}
		return new ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));
	}
	
	public boolean isBurning(){
		return this.burnTimer != 0;
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
	
	public void saveNBT(NonNullList<ItemStack> stackList, ItemStack stack){
		this.saveNBT(stack, this.getInfoOnStack(stackList.get(0)), Keys.S1S.key());
		this.saveNBT(stack, stackList.get(0).getItemDamage(), Keys.S1SN.key());
		this.saveNBT(stack, this.getInfoOnStack(stackList.get(1)), Keys.S2S.key());
		this.saveNBT(stack, stackList.get(1).getItemDamage(), Keys.S2SN.key());
		this.saveNBT(stack, this.getInfoOnStack(stackList.get(2)), Keys.S3S.key());
		this.saveNBT(stack, stackList.get(2).getItemDamage(), Keys.S3SN.key());
	}
	
	public String getInfoOnStack(ItemStack stack){
		Item it = stack.getItem();
		if(!Block.getBlockFromItem(it).equals(null)){
			return "0:" + Block.getIdFromBlock(Block.getBlockFromItem(it));
		}
		return "1:" + Item.getIdFromItem(it);
	}
	
	public NonNullList<ItemStack> getFurnaceStacksList(){
		return furnaceItemStacks;
	}
	
	public void setFurnaceStackSlot(int slot, ItemStack stack){
		furnaceItemStacks.set(slot, stack);
	}
	
	public void updateAllNBT(ItemStack stack){
		this.saveNBT(this.furnaceItemStacks, stack);
		this.saveNBT(stack, this.burnTimer, Keys.BT.key());
		this.saveNBT(stack, this.defaultTimer, Keys.FT.key());
		this.saveNBT(stack, this.progress, Keys.PROG.key());
	}
	
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.defaultTimer;
		case 1:
			return this.burnTimer;
		case 2:
			return this.progress;
		case 3:
			return 200;
		default:
			return 0;
		}
	}
	
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.defaultTimer = value;
			break;
		case 1:
			this.burnTimer = value;
			break;
		case 2:
			this.progress = value;
			break;
		}
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
		
	public ItemStack getItemOrBlock(String info){
		int id = Integer.valueOf(info.split(":")[1]);
		int type = Integer.valueOf(info.split(":")[0]);
		switch(type){
		case 0:
			return new ItemStack(Block.getBlockById(id), 1);
		case 1:	
			return new ItemStack(Item.getItemById(id), 1);
		}
		return null;
	}
	
	private void setupItemStackInfo(ItemStack stack){
		String info;
		int size;
		ItemStack item;
		info = this.getNBTString(stack, Keys.S1S.key());
		size = this.getNBTInt(stack, Keys.S1SN.key());
		furnaceItemStacks.set(0, saveItemBlock(info, size));
		info = this.getNBTString(stack, Keys.S2S.key());
		size = this.getNBTInt(stack, Keys.S2SN.key());
		furnaceItemStacks.set(1, saveItemBlock(info, size));
		info = this.getNBTString(stack, Keys.S3S.key());
		size = this.getNBTInt(stack, Keys.S3SN.key());
		furnaceItemStacks.set(2, saveItemBlock(info, size));
	}
	
	public ItemStack saveItemBlock(String info, int size) {
		int id = Integer.valueOf(info.split(":")[1]);
		int type = Integer.valueOf(info.split(":")[0]);
		switch(type){
		case 0:
			return new ItemStack(Block.getBlockById(id), size);
		case 1:
			return new ItemStack(Item.getItemById(id), size);
		}
		return new ItemStack(Blocks.AIR, 1);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5) {
		if(stack == null) return;
		if(!stack.hasTagCompound()) return;
		if (world.isRemote) return;
		setupItemStackInfo(stack);
		if(this.burnTimer == 0 && !this.getNBTString(stack, Keys.S2S.key()).split(":")[1].equals("0")){
			String info = this.getNBTString(stack, Keys.S2S.key());
			System.out.println(info);
			//System.out.println(info + " info " + Integer.valueOf(info)); 
			//System.out.println(sizeS + " sizeS " + Integer.valueOf(sizeS)); 			
			int size = this.getNBTInt(stack, Keys.S2SN.key());
			int id = Integer.valueOf(info.split(":")[1]);
			int type = Integer.valueOf(info.split(":")[0]);
			if(size == 0) return;
			switch(type){
			case 0:
				if(ReferenceMethods.isItemFuel(new ItemStack(Block.getBlockById(id), 1))){
					this.burnTimer = ReferenceMethods.getItemBurnTime(new ItemStack(Block.getBlockById(id), 1));	
					this.saveNBT(stack, size--, Keys.S2SN.key());
				}
				break;
			case 1:	
				if(ReferenceMethods.isItemFuel(new ItemStack(Item.getItemById(id), 1))){
					this.burnTimer = ReferenceMethods.getItemBurnTime(new ItemStack(Item.getItemById(id), 1));
					this.saveNBT(stack, size--, Keys.S2SN.key());
				}
				break;
			}
			this.defaultTimer = this.burnTimer;
			this.saveNBT(stack, this.defaultTimer, Keys.FT.key());
		}
		if(this.burnTimer > 0){
			this.burnTimer--;
			if(this.canSmelt()){
				this.progress++;
				if(this.progress == 200){
					this.smeltItem();
					this.progress = 0;
				}
			}
			this.saveNBT(furnaceItemStacks, stack);
		}
		this.saveNBT(stack, this.burnTimer, Keys.BT.key());
		this.saveNBT(stack, this.progress, Keys.PROG.key());
	}
	
	public int getInventoryStackLimit() {
		return 64;
	}
	
	private boolean canSmelt() {
		if (((ItemStack) this.furnaceItemStacks.get(0)).func_190926_b()) {
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.instance()
					.getSmeltingResult((ItemStack) this.furnaceItemStacks.get(0));

			if (itemstack.func_190926_b()) {
				return false;
			} else {
				ItemStack itemstack1 = (ItemStack) this.furnaceItemStacks.get(2);
				if (itemstack1.func_190926_b())
					return true;
				if (!itemstack1.isItemEqual(itemstack))
					return false;
				int result = itemstack1.func_190916_E() + itemstack.func_190916_E();
				return result <= getInventoryStackLimit() && result <= itemstack1.getMaxStackSize(); // Forge																							// recipes
			}
		}
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = (ItemStack) this.furnaceItemStacks.get(0);
			ItemStack itemstack1 = FurnaceRecipes.instance().getSmeltingResult(itemstack);
			ItemStack itemstack2 = (ItemStack) this.furnaceItemStacks.get(2);

			if (itemstack2.func_190926_b()) {
				this.furnaceItemStacks.set(2, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.func_190917_f(itemstack1.func_190916_E());
			}

			if (itemstack.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && itemstack.getMetadata() == 1
					&& !((ItemStack) this.furnaceItemStacks.get(1)).func_190926_b()
					&& ((ItemStack) this.furnaceItemStacks.get(1)).getItem() == Items.BUCKET) {
				this.furnaceItemStacks.set(1, new ItemStack(Items.WATER_BUCKET));
			}

			itemstack.func_190918_g(1);
		}
	}

	public ItemStack getStackInSlot(int index) {
		return furnaceItemStacks.get(index);
	}

	public ItemStack decrStackSize(int index, int count) {
		furnaceItemStacks.get(index).setItemDamage(furnaceItemStacks.get(index).getItemDamage() - count);
		return this.getStackInSlot(index);
	}

	public ItemStack removeStackFromSlot(int index) {
		return furnaceItemStacks.set(index, new ItemStack(Blocks.AIR));
	}
}
