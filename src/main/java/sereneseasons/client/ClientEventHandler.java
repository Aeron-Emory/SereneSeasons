package sereneseasons.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "sereneseasons", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // No need to register a container as we are directly opening a screen
    }
}
