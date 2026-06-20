/*
 * Based on the same Mixin approach used by Indicatia:
 * https://github.com/SteveKunG/Indicatia/blob/1.21.1/common/src/main/java/com/stevekung/indicatia/mixin/gui/screens/MixinPauseScreen.java
 */

package net.rimaki.safedisconnect.mixin;

import net.rimaki.safedisconnect.gui.DisconnectConfirmScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PauseScreen.class)
public abstract class MultiplayerScreenModification extends Screen {
    @Unique
    private static final Logger safedisconnect$LOGGER = LogManager.getLogger(MultiplayerScreenModification.class);

    protected MultiplayerScreenModification() {
        super(null);
    }

    @ModifyArg(
            method = "createPauseMenu",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/Button;builder(Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/Button$OnPress;)Lnet/minecraft/client/gui/components/Button$Builder;",
                    ordinal = 1
            ),
            index = 1
    )
    private Button.OnPress safedisconnect$replaceDisconnectButton(Button.OnPress originalOnPress) {
        assert this.minecraft != null;
        if (this.minecraft.isLocalServer()) {
            return originalOnPress;
        } else {
            return button -> this.minecraft.setScreen(
                    new DisconnectConfirmScreen(
                            this,
                            originalOnPress,
                            button
                    )
            );
        }
    }
}