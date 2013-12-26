package easyinventories.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerBlockSorting extends Container {

	private TileEntityBlockSorting sorting;

	public ContainerBlockSorting(InventoryPlayer invPlayer, TileEntityBlockSorting sorting) {
		this.sorting = sorting;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return sorting.isUseableByPlayer(entityplayer);
	}

}
