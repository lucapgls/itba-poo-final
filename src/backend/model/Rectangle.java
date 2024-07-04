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


    public Rectangle duplicate() {
        return new Rectangle(new Point(topLeft.getX() + 10, topLeft.getY() + 10), new Point(bottomRight.getX() + 10, bottomRight.getY() + 10));
    }

    public Rectangle divide(){
        //primero divido este rectangulo y dsp devuelvo el nuevo
        double newTopLeftX = topLeft.getX() + (bottomRight.getX() - topLeft.getX())/2;
        double newTopLeftY = topLeft.getY() - (topLeft.getY() - bottomRight.getY())/4;
        double newBottomRightY = bottomRight.getY() + (topLeft.getY() - bottomRight.getY())/4;
        Rectangle ans = new Rectangle(new Point(newTopLeftX, newTopLeftY), new Point(bottomRight.getX(), newBottomRightY));

        double newBottomRightX = bottomRight.getX() - (bottomRight.getX() - topLeft.getX())/2;

        this.topLeft = new Point(topLeft.getX(), newTopLeftY);
        this.bottomRight = new Point(newBottomRightX, newBottomRightY);

        return ans;

    }
}
