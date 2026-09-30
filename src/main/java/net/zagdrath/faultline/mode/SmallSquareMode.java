/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.mode;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.zagdrath.faultline.selection.SelectionContext;

/** A flat 3x3 on the face that was hit: level for floors and ceilings, upright for walls. */
public final class SmallSquareMode extends ShapedMode {
    @Override
    public String id() {
        return "small_square";
    }

    @Override
    protected int depth(SelectionContext context) {
        return 1;
    }

    @Override
    protected List<BlockPos> slice(SelectionContext context, int step) {
        return square(context.origin(), context.face().getAxis());
    }
}
