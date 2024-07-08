package frontend.ui.buttons;

import backend.model.Figure;
import backend.model.Point;
import frontend.CanvasState;
import frontend.ui.figures.DrawableFigure;
import frontend.ui.figures.FigureFactory;

public class FigureButton<F extends Figure> extends ActionButton {
    private final FigureFactory<F> factory;
    private Point start;
    private final CanvasState canvasState;
    private int layer;

    // new FigureButton("Cuadrado", canvasState, (start, end) -> new Square(start, end.getX() - start.getX())

    public FigureButton(String name, CanvasState canvasState, int layer,FigureFactory<F> factory) {
        super(name);
        this.canvasState = canvasState;
        this.layer = layer;
        this.factory = factory;
    }

    @Override
    public void onMousePressed(Point start){
        this.start = start;
        canvasState.clearSelectedFigure();
    }

    public void setLayer(int layer){
        this.layer = layer;
    }

    @Override
    public void onMouseReleased(Point end) {
//        figure.update(start, end);
        canvasState.clearSelectedFigure();
        DrawableFigure<F> drawableFigure = factory.create(start, end);
        canvasState.addFigure(drawableFigure, layer);
        canvasState.addSelectedFigure(drawableFigure);
        drawableFigure.setSelected(true);
    }
}
