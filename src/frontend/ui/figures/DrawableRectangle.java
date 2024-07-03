package frontend.ui.figures;

import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class DrawableRectangle<R extends Rectangle> extends DrawableFigure<R> {

        public DrawableRectangle(R figure, Color color, Color secondColor, String shadow, String stroke) {
            super(figure, color, secondColor, shadow, stroke);
        }

        @Override
        public void handleSelection(GraphicsContext gc) {
            R rectangle = getFigure();
            shadowHandler(rectangle, gc);
            strokeHandler(rectangle, gc);
            super.handleSelection(gc);
        }

        private void shadowHandler(R rectangle, GraphicsContext gc){

            if(getShadow().equals("Simple")){
                gc.setFill(Color.GRAY);
                gc.fillRect(rectangle.getTopLeft().getX() + 10, rectangle.getTopLeft().getY() + 10,
                        Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
            } else if (getShadow().equals("Coloreada")){
                gc.setFill(getColor().darker());
                gc.fillRect(rectangle.getTopLeft().getX() + 10, rectangle.getTopLeft().getY() + 10,
                        Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
            } else if(getShadow().equals("Simple Inversa")){
                gc.setFill(Color.GRAY);
                gc.fillRect(rectangle.getTopLeft().getX() - 10, rectangle.getTopLeft().getY() - 10,
                        Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
            } else if(getShadow().equals("Coloreada Inversa")){
                gc.setFill(getColor().darker());
                gc.fillRect(rectangle.getTopLeft().getX() - 10, rectangle.getTopLeft().getY() - 10,
                        Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
            }
        }

        private void strokeHandler(R rectangle, GraphicsContext gc){
            if(getStroke().equals("Simple")) {
                gc.setLineWidth(1);
            } else if(getStroke().equals("Punteado Simple")){
              gc.setLineDashes(10);
            } else if(getStroke().equals("Punteado Complejo")){
              gc.setLineDashes(30, 10, 15, 10);
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
