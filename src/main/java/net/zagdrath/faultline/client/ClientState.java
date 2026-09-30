/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.client;

import net.zagdrath.faultline.config.ClientConfig;
import net.zagdrath.faultline.mode.Mode;
import net.zagdrath.faultline.mode.Modes;
import net.zagdrath.faultline.selection.BlockedReason;
import org.jspecify.annotations.Nullable;

/** What the local player is doing with Faultline right now. */
final class ClientState {
    static boolean held;
    /** The key state the server was last told about. */
    static boolean sentHeld;
    static Mode mode = Modes.SHAPELESS;
    static int cooldownTicks;
    static @Nullable BlockedReason blocked;

    private ClientState() {}

    static void reset() {
        held = false;
        sentHeld = false;
        cooldownTicks = 0;
        blocked = null;
        mode = ClientConfig.REMEMBER_LAST_MODE.get() ? Modes.byId(ClientConfig.LAST_MODE.get()) : Modes.SHAPELESS;
    }

    /** {@return why Faultline is blocked, counting the cooldown, or {@code null}} */
    static @Nullable BlockedReason effectiveBlock() {
        if (blocked != null) {
            return blocked;
        }
        return cooldownTicks > 0 ? BlockedReason.COOLDOWN : null;
    }
}
