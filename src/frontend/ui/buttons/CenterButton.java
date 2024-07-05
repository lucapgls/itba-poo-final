package frontend.ui.buttons;

import backend.model.Point;
import frontend.CanvasState;

public class CenterButton extends ActionButton {

        private static final String BUTTON_NAME = "Centrar";
        private final CanvasState canvasState;

        public CenterButton(CanvasState canvasState) {
            super(BUTTON_NAME);
            this.canvasState = canvasState;

        }

}
