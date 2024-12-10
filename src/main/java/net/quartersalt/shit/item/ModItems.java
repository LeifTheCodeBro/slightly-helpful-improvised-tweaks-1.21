package net.quartersalt.shit.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.quartersalt.shit.SlightlyHelpfulImprovisedTweaks;

public class ModItems {

    // Custom Items

    public static final Item TWINE = registerItem("twine", new Item(new Item.Settings()));
    public static final Item HARDENED_ARMADILLO_PLATE = registerItem("hardened_armadillo_plate", new Item(new Item.Settings()));
    public static final Item HARDENED_TURTLE_SHELL = registerItem("hardened_turtle_shell", new Item(new Item.Settings()));

    public static final Item TURTLE_SHELL_CHESTPLATE = registerItem("turtle_shell_chestplate",
            new ArmorItem(ModArmorMaterials.TURTLE_SHELL, ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(45))));
    public static final Item TURTLE_SHELL_HELMET = registerItem("turtle_shell_helmet",
            new ArmorItem(ModArmorMaterials.TURTLE_SHELL, ArmorItem.Type.HELMET,
                    new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(45))));
    public static final Item TURTLE_SHELL_LEGGINGS = registerItem("turtle_shell_leggings",
            new ArmorItem(ModArmorMaterials.TURTLE_SHELL, ArmorItem.Type.LEGGINGS,
                    new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(45))));
    public static final Item TURTLE_SHELL_BOOTS = registerItem("turtle_shell_boots",
            new ArmorItem(ModArmorMaterials.TURTLE_SHELL, ArmorItem.Type.BOOTS,
                    new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(45))));

    public static final Item TURTLE_ESSENCE = registerItem("turtle_essence",
            new Item(new Item.Settings()));

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
            entries.add(TURTLE_ESSENCE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(TURTLE_SHELL_HELMET);
            entries.add(TURTLE_SHELL_CHESTPLATE);
            entries.add(TURTLE_SHELL_LEGGINGS);
            entries.add(TURTLE_SHELL_BOOTS);
        });
    }
}
