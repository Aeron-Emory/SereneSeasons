/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package sereneseasons.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import sereneseasons.api.SSBlocks;
import sereneseasons.api.season.Season;
import sereneseasons.block.SeasonSensorBlock;
import sereneseasons.core.SereneSeasons;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SSBlockStateProvider extends BlockStateProvider
{
    public SSBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper)
    {
        super(output, SereneSeasons.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        this.registerSeasonSensor();
    }

    public void registerSeasonSensor()
    {
        Map<Season, ModelFile> models = Arrays.stream(Season.values()).collect(Collectors.toMap(key -> key, this::seasonSensor));
        getVariantBuilder(SSBlocks.SEASON_SENSOR)
            .forAllStates(state -> {
                Season season = Season.values()[state.getValue(SeasonSensorBlock.SEASON)];
                return ConfiguredModel.builder()
                    .modelFile(models.get(season))
                    .build();
            });

        this.simpleBlockItem(SSBlocks.SEASON_SENSOR, models.get(Season.SPRING));
    }

    public BlockModelBuilder seasonSensor(Season season)
    {
        String name = "season_sensor_" + season.name().toLowerCase();
        ResourceLocation topTexture = new ResourceLocation(SereneSeasons.MOD_ID, ModelProvider.BLOCK_FOLDER + "/" + name + "_top");
        ResourceLocation sideTexture = new ResourceLocation(SereneSeasons.MOD_ID, ModelProvider.BLOCK_FOLDER + "/season_sensor_side");
        return this.daylightDetector(name, topTexture, sideTexture);
    }

    public BlockModelBuilder daylightDetector(String name, ResourceLocation top, ResourceLocation side)
    {
        return this.models().withExistingParent(name, ModelProvider.BLOCK_FOLDER + "/template_daylight_detector")
            .texture("top", top)
            .texture("side", side);
    }
}
