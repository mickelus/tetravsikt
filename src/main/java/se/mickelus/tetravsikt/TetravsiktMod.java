package se.mickelus.tetravsikt;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import se.mickelus.mutil.network.PacketHandler;
import se.mickelus.mutil.scheduling.AbstractScheduler;
import se.mickelus.mutil.scheduling.ServerScheduler;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(TetravsiktMod.MOD_ID)
@ParametersAreNonnullByDefault
public class TetravsiktMod {
    public static final String MOD_ID = "tetravsikt";
    public static PacketHandler packetHandler;
    public static AbstractScheduler serverScheduler = new ServerScheduler();

    public TetravsiktMod() {
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(serverScheduler);

        TetravsiktRegistries.init(FMLJavaModLoadingContext.get().getModEventBus());

        packetHandler = new PacketHandler("tetravsikt", "main", "1");
    }

    @SubscribeEvent
    public void setup(FMLCommonSetupEvent event) {
//        packetHandler.registerPacket(SapParticlePacket.class, SapParticlePacket::new);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void clientSetup(FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        if (event.includeServer()) {
            DataGenerator gen = event.getGenerator();
            PackOutput packOutput = gen.getPackOutput();
            CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

//            dataGenerator.addProvider(true, new FarmersDelightCuttingRecipeProvider(packOutput));
        }
    }
}
