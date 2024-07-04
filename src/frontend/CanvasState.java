package frontend;



import backend.model.Figure;
import backend.model.Point;
import frontend.ui.SelectedSet;
import frontend.ui.figures.DrawableFigure;
import frontend.ui.styles.ShadowEnum;
import frontend.ui.styles.StrokeStyleEnum;
import javafx.scene.paint.Color;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CanvasState {


    // TODO remove selected


    private final List<DrawableFigure<? extends Figure>> list = new ArrayList<>();

    private Set<DrawableFigure<? extends Figure>> selectedList = new SelectedSet<>();

    public void addFigure(DrawableFigure figure) {
        list.add(figure);
    }

    public Iterable<DrawableFigure<? extends Figure>> figures() {
        return new ArrayList<>(list);
    }

    public DrawableFigure getSelectedFigure() {
        if (selectedList.isEmpty())
            return null;
        return selectedList.iterator().next();
    }


    public void deleteFigure() {
        for (DrawableFigure<?extends Figure> figure : figures()){
            if (selectedList.contains(figure)) {
                list.remove(figure);
            }
        }
        clearSelectedFigures();
    }

    public void addSelectedFigures(DrawableFigure<? extends Figure> figure) {
        selectedList.add(figure);
    }

    public void updateSelectedFigures(Color color, boolean isPrimaryColor) {
        for (DrawableFigure<?extends Figure> figure : figures()){
            if (selectedList.contains(figure)) {
                if (isPrimaryColor)
                    figure.changeColor(color);
                else
                    figure.changeSecondColor(color);

            }
        }
    }


    public void clearSelectedFigures() {
        selectedList.clear();
    }

    public boolean figureBelongs(DrawableFigure<? extends Figure> figure, Point eventPoint) {
        // temp
            if (figure.getFigure() == null)
                return false;
            else
                return figure.getFigure().isReachable(eventPoint);
    }



    public boolean noSelection() {
        return selectedList.isEmpty();
    }


    public void updateShadow(ShadowEnum shadow) {
        for (DrawableFigure<?extends Figure> figure : figures()) {
            if (selectedList.contains(figure)) {
                figure.updateShadow(shadow);
            }
        }
    }

    public void updateStrokeStyle(StrokeStyleEnum strokeStyle) {
        for (DrawableFigure<?extends Figure> figure : figures()) {
            if (selectedList.contains(figure)) {
                figure.updateStrokeStyle(strokeStyle);
            }
        }
    }

    public void updateStrokeThickness(Double thickness) {
        for (DrawableFigure<?extends Figure> figure : figures()) {
            if (selectedList.contains(figure)) {
                figure.updateStrokeThickness(thickness);
            }
        }
    }



}
