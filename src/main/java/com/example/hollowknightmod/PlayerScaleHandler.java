package com.example.hollowknightmod;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.mojang.realmsclient.gui.LongRunningTask.LOGGER;

@Mod.EventBusSubscriber(modid = HollowKnightMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlayerScaleHandler {

    private static final float BLOCK_SIZE_SCALE = 0.5f;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
        System.out.println("onRenderPlayer called!");
        LOGGER.info("onRenderPlayer called!");
        if (event.getPlayer() != null && event.getPlayer() == Minecraft.getInstance().player) {
            MatrixStack matrixStack = event.getMatrixStack();
            matrixStack.pushPose();

            float currentScale = BLOCK_SIZE_SCALE;
            matrixStack.scale(currentScale, currentScale, currentScale);
        }
    }
}







