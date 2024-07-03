package frontend.ui;

import backend.model.Figure;
import frontend.ui.figures.DrawableFigure;
import javafx.scene.paint.Color;

import java.util.HashSet;

public class SelectedSet<T extends DrawableFigure<? extends Figure>> extends HashSet<T> {

        @Override
        public boolean add(T figure) {
            boolean added = super.add(figure);
            if (added) {
                figure.setSelected(true);
            }
            return added;
        }

        @Override
        public void clear() {
            for (DrawableFigure<? extends Figure> figure : this) {
                figure.setSelected(false);
            }
            super.clear();
        }

        public void update(Color color) {
            for (DrawableFigure<? extends Figure> figure : this) {
                figure.changeColor(color);
            }
        }


}
