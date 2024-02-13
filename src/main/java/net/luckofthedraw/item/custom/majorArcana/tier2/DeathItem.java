package net.luckofthedraw.item.custom.majorArcana.tier2;

import net.luckofthedraw.item.custom.base.MajorArcanaItem;
import net.luckofthedraw.util.SpawnpointUtilities;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class DeathItem extends MajorArcanaItem {
    // Item Settings
    public DeathItem(Settings majorArcanaItem) {
        super(majorArcanaItem,600);
    }

    // Teleports the user to their spawn point, and prints a death message in chat
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack stack = playerEntity.getStackInHand(hand);

        // Stops the interaction if it is on the client
        if (world.isClient) {
            return TypedActionResult.pass(stack);
        }

        // Teleports the player on the serverside, sends a message, and sets durability to 0
        if (stack.getDamage() == 0) {
            stack.setDamage(stack.getMaxDamage());

            // Teleports to their spawn point
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerEntity;
            SpawnpointUtilities.teleportToSpawn(serverPlayerEntity);

        // If the current durability is smaller than the max, send a cooldown message and stop usage for 5 ticks
        } else {
            playerEntity.getItemCooldownManager().set(this, 5);
            playerEntity.sendMessage(Text.translatable("item.luck_of_the_draw.tarot_card.interact_fail"), false);
        }

        // Validates the interaction
        return TypedActionResult.success(stack, true);
    }

    // The Tooltip
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.luck_of_the_draw.death_tarot_card.tooltip_line1"));
        tooltip.add(Text.translatable("item.luck_of_the_draw.death_tarot_card.tooltip_line2"));
    }
}