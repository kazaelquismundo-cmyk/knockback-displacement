package dev.kazael.kbdisplacement.client;

import dev.kazael.kbdisplacement.KnockbackConfig;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class KnockbackConfigScreen extends Screen {
    private final Screen parent;

    public KnockbackConfigScreen() {
        this(null);
    }

    public KnockbackConfigScreen(Screen parent) {
        super(Component.literal("Knockback Displacement"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int center = this.width / 2;
        KnockbackConfig config = KnockbackConfig.get();

        addRenderableWidget(Button.builder(enabledText(), button -> {
            config.enabled = !config.enabled;
            KnockbackConfig.save();
            button.setMessage(enabledText());
        }).bounds(center - 105, 55, 210, 20).build());

        addRenderableWidget(Button.builder(Component.literal("Horizontal: " + format(config.horizontalMultiplier)), button -> {
            config.changeHorizontal(0.25);
            button.setMessage(Component.literal("Horizontal: " + format(config.horizontalMultiplier)));
        }).bounds(center - 105, 85, 210, 20).build());

        addRenderableWidget(Button.builder(Component.literal("Vertical: " + format(config.verticalMultiplier)), button -> {
            config.changeVertical(0.25);
            button.setMessage(Component.literal("Vertical: " + format(config.verticalMultiplier)));
        }).bounds(center - 105, 115, 210, 20).build());

        addRenderableWidget(Button.builder(Component.literal("Reset multipliers"), button -> {
            config.horizontalMultiplier = 1.0;
            config.verticalMultiplier = 1.0;
            KnockbackConfig.save();
            rebuildWidgets();
        }).bounds(center - 105, 145, 210, 20).build());

        addRenderableWidget(Button.builder(Component.literal("Done"), button -> onClose())
                .bounds(center - 105, this.height - 30, 210, 20).build());
    }

    private Component enabledText() {
        return Component.literal("Knockback: " + (KnockbackConfig.get().enabled ? "ON" : "OFF"));
    }

    private static String format(double value) {
        return String.format(java.util.Locale.ROOT, "%.2fx", value);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics, mouseX, mouseY, delta);
        graphics.drawCenteredString(font, title, width / 2, 25, 0xFFFFFF);
        graphics.drawCenteredString(font, "Press K in-game to open this menu", width / 2, 42, 0xAAAAAA);
        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    public void onClose() {
        if (minecraft != null) minecraft.setScreen(parent);
    }
}
