package net.rimaki.safedisconnect.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class DisconnectConfirmScreen extends ConfirmScreen {

    public DisconnectConfirmScreen(
            Screen parent,
            Button.OnPress originalOnPress,
            Button button
    ) {
        super(
                yes -> {
                    if (yes) {
                        originalOnPress.onPress(button);
                    } else {
                        Minecraft.getInstance().setScreen(parent);
                    }
                },
                Component.translatable("menu.confirm_disconnect"),
                Component.empty(),
                CommonComponents.GUI_YES,
                CommonComponents.GUI_CANCEL
        );
    }
}