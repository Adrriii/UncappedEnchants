package com.adrriii.uncapped.enchants.mixins.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.adrriii.uncapped.enchants.config.Config;
import com.adrriii.uncapped.enchants.helpers.Numbers;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.StatCollector;

@Mixin(Enchantment.class)
public abstract class MixinEnchants {
    @Shadow public abstract String getName();

    @Inject(method = "getTranslatedName", at = @At(value = "HEAD"), cancellable = true)
    public void getTranslatedName(int level, CallbackInfoReturnable<String> cir) {
        String name = StatCollector.translateToLocal(this.getName());
        cir.setReturnValue(name + " " + Numbers.intToRoman(level));
    }

    @Inject(method = "getMaxLevel", at = @At(value = "HEAD"), cancellable = true)
    public void getMaxLevel(CallbackInfoReturnable<Integer> cir) {
        if(!Config.EnableUncappedEnchants) {
            cir.setReturnValue(1);
            return;
        }
        cir.setReturnValue(Config.DefaultLevelCap);
    }
}