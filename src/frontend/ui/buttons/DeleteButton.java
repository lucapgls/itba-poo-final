package frontend.ui.buttons;

import frontend.CanvasState;

public class DeleteButton extends ActionButton {

    private final CanvasState canvasState;

    private static final String BUTTON_NAME = "Borrar";
    public DeleteButton(CanvasState canvasState) {
        super(BUTTON_NAME);
        this.canvasState = canvasState;
    }
}
