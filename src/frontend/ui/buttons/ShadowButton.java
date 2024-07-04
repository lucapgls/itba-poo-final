package frontend.ui.buttons;
import frontend.ui.styles.ShadowEnum;
import javafx.collections.FXCollections;

public class ShadowButton extends DropDownButton<ShadowEnum> {
    private static final String BUTTON_NAME = "Sombra";

    public ShadowButton() {
        super(BUTTON_NAME);

        setItems(FXCollections.observableArrayList(ShadowEnum.values()));
        setValue(ShadowEnum.NONE);
    }


}