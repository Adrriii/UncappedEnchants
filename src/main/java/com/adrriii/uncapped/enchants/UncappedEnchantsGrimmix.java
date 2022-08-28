package com.adrriii.uncapped.enchants;

import io.github.crucible.grimoire.common.api.grimmix.Grimmix;
import io.github.crucible.grimoire.common.api.grimmix.GrimmixController;
import io.github.crucible.grimoire.common.api.grimmix.lifecycle.IConfigBuildingEvent;

@Grimmix(id = "uncappedenchantsgrimmix", name = "Uncapped Enchants Grimmix")
public class UncappedEnchantsGrimmix extends GrimmixController {

    @Override
    public void buildMixinConfigs(IConfigBuildingEvent event) {
        event.createBuilder("uncappedenchants/mixins.uncappedenchants.json")
        .mixinPackage("com.adrriii.uncapped.enchants.mixins")
        .commonMixins("common.*")
        .refmap("@MIXIN_REFMAP@")
        .verbose(true)
        .required(true)
        .build();
    }

}
