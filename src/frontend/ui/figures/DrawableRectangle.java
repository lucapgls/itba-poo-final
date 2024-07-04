package frontend.ui.figures;

import backend.model.Rectangle;
import frontend.ui.styles.ShadowEnum;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import static frontend.ui.styles.ShadowEnum.*;

public class DrawableRectangle<R extends Rectangle> extends DrawableFigure<R> {

        public DrawableRectangle(R figure, Color color, Color secondColor, ShadowEnum shadow, Double strokeThickness,String stroke) {
            super(figure, color, secondColor, shadow, strokeThickness,stroke);
        }

        @Override
        public void handleSelection(GraphicsContext gc) {
            R rectangle = getFigure();
            shadowHandler(rectangle, gc);
            strokeThicknessHandler(gc);
            strokeStyleHandler(gc);
            super.handleSelection(gc);
        }

        private void shadowHandler(R rectangle, GraphicsContext gc){

            switch (getShadow()) {
                case SIMPLE -> {
                    gc.setFill(Color.GRAY);
                    gc.fillRect(rectangle.getTopLeft().getX() + 10, rectangle.getTopLeft().getY() + 10,
                            Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
                }
                case COLORED -> {
                    gc.setFill(getColor().darker());
                    gc.fillRect(rectangle.getTopLeft().getX() + 10, rectangle.getTopLeft().getY() + 10,
                            Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
                }
                case INVERSE_SIMPLE -> {
                    gc.setFill(Color.GRAY);
                    gc.fillRect(rectangle.getTopLeft().getX() - 10, rectangle.getTopLeft().getY() - 10,
                            Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
                }
                case INVERSE_COLORED -> {
                    gc.setFill(getColor().darker());
                    gc.fillRect(rectangle.getTopLeft().getX() - 10, rectangle.getTopLeft().getY() - 10,
                            Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
                }
            }
        }

        private void strokeThicknessHandler(GraphicsContext gc){
            gc.setLineWidth(getStrokeThickness());
        }

        // TODO: Change stroke style to an enum
        private void strokeStyleHandler(GraphicsContext gc){
            switch (getStrokeStyle()) {
                case "Normal" -> gc.setLineDashes(0);
                case "Punteado Simple" -> gc.setLineDashes(10);
                case "Punteado Complejo" -> gc.setLineDashes(30, 10, 15, 10);
            }
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
