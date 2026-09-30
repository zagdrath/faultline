/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.mode;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.zagdrath.faultline.selection.SelectionContext;

/** A 1x1 line straight into the face that was hit. */
public final class SmallTunnelMode extends ShapedMode {
    @Override
    public String id() {
        return "small_tunnel";
    }

    @Override
    protected List<BlockPos> slice(SelectionContext context, int step) {
        return List.of(context.origin().relative(context.face().getOpposite(), step));
    }
}
