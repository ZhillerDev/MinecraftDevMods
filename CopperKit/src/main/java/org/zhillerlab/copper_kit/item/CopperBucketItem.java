package org.zhillerlab.copper_kit.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.zhillerlab.copper_kit.register.ItemsReg;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.world.level.block.LiquidBlock.LEVEL;

public class CopperBucketItem extends BucketItem {
  private InteractionHand useOnHand;
  
  public CopperBucketItem(Fluid content) {
    super(
        content,
        getBucketProperties(content)
    );
  }
  
  public static Properties getBucketProperties(Fluid content) {
    if (content == Fluids.EMPTY) {
      return new Properties().stacksTo(1).durability(10);
    } else if (content == Fluids.WATER) {
      return new Item.Properties().craftRemainder(ItemsReg.COPPER_BUCKET.asItem()).stacksTo(1).durability(10);
    } else if (content == Fluids.LAVA) {
      return new Properties().craftRemainder(ItemsReg.COPPER_BUCKET.asItem()).stacksTo(1).durability(10);
    }
    return new Properties().stacksTo(1).durability(10);
  }
  
  @Override
  public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
    tooltipComponents.add(Component.translatable("tooltip.copper_kit.copper_bucket"));
    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
  }
  
  @Override
  /**
   * 当玩家使用物品时调用此方法，用于处理物品的使用逻辑
   *
   * @param level 当前世界级别对象，用于访问世界数据
   * @param player 使用物品的玩家对象，用于访问玩家数据
   * @param hand 指明玩家使用的哪只手，主手或副手
   * @return 返回使用物品的结果，包括是否成功使用及使用的物品堆
   */
  public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    // 获取玩家手中持有的物品堆
    ItemStack itemstack = player.getItemInHand(hand);
    useOnHand = hand;
    // 根据玩家视角获取击中结果，用于确定玩家试图使用物品的位置
    BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, this.content == Fluids.EMPTY ? net.minecraft.world.level.ClipContext.Fluid.SOURCE_ONLY : net.minecraft.world.level.ClipContext.Fluid.NONE);
    
    // 如果击中结果为空，则不执行任何操作，直接返回
    if (blockhitresult.getType() == HitResult.Type.MISS) {
      return InteractionResultHolder.pass(itemstack);
    } else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
      // 如果击中结果不是方块，则不执行任何操作，直接返回
      return InteractionResultHolder.pass(itemstack);
    } else {
      // 获取击中的方块位置
      BlockPos blockpos = blockhitresult.getBlockPos();
      // 获取击中的方块面的方向
      Direction direction = blockhitresult.getDirection();
      // 计算相对于击中面的方块位置
      BlockPos blockposRelative = blockpos.relative(direction);
      
      // 检查玩家是否可以与当前及相邻方块交互
      if (level.mayInteract(player, blockpos) && player.mayUseItemAt(blockposRelative, direction, itemstack)) {
        // 根据桶中是否为空执行不同的操作
        if (this.content == Fluids.EMPTY) {
          // 执行空桶的拾取动作
          return bucketEmptyPickupAction(level, player, blockpos, itemstack);
        } else {
          // 执行装满流体的桶的丢弃动作
          return bucketFilledDropAction(level, player, blockpos, blockposRelative, blockhitresult, itemstack);
        }
      } else {
        // 如果玩家不能与方块交互，返回失败结果
        return InteractionResultHolder.fail(itemstack);
      }
    }
  }
  
  /**
   * 处理用桶装满液体时的掉落动作
   * 该方法主要负责判断玩家使用桶装满液体时的行为，包括更新世界状态、触发成就和修改玩家物品栏
   *
   * @param level          游戏世界对象，用于访问世界数据和修改世界状态
   * @param player         玩家对象，用于获取玩家数据和触发玩家成就
   * @param blockpos       第一个方块位置，用于判断是否可以装满液体
   * @param blockpos1      第二个方块位置，当第一个方块不可装满时使用
   * @param blockhitresult 方块命中结果，用于获取命中方块的详细信息
   * @param itemstack      当前物品堆，即玩家使用的桶
   * @return 返回一个交互结果持有者对象，包含更新后的物品堆和交互结果
   */
  private @NotNull InteractionResultHolder<ItemStack> bucketFilledDropAction(Level level, Player player, BlockPos blockpos, BlockPos blockpos1, BlockHitResult blockhitresult, ItemStack itemstack) {
    ItemStack itemstack3;
    BlockState blockstate1;
    
    // 获取第一个方块的位置和状态
    blockstate1 = level.getBlockState(blockpos);
    BlockPos blockpos2 = this.canBlockContainFluid(player, level, blockpos, blockstate1) ? blockpos : blockpos1;
    
    // 判断是否成功将桶装满液体
    if (this.emptyContents(player, level, blockpos2, blockhitresult, itemstack)) {
      // 检查是否有额外的内容需要处理
      this.checkExtraContent(player, level, itemstack, blockpos2);
      
      // 当玩家为服务器玩家时，触发放置方块的成就
      if (player instanceof ServerPlayer) {
        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockpos2, itemstack);
      }
      
      // 更新玩家统计信息，并创建装满液体后的桶的物品堆
      player.awardStat(Stats.ITEM_USED.get(this));
      itemstack3 = ItemUtils.createFilledResult(itemstack, player, getEmptyBucket(itemstack, player));
      
      // 返回成功交互的结果
      return InteractionResultHolder.sidedSuccess(itemstack3, level.isClientSide());
    } else {
      // 返回失败交互的结果
      return InteractionResultHolder.fail(itemstack);
    }
  }
  
  /**
   * 处理当桶为空时的捡起动作
   * 此方法主要用于当玩家使用空桶时，与方块交互的逻辑处理它检查方块是否可以被桶拾起，
   * 并在成功拾起时更新玩家的物品栏和世界状态
   *
   * @param level     游戏世界对象，用于访问世界数据
   * @param player    玩家对象，用于执行捡起动作
   * @param blockpos  方块的位置，用于确定捡起位置
   * @param itemstack 玩家手中的物品堆，即空桶
   * @return 返回一个交互结果持有者，包含更新后的物品堆和交互结果
   */
  private @NotNull InteractionResultHolder<ItemStack> bucketEmptyPickupAction(Level level, Player player, BlockPos blockpos, ItemStack itemstack) {
    // 获取目标方块的状态
    BlockState blockstate1;
    ItemStack itemstack3;
    blockstate1 = level.getBlockState(blockpos);
    // 检查方块是否实现了BucketPickup接口，即是否可以被桶拾起
    Block var14 = blockstate1.getBlock();
    if (var14 instanceof BucketPickup bucketpickup) {
      // 尝试获取装满流体的桶
      itemstack3 = getFilledBucket(bucketpickup, itemstack, player, level, blockpos, blockstate1);
      if (!itemstack3.isEmpty()) {
        // 更新玩家统计信息
        player.awardStat(Stats.ITEM_USED.get(this));
        // 播放拾起音效
        bucketpickup.getPickupSound(blockstate1).ifPresent((sd) -> {
          player.playSound(sd, 1.0F, 1.0F);
        });
        // 触发游戏事件：流体捡起
        level.gameEvent(player, GameEvent.FLUID_PICKUP, blockpos);
        // 创建装满流体的桶的物品堆
        ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, player, itemstack3);
        // 在服务器端触发成就/挑战
        if (!level.isClientSide) {
          CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, itemstack3);
        }
        // 返回成功交互的结果持有者
        return InteractionResultHolder.sidedSuccess(itemstack2, level.isClientSide());
      }
    }
    // 如果交互失败，返回失败的结果持有者
    return InteractionResultHolder.fail(itemstack);
  }
  
  public static @NotNull ItemStack getEmptyBucket(ItemStack bucketStack, Player player) {
    if (!player.hasInfiniteMaterials()) {
      if (bucketStack.getItem() == ItemsReg.COPPER_LAVA_BUCKET.asItem()) {
        return new ItemStack(Items.COPPER_INGOT, Math.random() > 0.5 ? 1 : 2);
      } else {
        return new ItemStack(ItemsReg.COPPER_BUCKET.asItem());
      }
    }
    return bucketStack;
  }
  
  /**
   * 获取装满液体的桶物品堆
   * 当玩家使用桶点击特定方块时，该方法会被调用
   * 它检查方块的状态，如果符合条件，则替换方块并返回装满特定液体的桶
   *
   * @param player 可能触发此事件的玩家，如果适用的话
   * @param level  方块所在的级别或世界
   * @param pos    方块的位置
   * @param state  方块的当前状态
   * @return 返回装满液体的桶的物品堆，如果没有液体可装，则返回空物品堆
   */
  public ItemStack getFilledBucket(BucketPickup bucketPickup, ItemStack itemstack, @Nullable Player player, LevelAccessor level, BlockPos pos, BlockState state) {
    // 检查方块状态值，以确定是否可以装满桶
    assert bucketPickup instanceof LiquidBlock;
    LiquidBlock block = (LiquidBlock) bucketPickup;
    
    if (state.getValue(LEVEL) == 0) {
      // 设置方块位置为空气，表示方块已被移除
      level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
      if (block.fluid == Fluids.WATER) {
        return new ItemStack(ItemsReg.COPPER_WATER_BUCKET.asItem());
      } else if (block.fluid == Fluids.LAVA) {
        return new ItemStack(ItemsReg.COPPER_LAVA_BUCKET.asItem());
      } else {
        return ItemStack.EMPTY;
      }
    } else {
      // 如果条件不满足，返回空物品堆，表示没有装满任何液体的桶
      return ItemStack.EMPTY;
    }
  }
}
