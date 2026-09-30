/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.mode;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.zagdrath.faultline.selection.SelectionContext;

/** A 3x3 tunnel centred on the target, going into the face that was hit. */
public final class LargeTunnelMode extends ShapedMode {
    @Override
    public String id() {
        return "large_tunnel";
    }

    @Override
    protected List<BlockPos> slice(SelectionContext context, int step) {
        Direction into = context.face().getOpposite();
        return square(context.origin().relative(into, step), into.getAxis());
    }
}
