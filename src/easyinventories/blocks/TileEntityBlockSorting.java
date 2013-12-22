package easyinventories.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBlockSorting extends TileEntity implements ISidedInventory {

	private ItemStack [] contents;

	public TileEntityBlockSorting() {
		contents = new ItemStack [36];
	}

	public ItemStack [] getContents() {
		return contents;
	}

	//ISidedInventory Methods

	@Override
	public int getSizeInventory() {
		return 36;
	}


	@Override
	public ItemStack getStackInSlot(int i) {
		return contents[i];
	}


	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (contents[i] == null) return null;

		ItemStack itemStack;

		if (this.contents[i].stackSize <= j) {
			itemStack = this.contents[i];
			this.contents[i] = null;
			return itemStack;
		}

		itemStack = this.contents[i].splitStack(j);

		if (this.contents[i].stackSize == 0) this.contents[i] = null;


		
		return itemStack;
	}


	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return this.contents[i];
	}


	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.contents[i] = itemstack;
		
	}

	@Override
	public String getInvName() {
		return "Sorting Block";
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Create gui first.
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}


	@Override
	public void onInventoryChanged() {
		// TODO Auto-generated method stub
		// I have no idea what this is meant to do
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}


	@Override
	public void openChest() {}


	@Override
	public void closeChest() {}


	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return new int [] {0,1,2,3,4,5};
	}

	@Override
	/**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		// TODO Add gui first
		return true;
	}


	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// TODO add gui first.
		return true;
	}
}