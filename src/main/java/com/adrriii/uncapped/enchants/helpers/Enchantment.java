package com.adrriii.uncapped.enchants.helpers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.adrriii.uncapped.enchants.UncappedEnchants;
import com.adrriii.uncapped.enchants.config.Config;
import com.adrriii.uncapped.enchants.mixins.common.MixinEnchants;

public enum Enchantment {
    Efficiency      ("Efficiency",      20),
    Unbreaking      ("Unbreaking",      20),
    Fortune         ("Fortune",         20, "Also for all loot types (Looting, Luck of the Sea)"),
    SilkTouch       ("SilkTouch",       1),
    Protection      ("Protection",      20, "Also for all protection types (Fire, Blast, Arrow, Falling)"),
    AquaAffinity    ("AquaAffinity",    1),
    Respiration     ("Respiration",     20),

    Sharpness       ("Sharpness",       20, "Also for all sword damage types (Smite, Arthropods)"),
    FireAspect      ("FireAspect",      20),
    Knockback       ("Knockback",       20),

    Power           ("Power",           20),
    Infinity        ("Infinity",        1),
    Punch           ("Punch",           20),
    Fire            ("Fire",            1),

    Lure            ("Lure",            20);

    private String name;
    private String comment = "";
    private int defaultLevelCap;

    private Enchantment(String name, int defaultLevelCap) {
        this.setName(name);
        this.setDefaultLevelCap(defaultLevelCap);
    }

    private Enchantment(String name, int defaultLevelCap, String comment) {
        this(name, defaultLevelCap);
        this.comment = comment;
    }

    public static List<Enchantment> getAll() {
        return Stream.of(Enchantment.values())
                .collect(Collectors.toList());
    }

    public static int getConfigCap(com.adrriii.uncapped.enchants.helpers.Enchantment enchant) {
        if(!Config.EnableUncappedEnchants) return -1;
        if(Config.DefaultCapEverywhere) return Config.DefaultLevelCap;

        return Config.getLevelCap(enchant);
    }

    public static void handleMaxLevelCallback(CallbackInfoReturnable<Integer> cir, com.adrriii.uncapped.enchants.helpers.Enchantment enchant) {
        int cap = getConfigCap(enchant);
        if(cap != -1) {
            cir.setReturnValue(cap);
        }
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getDefaultLevelCap() {
        return this.defaultLevelCap;
    }

    private void setDefaultLevelCap(int defaultLevelCap) {
        this.defaultLevelCap = defaultLevelCap;
    }

    public String getComment() {
        return this.comment;
    }
}
