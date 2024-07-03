package frontend.ui.buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.CanvasState;
import frontend.ui.MouseActions;
import frontend.ui.figures.DrawableFigure;
import javafx.scene.control.ToggleButton;

import java.util.List;
import java.util.function.Predicate;

public class SelectionButton extends ActionButton {

    private static final String BUTTON_NAME = "Seleccionar";

    private Rectangle selectionArea;

    private Point start, end;
    private final CanvasState canvasState;

    public SelectionButton(CanvasState canvasState) {
        super(BUTTON_NAME);
        this.canvasState = canvasState;
    }


    @Override
    public void onMouseClicked(Point point) {
        start = point;
        end = point;
        canvasState.clearSelectedFigures();
        for (DrawableFigure<? extends Figure> figure : canvasState.figures()) {
            if (figure.getFigure().isReachable(start)) {
                // add to seleceted list from canvasstate
                canvasState.addSelectedFigures(figure);
            }
        }
    }

    @Override
    public void onMousePressed(Point point) {
        start = point;
    }

    @Override
    public void onMouseReleased(Point point) {
        end = point;
        // Released but no movement
        if (start != null && end != null && start.getX() == end.getX() && start.getY() == end.getY()) {
            canvasState.clearSelectedFigures();
            return;
        }


        selectionArea = new Rectangle(start, end);

        // and clear any selected figures within it
        for (DrawableFigure<? extends Figure> figure : canvasState.figures()) {
            if (figure.getFigure().isContained(selectionArea)) {
                // add to seleceted list from canvasstate
                canvasState.addSelectedFigures(figure);
            }
        }
        selectionArea = null;
    }

    @Override
    public void onMouseDragged(Point point) {

        canvasState.clearSelectedFigures();

        if (selectionArea != null) {
            double diffX = (point.getX() - selectionArea.getTopLeft().getX());
            double diffY = (point.getY() - selectionArea.getTopLeft().getY());
            end.move(diffX, diffY);
        }

        // modify the starting point of the imaginary rectangle
    }
}
