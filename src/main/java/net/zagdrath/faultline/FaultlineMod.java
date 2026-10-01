/*
 * Copyright (c) 2026 Zagdrath
 * SPDX-License-Identifier: MIT
 */
package net.zagdrath.faultline;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.zagdrath.faultline.config.ServerConfig;
import net.zagdrath.faultline.network.FaultlineNetwork;
import net.zagdrath.faultline.server.VeinMineHandler;
import org.slf4j.Logger;

@Mod(FaultlineMod.MOD_ID)
public final class FaultlineMod {
    public static final String MOD_ID = "faultline";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FaultlineMod(IEventBus modBus, ModContainer container) {
        container.registerConfig(syncedConfigType(), ServerConfig.SPEC);
        modBus.addListener(FaultlineNetwork::register);
        VeinMineHandler.register(NeoForge.EVENT_BUS);
    }

    /**
     * The server-owned config type that is synced to clients. NeoForge 26.3.0.39-beta renamed it from
     * {@code SERVER} to {@code SYNCED}; looking it up by name keeps one jar working on both sides of
     * the rename, because the bytecode never refers to the constant that might be missing.
     */
    private static ModConfig.Type syncedConfigType() {
        try {
            return ModConfig.Type.valueOf("SYNCED");
        } catch (IllegalArgumentException renamedLater) {
            return ModConfig.Type.valueOf("SERVER");
        }
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
