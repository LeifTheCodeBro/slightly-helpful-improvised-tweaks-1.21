package net.quartersalt.shit.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.quartersalt.shit.SlightlyHelpfulImprovisedTweaks;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> ARMADILLO_PLATE = registerArmorMaterial("armadillo_plate",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }), 0,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> Ingredient.ofItems(ModItems.HARDENED_ARMADILLO_PLATE),
                    List.of(new ArmorMaterial.Layer(Identifier.of(SlightlyHelpfulImprovisedTweaks.MOD_ID, "armadillo_plate_armor"))), 2.0F, 0.0F));

    public static final RegistryEntry<ArmorMaterial> TURTLE_SHELL = registerArmorMaterial("turtle_shell",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }), 0,SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, () -> Ingredient.ofItems(ModItems.HARDENED_TURTLE_SHELL),
                    List.of(new ArmorMaterial.Layer(Identifier.of(SlightlyHelpfulImprovisedTweaks.MOD_ID, "turtle_shell_armor"))), 2.0F, 0.0F));

    public static final RegistryEntry<ArmorMaterial> TURTLE_MASTER = registerArmorMaterial("turtle_master",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 4);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.HELMET, 4);
                map.put(ArmorItem.Type.BODY, 12);
            }), 0,SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, () -> Ingredient.ofItems(ModItems.HARDENED_TURTLE_SHELL),
                    List.of(new ArmorMaterial.Layer(Identifier.of(SlightlyHelpfulImprovisedTweaks.MOD_ID, "turtle_master_armor"))), 3.0F, 0.1F));

    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(SlightlyHelpfulImprovisedTweaks.MOD_ID, name), material.get());
    }

    public static void registerArmorMaterial() {
    }
}

