/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline.network;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.zagdrath.faultline.server.VeinMineHandler;

public final class FaultlineNetwork {
    private static final String VERSION = "1";

    private FaultlineNetwork() {}

    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(VERSION);
        registrar.playToServer(KeyStatePayload.TYPE, KeyStatePayload.STREAM_CODEC,
                (payload, context) -> VeinMineHandler.setKeyHeld(context.player(), payload.held()));
        registrar.playToServer(ModePayload.TYPE, ModePayload.STREAM_CODEC,
                (payload, context) -> VeinMineHandler.setMode(context.player(), payload.mode()));
        // The client registers its handler in RegisterClientPayloadHandlersEvent, keeping client code out of here.
        registrar.playToClient(CooldownPayload.TYPE, CooldownPayload.STREAM_CODEC);
    }
}
