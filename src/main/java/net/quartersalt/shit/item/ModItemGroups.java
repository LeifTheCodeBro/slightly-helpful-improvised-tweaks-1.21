package net.quartersalt.shit.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.quartersalt.shit.SlightlyHelpfulImprovisedTweaks;

public class ModItemGroups {

    public static final ItemGroup SHIT_RESOURCES_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SlightlyHelpfulImprovisedTweaks.MOD_ID, "shit_resources"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.HARDENED_ARMADILLO_PLATE))
                    .displayName(Text.translatable("itemgroup.shit.shit_resources"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.TWINE);
                        entries.add(ModItems.HARDENED_ARMADILLO_PLATE);
                        entries.add(ModItems.HARDENED_TURTLE_SHELL);
                        entries.add(ModItems.TURTLE_ESSENCE);
                    }).build());

    public static final ItemGroup SHIT_ARMOR_WEAPONS_AND_TOOL_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SlightlyHelpfulImprovisedTweaks.MOD_ID, "shit_armor_tools"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.TURTLE_SHELL_CHESTPLATE))
                    .displayName(Text.translatable("itemgroup.shit.shit_armor_and_tools"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.TURTLE_SHELL_HELMET);
                        entries.add(ModItems.TURTLE_SHELL_CHESTPLATE);
                        entries.add(ModItems.TURTLE_SHELL_LEGGINGS);
                        entries.add(ModItems.TURTLE_SHELL_BOOTS);
                    }).build());

    public static void registerItemGroups() {
        SlightlyHelpfulImprovisedTweaks.LOGGER.info("Registering Item Groups for " + SlightlyHelpfulImprovisedTweaks.MOD_ID);
    }
}
