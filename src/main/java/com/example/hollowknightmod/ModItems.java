package com.example.hollowknightmod;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HollowKnightMod.MOD_ID);

    public static final RegistryObject<Item> PALE_ORE = ITEMS.register("pale_ore",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HOLLOW_KNIGHT)));

    public static final RegistryObject<Item> PALE_INGOT = ITEMS.register("pale_ingot",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HOLLOW_KNIGHT)));

    public static final RegistryObject<Item> OLD_NAIL = ITEMS.register("old_nail",
            () -> new SwordItem(ModItemTier.PALE_ORE, 7, 2f,
                    new Item.Properties().tab(ModItemGroup.HOLLOW_KNIGHT)));

    public static final RegistryObject<Item> KNIGHT_HELMET = ITEMS.register("knight_helmet",
            () -> new KnightArmorItem(EquipmentSlotType.HEAD));

    public static final RegistryObject<Item> KNIGHT_CHESTPLATE = ITEMS.register("knight_chestplate",
            () -> new KnightArmorItem(EquipmentSlotType.CHEST));

    public static final RegistryObject<Item> KNIGHT_LEGGINGS = ITEMS.register("knight_leggings",
            () -> new KnightArmorItem(EquipmentSlotType.LEGS));

    public static final RegistryObject<Item> KNIGHT_BOOTS = ITEMS.register("knight_boots",
            () -> new KnightArmorItem(EquipmentSlotType.FEET));

    public static final RegistryObject<Item> SONG_MUSIC_DISC = ITEMS.register("hollow_disc",
            () -> new MusicDiscItem(1, () -> ModSoundEvents.SONG.get(),
                    new Item.Properties().stacksTo(1).tab(ModItemGroup.HOLLOW_KNIGHT)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
