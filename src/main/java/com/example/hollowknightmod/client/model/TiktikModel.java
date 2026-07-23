package com.example.hollowknightmod.client.model;

import com.example.hollowknightmod.entity.TiktikEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class TiktikModel extends EntityModel<TiktikEntity> {
    private static final float[] SHELL_COLOR = color(0xC9CDCE);
    private static final float[] PLATE_COLOR = color(0xA19CBD);
    private static final float[] UNDERSIDE_COLOR = color(0x434589);
    private static final float[] DARK_COLOR = color(0x0D2135);

    private final ModelRenderer underside;
    private final ModelRenderer centerShell;
    private final ModelRenderer frontPlate;
    private final ModelRenderer rearPlate;
    private final ModelRenderer[] spikes = new ModelRenderer[5];
    private final ModelRenderer[] legs = new ModelRenderer[6];
    private final ModelRenderer[] eyes = new ModelRenderer[2];

    public TiktikModel() {
        texWidth = 64;
        texHeight = 64;

        underside = box(-5.0F, -2.0F, -6.0F, 10.0F, 4.0F, 12.0F, 0.0F, 20.0F, 0.0F);
        centerShell = box(-5.5F, -5.0F, -4.5F, 11.0F, 7.0F, 9.0F, 0.0F, 17.0F, 0.5F);
        frontPlate = box(-4.5F, -4.0F, -3.0F, 9.0F, 6.0F, 5.0F, 0.0F, 18.0F, -6.0F);
        rearPlate = box(-4.5F, -4.0F, -2.0F, 9.0F, 6.0F, 5.0F, 0.0F, 18.0F, 6.0F);

        float[][] spikeData = {
                {0.0F, 12.5F, -5.0F, 4.0F, 0.35F},
                {-3.25F, 13.5F, -1.5F, 5.0F, -0.18F},
                {0.0F, 12.0F, 0.5F, 6.0F, 0.0F},
                {3.25F, 13.5F, 2.5F, 5.0F, 0.18F},
                {0.0F, 14.0F, 6.0F, 4.0F, -0.35F}
        };
        for (int i = 0; i < spikes.length; i++) {
            float height = spikeData[i][3];
            spikes[i] = box(-1.0F, -height, -1.0F, 2.0F, height, 2.0F,
                    spikeData[i][0], spikeData[i][1], spikeData[i][2]);
            spikes[i].zRot = spikeData[i][4];
        }

        for (int i = 0; i < legs.length; i++) {
            boolean right = i >= 3;
            int row = i % 3;
            float z = -4.0F + row * 4.0F;
            legs[i] = new ModelRenderer(this, 0, 0);
            legs[i].setPos(right ? 4.0F : -4.0F, 21.0F, z);
            legs[i].addBox(right ? 0.0F : -3.5F, 0.0F, -1.0F, 3.5F, 1.5F, 2.0F);
            legs[i].zRot = right ? 0.28F : -0.28F;
        }

        eyes[0] = box(-2.75F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, 17.5F, -9.0F);
        eyes[1] = box(1.75F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, 17.5F, -9.0F);
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
            TiktikEntity entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        float step = MathHelper.cos(limbSwing * 1.6F) * 0.28F * limbSwingAmount;
        for (int i = 0; i < legs.length; i++) {
            boolean right = i >= 3;
            int row = i % 3;
            float phase = row == 1 ? -step : step;
            legs[i].zRot = (right ? 0.28F : -0.28F) + (right ? phase : -phase);
        }
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
        render(underside, UNDERSIDE_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
        render(centerShell, SHELL_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
        render(frontPlate, PLATE_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
        render(rearPlate, PLATE_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
        render(spikes, SHELL_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
        render(legs, DARK_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
        render(eyes, DARK_COLOR, matrixStack, builder, packedLight, packedOverlay, alpha);
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
