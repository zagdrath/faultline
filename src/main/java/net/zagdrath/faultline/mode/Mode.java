/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.mode;

import net.minecraft.network.chat.Component;
import net.zagdrath.faultline.selection.SelectionContext;

/** A way of choosing which blocks around the origin get mined. */
public interface Mode {
    /** Stable id, used in the config, lang keys and the saved last mode. */
    String id();

    /** Whether every selected block has to match the origin block regardless of config. */
    default boolean alwaysMatches() {
        return false;
    }

    /**
     * Offers positions to the context, nearest to the origin first, until the context is full or the
     * shape is exhausted. The origin itself has already been added.
     */
    void collect(SelectionContext context);

    default Component displayName() {
        return Component.translatable("faultline.mode." + id());
    }
}
