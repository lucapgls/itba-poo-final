package frontend.ui.figures;

import backend.model.Figure;
import backend.model.Rectangle;
import frontend.ui.styles.ShadowEnum;
import frontend.ui.styles.ShadowHandler;
import frontend.ui.styles.StrokeStyleEnum;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import static frontend.ui.styles.ShadowEnum.*;

public class DrawableRectangle<R extends Rectangle> extends DrawableFigure<R> implements ShadowHandler<R> {

        public DrawableRectangle(R figure, Color color, Color secondColor, ShadowEnum shadow, Double strokeThickness, StrokeStyleEnum stroke) {
            super(figure, color, secondColor, shadow, strokeThickness,stroke);
        }

        @Override
        public void handleSelection(GraphicsContext gc) {
            applyShadow(getShadow(), getFigure(), gc);
            applyStrokeThickness(gc);
            super.handleSelection(gc);
        }

        @Override
        public void applyShadow(ShadowEnum shadow, R rectangle, GraphicsContext gc) {
            if (shadow == NONE) return;
            gc.setFill(shadow.isSimple() ? Color.GRAY : getColor().darker());
            gc.fillRect(rectangle.getTopLeft().getX() + (shadow.isInversed() ? -10 : 10),
                    rectangle.getTopLeft().getY() + (shadow.isInversed() ? -10 : 10),
                    Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()),
                    Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
        }


        @Override
        public DrawableFigure<? extends Figure> duplicateFigure(){
            R newRectangle = getFigure();
            return new DrawableRectangle<>(newRectangle.duplicate(), getColor(), getSecondColor(), getShadow(), getStrokeThickness(), getStrokeStyle());
        }

        public DrawableFigure<R>[] divideFigure() {
            R rectangle = getFigure();
            Rectangle[] divided = rectangle.divide();
            return new DrawableFigure[]{new DrawableRectangle<>(divided[0], getColor(), getSecondColor(), getShadow(), getStrokeThickness(), getStrokeStyle()),
                    new DrawableRectangle<>(divided[1], getColor(), getSecondColor(), getShadow(), getStrokeThickness(), getStrokeStyle())};

        }

        @Override
        public void centerFigure(double maxWidth, double maxHeight) {
            R rectangle = getFigure();
            rectangle.center(maxWidth, maxHeight);
        }

        @Override
        public void draw(GraphicsContext gc) {

            LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, getColor()),
                    new Stop(1, getSecondColor()));
            gc.setFill(linearGradient);

            R rectangle = getFigure();

            handleSelection(gc);


            gc.setFill(linearGradient);
            gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
						Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
				gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
					Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));


        }
}
