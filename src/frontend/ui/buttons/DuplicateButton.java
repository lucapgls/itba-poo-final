package frontend.ui.buttons;

import backend.model.Figure;
import backend.model.Point;
import frontend.CanvasState;
import frontend.ui.figures.DrawableFigure;
import javafx.scene.control.ToggleButton;

public class DuplicateButton extends ActionButton {

        private final CanvasState canvasState;

        private static final String BUTTON_NAME = "Duplicar";
        public DuplicateButton(CanvasState canvasState) {
            super(BUTTON_NAME);
            this.canvasState = canvasState;
        }



}
