package easyinventories.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import easyinventories.ModInformation;

public class BlockSorting extends Block {

	public BlockSorting(int par1) {
		super(par1, Material.wood);
		
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(3F);
		setStepSound(soundWoodFootstep);
		setResistance(blockResistance);
	}
	
	@SideOnly(Side.CLIENT)
	private Icon blockIcon;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon("easyinventories" + ":" + ModInformation.BLOCKSORTINGTEXTURE);
	}
	
		
	/** Sets the icon for each side of the block.
     * Icons are registered in the registerIcons(IconRegister) method above
     * and declared private in the class.
     * 
     * @param side     | The side of the block ranging from 0-5
     *                 | If you wanted to place a different texture on the top of the block you would use
     *                 | if (side == 1) return topIcon else return coderDojoIcon;
     *                 | 0: Bottom
     *                 | 1: Top
     *                 | 2: North
     *                 | 3: South
     *                 | 4: West
     *                 | 5: East
     * 
     * @param metadata
     */
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return blockIcon;
	}
}
