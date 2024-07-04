package frontend.ui.figures;

import backend.model.Figure;
import frontend.ui.styles.ShadowEnum;
import frontend.ui.styles.StrokeStyleEnum;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class DrawableFigure<F extends Figure> {

    private final F figure;

    private boolean selected = false;
    private static final Color SELECTED_COLOR = Color.RED;
    private static final Color LINE_COLOR = Color.BLACK;


    private Color figureColor;
    private Color secondFigureColor;
    private ShadowEnum shadow;
    private Double strokeThickness;
    private StrokeStyleEnum strokeStyle;

    public DrawableFigure(F figure, Color figureColor, Color secondFigureColor, ShadowEnum shadow, Double strokeThickness, StrokeStyleEnum stroke) {
        this.figure = figure;
        this.figureColor = figureColor;
        this.shadow = shadow;
        this.secondFigureColor = secondFigureColor;
        this.strokeThickness = strokeThickness;
        this.strokeStyle = stroke;
    }

    public F getFigure() {
        return figure;
    }

    public Color getColor() {
        return figureColor;
    }

    public Color getSecondColor() {
        return secondFigureColor;
    }


    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void changeColor(Color color) {
         this.figureColor = color;
    }

    public void changeSecondColor(Color color) {
         this.secondFigureColor = color;
    }


    public void updateShadow(ShadowEnum shadow){ this.shadow = shadow; }

    public void updateStrokeStyle(StrokeStyleEnum stroke){ this.strokeStyle = stroke; }

    public void updateStrokeThickness(Double thickness){ this.strokeThickness = thickness; }

    public ShadowEnum getShadow(){
        return shadow;
    }

    public StrokeStyleEnum getStrokeStyle(){ return strokeStyle;}

    public Double getStrokeThickness(){ return strokeThickness; }


    public void handleSelection(GraphicsContext gc) {

        // Handle figure selection
        gc.setFill(figureColor);
        gc.setStroke(selected ? SELECTED_COLOR : LINE_COLOR);
        getStrokeStyle().applyStrokeStyle(gc);
    }


    public abstract DrawableFigure<? extends Figure> duplicateFigure();

    public abstract void draw(GraphicsContext gc);

}
