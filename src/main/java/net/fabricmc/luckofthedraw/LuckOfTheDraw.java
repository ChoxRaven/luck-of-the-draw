package net.fabricmc.luckofthedraw;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.luckofthedraw.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuckOfTheDraw implements ModInitializer {
    // Mod ID and the logger
    public static final String MOD_ID = "luck_of_the_draw";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
    }
}