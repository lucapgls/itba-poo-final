package frontend.ui.buttons;

import backend.model.Figure;
import backend.model.Point;
import frontend.CanvasState;
import frontend.ui.MouseActions;
import frontend.ui.figures.DrawableFigure;
import frontend.ui.figures.FigureFactory;
import javafx.scene.control.ToggleButton;

public class FigureButton<F extends Figure> extends ActionButton {
    private final FigureFactory<F> factory;
    private Point start;
    private final CanvasState canvasState;

    // new FigureButton("Cuadrado", canvasState, (start, end) -> new Square(start, end.getX() - start.getX())

    public FigureButton(String name, CanvasState canvasState, FigureFactory<F> factory) {
        super(name);
        this.canvasState = canvasState;
        this.factory = factory;
    }

    @Override
    public void onMousePressed(Point start){
        this.start = start;
        canvasState.clearSelectedFigures();
    }

    @Override
    public void onMouseReleased(Point end) {
//        figure.update(start, end);
        canvasState.clearSelectedFigures();
        DrawableFigure<F> drawableFigure = factory.create(start, end);
        canvasState.addFigure(drawableFigure);
        canvasState.addSelectedFigures(drawableFigure);
    }
}
