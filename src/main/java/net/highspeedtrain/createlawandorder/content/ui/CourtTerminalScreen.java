package net.highspeedtrain.createlawandorder.content.ui;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.systems.RenderSystem;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.highspeedtrain.createlawandorder.content.network.CLONetworking;
import net.highspeedtrain.createlawandorder.content.network.packets.CourtTerminalTextPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CourtTerminalScreen extends AbstractContainerScreen<CourtTerminalMenu> {
    private static final ResourceLocation TEXTURE = CreateLawAndOrder.modPath("textures/gui/court_terminal_gui.png");
    private EditBox textBox;
    public Boolean isValid = false;
    public String error = "Invalid material";

    public CourtTerminalScreen(CourtTerminalMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.imageWidth = 176;
        this.imageHeight = 128;
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 37;
        this.titleLabelX = 8;
        this.titleLabelY = 5;

        this.textBox = new EditBox(
            this.font,
            this.leftPos + 72,
            this.topPos + 18,
            64,
            13,
            Component.translatable("createlawandorder.courtterminal.ui.entertext")
        );

        this.textBox.setMaxLength(16);
        this.textBox.setBordered(false);
        this.textBox.setResponder(this::onTextChanged);
        this.addRenderableWidget(this.textBox);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        guiGraphics.blit(TEXTURE, textBox.getX(), textBox.getY(), 1, this.textBox.isFocused() ? 129 : 143, textBox.getWidth(), textBox.getHeight());
        guiGraphics.blit(TEXTURE, leftPos + (!isValid ? 137 : 5000), topPos + (!isValid ? 18 : 0), 177, 0, 15, 13);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);

        if (isValid != null && !isValid) {
            guiGraphics.drawString(
                font,
                error,
                this.leftPos + 168 - this.font.width(error),
                this.topPos + 33,
                0xFF5555
            );
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (textBox.isFocused()) {
            if (keyCode == GLFW.GLFW_KEY_ESCAPE || keyCode == GLFW.GLFW_KEY_ENTER) {
                textBox.setFocused(false);
                return true;
            }
            return textBox.keyPressed(keyCode, scanCode, modifiers) || textBox.canConsumeInput();
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public void onTextChanged(String text) {
        CLONetworking.CHANNEL.sendToServer(new CourtTerminalTextPacket(menu.containerId, text));
    }

    public CourtTerminalMenu getMenu() {
        return this.menu;
    }
}
