package backend.model;

public class Ellipse extends Figure {

    protected Point centerPoint;
    protected double sMayorAxis;
    protected double sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        super(new Point[]{centerPoint});
        this.centerPoint = centerPoint;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, DMayor: %.2f, DMenor: %.2f]", centerPoint, sMayorAxis, sMinorAxis);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getsMayorAxis() {
        return sMayorAxis;
    }

    public double getsMinorAxis() {
        return sMinorAxis;
    }

    @Override
    public boolean isReachable(Point selection) {
        return ((Math.pow(selection.getX() - this.getCenterPoint().getX(), 2) / Math.pow(this.getsMayorAxis(), 2)) +
                (Math.pow(selection.getY() - this.getCenterPoint().getY(), 2) / Math.pow(this.getsMinorAxis(), 2))) <= 0.30;
    }

    @Override
    public boolean isContained(Rectangle selectionRect) {
        return selectionRect.isReachable(new Point(getCenterPoint().getX() + getsMayorAxis() / 2,
                                getCenterPoint().getY())) &&
                selectionRect.isReachable(new Point(getCenterPoint().getX() - getsMinorAxis() / 2 ,
                        getCenterPoint().getY())) &&
                selectionRect.isReachable(new Point(getCenterPoint().getX(),
                        getCenterPoint().getY() + getsMinorAxis() / 2)) &&
                selectionRect.isReachable(new Point(getCenterPoint().getX(),
                        getCenterPoint().getY() - getsMinorAxis() / 2));

    }
}
