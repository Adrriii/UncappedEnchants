package com.adrriii.uncapped.enchants.mixins.common;

import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import com.adrriii.uncapped.enchants.config.Config;

import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.ContainerRepair;

@Mixin(ContainerRepair.class)
public abstract class MixinAnvilRepair {

    @Redirect(
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.GETFIELD,
                    target = "Lnet/minecraft/entity/player/PlayerCapabilities;isCreativeMode:Z"
                    ),
            method = { "updateRepairOutput" },
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "intValue=40"
                            })
                    )
            )
    public boolean isCreativeMode(@SuppressWarnings("unused") PlayerCapabilities instance) {
        if(!Config.EnableUncappedAnvil) return instance.isCreativeMode;
        return true;
    }
}
