/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.client;

import java.util.List;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.zagdrath.faultline.config.ClientConfig;
import net.zagdrath.faultline.mode.Mode;
import net.zagdrath.faultline.selection.BlockedReason;

/** The small panel showing the mode, the scroll hint and how many blocks are selected. */
final class HudOverlay {
    private static final int PADDING = 1;
    private static final int BACKGROUND = 0x90000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int GREY = 0xFFAAAAAA;
    private static final int RED = 0xFFFF5555;

    private HudOverlay() {}

    static void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        if (!ClientState.held || !ClientConfig.HUD_ENABLED.get() || minecraft.player == null) {
            return;
        }

        Mode mode = ClientState.mode;
        BlockedReason blocked = ClientState.effectiveBlock();
        Component status;
        if (blocked == BlockedReason.COOLDOWN) {
            status = Component.translatable("faultline.hud.cooldown", String.format("%.1f", ClientState.cooldownTicks / 20.0F));
        } else if (blocked != null) {
            status = blocked.message();
        } else {
            status = Component.translatable("faultline.hud.blocks", Preview.count(), Preview.maxBlocks());
        }
        List<Component> lines = List.of(mode.displayName(), Component.translatable("faultline.hud.scroll"), status);
        int[] colours = blocked != null ? new int[] {RED, RED, RED} : new int[] {WHITE, GREY, WHITE};

        Font font = minecraft.font;
        int width = 0;
        for (Component line : lines) {
            width = Math.max(width, font.width(line));
        }
        int panelWidth = width + PADDING * 2;
        int panelHeight = lines.size() * font.lineHeight + PADDING * 2;

        ClientConfig.Corner corner = ClientConfig.HUD_CORNER.get();
        boolean right = corner == ClientConfig.Corner.TOP_RIGHT || corner == ClientConfig.Corner.BOTTOM_RIGHT;
        boolean bottom = corner == ClientConfig.Corner.BOTTOM_LEFT || corner == ClientConfig.Corner.BOTTOM_RIGHT;
        // Clamp so a large offset or a small window never pushes the panel off screen.
        int offsetX = Math.min(ClientConfig.HUD_OFFSET_X.get(), Math.max(0, graphics.guiWidth() - panelWidth));
        int offsetY = Math.min(ClientConfig.HUD_OFFSET_Y.get(), Math.max(0, graphics.guiHeight() - panelHeight));
        int x = right ? graphics.guiWidth() - offsetX - panelWidth : offsetX;
        int y = bottom ? graphics.guiHeight() - offsetY - panelHeight : offsetY;

        graphics.fill(x, y, x + panelWidth, y + panelHeight, BACKGROUND);
        for (int i = 0; i < lines.size(); i++) {
            graphics.text(font, lines.get(i), x + PADDING, y + PADDING + i * font.lineHeight, colours[i], false);
        }
    }
}
