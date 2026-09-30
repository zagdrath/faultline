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

/** Client to server: the Faultline key was pressed or released. */
public record KeyStatePayload(boolean held) implements CustomPacketPayload {
    public static final Type<KeyStatePayload> TYPE = new Type<>(FaultlineMod.id("key_state"));
    public static final StreamCodec<ByteBuf, KeyStatePayload> STREAM_CODEC =
            ByteBufCodecs.BOOL.map(KeyStatePayload::new, KeyStatePayload::held);

    @Override
    public Type<KeyStatePayload> type() {
        return TYPE;
    }
}
