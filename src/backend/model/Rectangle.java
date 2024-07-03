package backend.model;

public class Rectangle extends Figure {

    private Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        super(new Point[]{topLeft, bottomRight});
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }


    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public boolean isReachable(Point selection) {
        return selection.getX() > this.getTopLeft().getX() && selection.getX() < this.getBottomRight().getX() &&
                selection.getY() > this.getTopLeft().getY() && selection.getY() < this.getBottomRight().getY();
    }

    @Override
    public boolean isContained(Rectangle selectionRect) {
        return selectionRect.isReachable(this.getTopLeft())
                && selectionRect.isReachable(this.getBottomRight());
    }
}
