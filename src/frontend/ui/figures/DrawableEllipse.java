package frontend.ui.figures;

import backend.model.Ellipse;
import backend.model.Figure;
import frontend.ui.styles.ShadowEnum;
import frontend.ui.styles.ShadowHandler;
import frontend.ui.styles.StrokeStyleEnum;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DrawableEllipse<E extends Ellipse> extends DrawableFigure<E> implements ShadowHandler<E> {


    public DrawableEllipse(E figure, Color color, Color secondColor, ShadowEnum shadow, Double strokeThickness, StrokeStyleEnum stroke) { super(figure, color,secondColor ,shadow, strokeThickness, stroke); }

    @Override
    public void handleSelection(GraphicsContext gc) {

        applyShadow(getShadow(), getFigure(), gc);
        applyStrokeThickness(gc);
        super.handleSelection(gc);
    }

    @Override
    public void applyShadow(ShadowEnum shadow, E ellipse, GraphicsContext gc) {
        if (shadow == ShadowEnum.NONE) return;
        gc.setFill(shadow.isSimple() ? Color.GRAY : getColor().darker());
        gc.fillOval(ellipse.getCenter().getX() + (shadow.isInversed() ? -10 : 10) - (ellipse.getsMayorAxis() / 2),
                ellipse.getCenter().getY() + (shadow.isInversed() ? -10 : 10) - (ellipse.getsMinorAxis() / 2),
                ellipse.getsMayorAxis(),
                ellipse.getsMinorAxis());
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
