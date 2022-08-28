package com.adrriii.uncapped.enchants.config;

import java.lang.reflect.ParameterizedType;

import com.adrriii.uncapped.enchants.UncappedEnchants;

public class ConfigItem<V> {
    public String category;
    public String name;
    public V defaultValue;
    public String comment;

    public ConfigItem() {
        this.category = ConfigValues.defaultCategory.name;
        this.name = ConfigValues.UNASSIGNED;
        this.defaultValue = null;
        this.comment = "";
    }

    public ConfigItem<V> category(String category) {
        this.category = category;
        return this;
    }

    public ConfigItem<V> name(String name) {
        this.name = name;
        return this;
    }

    public ConfigItem<V> defaultValue(V defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public ConfigItem<V> comment(String comment) {
        this.comment = comment;
        return this;
    }

    protected String getGenericName() {
        return this.defaultValue.getClass().getSimpleName();
    }

    @SuppressWarnings("unchecked")
    public V get() {
        switch(this.getGenericName()) {
            case "Integer":
                return (V) (Integer) Config.config.getInt(this.name, this.category, (int) this.defaultValue, 0, (this.name.startsWith(Config.LevelCapPrefix) ? 255 : 999), this.comment);
            case "Float":
                return (V) (Float) Float.parseFloat(Config.config.getString(this.name, this.category, this.defaultValue.toString(), this.comment));
            case "Boolean":
                return (V) (Boolean) Config.config.getBoolean(this.name, this.category, (Boolean) this.defaultValue, this.comment);
            default:
                return (V) Config.config.getString(this.name, this.category, this.defaultValue.toString(), this.comment);
        }
    }
}
