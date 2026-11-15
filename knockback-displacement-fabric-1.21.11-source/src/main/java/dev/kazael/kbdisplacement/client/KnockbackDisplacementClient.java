package dev.kazael.kbdisplacement.client;

import dev.kazael.kbdisplacement.KnockbackConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

public class KnockbackDisplacementClient implements ClientModInitializer {
    private static KeyMapping openConfig;

    @Override
    public void onInitializeClient() {
        openConfig = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.knockback_displacement.open_config",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                KeyMapping.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openConfig.consumeClick()) {
                if (client.screen == null) client.setScreen(new KnockbackConfigScreen());
            }
        });
    }
}
