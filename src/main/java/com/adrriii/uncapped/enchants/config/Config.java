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

    // (maintain with ConfigValues)
    // Main config

    public static boolean EnableUncappedEnchants;
    public static boolean EnableUncappedAnvil;
    public static boolean DefaultCapEverywhere;

    // Enchant caps

    public static int DefaultLevelCap;

    public static String LevelCapPrefix = "LevelCap_";
    public static Map<String, Integer> LevelCaps = new HashMap<>();

    /**
     * Loads the configuration defaults and values
     * @param event
     */
    public static void load(FMLPreInitializationEvent event) {
        config = new Configuration(new File(event.getModConfigurationDirectory(), "UncappedEnchants.cfg"), "1.0", true);
        config.load();
        ConfigValues.init();

        for(ConfigCategory category : ConfigValues.configCategories) {
            config.addCustomCategoryComment(category.name, category.comment);
        }

        for(@SuppressWarnings("rawtypes") ConfigItem item : ConfigValues.configItems) {
            setConfigItemValue(item);
        }

        for(@SuppressWarnings("rawtypes") ConfigItem item : ConfigValues.configEnchants) {
            setConfigItemValue(item);
        }

        config.save();
    }

    /**
     * Obtain the configuration value for a given Enchantment's maximum level
     * @param enchant
     * @return
     */
    public static Integer getLevelCap(Enchantment enchant) {
        return LevelCaps.get(LevelCapPrefix+enchant.getName());
    }

    /**
     * Load the default value for a config item
     * @param item
     */
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
