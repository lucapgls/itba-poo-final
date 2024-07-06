package frontend;

import backend.model.Figure;
import frontend.ui.figures.DrawableFigure;

import java.util.ArrayList;

public class Layer extends ArrayList<DrawableFigure<? extends Figure>> {

    private boolean showLayer = true;

    public Layer(){

    }

  public void show(){
        this.showLayer = true;
  }

  public void hide(){
        this.showLayer = false;
  }

  public boolean isShown(){
        return this.showLayer;
  }



}
