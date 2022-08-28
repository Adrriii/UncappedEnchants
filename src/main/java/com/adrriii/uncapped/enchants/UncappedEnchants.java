package com.adrriii.uncapped.enchants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.adrriii.uncapped.enchants.config.Config;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = UncappedEnchants.MODID, version = UncappedEnchants.VERSION, name = UncappedEnchants.NAME)
public class UncappedEnchants {
    public static final String MODID = "ue";
    public static final String NAME = "Uncapped Enchants";
    public static final String VERSION = "0.1.0";

    public static final Logger logger = LogManager.getLogger("UncappedEnchants");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.load(event);
    }
}
