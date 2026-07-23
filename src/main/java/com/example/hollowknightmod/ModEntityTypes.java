package com.example.hollowknightmod;

import com.example.hollowknightmod.entity.CrawtidEntity;
import com.example.hollowknightmod.entity.TiktikEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, HollowKnightMod.MOD_ID);

    public static final RegistryObject<EntityType<CrawtidEntity>> CRAWTID =
            ENTITY_TYPES.register("crawtid", () -> EntityType.Builder
                    .of(CrawtidEntity::new, EntityClassification.MONSTER)
                    .sized(0.675F, 0.4875F)
                    .clientTrackingRange(8)
                    .build(HollowKnightMod.MOD_ID + ":crawtid"));

    public static final RegistryObject<EntityType<TiktikEntity>> TIKTIK =
            ENTITY_TYPES.register("tiktik", () -> EntityType.Builder
                    .of(TiktikEntity::new, EntityClassification.MONSTER)
                    .sized(0.6F, 0.525F)
                    .clientTrackingRange(8)
                    .build(HollowKnightMod.MOD_ID + ":tiktik"));

    private ModEntityTypes() {
    }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
