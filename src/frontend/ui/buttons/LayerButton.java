package frontend.ui.buttons;



import frontend.ui.buttons.DropDownButton;
import javafx.collections.FXCollections;

public class LayerButton extends DropDownButton<String> {
    private static final String BUTTON_NAME = "Capas";

    public LayerButton(int layers) {
        super(BUTTON_NAME);
        for(int i = 1; i<= layers; i++){
            getItems().add("Capa " + (i) ) ;
        }
        setValue(getItems().getFirst());
    }


}