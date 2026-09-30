/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.selection;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.zagdrath.faultline.FaultlineMod;

public final class FaultlineTags {
    /** Blocks that are never selected. */
    public static final TagKey<Block> EXCLUDED = TagKey.create(Registries.BLOCK, FaultlineMod.id("excluded"));
    /** When this tag has any entries, only these blocks can be selected. */
    public static final TagKey<Block> INCLUDED_ONLY = TagKey.create(Registries.BLOCK, FaultlineMod.id("included_only"));
    /** Holding one of these turns Faultline off. */
    public static final TagKey<Item> EXCLUDED_TOOLS = TagKey.create(Registries.ITEM, FaultlineMod.id("excluded_tools"));

    private FaultlineTags() {}
}
