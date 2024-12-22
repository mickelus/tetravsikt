package se.mickelus.tetravsikt.craftingeffect;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import se.mickelus.tetra.craftingeffect.outcome.CraftingEffectOutcome;
import se.mickelus.tetra.module.schematic.UpgradeSchematic;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

@ParametersAreNonnullByDefault
public class DamageAnvilOutcome implements CraftingEffectOutcome {

    int range = 2;
    double chance = 0.12f;

    @Override
    public boolean apply(ResourceLocation[] unlockedEffects, ItemStack upgradedStack, String slot, boolean isReplacing, Player player,
            ItemStack[] preMaterials,
            Map<ToolAction, Integer> tools, Level world, UpgradeSchematic schematic, BlockPos origin, BlockState blockState, boolean consumeResources,
            ItemStack[] postMaterials) {
        if (consumeResources && !world.isClientSide() && world.random.nextDouble() < chance) {
            AtomicBoolean success = new AtomicBoolean(false);
            IntStream.range(0, range * 2)
                    .mapToObj(i -> StreamSupport.stream(BlockPos.spiralAround(origin.above(i), range, Direction.NORTH, Direction.EAST).spliterator(), false))
                    .flatMap(Function.identity())
                    .filter(pos -> world.getBlockState(pos).is(BlockTags.ANVIL))
                    .findFirst()
                    .ifPresent(pos -> {
                        BlockState updatedState = AnvilBlock.damage(world.getBlockState(pos));
                        if (updatedState == null) {
                            world.removeBlock(pos, false);
                            world.levelEvent(LevelEvent.SOUND_ANVIL_BROKEN, pos, 0);
                            success.set(true);
                        } else {
                            world.setBlock(pos, updatedState, 2);
                            world.levelEvent(LevelEvent.SOUND_ANVIL_USED, pos, 0);
                            success.set(true);
                        }
                    });
            return success.get();
        }
        return false;
    }
}
