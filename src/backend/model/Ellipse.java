package backend.model;

public class Ellipse extends Figure {

    protected Point centerPoint;
    protected double sMayorAxis;
    protected double sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        super(new Point[]{centerPoint});
        this.centerPoint = centerPoint;
        this.sMayorAxis = Math.abs(sMayorAxis);
        this.sMinorAxis = Math.abs(sMinorAxis);
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, DMayor: %.2f, DMenor: %.2f]", centerPoint, sMayorAxis, sMinorAxis);
    }


    @Override
    public Point getCenter() {
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
        return ((Math.pow(selection.getX() - this.getCenter().getX(), 2) / Math.pow(this.getsMayorAxis(), 2)) +
                (Math.pow(selection.getY() - this.getCenter().getY(), 2) / Math.pow(this.getsMinorAxis(), 2))) <= 0.30;
    }

    @Override
    public boolean isContained(Rectangle selectionRect) {
        return selectionRect.isReachable(new Point(getCenter().getX() + getsMayorAxis() / 2,
                                getCenter().getY())) &&
                selectionRect.isReachable(new Point(getCenter().getX() - getsMinorAxis() / 2 ,
                        getCenter().getY())) &&
                selectionRect.isReachable(new Point(getCenter().getX(),
                        getCenter().getY() + getsMinorAxis() / 2)) &&
                selectionRect.isReachable(new Point(getCenter().getX(),
                        getCenter().getY() - getsMinorAxis() / 2));

    }


    @Override
    public Ellipse duplicate() {
        return new Ellipse(new Point(centerPoint.getX() + 10, centerPoint.getY() + 10), sMayorAxis , sMinorAxis);
    }

    @Override
    public Ellipse[] divide(){
        double centerX1 = centerPoint.getX() + (getsMayorAxis()/2)/2;
        double centerX2 = centerPoint.getX() - (getsMayorAxis()/2)/2;
        return new Ellipse[] {
                new Ellipse(new Point(centerX1, centerPoint.getY()), sMayorAxis/2 , sMinorAxis/2),
                new Ellipse(new Point(centerX2, centerPoint.getY()), sMayorAxis/2 , sMinorAxis/2)
        };
    }

    @Override
    public void center(double maxWidth, double maxHeight) {
        centerPoint.setX(maxWidth*0.5);
        centerPoint.setY(maxHeight*0.5);
    }


}
