package org.zhillerlab.copper_kit.item.props;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.zhillerlab.copper_kit.common.config.GlobalConfig;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ArmorMaterialsProp {
  public static final Holder<ArmorMaterial> COPPER_ARMOR_MATERIAL = register("copper",
      Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
        attribute.put(ArmorItem.Type.BOOTS, 1);
        attribute.put(ArmorItem.Type.LEGGINGS, 4);
        attribute.put(ArmorItem.Type.CHESTPLATE, 5);
        attribute.put(ArmorItem.Type.HELMET, 2);
        attribute.put(ArmorItem.Type.BODY, 4);
      }), 20, 0f, 0.0f, Items.COPPER_INGOT);
  
  /**
   * 注册一个新的装甲材料
   *
   * @param name                装甲材料的名称，用于在资源位置中标识
   * @param typeProtection      一个映射，定义了每种装甲类型对应的防护值
   * @param enchantability      装甲的附魔能力，决定了附魔时的难度
   * @param toughness           装甲的坚韧度，影响玩家受到的伤害
   * @param knockbackResistance 反击阻力，决定了玩家被击退的程度
   * @param ingredientItem      一个供应商，用于提供制作装甲所需的材料项
   * @return 返回注册后的装甲材料持有者
   */
  private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                int enchantability, float toughness, float knockbackResistance,
                                                Item ingredientItem) {
    // 构建资源位置，使用模组ID和提供的名称
    ResourceLocation location = ResourceLocation.fromNamespaceAndPath(GlobalConfig.MOD_ID, name);
    // 设置装备声音事件
    Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_IRON;
    // 构建制作材料的供应商
    Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem);
    // 创建装甲层列表
    List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));
    
    // 初始化装甲类型到防护值的映射
    EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
    // 遍历每种装甲类型，填充映射
    for (ArmorItem.Type type : ArmorItem.Type.values()) {
      typeMap.put(type, typeProtection.get(type));
    }
    
    // 注册新的装甲材料并返回其持有者
    return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
        new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
  }
}
