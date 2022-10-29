package io.github.fripe070.utilitymod.hacks;

import net.minecraft.client.MinecraftClient;

import static io.github.fripe070.utilitymod.client.UtilitymodClient.FASTCLICK_KEY;

public class FastClick {
    
    public static void tick(MinecraftClient client) {
        if (client.player == null || !FASTCLICK_KEY.isPressed()) return;

        client.options.useKey.setPressed(!client.options.useKey.isPressed());
        System.out.println(!client.options.useKey.isPressed());
    }
}
