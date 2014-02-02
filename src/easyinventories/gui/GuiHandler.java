package easyinventories.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import easyinventories.EasyInventories;
import easyinventories.blocks.ContainerBlockSorting;
import easyinventories.blocks.TileEntityBlockSorting;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
		NetworkRegistry.instance().registerGuiHandler(EasyInventories.easyInventories, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case 0:
				TileEntity te = world.getBlockTileEntity(x, y, z);
				if (te != null && te instanceof TileEntityBlockSorting) {
					return new ContainerBlockSorting(player.inventory, (TileEntityBlockSorting)te);
				}
				break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case 0:
				TileEntity te = world.getBlockTileEntity(x, y, z);
				if (te != null && te instanceof TileEntityBlockSorting) {
					return new GuiBlockSorting(player.inventory, (TileEntityBlockSorting)te);
				}
				break;
		}
		return null;
	}

}
