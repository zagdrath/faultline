/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.zagdrath.faultline.FaultlineMod;

public final class FaultlineKeys {
    public static final KeyMapping.Category CATEGORY = new KeyMapping.Category(FaultlineMod.id("faultline"));

    /** Hold to vein-mine. Defaults to the grave (backtick) key. */
    public static final KeyMapping ACTIVATE = new KeyMapping(
            "key.faultline.activate",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYBOARD,
            InputConstants.KEY_GRAVE,
            CATEGORY);

    private FaultlineKeys() {}

    static void register(RegisterKeyMappingsEvent event) {
        event.registerCategory(CATEGORY);
        event.register(ACTIVATE);
    }
}
