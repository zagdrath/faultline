/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.mode;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.Comparator;
import java.util.PriorityQueue;
import net.minecraft.core.BlockPos;
import net.zagdrath.faultline.selection.SelectionContext;

/** Flood fill through every face, edge and corner neighbour, closest blocks first. */
public final class ShapelessMode implements Mode {
    @Override
    public String id() {
        return "shapeless";
    }

    @Override
    public boolean alwaysMatches() {
        return true;
    }

    @Override
    public void collect(SelectionContext context) {
        BlockPos origin = context.origin();
        PriorityQueue<BlockPos> frontier = new PriorityQueue<>(Comparator.comparingDouble(origin::distSqr));
        LongSet seen = new LongOpenHashSet();
        frontier.add(origin);
        seen.add(origin.asLong());

        while (!frontier.isEmpty()) {
            BlockPos current = frontier.poll();
            if (!current.equals(origin) && context.offer(current) && context.isFull()) {
                return;
            }
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        if (dx == 0 && dy == 0 && dz == 0) {
                            continue;
                        }
                        BlockPos next = current.offset(dx, dy, dz);
                        if (seen.add(next.asLong()) && context.inRange(next) && context.accepts(next)) {
                            frontier.add(next);
                        }
                    }
                }
            }
        }
    }
}
