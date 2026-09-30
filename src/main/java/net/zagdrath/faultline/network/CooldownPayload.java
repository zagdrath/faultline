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

/** Server to client: the player has to wait this many ticks before the next vein-mine. */
public record CooldownPayload(int ticks) implements CustomPacketPayload {
    public static final Type<CooldownPayload> TYPE = new Type<>(FaultlineMod.id("cooldown"));
    public static final StreamCodec<ByteBuf, CooldownPayload> STREAM_CODEC =
            ByteBufCodecs.VAR_INT.map(CooldownPayload::new, CooldownPayload::ticks);

    @Override
    public Type<CooldownPayload> type() {
        return TYPE;
    }
}
