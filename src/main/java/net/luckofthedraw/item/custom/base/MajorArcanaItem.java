package net.luckofthedraw.item.custom.base;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class MajorArcanaItem extends Item {
    // Base settings
    public MajorArcanaItem(Settings settings, int cooldown) {
        super(
            settings.maxDamage(cooldown)
        );

        if (cooldown < 0) {
            settings.maxCount(1);
        }
    }

    // Cooldown bar color
    @Override
    public int getItemBarColor(ItemStack stack) {
        return MathHelper.hsvToRgb(0.5f, 1.0F, 1.0F);
    }

    // The code for the cooldown. NOTE: 1 tick = 1/20th of a second, this 20 times per second
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.getMaxDamage()==0) {
            return;
        }

        if (stack.getDamage() > 0) {
            stack.setDamage(stack.getDamage() - 1);
        }
    }
}
