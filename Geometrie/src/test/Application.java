package test;

import figures.Cercle;
import figures.Point;

public class Application {

    public static void main(String[] args) {
        Cercle c1 = new Cercle(new Point(100,100), 50);
        Cercle c2 = new Cercle(new Point(150,150), new Point(200,200));

        Cercle geomtrieR1 = new Cercle(new Point(0,0), 4);
        Cercle geometrieR2 = new Cercle(new Point(10,0), 1);

/*        System.out.println("Perimetre"+ " "+c1.toString()+ " " +"est: " + c1.getPerimetre());

        System.out.println("Surface"+ " "+c2.toString()+ "est: " + c2.getSurface());

        // Verifier si un point appartient a l intersection des 2 cercles

        Point p = new Point(130,130);
        if(c1.appartient(p)&&c2.appartient(p))
        {
            System.out.println("Le point appartient");
        } else
        {
            System.out.println("Le point n appartient");
        }*/


        // Calculer les Valeurs de Y

/*        Point intersection = geomtrieR1.findOuterIntersectionPoint(geometrieR2);

        System.out.println("Coordonnees de l intersection: " + "X: " + intersection.getX() + " " + "Y: " + intersection.getY());*/

        Point tangentPoints = geomtrieR1.tangentPointsOfCircle(geometrieR2);

        Point R2Y = geometrieR2.tangentPointsOfCircle(geomtrieR1);

        System.out.println("Tangent Points of the Circle R1: " + "X:" + tangentPoints.getX() + " " + "Y: " + tangentPoints.getY());

        System.out.println("Tangent Points of the Circle R2: " + "X:" + geometrieR2.tangentPointsOfCircle(geomtrieR1).getX() + " " + "Y: " + geometrieR2.tangentPointsOfCircle(geomtrieR1).getY());

/*        for (double xStep = -10; xStep <15 ; xStep = xStep+0.01)
        {

            System.out.println("La valeur de xStep est:" + " " +xStep + " Et la Valeur Y R1 est: " + geomtrieR1.getValueY(xStep));
            System.out.println("******************** Les Valeurs de R2 **********************************");
            System.out.println("La valeur de xStep est:" + " " +xStep + " Et la Valeur Y R2 est: " + geometrieR2.getValueY(xStep));
        }*/
    }
}
