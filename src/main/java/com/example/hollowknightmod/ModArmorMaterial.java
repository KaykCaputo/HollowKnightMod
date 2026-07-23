package com.example.hollowknightmod;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {
    KNIGHT(
            HollowKnightMod.MOD_ID + ":knight",
            30,
            new int[]{3, 6, 8, 3},
            15,
            SoundEvents.ARMOR_EQUIP_IRON,
            2.0F,
            0.0F,
            () -> Ingredient.of(ModItems.PALE_INGOT.get())
    );

    private static final int[] DURABILITY_BY_SLOT = {13, 15, 16, 11};

    private final String name;
    private final int durabilityMultiplier;
    private final int[] defenseBySlot;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    ModArmorMaterial(String name, int durabilityMultiplier, int[] defenseBySlot,
                     int enchantmentValue, SoundEvent equipSound, float toughness,
                     float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.defenseBySlot = defenseBySlot;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return DURABILITY_BY_SLOT[slot.getIndex()] * durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slot) {
        return defenseBySlot[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
