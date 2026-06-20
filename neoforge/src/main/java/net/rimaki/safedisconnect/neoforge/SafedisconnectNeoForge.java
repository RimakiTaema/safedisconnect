package net.rimaki.safedisconnect.neoforge;

import net.rimaki.safedisconnect.Safedisconnect;
import net.neoforged.fml.common.Mod;

@Mod(Safedisconnect.MOD_ID)
public final class SafedisconnectNeoForge {
    public SafedisconnectNeoForge() {
        // Run our common setup.
        Safedisconnect.init();
    }
}
