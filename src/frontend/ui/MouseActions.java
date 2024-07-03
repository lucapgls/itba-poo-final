package frontend.ui;

import backend.model.Point;

public interface MouseActions {

    default void onMousePressed(Point point) {}
    default void onMouseReleased(Point point) {}

    default void onMouseDragged(Point point) {}

    default void onMouseMoved(Point point) {}

    default void onMouseClicked(Point point) {}
}