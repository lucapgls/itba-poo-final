package frontend.ui.bars;

import frontend.CanvasState;
import frontend.ui.buttons.LayerButton;
import frontend.ui.styles.ShadowEnum;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;


public class TopBar extends HBox {
    private final CanvasState canvasState;

    private final ChoiceBox<String> layerChoiceBox;

    public TopBar(CanvasState canvasState) {
        this.canvasState = canvasState;
        layerChoiceBox = new LayerButton(canvasState.layerList.size());
    }




}
