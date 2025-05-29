package com.achopto999.newitems.items;

import com.achopto999.GadbuyMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.World;

import java.util.function.Function;

public class Sulfur {
    // 注册行为
    public static final Item SULFUR_ITEM = register("sulfur", Item::new,
            new Item.Settings().component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE));
    public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(GadbuyMod.MOD_ID, path));
        return Items.register(registryKey, factory, settings);
    }

    // 硫磺属性
    public static class CustomItem extends Item {
        public CustomItem(Settings settings) {
            super(settings);
        }
        @Override
        public ActionResult use(World world, PlayerEntity user, Hand hand) {
            user.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
            return ActionResult.SUCCESS;
        }
    }

    // 硫磺的物品栏位置
    public static void registerToVanillaItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(Items.CHARCOAL , SULFUR_ITEM);
        });
    }

    // 初始化
    public static void initialize() {
    }
}
