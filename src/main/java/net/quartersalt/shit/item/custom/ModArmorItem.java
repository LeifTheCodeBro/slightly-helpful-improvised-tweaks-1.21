package net.quartersalt.shit.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.server.network.ServerPlayerEntity;
import net.quartersalt.shit.item.ModArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import net.quartersalt.shit.util.ArmorRepairHelper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ModArmorItem extends ArmorItem {
    private static final Map<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>>())
                    .put(ModArmorMaterials.TURTLE_MASTER,
                            List.of(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 60, 0, false, false),
                                    new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 60, 2, false, false),
                                    new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 2, false, false)))
                    .put(ModArmorMaterials.TURTLE_SHELL,
                            List.of(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 60, 0, false, false),
                                    new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 60, 0, false, false))).build();

    // Variable to track time
    private long lastHealTime = 0;

    public ModArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof ServerPlayerEntity player) {

                applySprintEffect(player);
                applySneakEffect(player);

                long currentTime = world.getTime();

                if (hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }

                // Heal the armor every 10 seconds (200 ticks)
                if (currentTime - lastHealTime >= 600) { // 200 ticks = 10 seconds
                    ArmorRepairHelper.healArmor(player, 1); // Heal 1 point
                    lastHealTime = currentTime; // Reset the timer
                }

            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void applySprintEffect(PlayerEntity player) {
        // Check if the player is sprinting
        if (player.isSprinting()) {
            // Check if the player is wearing the full Armadillo armor
            if (hasFullArmadilloArmor(player)) {
                // Define a list of effects to apply
                List<StatusEffectInstance> effectsToApply = List.of(
                        new StatusEffectInstance(StatusEffects.SPEED, 60, 0, false, false),
                        (new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 0, false, false)));

                // Iterate over the effects
                for (StatusEffectInstance effect : effectsToApply) {
                    // Check if the player already has the effect (to prevent stacking)
                    if (player.getStatusEffect(effect.getEffectType()) == null || Objects.requireNonNull(player.getStatusEffect(effect.getEffectType())).getDuration() <= 30) {
                        // Apply the effect if not present or if the effect has a short duration left
                        player.addStatusEffect(effect);
                    }
                }
            }
        }
    }

    private boolean hasFullArmadilloArmor(PlayerEntity player) {
        // Check if the player is wearing the full ARMADILLO_PLATE armor set
        ItemStack boots = player.getInventory().getArmorStack(0); // Boots
        ItemStack leggings = player.getInventory().getArmorStack(1); // Leggings
        ItemStack chestplate = player.getInventory().getArmorStack(2); // Chestplate
        ItemStack helmet = player.getInventory().getArmorStack(3); // Helmet

        // Ensure all pieces are not empty and match the ARMADILLO_PLATE material
        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty() &&
                isArmadilloArmor(helmet) && isArmadilloArmor(chestplate) &&
                isArmadilloArmor(leggings) && isArmadilloArmor(boots);
    }

    private boolean isArmadilloArmor(ItemStack stack) {
        if (stack.getItem() instanceof ArmorItem armorItem) {
            return armorItem.getMaterial() == ModArmorMaterials.ARMADILLO_PLATE;
        }
        return false;
    }

    private void applySneakEffect(PlayerEntity player) {
        // Check if the player is sneaking
        if (player.isSneaking()) {
            // Check if the player is wearing the full Turtle Master armor
            if (hasFullTurtleMasterArmor(player)) {
                // Define a list of effects to apply
                List<StatusEffectInstance> effectsToApply = List.of(
                        new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 3, false, false),
                        new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 0, false, false)
                );

                // Iterate over the effects
                for (StatusEffectInstance effect : effectsToApply) {
                    // Check if the player already has the effect (to prevent stacking)
                    if (player.getStatusEffect(effect.getEffectType()) == null || Objects.requireNonNull(player.getStatusEffect(effect.getEffectType())).getDuration() <= 30) {
                        // Apply the effect if not present or if the effect has a short duration left
                        player.addStatusEffect(effect);
                    }
                }
            }
        }
    }

    private boolean hasFullTurtleMasterArmor(PlayerEntity player) {
        // Check if the player is wearing the full TURTLE_MASTER armor set
        ItemStack boots = player.getInventory().getArmorStack(0); // Boots
        ItemStack leggings = player.getInventory().getArmorStack(1); // Leggings
        ItemStack chestplate = player.getInventory().getArmorStack(2); // Chestplate
        ItemStack helmet = player.getInventory().getArmorStack(3); // Helmet

        // Ensure all pieces are not empty and match the TURTLE_MASTER material
        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty() &&
                isTurtleMasterArmor(helmet) && isTurtleMasterArmor(chestplate) &&
                isTurtleMasterArmor(leggings) && isTurtleMasterArmor(boots);
    }

    private boolean isTurtleMasterArmor(ItemStack stack) {
        if (stack.getItem() instanceof ArmorItem armorItem) {
            return armorItem.getMaterial() == ModArmorMaterials.TURTLE_MASTER;
        }
        return false;
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            RegistryEntry<ArmorMaterial> mapArmorMaterial = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, RegistryEntry<ArmorMaterial> mapArmorMaterial, List<StatusEffectInstance> mapStatusEffect) {
        for (StatusEffectInstance instance : mapStatusEffect) {
            StatusEffectInstance currentEffect = player.getStatusEffect(instance.getEffectType());
            if (currentEffect == null || currentEffect.getDuration() <= 30) {
                player.addStatusEffect(new StatusEffectInstance(
                        instance.getEffectType(),
                        instance.getDuration(),
                        instance.getAmplifier(),
                        instance.isAmbient(),
                        instance.shouldShowParticles()
                ));
            }
        }
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack breastplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !breastplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(RegistryEntry<ArmorMaterial> material, PlayerEntity player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem) player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}