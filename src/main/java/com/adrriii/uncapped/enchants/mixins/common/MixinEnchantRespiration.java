package com.adrriii.uncapped.enchants.mixins.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.adrriii.uncapped.enchants.helpers.Enchantment;

import net.minecraft.enchantment.EnchantmentOxygen;

@Mixin(EnchantmentOxygen.class)
public abstract class MixinEnchantRespiration {
    @Inject(method = "getMaxLevel", at = @At(value = "HEAD"), cancellable = true)
    public void getMaxLevel(CallbackInfoReturnable<Integer> cir) {
        Enchantment.handleMaxLevelCallback(cir, Enchantment.Respiration);
    }
}