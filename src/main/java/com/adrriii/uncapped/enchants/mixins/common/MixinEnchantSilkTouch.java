package com.adrriii.uncapped.enchants.mixins.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.adrriii.uncapped.enchants.helpers.Enchantment;

import net.minecraft.enchantment.EnchantmentUntouching;

@Mixin(EnchantmentUntouching.class)
public abstract class MixinEnchantSilkTouch {
    @Inject(method = "getMaxLevel", at = @At(value = "HEAD"), cancellable = true)
    public void getMaxLevel(CallbackInfoReturnable<Integer> cir) {
        Enchantment.handleMaxLevelCallback(cir, Enchantment.SilkTouch);
    }
}