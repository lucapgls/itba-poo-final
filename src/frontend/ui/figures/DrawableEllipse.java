package frontend.ui.figures;

import backend.model.Ellipse;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableEllipse<E extends Ellipse> extends DrawableFigure<E> {


    public DrawableEllipse(E figure, Color color, String shadow) { super(figure, color,shadow); }

    @Override
    public void handleSelection(GraphicsContext gc) {

        shadowHandler(gc);
        super.handleSelection(gc);
    }

    private void shadowHandler(GraphicsContext gc){
        E ellipse = getFigure();

        if(getShadow().equals("Simple")){
            gc.setFill(Color.GRAY);
            gc.fillOval(ellipse.getCenterPoint().getX() + 10 - (ellipse.getsMayorAxis() / 2),
                    ellipse.getCenterPoint().getY() + 10 - (ellipse.getsMinorAxis() / 2),
                    ellipse.getsMayorAxis(),
                    ellipse.getsMinorAxis());
        } else if (getShadow().equals("Coloreada")){
            gc.setFill(getColor().darker());
            gc.fillOval(ellipse.getCenterPoint().getX() + 10 - (ellipse.getsMayorAxis() / 2),
                    ellipse.getCenterPoint().getY() + 10 - (ellipse.getsMinorAxis() / 2),
                    ellipse.getsMayorAxis(),
                    ellipse.getsMinorAxis());
        } else if (getShadow().equals("Simple Inversa")){
            gc.setFill(Color.GRAY);
            gc.fillOval(ellipse.getCenterPoint().getX() - 10 - (ellipse.getsMayorAxis() / 2),
                    ellipse.getCenterPoint().getY() - 10 - (ellipse.getsMinorAxis() / 2),
                    ellipse.getsMayorAxis(),
                    ellipse.getsMinorAxis());
        } else if (getShadow().equals("Coloreada Inversa")){
            gc.setFill(getColor().darker());
            gc.fillOval(ellipse.getCenterPoint().getX() - 10 - (ellipse.getsMayorAxis() / 2),
                    ellipse.getCenterPoint().getY() - 10 - (ellipse.getsMinorAxis() / 2),
                    ellipse.getsMayorAxis(),
                    ellipse.getsMinorAxis());
        }
    }

    @Override
    public void draw(GraphicsContext gc) {

        E ellipse = getFigure();

        handleSelection(gc);

        gc.fillOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2),
                    ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2),
                    ellipse.getsMayorAxis(),
                    ellipse.getsMinorAxis());
        gc.strokeOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2),
                ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2),
                ellipse.getsMayorAxis(),
                ellipse.getsMinorAxis());
    }
}
