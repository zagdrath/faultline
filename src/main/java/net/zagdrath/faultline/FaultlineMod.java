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
        container.registerConfig(ModConfig.Type.SYNCED, ServerConfig.SPEC);
        modBus.addListener(FaultlineNetwork::register);
        VeinMineHandler.register(NeoForge.EVENT_BUS);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
