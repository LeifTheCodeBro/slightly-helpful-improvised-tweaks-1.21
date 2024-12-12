package net.quartersalt.shit.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.quartersalt.shit.SlightlyHelpfulImprovisedTweaks;
import net.quartersalt.shit.item.custom.ModArmorItem;

public class ModItems {

    // Custom Items

    public static final Item TWINE = registerItem("twine", new Item(new Item.Settings()));
    public static final Item HARDENED_ARMADILLO_PLATE = registerItem("hardened_armadillo_plate", new Item(new Item.Settings()));

    public static final Item ARMADILLO_PLATE_CHESTPLATE = registerItem("armadillo_plate_chestplate",
            new ModArmorItem(ModArmorMaterials.ARMADILLO_PLATE, ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(55)).rarity(Rarity.UNCOMMON)));
    public static final Item ARMADILLO_PLATE_HELMET = registerItem("armadillo_plate_helmet",
            new ModArmorItem(ModArmorMaterials.ARMADILLO_PLATE, ArmorItem.Type.HELMET,
                    new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(55)).rarity(Rarity.UNCOMMON)));
    public static final Item ARMADILLO_PLATE_LEGGINGS = registerItem("armadillo_plate_leggings",
            new ModArmorItem(ModArmorMaterials.ARMADILLO_PLATE, ArmorItem.Type.LEGGINGS,
                    new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(55)).rarity(Rarity.UNCOMMON)));
    public static final Item ARMADILLO_PLATE_BOOTS = registerItem("armadillo_plate_boots",
            new ModArmorItem(ModArmorMaterials.ARMADILLO_PLATE, ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(55)).rarity(Rarity.UNCOMMON)));

    public static final Item HARDENED_TURTLE_SHELL = registerItem("hardened_turtle_shell", new Item(new Item.Settings()));

    public static final Item TURTLE_SHELL_CHESTPLATE = registerItem("turtle_shell_chestplate",
            new ModArmorItem(ModArmorMaterials.TURTLE_SHELL, ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(45)).rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item TURTLE_SHELL_HELMET = registerItem("turtle_shell_helmet",
            new ArmorItem(ModArmorMaterials.TURTLE_SHELL, ArmorItem.Type.HELMET,
                    new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(45)).rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item TURTLE_SHELL_LEGGINGS = registerItem("turtle_shell_leggings",
            new ArmorItem(ModArmorMaterials.TURTLE_SHELL, ArmorItem.Type.LEGGINGS,
                    new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(45)).rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item TURTLE_SHELL_BOOTS = registerItem("turtle_shell_boots",
            new ArmorItem(ModArmorMaterials.TURTLE_SHELL, ArmorItem.Type.BOOTS,
                    new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(45)).rarity(Rarity.UNCOMMON).fireproof()));

    public static final Item TURTLE_MASTER_HELMET = registerItem("turtle_master_helmet",
            new ModArmorItem(ModArmorMaterials.TURTLE_MASTER, ArmorItem.Type.HELMET,
                    new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(65)).rarity(Rarity.EPIC).fireproof()));
    public static final Item TURTLE_MASTER_CHESTPLATE = registerItem("turtle_master_chestplate",
            new ArmorItem(ModArmorMaterials.TURTLE_MASTER, ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(65)).rarity(Rarity.EPIC).fireproof()));
    public static final Item TURTLE_MASTER_LEGGINGS = registerItem("turtle_master_leggings",
            new ArmorItem(ModArmorMaterials.TURTLE_MASTER, ArmorItem.Type.LEGGINGS,
                    new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(65)).rarity(Rarity.EPIC).fireproof()));
    public static final Item TURTLE_MASTER_BOOTS = registerItem("turtle_master_boots",
            new ArmorItem(ModArmorMaterials.TURTLE_MASTER, ArmorItem.Type.BOOTS,
                    new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(65)).rarity(Rarity.EPIC).fireproof()));

    public static final Item SEA_ESSENCE = registerItem("sea_essence",
            new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));

    // Helper Method

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SlightlyHelpfulImprovisedTweaks.MOD_ID, name), item);
    }

    // Adding Items to Creative Menu

    public static void registerModItems() {
        SlightlyHelpfulImprovisedTweaks.LOGGER.info("Registering Mod Items for " + SlightlyHelpfulImprovisedTweaks.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(TWINE);
            entries.add(HARDENED_ARMADILLO_PLATE);
            entries.add(HARDENED_TURTLE_SHELL);
            entries.add(SEA_ESSENCE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(TURTLE_SHELL_HELMET);
            entries.add(TURTLE_SHELL_CHESTPLATE);
            entries.add(TURTLE_SHELL_LEGGINGS);
            entries.add(TURTLE_SHELL_BOOTS);
            entries.add(TURTLE_MASTER_HELMET);
            entries.add(TURTLE_MASTER_CHESTPLATE);
            entries.add(TURTLE_MASTER_LEGGINGS);
            entries.add(TURTLE_MASTER_BOOTS);
        });
    }
}
