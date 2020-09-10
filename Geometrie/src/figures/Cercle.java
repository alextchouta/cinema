package figures;

public class Cercle {

    private Point centre;
    private double rayon;

    public Cercle(Point centre, double rayon) {
        this.centre = centre;
        this.rayon = rayon;
    }

    public Cercle(Point centre, Point p) {
        this.centre = centre;
        this.rayon = this.centre.distance(p);
    }

    public Point getCentre() {
        return centre;
    }

    public double getRayon() {
        return rayon;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    public double getSurface() {
        return (Math.PI * this.getRayon() * this.getRayon());
    }

    public double getPerimetre() {
        return 2 * Math.PI * this.getRayon();
    }

    public boolean appartient(Point point) {
        double distance = this.centre.distance(point);

        return (distance <= this.getRayon());
    }

    public Point findOuterIntersectionPoint(Cercle cercle)
    {
        double xp = this.getCentre().getX() * cercle.getRayon()-cercle.getCentre().getX() * this.getRayon();
        double yp = this.getCentre().getY() * cercle.getRayon()-cercle.getCentre().getY() * this.getRayon();

        double denominateur = Math.abs(this.getRayon() - cercle.getRayon());

        return new Point(xp / denominateur, yp / denominateur);

    }


    public double getValueY(double xStep) {
        return Math.sqrt((this.getRayon() * this.getRayon()) - (Math.pow(xStep - this.getCentre().getX(), 2)));
    }

    public Point tangentPointsOfCircle(Cercle cercle) {


        Point findIntersectionPoint = cercle.findOuterIntersectionPoint(this);

        double xDiff = findIntersectionPoint.getX() - this.getCentre().getX();
        double yDiff = findIntersectionPoint.getY() - this.getCentre().getY();

        double constantSquare = Math.pow(xDiff,2) + Math.pow(yDiff,2) - Math.pow(this.getRayon(),2);

        double denominateur = (xDiff * xDiff) + (yDiff * yDiff);

        double xTang = (((Math.pow(this.getRayon(),2)* xDiff)
                +(this.getRayon() * (yDiff)
                * Math.sqrt(constantSquare)))
                / (denominateur))
                + this.getCentre().getX();

        double yTang = (((Math.pow(this.getRayon(),2)* yDiff)
                -+(this.getRayon() * (xDiff)
                * Math.sqrt(constantSquare)))
                / (denominateur))
                + this.getCentre().getY();
        return new Point(xTang, yTang);
    }

    @Override
    public String toString() {
        return "Cercle{" +
                "centre=" + " X: " + " " + centre.getX() + " " + "Y:" + " " + centre.getY() +
                ", rayon=" + rayon +
                '}';
    }
}
