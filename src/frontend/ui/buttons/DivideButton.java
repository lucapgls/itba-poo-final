package frontend.ui.buttons;

import frontend.CanvasState;

public class DivideButton extends ActionButton {

    private final CanvasState canvasState;

    private static final String BUTTON_NAME = "Dividir";
    public DivideButton(CanvasState canvasState) {
        super(BUTTON_NAME);
        this.canvasState = canvasState;
    }



}