/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.mode;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.zagdrath.faultline.selection.SelectionContext;

/**
 * A walkable 1 wide, 2 tall tunnel into the face that was hit. The second row is added above the
 * target if it is at or below the player's feet, and below it otherwise, so aiming at either your
 * feet or your head digs the same tunnel. Looking straight up or down digs a 1x2 shaft instead,
 * with the second column in the direction the player faces.
 */
public final class MiningTunnelMode extends ShapedMode {
    @Override
    public String id() {
        return "mining_tunnel";
    }

    @Override
    protected List<BlockPos> slice(SelectionContext context, int step) {
        Direction into = context.face().getOpposite();
        BlockPos base = context.origin().relative(into, step);
        Direction second;
        if (into.getAxis().isVertical()) {
            second = context.facing();
        } else {
            second = context.origin().getY() > context.feetY() ? Direction.DOWN : Direction.UP;
        }
        return List.of(base, base.relative(second));
    }
}
