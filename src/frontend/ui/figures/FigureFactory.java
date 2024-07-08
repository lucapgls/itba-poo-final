package frontend.ui.figures;

import backend.model.Figure;
import backend.model.Point;

@FunctionalInterface
public interface FigureFactory<F extends Figure> {

    DrawableFigure<F> create(Point start, Point end);



}
