package backend.model;

@FunctionalInterface
public interface Movable {

    void move(double deltaX, double deltaY);
}
