package backend.model;

public abstract class Figure implements Movable, Reachable {

    private Point[] points;

    public Figure(Point[] points) {
        this.points = points;
    }

    @Override
    public void move(double diffX, double diffY) {
        // figure tiene lista de points
        for (Point p : points) {
            p.move(diffX, diffY);
        }
    }

    public void setPosition(double x, double y) {
        for (Point p : points) {
            p.setX(x);
            p.setY(y);
        }
    }

    public abstract Point getCenter();

    public abstract void center(double maxWidth, double maxHeight);
    public abstract Figure duplicate();
    public abstract Figure[] divide();

}
