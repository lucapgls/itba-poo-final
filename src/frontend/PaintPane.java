package frontend;

import backend.model.*;
import frontend.ui.bars.SideBar;
import frontend.ui.bars.TopBar;
import frontend.ui.buttons.ActionButton;
import frontend.ui.figures.DrawableFigure;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaintPane extends BorderPane {

    // BackEnd
    private final CanvasState canvasState;

    // Canvas y relacionados
    private final Canvas canvas = new Canvas(800, 600);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();


    // Dibujar una figura
    private Point startPoint;


    // StatusBar

    // Colores de relleno de cada figura

    private final SideBar sideBar;
    private final TopBar topBar;

    public PaintPane(CanvasState canvasState, StatusPane statusPane) {
        this.canvasState = canvasState;
        //  this.statusPane = statusPane;

        sideBar = new SideBar(canvasState);
        topBar = new TopBar(canvasState);

        gc.setLineWidth(1);

        canvas.setOnMousePressed(event -> {
            startPoint = new Point(event.getX(), event.getY());


            // FIX ME Important casting togglebutton to action button generates exception
            // this is due to the fact that the togglebutton is not an action button
            // and this cannot be easily fixed since getTools().getSelectedToggle() returns a togglebutton (from javafx)
            // and not an action button (from our code).

            ActionButton button = (ActionButton) sideBar.getTools().getSelectedToggle();
            if (button != null) {
                button.onMousePressed(startPoint);
            }


        });


        canvas.setOnMouseReleased(event -> {
            Point endPoint = new Point(event.getX(), event.getY());
            if (startPoint == null) {
                return;
            }
            if (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
                return;
            }

            ActionButton button = (ActionButton) sideBar.getTools().getSelectedToggle();
            if (button != null) {
                button.onMouseReleased(endPoint);
            }
            ActionButton actionButton = (ActionButton) sideBar.getActions().getSelectedToggle();
            if (actionButton != null) {
                actionButton.onMouseReleased(endPoint);
            }

            redrawCanvas();
        });

        canvas.setOnMouseMoved(event -> {
            Point eventPoint = new Point(event.getX(), event.getY());
            boolean found = false;
            StringBuilder label = new StringBuilder();
            for (DrawableFigure<? extends Figure> figure : canvasState.figures()) {
                if (canvasState.figureBelongs(figure, eventPoint)) {
                    found = true;
                    label.append(figure.getFigure().toString());
                }

            }
            if (found) {
                statusPane.updateStatus(label.toString());
            } else {
                statusPane.updateStatus(eventPoint.toString());
            }
            redrawCanvas();

        });

        canvas.setOnMouseClicked(event -> {

            Point eventPoint = new Point(event.getX(), event.getY());
            //boolean found = false;
            //StringBuilder label = new StringBuilder("Se seleccionó: ");

            // Clicked but moved
            if (startPoint.getX() != eventPoint.getX() && startPoint.getY() != eventPoint.getY()) {
                return;
            }

            ActionButton button = (ActionButton) sideBar.getTools().getSelectedToggle();
            if (button != null) {
                button.onMouseClicked(eventPoint);
                if (!canvasState.noSelection()) {
                    DrawableFigure selectedFigure = canvasState.getSelectedFigure();
                    sideBar.getColorPickerButton().setValue(selectedFigure.getColor());
                    sideBar.getSecondaryColorPickerButton().setValue(selectedFigure.getSecondColor());
                    sideBar.getShadowButton().setValue(selectedFigure.getShadow());
                    sideBar.getStrokeStyleButton().setValue(selectedFigure.getStrokeStyle());
                    sideBar.getStrokeSlider().setValue(selectedFigure.getStrokeThickness());
                }

            }

//            if (found) {
//                statusPane.updateStatus(label.toString());
//            } else {
//                selectedFigure = null;
//                statusPane.updateStatus("Ninguna figura encontrada");
//            }
            redrawCanvas();

        });

        canvas.setOnMouseDragged(event -> {
            Point eventPoint = new Point(event.getX(), event.getY());

            ActionButton button = (ActionButton) sideBar.getTools().getSelectedToggle();
            if (button != null) {
                button.onMouseDragged(eventPoint);
                if (canvasState.getSelectedFigure() != null) {
                    canvasState.getSelectedFigure().move(eventPoint.getX() - startPoint.getX(), eventPoint.getY() - startPoint.getY());
                }
            }
            redrawCanvas();
        });


        sideBar.getDeleteButton().setOnAction(event -> {

            noSelectionAlert(canvasState);

            canvasState.deleteFigure();
            redrawCanvas();
        });

        sideBar.getDuplicateButton().setOnAction(event -> {
            noSelectionAlert(canvasState);

            DrawableFigure<? extends Figure> selectedFigure = canvasState.getSelectedFigure();
            if (selectedFigure != null) {
                DrawableFigure<? extends Figure> newFigure = selectedFigure.duplicateFigure();
                //    canvasState.addFigure(newFigure);
            }
            redrawCanvas();
        });


        sideBar.getDivideButton().setOnAction(event -> {
            noSelectionAlert(canvasState);

            DrawableFigure<? extends Figure> selectedFigure = canvasState.getSelectedFigure();
            if (selectedFigure != null) {
                DrawableFigure<? extends Figure>[] newFigures = selectedFigure.divideFigure();
                //  canvasState.addFigure(newFigures[0]);
                //  canvasState.addFigure(newFigures[1]);
                canvasState.deleteFigure();
            }
            redrawCanvas();
        });

        sideBar.getCenterButton().setOnAction(event -> {
            noSelectionAlert(canvasState);

            DrawableFigure<? extends Figure> selectedFigure = canvasState.getSelectedFigure();
            if (selectedFigure != null) {
                selectedFigure.centerFigure(canvas.getWidth(), canvas.getHeight());
            }
            redrawCanvas();

        });

        sideBar.getColorPickerButton().setOnAction(event -> {
            canvasState.updateSelectedFigure(sideBar.getColorPicker(), true);
            redrawCanvas();
        });

        sideBar.getSecondaryColorPickerButton().setOnAction(event -> {
            canvasState.updateSelectedFigure(sideBar.getSecondaryColorPicker(), false);
            redrawCanvas();
        });

        sideBar.getShadowButton().setOnAction(event -> {
            canvasState.updateShadow(sideBar.getShadowButton().getValue());
            redrawCanvas();
        });

        sideBar.getStrokeStyleButton().setOnAction(event -> {
            canvasState.updateStrokeStyle(sideBar.getStrokeStyleButton().getValue());
            redrawCanvas();
        });

        sideBar.getStrokeSlider().setOnMouseReleased(event -> {
            canvasState.updateStrokeThickness(sideBar.getStrokeSlider().getValue());
            redrawCanvas();
        });

        topBar.getLayerButton().setOnAction(event -> {
            String valueStr = topBar.getLayerButton().getValue(); // Assuming getValue() returns a string like "Capa 2"
            Pattern pattern = Pattern.compile("\\d+"); // Regular expression to find numbers
            Matcher matcher = pattern.matcher(valueStr);

            int num = 0;
            if (matcher.find()) {
                num = Integer.parseInt(matcher.group()); // Convert the first occurrence of number string to integer
            }

            sideBar.setLayer(num-1);

            topBar.setRadioButtons(canvasState.getFigureList().get(num-1).isShown());


        });

        topBar.getShowLayer().setOnAction(event -> {
            topBar.ShowLayer();
            redrawCanvas();
        });

        topBar.getHideLayer().setOnAction(event -> {
            topBar.HideLayer();
            redrawCanvas();
        });


        setTop(topBar);
        setLeft(sideBar);
        setRight(canvas);


    }

    void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Layer layer : canvasState.getFigureListToShow()) {
            for (DrawableFigure<? extends Figure> figure : layer) {
                figure.draw(gc);
            }
        }
    }

    private void noSelectionAlert(CanvasState c) {
        if (c.noSelection()) {
            alertInfo("No hay figuras seleccionadas");
            return;
        }
    }

    private void alertInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText(message);
        alert.setContentText("TPE Final POO Julio 2024");
        alert.showAndWait();
    }


}

