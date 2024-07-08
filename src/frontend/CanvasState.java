package frontend;


import backend.model.Figure;
import backend.model.Point;
import frontend.ui.figures.DrawableFigure;
import frontend.ui.styles.ShadowEnum;
import frontend.ui.styles.StrokeStyleEnum;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CanvasState {


    public static int LAYER_COUNT = 3;


    private final List<DrawableFigure<? extends Figure>> list = new ArrayList<>();
    private final List<Layer> layerList = new ArrayList<>();


    private DrawableFigure<? extends Figure> selectedFigure = null;

    public void addFigure(DrawableFigure figure, int layerIndex) {
        layerList.get(layerIndex).add(figure);
    }


    public List<DrawableFigure<? extends Figure>> figures() {
        List<DrawableFigure<? extends Figure>> shownFigures = new ArrayList<>();
        for (Layer layer : layerList) {
            if (layer.isShown()) {
                shownFigures.addAll(layer);
            }
        }
        return shownFigures;
    }


    public DrawableFigure getSelectedFigure() {
        return selectedFigure;
    }

    public void addLayer() {
        layerList.add(new Layer("Capa " + (CanvasState.LAYER_COUNT)));
    }

    public void deleteLayer(int layerIndex) {
        layerList.remove(layerIndex);
    }

//
//    public void deleteFigure(int index) {
//        for (DrawableFigure<? extends Figure> figure : figures()) {
//            if (figure == selectedFigure) {
//
//                for(Layer layer : layerList){
//                    layer.remove(figure);
//                }
//                clearSelectedFigure();
//                return;
//            }
//        }
//        clearSelectedFigure();
//    }

    public void addSelectedFigure(DrawableFigure<? extends Figure> figure) {
        selectedFigure = figure;
    }

    public void updateSelectedFigure(Color color, int layer, boolean isPrimaryColor) {
        for (DrawableFigure<? extends Figure> figure : getLayerList().get(layer)) {
            if (selectedFigure == figure) {
                if (isPrimaryColor)
                    figure.changeColor(color);
                else
                    figure.changeSecondColor(color);

            }
        }
    }


    public void clearSelectedFigure() {
        selectedFigure = null;
        for (DrawableFigure<? extends Figure> figure : figures()) {
            figure.setSelected(false);
        }
    }

    public boolean figureBelongs(DrawableFigure<? extends Figure> figure, Point eventPoint) {
        // temp
        if (figure.getFigure() == null)
            return false;
        else
            return figure.getFigure().isReachable(eventPoint);
    }


    public boolean noSelection() {
        return selectedFigure == null;
    }


    public void updateShadow(ShadowEnum shadow, int layer) {
        for (DrawableFigure<? extends Figure> figure : getLayerList().get(layer)) {
            if (selectedFigure == figure) {
                figure.updateShadow(shadow);
            }
        }
    }

    public void updateStrokeStyle(StrokeStyleEnum strokeStyle, int layer) {
        for (DrawableFigure<? extends Figure> figure : getLayerList().get(layer)) {
            if (selectedFigure == figure) {
                figure.updateStrokeStyle(strokeStyle);
            }
        }
    }

    public void updateStrokeThickness(Double thickness, int layer) {
        for (DrawableFigure<? extends Figure> figure : getLayerList().get(layer)) {
            if (selectedFigure == figure) {
                figure.updateStrokeThickness(thickness);
            }
        }
    }

    public List<Layer> getLayerListToShow() {
        List<Layer> ans = new ArrayList<>();
        for(Layer layer : layerList){
            if(layer.isShown()){
                ans.add(layer);
            }
        }
        return ans;
    }

    public List<Layer> getLayerList() {
        return layerList;
    }

    public int getLayerIndexByName(String name) {

        for (int i = 0; i < getLayerList().size(); i++) {
            System.out.println(name);
            Layer layer = getLayerList().get(i);

            if (layer.getName().equals(name)) {
                return i;
            }
        }

        // No se encontro layer, fallbackea a la layer 0
        return 0;
    }



}
