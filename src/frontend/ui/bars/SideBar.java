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
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SideBar extends VBox {

    private final CanvasState canvasState;

    // Width of the toolbar
    private static final int TOOLBAR_WIDTH = 120;

    // Botones Barra Izquierda
    private final ToggleButton deleteButton;

    private final ToggleGroup tools = new ToggleGroup();
    private final ToggleGroup actions = new ToggleGroup();

    private final ColorPicker fillColorPicker = new ColorPicker(Color.YELLOW);
    private final ColorPicker secondaryColorPicker = new ColorPicker(Color.ORANGE);

    private final ChoiceBox<ShadowEnum> shadowButton = new ShadowButton();
    private final ChoiceBox<StrokeStyleEnum> strokeStyleButton = new StrokeButton();
    private final Slider strokeSlider = new StrokeSlider();

    //Labels
    Label toolsLabel = new Label("Herramientas");
    Label shadowLabel = new Label("Sombra");
    Label fillLabel = new Label("Relleno");
    Label strokeLabel = new Label("Borde");
    Label actionsLabel = new Label("Acciones");
    public SideBar(CanvasState canvasState) {
            super(10);
            this.canvasState = canvasState;
            this.deleteButton = new DeleteButton(canvasState);
            setPadding(new Insets(3));
            setStyle("-fx-background-color: #282828");
            setPrefWidth(TOOLBAR_WIDTH);

            initializeToolsButtons();
            initializeColorPickers();
            initializeShadowControls();
            initializeStrokeControls();
            initializeActionButtons();
    }

    private void initializeColorPickers() {
        initializeWithStyle(fillLabel, fillColorPicker, secondaryColorPicker);
    }

    private void initializeShadowControls() {
        initializeWithStyle(shadowLabel, shadowButton);
    }

    private void initializeStrokeControls() {
        initializeWithStyle(strokeLabel, strokeSlider, strokeStyleButton);
    }

    private void initializeToolsButtons() {
        initializeWithStyle(toolsLabel, tools, getToggleButtons(canvasState));
    }

    private void initializeActionButtons() {
        initializeWithStyle(actionsLabel, actions, getActionsButtons(canvasState));
    }

    private void initializeWithStyle(Label label, ToggleGroup group, ToggleButton... buttons) {
        for (ToggleButton button : buttons) {
            button.setPrefWidth(TOOLBAR_WIDTH);
            button.setToggleGroup(group);
            button.setCursor(Cursor.HAND);
        }
        initializeWithStyle(label, buttons);
    }

    private void initializeWithStyle(Label label, Node... nodes) {
        String toolbarButtonStyle = "-fx-background-color: #282828; " +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;";
        label.setStyle(toolbarButtonStyle);
        getChildren().add(label);
        for (Node node : nodes) {
            getChildren().add(node);
        }
    }

    // Getters

    private ToggleButton[] getActionsButtons(CanvasState canvasState){
        DuplicateButton duplicateButton = new DuplicateButton(canvasState);
        DivideButton divideButton = new DivideButton(canvasState);
        CenterButton centerButton = new CenterButton(canvasState);
        return new ToggleButton[]{duplicateButton, divideButton, centerButton};
    }

    private ToggleButton[] getToggleButtons(CanvasState canvasState) {
        FigureButton<Rectangle> rectangleButton = new FigureButton<>("Rectángulo", canvasState,
                (start, end) -> new DrawableRectangle<>(new Rectangle(start, end),fillColorPicker.getValue(), secondaryColorPicker.getValue(), shadowButton.getValue(), strokeSlider.getValue(), strokeStyleButton.getValue())
        );

        FigureButton<Ellipse> ellipseButton = new FigureButton<>("Elipse", canvasState,
                (start, end) -> new DrawableEllipse<>(new Ellipse(
                    new Point(Math.abs(end.getX() + start.getX()) / 2, (Math.abs((end.getY() + start.getY())) / 2)),
                    Math.abs(end.getX() - start.getX()),
                    Math.abs(end.getY() - start.getY())), fillColorPicker.getValue(), secondaryColorPicker.getValue(), shadowButton.getValue(), strokeSlider.getValue(), strokeStyleButton.getValue())
        );

        FigureButton<Square> squareButton = new FigureButton<>("Cuadrado", canvasState,
                (start, end) -> new DrawableRectangle<>(new Square(start, end.getX() - start.getX()),
                fillColorPicker.getValue(), secondaryColorPicker.getValue(), shadowButton.getValue(), strokeSlider.getValue(), strokeStyleButton.getValue())
        );

        FigureButton<Circle> circleButton = new FigureButton<>("Círculo", canvasState,
                (start, end) -> new DrawableEllipse<>(new Circle(start, end.getX() - start.getX()),
                        fillColorPicker.getValue(), secondaryColorPicker.getValue(), shadowButton.getValue(), strokeSlider.getValue(), strokeStyleButton.getValue())
        );

        // Other buttons
        SelectionButton selectionButton = new SelectionButton(canvasState);


        return new ToggleButton[]{selectionButton, circleButton, rectangleButton, ellipseButton, squareButton, deleteButton};
    }


    public ChoiceBox<ShadowEnum> getShadowButton(){
        return this.shadowButton;
    }
    public ChoiceBox<StrokeStyleEnum> getStrokeStyleButton(){
        return this.strokeStyleButton;
    }
    public Slider getStrokeSlider(){
        return this.strokeSlider;
    }

    public ToggleGroup getTools() {
        return tools;
    }

    public ToggleGroup getActions(){
        return actions;
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

    // TODO remove ui/buttons that have no purpose of being its own class. and avoid casting them to togglebutton
    public ToggleButton getDuplicateButton() {
        return (ToggleButton) actions.getToggles().getFirst();
    }

    public ToggleButton getDivideButton() {
        return (ToggleButton) actions.getToggles().get(1);
    }

    public ToggleButton getCenterButton(){ 
        return (ToggleButton) actions.getToggles().get(2);
    }
    }