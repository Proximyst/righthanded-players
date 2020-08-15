package com.proximyst.righthandedplayers.mixins;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
  @Inject(method = "getMainArm", at = @At("RETURN"), cancellable = true)
  public void getMainArmInjection(CallbackInfoReturnable<Arm> cir) {
    cir.setReturnValue(Arm.RIGHT);
  }
}
