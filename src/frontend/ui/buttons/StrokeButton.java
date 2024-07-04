package frontend.ui.buttons;

import frontend.ui.styles.StrokeStyleEnum;
import javafx.collections.FXCollections;

public class StrokeButton extends DropDownButton<StrokeStyleEnum> {

    private static final String BUTTON_NAME = "Borde";

    public StrokeButton() {
        super(BUTTON_NAME);

        setItems(FXCollections.observableArrayList(StrokeStyleEnum.values()));
        setValue(StrokeStyleEnum.NORMAL);
    }

}
