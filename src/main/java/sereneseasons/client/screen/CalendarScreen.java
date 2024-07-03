package sereneseasons.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CalendarScreen extends Screen {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("sereneseasons", "textures/gui/calendar.png");
    private final int xSize = 176;
    private final int ySize = 166;

    public CalendarScreen() {
        super(new TranslationTextComponent("screen.sereneseasons.calendar"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        Minecraft.getInstance().getTextureManager().bindTexture(GUI_TEXTURE);
        this.blit(matrixStack, (this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);

        // Draw season information
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        String currentSeason = "Spring"; // Replace with actual data
        String nextSeason = "Summer"; // Replace with actual data
        String timeUntilNextSeason = "10 days"; // Replace with actual data

        this.font.drawString(matrixStack, "Current Season: " + currentSeason, centerX - 50, centerY - 20, 4210752);
        this.font.drawString(matrixStack, "Next Season: " + nextSeason, centerX - 50, centerY, 4210752);
        this.font.drawString(matrixStack, "Time Until Next Season: " + timeUntilNextSeason, centerX - 50, centerY + 20, 4210752);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY); // Render tooltips if necessary
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
