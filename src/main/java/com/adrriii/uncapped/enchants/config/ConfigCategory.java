package com.adrriii.uncapped.enchants.config;

public class ConfigCategory {
    public String name;
    public String comment;

    public ConfigCategory() {
        this.name = ConfigValues.UNASSIGNED;
        this.comment = "";
    }

    public ConfigCategory name(String name) {
        this.name = name;
        return this;
    }

    public ConfigCategory comment(String comment) {
        this.comment = comment;
        return this;
    }
}
