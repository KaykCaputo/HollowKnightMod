package com.example.hollowknightmod.client.model;

import com.example.hollowknightmod.entity.CrawtidEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class CrawtidModel extends EntityModel<CrawtidEntity> {
    private static final float[] SHELL_COLOR = color(0x4F6177);
    private static final float[] BODY_COLOR = color(0x304665);
    private static final float[] DARK_COLOR = color(0x0A1726);
    private static final float[] EYE_COLOR = color(0xD8D2E4);

    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer snout;
    private final ModelRenderer[] shellSegments = new ModelRenderer[3];
    private final ModelRenderer[] spikes = new ModelRenderer[5];
    private final ModelRenderer[] legs = new ModelRenderer[6];
    private final ModelRenderer[] eyes = new ModelRenderer[2];

    public CrawtidModel() {
        texWidth = 64;
        texHeight = 64;

        body = box(-5.0F, -2.5F, -7.0F, 10.0F, 5.0F, 14.0F, 0.0F, 18.0F, 1.0F);
        head = box(-4.5F, -3.0F, -4.0F, 9.0F, 6.0F, 6.0F, 0.0F, 17.5F, -7.0F);
        snout = box(-3.5F, -2.0F, -3.0F, 7.0F, 4.0F, 3.0F, 0.0F, 18.5F, -11.0F);

        shellSegments[0] = box(-5.5F, -3.5F, -3.0F, 11.0F, 5.0F, 5.0F, 0.0F, 16.5F, -2.5F);
        shellSegments[1] = box(-5.75F, -3.75F, -3.0F, 11.5F, 5.5F, 5.0F, 0.0F, 16.25F, 2.0F);
        shellSegments[2] = box(-5.25F, -3.25F, -2.0F, 10.5F, 4.5F, 4.0F, 0.0F, 16.75F, 6.0F);

        float[] spikeZ = {-4.5F, -1.0F, 2.5F, 5.5F, 7.5F};
        float[] spikeHeight = {5.0F, 7.0F, 6.0F, 4.5F, 3.5F};
        for (int i = 0; i < spikes.length; i++) {
            spikes[i] = box(-1.0F, -spikeHeight[i], -1.0F, 2.0F, spikeHeight[i], 2.0F,
                    0.0F, 15.0F, spikeZ[i]);
            spikes[i].xRot = -0.28F;
        }

        for (int i = 0; i < legs.length; i++) {
            boolean right = i >= 3;
            int row = i % 3;
            float z = -4.0F + row * 4.0F;
            legs[i] = new ModelRenderer(this, 0, 0);
            legs[i].setPos(right ? 4.0F : -4.0F, 20.0F, z);
            legs[i].addBox(right ? 0.0F : -4.0F, 0.0F, -1.0F, 4.0F, 1.5F, 2.0F);
            legs[i].zRot = right ? 0.22F : -0.22F;
        }

        eyes[0] = box(-3.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, 17.5F, -14.0F);
        eyes[1] = box(2.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, 17.5F, -14.0F);
    }

    private ModelRenderer box(
            float x,
            float y,
            float z,
            float width,
            float height,
            float depth,
            float pivotX,
            float pivotY,
            float pivotZ
    ) {
        ModelRenderer part = new ModelRenderer(this, 0, 0);
        part.setPos(pivotX, pivotY, pivotZ);
        part.addBox(x, y, z, width, height, depth);
        return part;
    }

    @Override
    public void setupAnim(
            CrawtidEntity entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        float step = MathHelper.cos(limbSwing * 1.8F) * 0.35F * limbSwingAmount;
        for (int i = 0; i < legs.length; i++) {
            boolean right = i >= 3;
            int row = i % 3;
            float phase = row == 1 ? -step : step;
            legs[i].zRot = (right ? 0.22F : -0.22F) + (right ? phase : -phase);
        }
        head.yRot = netHeadYaw * ((float) Math.PI / 180.0F) * 0.15F;
        snout.yRot = head.yRot;
    }

    @Override
    public void renderToBuffer(
            MatrixStack matrixStack,
            IVertexBuilder builder,
            int packedLight,
            int packedOverlay,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        render(body, BODY_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
        render(head, BODY_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
        render(snout, DARK_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
        render(shellSegments, SHELL_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
        render(spikes, SHELL_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
        render(legs, DARK_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
        render(eyes, EYE_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
    }

    private static void render(
            ModelRenderer[] parts,
            float[] color,
            MatrixStack matrixStack,
            IVertexBuilder builder,
            int packedLight,
            int packedOverlay,
            float alpha
    ) {
        for (ModelRenderer part : parts) {
            render(part, color, matrixStack, builder, packedLight, packedOverlay, alpha);
        }
    }

    private static void render(
            ModelRenderer part,
            float[] color,
            MatrixStack matrixStack,
            IVertexBuilder builder,
            int packedLight,
            int packedOverlay,
            float alpha
    ) {
        part.render(matrixStack, builder, packedLight, packedOverlay, color[0], color[1], color[2], alpha);
    }

    private static float[] color(int rgb) {
        return new float[]{
                ((rgb >> 16) & 0xFF) / 255.0F,
                ((rgb >> 8) & 0xFF) / 255.0F,
                (rgb & 0xFF) / 255.0F
        };
    }
}
