package com.sangam1.InventoryRepair.Item;

import com.sangam1.InventoryRepair.Main;
import com.sangam1.InventoryRepair.GUI.Container.EntityGoFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGoSmeltTool extends ItemBase implements IInventory {

	private EntityGoFurnace furnace;
	private static final int[] SLOTS_TOP = new int[] { 0 };
	private static final int[] SLOTS_BOTTOM = new int[] { 2, 1 };
	private static final int[] SLOTS_SIDES = new int[] { 1 };
	private NonNullList<ItemStack> furnaceItemStacks = NonNullList.<ItemStack>func_191197_a(3,
			ItemStack.field_190927_a);
	/** The number of ticks that the furnace will keep burning */
	private int furnaceBurnTime;
	/**
	 * The number of ticks that a fresh copy of the currently-burning item would
	 * keep the furnace burning for
	 */
	private int currentItemBurnTime;
	private int cookTime;
	private int totalCookTime;
	private String furnaceCustomName;
	private ItemGoSmeltTool tool;

	public ItemGoSmeltTool(String name) {
		super(name);
	}

	public IInventory getInventory() {
		return this;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand playerIn) {
		if (!world.isRemote) {
			// System.out.println("Opening");
			// if(furnace instanceof ){
			player.openGui(Main.instance, 21, world, (int) player.posX, (int) player.posY, (int) player.posZ);
			return new ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));
			// }
		}
		// System.out.println("Nothing");
		return new ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World world, Entity par3Entity, int par4, boolean par5) {
		boolean flag = this.isBurning();
		boolean flag1 = false;
		if (this.isBurning()) {
			--this.furnaceBurnTime;
		}

		if (!world.isRemote) {
			//System.out.println("Hello");
			ItemStack itemstack = (ItemStack) this.furnaceItemStacks.get(1);

			if (this.isBurning()
					|| !itemstack.func_190926_b() && !((ItemStack) this.furnaceItemStacks.get(0)).func_190926_b()) {
				if (!this.isBurning() && this.canSmelt()) {
					this.furnaceBurnTime = getItemBurnTime(itemstack);
					this.currentItemBurnTime = this.furnaceBurnTime;

					if (this.isBurning()) {
						flag1 = true;

						if (!itemstack.func_190926_b()) {
							Item item = itemstack.getItem();
							itemstack.func_190918_g(1);

							if (itemstack.func_190926_b()) {
								ItemStack item1 = item.getContainerItem(itemstack);
								this.furnaceItemStacks.set(1, item1);
							}
						}
					}
				}

				if (this.isBurning() && this.canSmelt()) {
					++this.cookTime;

					if (this.cookTime == this.totalCookTime) {
						this.cookTime = 0;
						this.totalCookTime = this.getCookTime((ItemStack) this.furnaceItemStacks.get(0));
						this.smeltItem();
						flag1 = true;
					}
				} else {
					this.cookTime = 0;
				}
			} else if (!this.isBurning() && this.cookTime > 0) {
				this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
			}

			if (flag != this.isBurning() && tool != null) {
				flag1 = true;
			}
			writeNBT();
		}

		if (flag1) {
			// this.markDirty();
		}
	}

	public void writeNBT() {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("BurnTime", (short) this.furnaceBurnTime);
		compound.setInteger("CookTime", (short) this.cookTime);
		compound.setInteger("CookTimeTotal", (short) this.totalCookTime);
		ItemStackHelper.func_191282_a(compound, this.furnaceItemStacks);
		this.updateItemStackNBT(compound);
	}

	public ItemStack getStackInSlot(int index) {
		return (ItemStack) this.furnaceItemStacks.get(index);
	}

	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.furnaceItemStacks, index, count);
	}

	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.furnaceItemStacks, index);
	}

	public void setInventorySlotContents(int index, ItemStack stack) {
		//if(Minecraft.getMinecraft().theWorld.isRemote) return;
		ItemStack itemstack = (ItemStack) this.furnaceItemStacks.get(index);
		boolean flag = !stack.func_190926_b() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.furnaceItemStacks.set(index, stack);

		if (stack.func_190916_E() > this.getInventoryStackLimit()) {
			stack.func_190920_e(this.getInventoryStackLimit());
		}

		if (index == 0 && !flag) {
			this.totalCookTime = this.getCookTime(stack);
			this.cookTime = 0;
		}
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	/**
	 * Furnace isBurning
	 */
	public boolean isBurning() {
		return this.furnaceBurnTime > 0;
	}

	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory) {
		return inventory.getField(0) > 0;
	}

	public int getCookTime(ItemStack stack) {
		return 200;
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

	public static int getItemBurnTime(ItemStack stack) {
		if (stack.func_190926_b()) {
			return 0;
		} else {
			Item item = stack.getItem();

			if (item instanceof net.minecraft.item.ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
				Block block = Block.getBlockFromItem(item);

				if (block == Blocks.WOODEN_SLAB) {
					return 150;
				}

				if (block.getDefaultState().getMaterial() == Material.WOOD) {
					return 300;
				}

				if (block == Blocks.COAL_BLOCK) {
					return 16000;
				}
			}

			if (item instanceof ItemTool && "WOOD".equals(((ItemTool) item).getToolMaterialName()))
				return 200;
			if (item instanceof ItemSword && "WOOD".equals(((ItemSword) item).getToolMaterialName()))
				return 200;
			if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe) item).getMaterialName()))
				return 200;
			if (item == Items.STICK)
				return 100;
			if (item == Items.COAL)
				return 1600;
			if (item == Items.LAVA_BUCKET)
				return 20000;
			if (item == Item.getItemFromBlock(Blocks.SAPLING))
				return 100;
			if (item == Items.BLAZE_ROD)
				return 2400;
			return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(stack);
		}
	}

	public static boolean isItemFuel(ItemStack stack) {
		/**
		 * Returns the number of ticks that the supplied fuel item will keep the
		 * furnace burning, or 0 if the item isn't fuel
		 */
		return getItemBurnTime(stack) > 0;
	}

	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 2) {
			return false;
		} else if (index != 1) {
			return true;
		} else {
			ItemStack itemstack = (ItemStack) this.furnaceItemStacks.get(1);
			return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && itemstack.getItem() != Items.BUCKET;
		}
	}

	public String getGuiID() {
		return "minecraft:furnace";
	}

	public int getField(int id) {
		switch (id) {
		case 0:
			return this.furnaceBurnTime;
		case 1:
			return this.currentItemBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.furnaceBurnTime = value;
			break;
		case 1:
			this.currentItemBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
		}
	}

	public int getFieldCount() {
		return 4;
	}

	public void clear() {
		this.furnaceItemStacks.clear();
	}
	
	/*
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new PortableFurnaceContainer(playerInventory, this);
    }
	 */
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.furnaceCustomName : "portableFurnace";
	}

	@Override
	public boolean hasCustomName() {
		return this.furnaceCustomName != null && !this.furnaceCustomName.isEmpty();
	}

	@Override
	public ITextComponent getDisplayName() {
		//return this.getDisplayName();
		return null;
	}

	@Override
	public int getSizeInventory() {
		return this.furnaceItemStacks.size();
	}

	@Override
	public boolean func_191420_l() {
		for (ItemStack itemstack : this.furnaceItemStacks) {
			if (!itemstack.func_190926_b()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void markDirty() {

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

}
