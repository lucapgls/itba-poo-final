package frontend.ui.figures;

import backend.model.Ellipse;
import backend.model.Figure;
import frontend.ui.styles.ShadowEnum;
import frontend.ui.styles.StrokeStyleEnum;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DrawableEllipse<E extends Ellipse> extends DrawableFigure<E> {


    public DrawableEllipse(E figure, Color color, Color secondColor, ShadowEnum shadow, Double strokeThickness, StrokeStyleEnum stroke) { super(figure, color,secondColor ,shadow, strokeThickness, stroke); }

    @Override
    public void handleSelection(GraphicsContext gc) {

        E ellipse = getFigure();
        shadowHandler(ellipse, gc);
        strokeThicknessHandler(gc);
        super.handleSelection(gc);
    }

    private void shadowHandler(E ellipse, GraphicsContext gc){

        // TODO Make it more generic with functions
        switch (getShadow()) {
            case SIMPLE -> {
                gc.setFill(Color.GRAY);
                gc.fillOval(ellipse.getCenter().getX() + 10 - (ellipse.getsMayorAxis() / 2),
                        ellipse.getCenter().getY() + 10 - (ellipse.getsMinorAxis() / 2),
                        ellipse.getsMayorAxis(),
                        ellipse.getsMinorAxis());
            }
            case COLORED -> {
                gc.setFill(getColor().darker());
                gc.fillOval(ellipse.getCenter().getX() + 10 - (ellipse.getsMayorAxis() / 2),
                        ellipse.getCenter().getY() + 10 - (ellipse.getsMinorAxis() / 2),
                        ellipse.getsMayorAxis(),
                        ellipse.getsMinorAxis());
            }
            case INVERSE_SIMPLE -> {
                gc.setFill(Color.GRAY);
                gc.fillOval(ellipse.getCenter().getX() - 10 - (ellipse.getsMayorAxis() / 2),
                        ellipse.getCenter().getY() - 10 - (ellipse.getsMinorAxis() / 2),
                        ellipse.getsMayorAxis(),
                        ellipse.getsMinorAxis());
            }
            case INVERSE_COLORED -> {
                gc.setFill(getColor().darker());
                gc.fillOval(ellipse.getCenter().getX() - 10 - (ellipse.getsMayorAxis() / 2),
                        ellipse.getCenter().getY() - 10 - (ellipse.getsMinorAxis() / 2),
                        ellipse.getsMayorAxis(),
                        ellipse.getsMinorAxis());
            }
        }
    }

    private void strokeThicknessHandler(GraphicsContext gc){
        gc.setLineWidth(getStrokeThickness());
    }


    @Override
    public DrawableFigure<? extends Figure> duplicateFigure() {
        E ellipse = getFigure();
        return new DrawableEllipse<>(ellipse.duplicate(), getColor(), getSecondColor(), getShadow(), getStrokeThickness(), getStrokeStyle());
    }

    public DrawableFigure<? extends Figure>[] divideFigure() {
        E ellipse = getFigure();
        Ellipse[] divided = ellipse.divide();
        return new DrawableFigure[]{
                new DrawableEllipse<>(divided[0], getColor(), getSecondColor(), getShadow(), getStrokeThickness(), getStrokeStyle()),
                new DrawableEllipse<>(divided[1], getColor(), getSecondColor(), getShadow(), getStrokeThickness(), getStrokeStyle())
        };

    }

    @Override
    public void centerFigure(double maxWidth, double maxHeight) {
        E ellipse = getFigure();
        ellipse.center(maxWidth, maxHeight);
    }

    @Override
    public void draw(GraphicsContext gc) {

        RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                CycleMethod.NO_CYCLE,new Stop(0, getColor()),
                new Stop(1, getSecondColor()));



        E ellipse = getFigure();

        handleSelection(gc);
        gc.setFill(radialGradient);
        gc.fillOval(ellipse.getCenter().getX() - (ellipse.getsMayorAxis() / 2),
                    ellipse.getCenter().getY() - (ellipse.getsMinorAxis() / 2),
                    ellipse.getsMayorAxis(),
                    ellipse.getsMinorAxis());
        gc.strokeOval(ellipse.getCenter().getX() - (ellipse.getsMayorAxis() / 2),
                ellipse.getCenter().getY() - (ellipse.getsMinorAxis() / 2),
                ellipse.getsMayorAxis(),
                ellipse.getsMinorAxis());
    }
}
