package frontend.ui.buttons;

import frontend.ui.MouseActions;
import javafx.scene.control.ToggleButton;

public abstract class ActionButton extends ToggleButton implements MouseActions {

    public ActionButton(String name) {
        super(name);
    }
}
