package io.github.fripe070.utilitymod.mixin;

import io.github.fripe070.utilitymod.UtilityModConfigScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text title) {super(title);}

    @Inject(method = "initWidgets", at = @At("HEAD"))
    private void initWidgets(CallbackInfo ci) {
        // TODO: Put this in the correct place on screen (upper left?)
        this.addDrawableChild(new ButtonWidget(10, 10, 50, 20, Text.literal("UtilityMod"), (button) -> {
//            this.client.setScreen(new UtilityModConfigScreen(this, this.client.options));
            System.out.println("booped");
        }));
    }
}
