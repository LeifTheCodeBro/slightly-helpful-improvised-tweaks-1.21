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
    public static final RegistryEntry<ArmorMaterial> TURTLE_SHELL = registerArmorMaterial("turtle_shell",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }), 15,SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, () -> Ingredient.ofItems(ModItems.HARDENED_TURTLE_SHELL),
                    List.of(new ArmorMaterial.Layer(Identifier.of(SlightlyHelpfulImprovisedTweaks.MOD_ID, "turtle_shell_armor"))), 2, 0.0F));

    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(SlightlyHelpfulImprovisedTweaks.MOD_ID, name), material.get());
    }

}

