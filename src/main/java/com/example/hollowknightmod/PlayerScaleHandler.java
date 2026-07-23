package com.example.hollowknightmod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HollowKnightMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class PlayerScaleHandler {

    public static final float PLAYER_SCALE = 0.5F;

    private PlayerScaleHandler() {
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onPlayerSize(EntityEvent.Size event) {
        if (event.getEntity() instanceof PlayerEntity) {
            event.setNewSize(event.getNewSize().scale(PLAYER_SCALE));
            event.setNewEyeHeight(event.getNewEyeHeight() * PLAYER_SCALE);
        }
    }
}
