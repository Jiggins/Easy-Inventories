package easyinventories.config;

import java.io.File;

import easyinventories.ModInformation;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	private static int nextId;

	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();

		//Blocks
		ModInformation.SORTING_ID = config.getBlock(ModInformation.BLOCKSORTING_KEY, ModInformation.DEFAULT_BLOCK_ID).getInt();
		nextId = ModInformation.SORTING_ID;
		config.save();
	}

	public static int nextID() {
		return ++nextId;
	}

}
