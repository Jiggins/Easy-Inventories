package easyinventories.blocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBlockSorting extends GuiContainer {

	static public final ResourceLocation texture = new ResourceLocation("easyinventories", "textures/gui/SortingGui.png");

	public GuiBlockSorting(InventoryPlayer invPlayer, TileEntityBlockSorting sorting) {
		super(new ContainerBlockSorting(invPlayer, sorting));
		
		xSize = 176;
		ySize = 184;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
