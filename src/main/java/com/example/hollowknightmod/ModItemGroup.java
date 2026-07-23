package com.example.hollowknightmod;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public final class ModItemGroup {
    public static final ItemGroup HOLLOW_KNIGHT = new ItemGroup(HollowKnightMod.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.OLD_NAIL.get());
        }
    };

    private ModItemGroup() {
    }
}
