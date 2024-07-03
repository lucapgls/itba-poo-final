package frontend.ui.buttons;
import javafx.collections.FXCollections;

public class ShadowButton extends DropDownButton {
    private static final String BUTTON_NAME = "Sombra";
    private static final String[] shadows = {"Ninguna", "Simple", "Coloreada", "Simple Inversa", "Coloreada Inversa"};

    public ShadowButton() {
        super(BUTTON_NAME);
        setItems(FXCollections.observableArrayList(shadows));
        setValue(shadows[0]);
    }


}