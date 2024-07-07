package frontend.ui.bars;

import frontend.CanvasState;
import frontend.ui.buttons.LayerButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;


public class TopBar extends HBox {
    private final CanvasState canvasState;

    private final ChoiceBox<String> layerChoiceBox;

    public TopBar(CanvasState canvasState) {
        this.canvasState = canvasState;
        layerChoiceBox = new LayerButton(canvasState.layerList.size());
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
        showLayer.setSelected(false);
        String valueStr = getLayerButton().getValue(); // Assuming getValue() returns a string like "Capa 2"
        Pattern pattern = Pattern.compile("\\d+"); // Regular expression to find numbers
        Matcher matcher = pattern.matcher(valueStr);

        int num = 0;
        if (matcher.find()) {
            num = Integer.parseInt(matcher.group()); // Convert the first occurrence of number string to integer
        }


        canvasState.getFigureList().get(num-1).hide();
    }

    public void ShowLayer() {
        hideLayer.setSelected(false);
        String valueStr = getLayerButton().getValue(); // Assuming getValue() returns a string like "Capa 2"
        Pattern pattern = Pattern.compile("\\d+"); // Regular expression to find numbers
        Matcher matcher = pattern.matcher(valueStr);

        int num = 0;
        if (matcher.find()) {
            num = Integer.parseInt(matcher.group()); // Convert the first occurrence of number string to integer
        }

        canvasState.getFigureList().get(num-1).show();

    }


}
