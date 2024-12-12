package net.quartersalt.shit.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ArmorItem;
import net.minecraft.entity.player.PlayerEntity;

public class ArmorRepairHelper {

    // Heal the armor in the player's inventory
    public static void healArmor(PlayerEntity player, int healAmount) {
        // Iterate over the player's armor slots
        for (int i = 0; i < 4; i++) {
            ItemStack armorStack = player.getInventory().getArmorStack(i);

            // Check if the armor stack is valid and if it's an armor item
            if (!armorStack.isEmpty() && armorStack.getItem() instanceof ArmorItem) {
                healArmorPiece(armorStack, healAmount);
            }
        }
    }

    // Heal a specific armor piece by reducing its damage
    private static void healArmorPiece(ItemStack stack, int healAmount) {
        // Get the current damage on the armor piece
        int currentDamage = stack.getDamage();

        // Calculate the new damage after healing
        int newDamage = Math.max(currentDamage - healAmount, 0); // Ensure damage doesn't go below 0 (fully repaired)

        // Update the armor piece's damage
        stack.setDamage(newDamage);
    }

    // Helper method to check if armor is fully repaired (no damage)
    public static boolean isArmorFullyRepaired(ItemStack armorStack) {
        // Armor is fully repaired if its damage is 0
        return armorStack.getDamage() == 0;
    }
}