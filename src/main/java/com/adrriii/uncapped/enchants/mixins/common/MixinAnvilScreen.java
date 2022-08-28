package com.adrriii.uncapped.enchants.mixins.common;

import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.adrriii.uncapped.enchants.config.Config;

import net.minecraft.client.gui.GuiRepair;
import net.minecraft.entity.player.PlayerCapabilities;

@Mixin(GuiRepair.class)
public abstract class MixinAnvilScreen {

    @Redirect(
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.GETFIELD,
                    target = "Lnet/minecraft/entity/player/PlayerCapabilities;isCreativeMode:Z"
                    ),
            method = { "drawGuiContainerForegroundLayer" }
            )
    public boolean isCreativeMode(@SuppressWarnings("unused") PlayerCapabilities instance) {
        if(!Config.EnableUncappedAnvil) return instance.isCreativeMode;
        return true;
    }
}
