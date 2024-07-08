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
    public Point getCenter(){
        return new Point((topLeft.getX()+bottomRight.getX())*0.5, (topLeft.getY()+bottomRight.getY())*0.5);
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

    @Override
    public void center(double maxWidth, double maxHeight) {

        double offsetX = (bottomRight.getX() - topLeft.getX())*0.5;
        double offsetY = (bottomRight.getY() - topLeft.getY())*0.5;

        topLeft.setX(maxWidth*0.5 - offsetX);
        topLeft.setY(maxHeight*0.5 - offsetY);
        bottomRight.setX(maxWidth*0.5 + offsetX);
        bottomRight.setY(maxHeight*0.5 + offsetY);

    }

    public Rectangle[] divide(){
        //primero divido este rectangulo y dsp devuelvo el nuevo
       double newTopLeftX = topLeft.getX() + (bottomRight.getX() - topLeft.getX())/2;
        double newTopLeftY = topLeft.getY() - (topLeft.getY() - bottomRight.getY())/4;
        double newBottomRightX = bottomRight.getX() - (bottomRight.getX() - topLeft.getX())/2;
        double newBottomRightY = bottomRight.getY() + (topLeft.getY() - bottomRight.getY())/4;
        return new Rectangle[]{
                new Rectangle(new Point(newTopLeftX, newTopLeftY), new Point(bottomRight.getX(), newBottomRightY)),
                new Rectangle(new Point(topLeft.getX(), newTopLeftY), new Point(newBottomRightX, newBottomRightY))

        };
    }

    @Override
    public void move(double diffX, double diffY) {
//        super.move(diffX, diffY);
        topLeft.move(diffX, diffY);
        bottomRight.move(diffX, diffY);
    }


}
