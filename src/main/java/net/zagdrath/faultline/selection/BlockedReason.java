/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.selection;

import java.util.Locale;
import net.minecraft.network.chat.Component;

/** Why Faultline will not run right now. */
public enum BlockedReason {
    EXCLUDED_TOOL,
    TOOL_REQUIRED,
    NO_PERMISSION,
    NO_MODES,
    COOLDOWN;

    public Component message() {
        return Component.translatable("faultline.blocked." + name().toLowerCase(Locale.ROOT));
    }
}
