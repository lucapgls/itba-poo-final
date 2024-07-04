package frontend;


import backend.model.Figure;
import backend.model.Point;
import frontend.ui.figures.DrawableFigure;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class CanvasState {


    // TODO remove selected


    private final List<DrawableFigure<? extends Figure>> list = new ArrayList<>();


    private DrawableFigure<? extends Figure> selectedFigure = null;

    public void addFigure(DrawableFigure figure) {
        list.add(figure);
    }

    public Iterable<DrawableFigure<? extends Figure>> figures() {
        return new ArrayList<>(list);
    }

    public DrawableFigure getSelectedFigure() {
        return selectedFigure;
    }


    public void deleteFigure() {
        for (DrawableFigure<? extends Figure> figure : figures()) {
            if (selectedFigure == figure) {
                list.remove(figure);
            }
        }
        clearSelectedFigure();
    }

    public void addSelectedFigure(DrawableFigure<? extends Figure> figure) {
        selectedFigure = figure;
    }

    public void updateSelectedFigure(Color color, boolean isPrimaryColor) {
        for (DrawableFigure<? extends Figure> figure : figures()) {
            if (selectedFigure == figure) {
                if (isPrimaryColor)
                    figure.changeColor(color);
                else
                    figure.changeSecondColor(color);

            }
        }
    }


    public void clearSelectedFigure() {
        selectedFigure = null;
        for (DrawableFigure<? extends Figure> figure : figures()) {
            figure.setSelected(false);
        }
    }

    public boolean figureBelongs(DrawableFigure<? extends Figure> figure, Point eventPoint) {
        // temp
        if (figure.getFigure() == null)
            return false;
        else
            return figure.getFigure().isReachable(eventPoint);
    }


    public boolean noSelection() {
        return selectedFigure == null;
    }


    public void updateShadow(String shadow) {
        for (DrawableFigure<? extends Figure> figure : figures()) {
            if (selectedFigure == figure) {
                figure.updateShadow(shadow);
            }
        }
    }

    public void updateStrokeStyle(String strokeStyle) {
        for (DrawableFigure<? extends Figure> figure : figures()) {
            if (selectedFigure == figure) {
                figure.updateStrokeStyle(strokeStyle);
            }
        }
    }

    public void updateStrokeThickness(Double thickness) {
        for (DrawableFigure<? extends Figure> figure : figures()) {
            if (selectedFigure == figure) {
                figure.updateStrokeThickness(thickness);
            }
        }
    }


}
