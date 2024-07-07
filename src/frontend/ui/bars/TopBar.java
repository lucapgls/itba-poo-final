package frontend.ui.bars;

import frontend.CanvasState;
import frontend.Layer;
import frontend.ui.buttons.LayerButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TopBar extends HBox {
    private final CanvasState canvasState;

    private final ChoiceBox<String> layerChoiceBox;

    private final RadioButton showLayer = new RadioButton("Mostrar");
    private final RadioButton hideLayer = new RadioButton("Ocultar");

    public TopBar(CanvasState canvasState) {
        this.canvasState = canvasState;
        layerChoiceBox = new LayerButton(3);
        setPadding(new Insets(3));
        setStyle("-fx-background-color: #282828");
        getChildren().add(layerChoiceBox);
        getChildren().add(showLayer);
        showLayer.setStyle("-fx-text-fill: white");
        getChildren().add(hideLayer);
        hideLayer.setStyle("-fx-text-fill: white");
        setAlignment(Pos.CENTER);

        canvasState.getFigureList().add(new Layer("Capa 1"));
        canvasState.getFigureList().add(new Layer("Capa 2"));
        canvasState.getFigureList().add(new Layer("Capa 3"));

        // Use get(0) instead of getFirst()
        showLayer.setSelected(canvasState.getFigureList().getFirst().isShown());
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

    public void setRadioButtons(boolean show) {
        showLayer.setSelected(show);
        hideLayer.setSelected(!show);
    }


    public void HideLayer() {
        showLayer.setSelected(!showLayer.isSelected());
        String name = getLayerButton().getValue(); // Assuming getValue() returns a string like "Capa 2"
        int num = getLayerByName(name);

        if(hideLayer.isSelected())
        canvasState.getFigureList().get(num-1).hide();
        else if(!hideLayer.isSelected())
        canvasState.getFigureList().get(num-1).show();

    }

    public void ShowLayer() {
        hideLayer.setSelected(!showLayer.isSelected());
        String name = getLayerButton().getValue(); // Assuming getValue() returns a string like "Capa 2"
        int num = getLayerByName(name);

        if(showLayer.isSelected())
        canvasState.getFigureList().get(num-1).show();
        else if(!showLayer.isSelected())
        canvasState.getFigureList().get(num-1).hide();

    }

    private int getLayerByName(String name){
        Pattern pattern = Pattern.compile("\\d+"); // Regular expression to find numbers
        Matcher matcher = pattern.matcher(name);

        int num = 0;
        if (matcher.find()) {
            num = Integer.parseInt(matcher.group()); // Convert the first occurrence of number string to integer
        }
        return num;
    }

}
