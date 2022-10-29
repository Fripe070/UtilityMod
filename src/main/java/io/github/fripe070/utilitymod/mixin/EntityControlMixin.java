package io.github.fripe070.utilitymod.mixin;

import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.PigEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={PigEntity.class})
public class EntityControlMixin {
    @Inject(method={"canBeControlledByRider"}, at={@At(value="HEAD")}, cancellable=true)
    public void canBeControlledByRider(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
