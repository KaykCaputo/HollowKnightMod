package com.example.hollowknightmod;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = HollowKnightMod.MOD_ID,
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public final class PlayerRenderScaleHandler {

    private PlayerRenderScaleHandler() {
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void beforePlayerRender(RenderPlayerEvent.Pre event) {
        if (!event.isCanceled()) {
            event.getMatrixStack().pushPose();
            event.getMatrixStack().scale(
                    PlayerScaleHandler.PLAYER_SCALE,
                    PlayerScaleHandler.PLAYER_SCALE,
                    PlayerScaleHandler.PLAYER_SCALE
            );
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void afterPlayerRender(RenderPlayerEvent.Post event) {
        event.getMatrixStack().popPose();
    }
}
