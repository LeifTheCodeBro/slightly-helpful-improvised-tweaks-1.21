package net.quartersalt.shit;

import net.fabricmc.api.ClientModInitializer;
import net.quartersalt.shit.item.ModArmorMaterials;
import net.quartersalt.shit.item.ModItemGroups;
import net.quartersalt.shit.item.ModItems;

public class SHITClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
        ModArmorMaterials.registerArmorMaterial();
    }
}
