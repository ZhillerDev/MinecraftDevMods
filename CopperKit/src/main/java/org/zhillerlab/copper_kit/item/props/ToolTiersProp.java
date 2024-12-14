package org.zhillerlab.copper_kit.item.props;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import org.zhillerlab.copper_kit.register.TagsReg;

public class ToolTiersProp {
  /**
   * 定义一个铜级别的Tier对象
   * 该对象用于表示铜制工具的属性，包括其适用的方块标签、耐久度、挖掘速度、攻击伤害、挖掘等级以及修复材料
   *
   * TagsReg.Blocks.INCORRECT_FOR_COPPER_TOOL: 铜制工具不适用的方块标签，这定义了铜制工具不应被用于挖掘哪些方块
   * 1400: 铜制工具的耐久度，表示工具在损坏前可以使用的次数
   * 4f: 铜制工具的挖掘速度，相比木制工具的提升倍数
   * 3f: 铜制工具的攻击伤害，相比木制工具的提升倍数
   * 28: 铜制工具的挖掘等级，决定了它可以挖掘哪些硬度的方块
   * () -> Ingredient.of(Items.COPPER_INGOT): 铜制工具的修复材料，这里指定为铜锭
   */
  public static final Tier COPPER_TIER = new SimpleTier(
      TagsReg.Blocks.INCORRECT_FOR_COPPER_TOOL,
        208, 10f, 1.0f, 20,
      () -> Ingredient.of(Items.COPPER_INGOT));
}
