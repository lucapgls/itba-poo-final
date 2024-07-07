package frontend;

import backend.model.Figure;
import frontend.ui.figures.DrawableFigure;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Layer extends ArrayList<DrawableFigure<? extends Figure>> {

    private boolean showLayer = true;
    String name;

    public Layer(String name) {
        this.name = name;
    }

    public void show() {
        this.showLayer = true;
    }

    public void hide() {
        this.showLayer = false;
    }

    public boolean isShown() {
        return this.showLayer;
    }

}