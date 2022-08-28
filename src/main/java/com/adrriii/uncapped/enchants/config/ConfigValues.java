package com.adrriii.uncapped.enchants.config;

import java.util.ArrayList;
import java.util.List;

import com.adrriii.uncapped.enchants.helpers.Enchantment;

public class ConfigValues {
    public static String UNASSIGNED = "UNASSIGNED";

    public static ConfigCategory defaultCategory = new ConfigCategory().name("Enchant Config");
    public static ConfigCategory enchantCaps = new ConfigCategory().name("Enchantment levels").comment("Set the maximum level for each enchant type. A value of -1 will fallback to DefaultLevelCap");

    // Add categories
    public static ConfigCategory[] configCategories = new ConfigCategory[] {
            defaultCategory,
            enchantCaps
    };

    // Add config values (also in Config fields)
    public static ConfigItem<?>[] configItems = new ConfigItem<?>[] {
        // == Main ==
        new ConfigItem<Boolean>().name("EnableUncappedEnchants").defaultValue(true).comment("Whether to change the cap for enchants (Book merge and application on tools)"),
        new ConfigItem<Boolean>().name("EnableUncappedAnvil").defaultValue(true).comment("Whether to allow unlimited costs for repairs and fusions"),
        new ConfigItem<Boolean>().name("DefaultCapEverywhere").defaultValue(false).comment("Set this to true if you want to use the same cap for all enchants, using the DefaultLevelCap value"),

        // == Enchants ==
        new ConfigItem<Integer>().category(enchantCaps.name).name("DefaultLevelCap").defaultValue(1).comment("The default cap for unconfigured enchants"),
    };

    public static List<ConfigItem<Integer>> configEnchants = new ArrayList<>();

    public static void init() {
        initEnchantCaps();
    }

    public static void initEnchantCaps() {
        for(Enchantment enchant : Enchantment.getAll()) {
            configEnchants.add(new ConfigItem<Integer>()
                    .category(enchantCaps.name)
                    .name(Config.LevelCapPrefix+enchant.getName())
                    .comment(enchant.getComment())
                    .defaultValue(enchant.getDefaultLevelCap()));
        }
    }
}
