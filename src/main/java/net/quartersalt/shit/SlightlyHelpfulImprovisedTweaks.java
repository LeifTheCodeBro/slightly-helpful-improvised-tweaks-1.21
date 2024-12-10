package net.quartersalt.shit;

import net.fabricmc.api.ModInitializer;

import net.quartersalt.shit.item.ModItemGroups;
import net.quartersalt.shit.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlightlyHelpfulImprovisedTweaks implements ModInitializer {
	public static final String MOD_ID = "shit";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}