package com.example.hollowknightmod;

import com.example.hollowknightmod.client.model.KnightArmorModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class KnightArmorItem extends ArmorItem {
    private static final String OUTER_ARMOR_TEXTURE =
            HollowKnightMod.MOD_ID + ":textures/models/armor/knight_layer_1.png";
    private static final String LEGGINGS_TEXTURE =
            HollowKnightMod.MOD_ID + ":textures/models/armor/knight_layer_2.png";

    public KnightArmorItem(EquipmentSlotType slot) {
        super(ModArmorMaterial.KNIGHT, slot,
                new Properties().tab(ModItemGroup.HOLLOW_KNIGHT));
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity,
                                  EquipmentSlotType slot, String type) {
        return slot == EquipmentSlotType.LEGS
                ? LEGGINGS_TEXTURE
                : OUTER_ARMOR_TEXTURE;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    @SuppressWarnings("unchecked")
    public <A extends BipedModel<?>> A getArmorModel(
            LivingEntity entity, ItemStack stack, EquipmentSlotType slot, A defaultModel) {
        return (A) (slot == EquipmentSlotType.LEGS
                ? ClientModels.INNER
                : ClientModels.OUTER);
    }

    @OnlyIn(Dist.CLIENT)
    private static final class ClientModels {
        private static final KnightArmorModel INNER = new KnightArmorModel(0.5F);
        private static final KnightArmorModel OUTER = new KnightArmorModel(1.0F);
    }
}
