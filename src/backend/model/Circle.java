package backend.model;

public class Circle extends Ellipse {

    private double radius;

    public Circle(Point centerPoint, double radius) {
        super(centerPoint, radius*2, radius*2);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", getCenterPoint(), radius);
    }

    @Override
    public boolean isReachable(Point selection) {
        return Math.sqrt(Math.pow(this.getCenterPoint().getX() - selection.getX(), 2) +
                Math.pow(this.getCenterPoint().getY() - selection.getY(), 2)) < this.getRadius();
    }

    @Override
    public boolean isContained(Rectangle selectionRect) {
        return super.isContained(selectionRect);
    }
}
