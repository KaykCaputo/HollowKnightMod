package com.example.hollowknightmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.world.World;

public class TiktikEntity extends SpiderEntity {
    public TiktikEntity(EntityType<? extends SpiderEntity> entityType, World world) {
        super(entityType, world);
        xpReward = 4;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.24D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D);
    }
}
