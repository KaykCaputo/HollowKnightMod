package com.example.hollowknightmod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.RegistryObject;

@Mod.EventBusSubscriber(modid = HollowKnightMod.MOD_ID)
public final class NailUpgradeHandler {
    private NailUpgradeHandler() {
    }

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        if (event.getRight().getItem() != ModItems.PALE_INGOT.get()) {
            return;
        }

        Upgrade upgrade = findUpgrade(event.getLeft().getItem());
        if (upgrade == null || event.getRight().getCount() < upgrade.ingotCost) {
            return;
        }

        ItemStack output = new ItemStack(upgrade.result.get());
        CompoundNBT originalTag = event.getLeft().getTag();
        if (originalTag != null) {
            output.setTag(originalTag.copy());
        }

        if (event.getName() != null && !event.getName().isEmpty()) {
            output.setHoverName(new StringTextComponent(event.getName()));
        }

        event.setOutput(output);
        event.setMaterialCost(upgrade.ingotCost);
        event.setCost(upgrade.levelCost);
    }

    private static Upgrade findUpgrade(Item nail) {
        if (nail == ModItems.OLD_NAIL.get()) {
            return new Upgrade(ModItems.SHARPENED_NAIL, 1, 1);
        }
        if (nail == ModItems.SHARPENED_NAIL.get()) {
            return new Upgrade(ModItems.CHANNELLED_NAIL, 1, 2);
        }
        if (nail == ModItems.CHANNELLED_NAIL.get()) {
            return new Upgrade(ModItems.COILED_NAIL, 2, 4);
        }
        if (nail == ModItems.COILED_NAIL.get()) {
            return new Upgrade(ModItems.PURE_NAIL, 3, 6);
        }
        return null;
    }

    private static final class Upgrade {
        private final RegistryObject<Item> result;
        private final int ingotCost;
        private final int levelCost;

        private Upgrade(RegistryObject<Item> result, int ingotCost, int levelCost) {
            this.result = result;
            this.ingotCost = ingotCost;
            this.levelCost = levelCost;
        }
    }
}
