package com.adrriii.uncapped.enchants.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.adrriii.uncapped.enchants.UncappedEnchants;
import com.adrriii.uncapped.enchants.helpers.Enchantment;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {
    public static Configuration config;

    // Main config

    public static boolean EnableUncappedEnchants;
    public static boolean EnableUncappedAnvil;
    public static boolean DefaultCapEverywhere;

    // Enchant caps
    // Add config values (also in ConfigValues array)

    public static int DefaultLevelCap;

    public static String LevelCapPrefix = "LevelCap_";
    public static Map<String, Integer> LevelCaps = new HashMap<>();

    public static void load(FMLPreInitializationEvent event) {
        config = new Configuration(new File(event.getModConfigurationDirectory(), "UncappedEnchants.cfg"), "1.0", true);
        config.load();

        for(ConfigCategory category : ConfigValues.configCategories) {
            config.addCustomCategoryComment(category.name, category.comment);
        }

        ConfigValues.init();

        for(@SuppressWarnings("rawtypes") ConfigItem item : ConfigValues.configItems) {
            setConfigItemValue(item);
        }

        for(@SuppressWarnings("rawtypes") ConfigItem item : ConfigValues.configEnchants) {
            setConfigItemValue(item);
        }

        config.save();
    }

    public static Integer getLevelCap(Enchantment enchant) {
        return LevelCaps.get(LevelCapPrefix+enchant.getName());
    }

    private static void setConfigItemValue(@SuppressWarnings("rawtypes") ConfigItem item) {
        if(!item.name.equals("UNASSIGNED")) {

            if(item.name.startsWith(LevelCapPrefix)) {
                Config.LevelCaps.put(item.name, (Integer) item.get());
                return;
            }

            try {
                Config.class.getField(item.name).set(null, item.get());
            } catch (NoSuchFieldException e) {
                com.adrriii.uncapped.enchants.UncappedEnchants.logger.error("Config item not added to com.adrriii.uncapped.enchants.config.Config : "+item.name);
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                com.adrriii.uncapped.enchants.UncappedEnchants.logger.error("Bad configuration for item '"+item.name+"'");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        else {
            com.adrriii.uncapped.enchants.UncappedEnchants.logger.error("Unassigned config item !");
        }
    }
}
