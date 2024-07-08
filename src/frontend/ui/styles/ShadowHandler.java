package frontend.ui.styles;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;

@FunctionalInterface
public interface ShadowHandler<T extends Figure> {
    void applyShadow(ShadowEnum shadow, T figure, GraphicsContext gc);
}
