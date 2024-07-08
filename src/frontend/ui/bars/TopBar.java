package frontend.ui.bars;

import frontend.CanvasState;
import frontend.Layer;
import frontend.ui.buttons.LayerButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TopBar extends HBox {
    private final CanvasState canvasState;

    private final ChoiceBox<String> layerChoiceBox;
    private final Button addLayer = new Button("Agregar Capa");
    private final Button deleteLayer = new Button("Eliminar Capa");


    private int layerCount = 3;
    private final RadioButton showLayer = new RadioButton("Mostrar");
    private final RadioButton hideLayer = new RadioButton("Ocultar");
    Label layersLabel = new Label("Capas");


    // TODO initializeWithStyle
    public TopBar(CanvasState canvasState) {
        this.canvasState = canvasState;
        layerChoiceBox = new LayerButton(3);
        setPadding(new Insets(3));
        setStyle("-fx-background-color: #282828");

        getChildren().add(layersLabel);
        layersLabel.setStyle("-fx-text-fill: white");
        HBox.setMargin(layersLabel, new Insets(0, 8, 0, 0));
        getChildren().add(layerChoiceBox);

        HBox.setMargin(layerChoiceBox, new Insets(0, 15, 0, 0));

        getChildren().add(showLayer);
        showLayer.setStyle("-fx-text-fill: white");

        HBox.setMargin(showLayer, new Insets(0, 15, 0, 0));

        getChildren().add(hideLayer);
        hideLayer.setStyle("-fx-text-fill: white");
        setAlignment(Pos.CENTER);
        HBox.setMargin(hideLayer, new Insets(0, 15, 0, 0));

        getChildren().add(addLayer);
        addLayer.setStyle("-fx-text-fill: black");
        HBox.setMargin(addLayer, new Insets(0, 15, 0, 0));

        getChildren().add(deleteLayer);
        deleteLayer.setStyle("-fx-text-fill: black");
        HBox.setMargin(deleteLayer, new Insets(0, 15, 0, 0));




        // Iniciales
        for(int i = 1; i <= layerCount; i++){
            Layer newLayer = new Layer("Capa " + (i));
            canvasState.getLayerList().add(newLayer);

            System.out.println(canvasState.getLayerList().size());
        }

        // Boton de showLayer debe empezar seleccionado para layer1
        showLayer.setSelected(canvasState.getLayerList().getFirst().isShown());
    }

    public ChoiceBox<String> getLayerButton() {
        return layerChoiceBox;
    }

    public RadioButton getShowLayer() {
        return showLayer;
    }

    public RadioButton getHideLayer() {
        return hideLayer;
    }

    public Button getAddLayer() {
        return addLayer;
    }

    public Button getDeleteLayer(){
        return deleteLayer;
    }

    public void setRadioButtons(boolean show) {
        showLayer.setSelected(show);
        hideLayer.setSelected(!show);
    }

    public void toggleLayer(RadioButton button, RadioButton other) {
        if (other != null && button != null) {
            button.setSelected(button.isSelected());
            other.setSelected(!button.isSelected());
        }

        String name = getLayerButton().getValue();
        int idx = canvasState.getLayerIndexByName(name);

        Layer layer = canvasState.getLayerList().get(idx);

        layer.toggleOnLayer();
    }
}

