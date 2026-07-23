package com.example.hollowknightmod.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public class VoxelMobRenderer<T extends MobEntity, M extends EntityModel<T>> extends MobRenderer<T, M> {
    private final ResourceLocation texture;
    private final float modelScale;

    public VoxelMobRenderer(
            EntityRendererManager rendererManager,
            M model,
            float shadowRadius,
            float modelScale,
            ResourceLocation texture
    ) {
        super(rendererManager, model, shadowRadius);
        this.modelScale = modelScale;
        this.texture = texture;
    }

    @Override
    protected void scale(T entity, MatrixStack matrixStack, float partialTicks) {
        matrixStack.scale(modelScale, modelScale, modelScale);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return texture;
    }
}
