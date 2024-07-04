package frontend.ui.styles;

import java.util.function.Consumer;
import javafx.scene.canvas.GraphicsContext;

public enum StrokeStyleEnum {
    NORMAL("Normal", gc -> gc.setLineDashes(0)),
    DOTTED_SIMPLE("Punteado Simple", gc -> gc.setLineDashes(10)),
    DOTTED_COMPLEX("Punteado Complejo", gc -> gc.setLineDashes(30, 10, 15, 10));

    private final String name;
    private final Consumer<GraphicsContext> strokeStyleSetter;

    StrokeStyleEnum(String name, Consumer<GraphicsContext> strokeStyleSetter) {
        this.name = name;
        this.strokeStyleSetter = strokeStyleSetter;
    }

    public String getName() {
        return name;
    }

    public void applyStrokeStyle(GraphicsContext gc) {
        strokeStyleSetter.accept(gc);
    }

    @Override
    public String toString() {
        return name;
    }
}