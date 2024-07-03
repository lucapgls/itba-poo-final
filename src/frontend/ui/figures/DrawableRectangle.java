package frontend.ui.figures;

import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableRectangle<R extends Rectangle> extends DrawableFigure<R> {

        public DrawableRectangle(R figure, Color color, String shadow) {
            super(figure, color, shadow);
        }

        @Override
        public void handleSelection(GraphicsContext gc) {

            shadowHandler(gc);
            super.handleSelection(gc);
        }

        private void shadowHandler(GraphicsContext gc){
            R rectangle = getFigure();


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


        @Override
        public void draw(GraphicsContext gc) {

            R rectangle = getFigure();

            handleSelection(gc);



            gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
						Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
				gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
					Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));


        }
}
