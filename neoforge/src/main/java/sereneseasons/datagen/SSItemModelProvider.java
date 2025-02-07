/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package sereneseasons.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import sereneseasons.api.SSItems;
import sereneseasons.core.SereneSeasons;
import sereneseasons.item.CalendarType;

public class SSItemModelProvider extends ItemModelProvider
{
    public SSItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, SereneSeasons.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        this.basicItem(SSItems.SS_ICON);
        this.registerCalendarModels();
    }
    private void registerCalendarModels()
    {
        var calendar = getBuilder(location("calendar").toString()).parent(new ModelFile.UncheckedModelFile("item/generated"));
        var calendarNull = this.basicItem(location("calendar_null"));
        ModelFile[] calendarStandard = new ModelFile[12];
        ModelFile[] calendarTropical = new ModelFile[6];

        // Populate standard and tropical arrays
        for (int i = 0; i < 12; i++)
        {
            String pathIndex = String.format("%02d", i);
            calendarStandard[i] = this.basicItem(location("calendar_" + pathIndex));
            if (i < 6) calendarTropical[i] = this.basicItem(location("tropical_calendar_" + pathIndex));
        }

        // Standard calendar
        for (int i = 0; i < 12; i++)
        {
            calendar.override()
                .predicate(location("seasontype"), CalendarType.STANDARD.ordinal())
                .predicate(location("time"), (float)i / 12.0F)
                .model(calendarStandard[i]);
        }

        // Tropical calendar
        for (int i = 0; i < 12; i++)
        {
            calendar.override()
                .predicate(location("seasontype"), CalendarType.TROPICAL.ordinal())
                .predicate(location("time"), (float)i / 12.0F)
                .model(calendarTropical[((i + 3) / 2) % 6]);
        }

        // Null calendar
        calendar.override()
            .predicate(location("seasontype"), CalendarType.NONE.ordinal())
            .predicate(location("time"), 0.0f)
            .model(calendarNull);
    }

    private static ResourceLocation location(String name)
    {
        return ResourceLocation.fromNamespaceAndPath(SereneSeasons.MOD_ID, name);
    }
}
