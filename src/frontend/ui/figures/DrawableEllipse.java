package frontend.ui.figures;

import backend.model.Ellipse;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class DrawableEllipse<E extends Ellipse> extends DrawableFigure<E> {


    public DrawableEllipse(E figure, Color color, Color secondColor, String shadow) { super(figure, color,secondColor ,shadow); }

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

        RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                CycleMethod.NO_CYCLE,new Stop(0, getColor()),
                new Stop(1, getSecondColor()));



        E ellipse = getFigure();

        handleSelection(gc);
        gc.setFill(radialGradient);
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
