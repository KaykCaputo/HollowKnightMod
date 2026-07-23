package com.example.hollowknightmod.client;

import com.example.hollowknightmod.HollowKnightMod;
import com.example.hollowknightmod.ModEntityTypes;
import com.example.hollowknightmod.client.model.CrawtidModel;
import com.example.hollowknightmod.client.model.TiktikModel;
import com.example.hollowknightmod.client.render.VoxelMobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(
        modid = HollowKnightMod.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public final class ClientModEvents {
    private ClientModEvents() {
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(
                ModEntityTypes.CRAWTID.get(),
                manager -> new VoxelMobRenderer<>(
                        manager,
                        new CrawtidModel(),
                        0.3F,
                        0.75F,
                        new ResourceLocation(HollowKnightMod.MOD_ID, "textures/entity/crawtid.png")
                )
        );
        RenderingRegistry.registerEntityRenderingHandler(
                ModEntityTypes.TIKTIK.get(),
                manager -> new VoxelMobRenderer<>(
                        manager,
                        new TiktikModel(),
                        0.3F,
                        0.75F,
                        new ResourceLocation(HollowKnightMod.MOD_ID, "textures/entity/tiktik.png")
                )
        );
    }
}
