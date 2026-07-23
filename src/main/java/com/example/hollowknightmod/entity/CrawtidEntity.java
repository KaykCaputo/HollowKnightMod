package com.example.hollowknightmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.world.World;

public class CrawtidEntity extends SpiderEntity {
    public CrawtidEntity(EntityType<? extends SpiderEntity> entityType, World world) {
        super(entityType, world);
        xpReward = 3;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D);
    }
}
