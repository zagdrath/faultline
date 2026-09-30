/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.zagdrath.faultline.FaultlineMod;

/** Client to server: the player picked a mode, by its index in {@link net.zagdrath.faultline.mode.Modes#ALL}. */
public record ModePayload(int mode) implements CustomPacketPayload {
    public static final Type<ModePayload> TYPE = new Type<>(FaultlineMod.id("mode"));
    public static final StreamCodec<ByteBuf, ModePayload> STREAM_CODEC =
            ByteBufCodecs.VAR_INT.map(ModePayload::new, ModePayload::mode);

    @Override
    public Type<ModePayload> type() {
        return TYPE;
    }
}
