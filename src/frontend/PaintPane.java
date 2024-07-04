package frontend;

import backend.model.*;
import frontend.ui.bars.SideBar;
import frontend.ui.buttons.ActionButton;
import frontend.ui.figures.DrawableFigure;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

public class PaintPane extends BorderPane {

    // BackEnd
    private final CanvasState canvasState;

    // Canvas y relacionados
    private final Canvas canvas = new Canvas(800, 600);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();



    // Dibujar una figura
    private Point startPoint;


    // StatusBar
    //private final StatusPane statusPane;

    // Colores de relleno de cada figura
   // private final Map<DrawableFigure, Color> figureColorMap = new HashMap<>();

    private final SideBar sideBar;

    public PaintPane(CanvasState canvasState, StatusPane statusPane) {
        this.canvasState = canvasState;
      //  this.statusPane = statusPane;

        sideBar = new SideBar(canvasState);

        gc.setLineWidth(1);

        canvas.setOnMousePressed(event -> {
            startPoint = new Point(event.getX(), event.getY());


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
                if(!canvasState.noSelection()){
                    DrawableFigure selectedFigure = canvasState.getSelectedFigure();
                    sideBar.getColorPickerButton().setValue(selectedFigure.getColor());
                    sideBar.getSecondaryColorPickerButton().setValue(selectedFigure.getSecondColor());
                    sideBar.getShadowButton().setValue(selectedFigure.getShadow());
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
            }
            redrawCanvas();
        });


        sideBar.getDeleteButton().setOnAction(event -> {

            if(canvasState.noSelection()) {
                alertInfo("No hay figuras seleccionadas");
                return;
            }

                canvasState.deleteFigure();
                redrawCanvas();
        });

        sideBar.getDuplicateButton().setOnAction(event -> {
            if(canvasState.noSelection()) {
                alertInfo("No hay figuras seleccionadas");
                return;
            }

            DrawableFigure<? extends Figure> selectedFigure = canvasState.getSelectedFigure();
            if (selectedFigure != null) {
                DrawableFigure<? extends Figure> newFigure = selectedFigure.duplicateFigure();
                canvasState.addFigure(newFigure);
            }
            redrawCanvas();
        });

        sideBar.getColorPickerButton().setOnAction(event -> {
            canvasState.updateSelectedFigures(sideBar.getColorPicker(), true);
            redrawCanvas();
        });

        sideBar.getSecondaryColorPickerButton().setOnAction(event -> {
            canvasState.updateSelectedFigures(sideBar.getSecondaryColorPicker() , false);
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



        setLeft(sideBar);
        setRight(canvas);


    }

    void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (DrawableFigure<? extends Figure> figure : canvasState.figures()) {

            figure.draw(gc);

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

