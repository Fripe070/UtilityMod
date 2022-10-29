package io.github.fripe070.utilitymod;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

public class UtilityModConfigScreen extends Screen {
    private final Screen parent;
    private final GameOptions settings;

    public UtilityModConfigScreen(Screen parent, GameOptions gameOptions, Text title) {
        super(title);
        this.parent = parent;
        this.settings = gameOptions;
    }

    //TODO: Make this work
}
