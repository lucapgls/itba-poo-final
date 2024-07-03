package frontend.ui.buttons;

import javafx.scene.control.Slider;

public class StrokeSlider extends Slider {
    private static final double min = 0;
    private static final double max = 10;
    public StrokeSlider(){
        super(min, max, 5);
        showTickLabelsProperty().set(true);

    }
}
