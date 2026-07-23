package com.example.hollowknightmod.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class KnightArmorModel extends BipedModel<LivingEntity> {
    public KnightArmorModel(float modelSize) {
        super(modelSize);
        this.texWidth = 64;
        this.texHeight = 32;

        addHorn(1.0F);
        addHorn(-1.0F);
    }

    private void addHorn(float side) {
        ModelRenderer base = new ModelRenderer(this, 8, 0);
        base.setPos(4.0F * side, -5.5F, 0.0F);
        base.zRot = 0.55F * side;
        base.addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F);
        head.addChild(base);

        ModelRenderer middle = new ModelRenderer(this, 8, 0);
        middle.setPos(0.0F, -2.8F, 0.0F);
        middle.zRot = -0.35F * side;
        middle.addBox(-0.75F, -4.0F, -0.75F, 1.5F, 4.0F, 1.5F);
        base.addChild(middle);

        ModelRenderer tip = new ModelRenderer(this, 8, 0);
        tip.setPos(0.0F, -3.8F, 0.0F);
        tip.zRot = -0.4F * side;
        tip.addBox(-0.5F, -3.5F, -0.5F, 1.0F, 3.5F, 1.0F);
        middle.addChild(tip);
    }
}
