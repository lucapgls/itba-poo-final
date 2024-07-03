package frontend.ui.buttons;

import javafx.collections.FXCollections;

public class StrokeButton extends DropDownButton{

    private static final String BUTTON_NAME = "Borde";
    private static final String[] strokes = {"Normal", "Punteado Simple", "Punteado Complejo"};

    public StrokeButton() {
        super(BUTTON_NAME);
        setItems(FXCollections.observableArrayList(strokes));
        setValue(strokes[0]);
    }

}
