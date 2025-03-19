package se.mickelus.tetravsikt;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.forged.ForgedBlockCommon;

public class ClearTooltipHandler {
    public static final TagKey<Item> craftableRuinsLootTag = ItemTags.create(new ResourceLocation("tetravsikt", "craftable_ruins_loot"));

    @SubscribeEvent
    public static void onItemTooltipEvent(ItemTooltipEvent event) {
        if (event.getItemStack().is(craftableRuinsLootTag)) {
            event.getToolTip().removeIf(ForgedBlockCommon.locationTooltip::equals);
        }
    }
}
