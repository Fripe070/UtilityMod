package io.github.fripe070.utilitymod.client;

import io.github.fripe070.utilitymod.hacks.FastClick;
import io.github.fripe070.utilitymod.hacks.Flying;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class UtilitymodClient implements ClientModInitializer {
    public static KeyBinding flightToggle;

    public static final KeyBinding FLIGHT_KEY = new KeyBinding(
            "key.utilitymod.flighttoggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "key.category.utilitymod"
    );
    public static final KeyBinding FASTCLICK_KEY = new KeyBinding(
            "key.utilitymod.fastclick",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_Y,
            "key.category.utilitymod"
    );
    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(FLIGHT_KEY);
        KeyBindingHelper.registerKeyBinding(FASTCLICK_KEY);


        ClientTickEvents.END_CLIENT_TICK.register(Flying::tick);
        ClientTickEvents.END_CLIENT_TICK.register(FastClick::tick);
    }

}
