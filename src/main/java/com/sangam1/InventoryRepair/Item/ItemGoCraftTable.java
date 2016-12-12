package com.sangam1.InventoryRepair.Item;

import com.sangam1.InventoryRepair.Main;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

public class ItemGoCraftTable extends ItemBase {

	public ItemGoCraftTable(String name) {
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand playerIn) {
		if (!world.isRemote) {
			//System.out.println("Opening");
			player.openGui(Main.instance, 20, world, (int) player.posX, (int) player.posY, (int) player.posZ);
			return new ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));
		}
		//System.out.println("Nothing");
		return new ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));

		/*
		 * if (world.isRemote) { // System.out.println("Click!"); //
		 * drawGUI(world, player); return new
		 * ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn)); }
		 * System.out.println("Click!"); drawGUI(world, player); return new
		 * ActionResult(EnumActionResult.PASS, player.getHeldItem(playerIn));
		 */

	}

	private void drawGUI(World world, EntityPlayer player) {
		player.displayGui(new ItemGoCraftTable.InterfaceCraftingTable(world, player.getPosition()));
		if (world.isRemote)
			return;
		player.addStat(StatList.CRAFTING_TABLE_INTERACTION);
	}

	public static class InterfaceCraftingTable implements IInteractionObject {
		private final World world;
		private final BlockPos position;

		public InterfaceCraftingTable(World worldIn, BlockPos pos) {
			this.world = worldIn;
			this.position = pos;
		}

		/**
		 * Get the name of this object. For players this returns their username
		 */
		public String getName() {
			return "crafting_table";
		}

		/**
		 * Returns true if this thing is named
		 */
		public boolean hasCustomName() {
			return false;
		}

		/**
		 * Get the formatted ChatComponent that will be used for the sender's
		 * username in chat
		 */
		public ITextComponent getDisplayName() {
			return new TextComponentTranslation(Blocks.CRAFTING_TABLE.getUnlocalizedName() + ".name", new Object[0]);
		}

		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
			return new ContainerWorkbench(playerInventory, this.world, this.position);
		}

		public String getGuiID() {
			return "minecraft:crafting_table";
		}
	}

}
