package frontend.ui.bars;

import backend.model.*;
import frontend.CanvasState;
import frontend.ui.buttons.*;
import frontend.ui.figures.DrawableEllipse;
import frontend.ui.figures.DrawableRectangle;
import frontend.ui.styles.ShadowEnum;
import frontend.ui.styles.StrokeStyleEnum;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javax.swing.plaf.basic.BasicDesktopIconUI;

public class SideBar extends VBox {

    // Botones Barra Izquierda
    private final ToggleButton deleteButton = new ToggleButton("Borrar");
    private final CanvasState canvasState;

    private ToggleGroup tools = new ToggleGroup();

    private final ColorPicker fillColorPicker = new ColorPicker(Color.YELLOW);
    private final ColorPicker secondaryColorPicker = new ColorPicker(Color.ORANGE);

    private final ChoiceBox<ShadowEnum> shadowButton = new ShadowButton();
    private final ChoiceBox<StrokeStyleEnum> strokeButton = new StrokeButton();
    private final Slider strokeSlider = new StrokeSlider();

    //Labels
    Label shadowLabel = new Label("Sombra");
    Label fillLabel = new Label("Relleno");
    Label strokeLabel = new Label("Borde");
    Label actionsLabel = new Label("Acciones");
    public SideBar(CanvasState canvasState) {
            super(10);
            this.canvasState = canvasState;
            setPadding(new Insets(5));
            setStyle("-fx-background-color: #999;");
            setPrefWidth(100);


        ToggleButton[] toolsArr = getToggleButtons(canvasState);

        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
        // TODO getActionsButtons for the actions (divide, duplicate, move to center)
        ToggleButton[] actionsArr = getActionsButtons(canvasState);
        for (ToggleButton tool : actionsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }

        getChildren().addAll(toolsArr);
        shadowLabel.setStyle("-fx-text-fill: white;");
        getChildren().add(shadowLabel);
        getChildren().add(shadowButton);
        fillLabel.setStyle("-fx-text-fill: white;");
        getChildren().add(fillLabel);
		getChildren().add(fillColorPicker);
        getChildren().add(secondaryColorPicker);
        fillLabel.setStyle("-fx-text-fill: white;");
        getChildren().add(strokeLabel);
        getChildren().add(strokeSlider);
        getChildren().add(strokeButton);fillLabel.setStyle("-fx-text-fill: white;");getChildren().add(actionsLabel); getChildren().addAll(actionsArr);
        setPadding(new Insets(5));
        setStyle("-fx-background-color: #282828");
        setPrefWidth(100);

    }

    private ToggleButton[] getToggleButtons(CanvasState canvasState) {
        FigureButton<Rectangle> rectangleButton = new FigureButton<>("Rectángulo", canvasState,
                (start, end) -> new DrawableRectangle<>(new Rectangle(start, end),fillColorPicker.getValue(), secondaryColorPicker.getValue(), shadowButton.getValue(), strokeSlider.getValue(), strokeButton.getValue())
        );

        FigureButton<Ellipse> ellipseButton = new FigureButton<>("Elipse", canvasState,
                (start, end) -> new DrawableEllipse<>(new Ellipse(
                    new Point(Math.abs(end.getX() + start.getX()) / 2, (Math.abs((end.getY() + start.getY())) / 2)),
                    Math.abs(end.getX() - start.getX()),
                    Math.abs(end.getY() - start.getY())), fillColorPicker.getValue(), secondaryColorPicker.getValue(), shadowButton.getValue(), strokeSlider.getValue(),strokeButton.getValue())
        );

        FigureButton<Square> squareButton = new FigureButton<>("Cuadrado", canvasState,
                (start, end) -> new DrawableRectangle<>(new Square(start, end.getX() - start.getX()),
                fillColorPicker.getValue(), secondaryColorPicker.getValue(), shadowButton.getValue(), strokeSlider.getValue(), strokeButton.getValue())
        );

        FigureButton<Circle> circleButton = new FigureButton<>("Círculo", canvasState,
                (start, end) -> new DrawableEllipse<>(new Circle(start, end.getX() - start.getX()),
                        fillColorPicker.getValue(), secondaryColorPicker.getValue(), shadowButton.getValue(), strokeSlider.getValue(), strokeButton.getValue())
        );

        // Other buttons
        SelectionButton selectionButton = new SelectionButton(canvasState);


        ToggleButton[] toolsArr = {selectionButton, circleButton, rectangleButton, ellipseButton, squareButton, deleteButton};
        return toolsArr;
    }

    public ChoiceBox<ShadowEnum> getShadowButton(){
        return this.shadowButton;
    }
    public ChoiceBox<StrokeStyleEnum> getStrokeButton(){
        return this.strokeButton;
    }
    public Slider getStrokeSlider(){
        return this.strokeSlider;
    }

    public ToggleGroup getTools() {
        return tools;
    }

    public Color getColorPicker() {
        return fillColorPicker.getValue();
    }
    public Color getSecondaryColorPicker() {
        return secondaryColorPicker.getValue();
    }

    public ColorPicker getColorPickerButton() {
        return fillColorPicker;
    }
    public ColorPicker getSecondaryColorPickerButton() {
        return secondaryColorPicker;
    }

    public ToggleButton getDeleteButton() {
        return deleteButton;
    }
    }