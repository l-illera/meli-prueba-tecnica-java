package co.edu.meli.domain.utilities;

import co.edu.meli.domain.model.Point;

public class AngleCalculatorUtil {

    public static double beta(double ab, double bc, double ac) {
        double r1 = (0.5 * Math.pow(ab, 2)) - (0.5 * Math.pow(bc, 2)) + (0.5 * Math.pow(ac, 2));
        double r2 = ab * ac;
        double arcocoseno = Math.acos(r1 / r2);
        return Math.toDegrees(arcocoseno);
    }

    public static double fi(Point first, Point second) {
        var deltaX = second.getEast() - first.getEast();
        var deltaY = second.getNorth() - first.getNorth();
        return Math.toDegrees(Math.atan(deltaX / deltaY));
    }

    public static double azimuts(Point first, Point second) {
        final double alfa = fi(first, second);
        return adjustAzimut(first, second, alfa);
    }

    private static double adjustAzimut(Point first, Point second, double alfa) {
        if (first.getEast() < second.getEast() && first.getNorth() > second.getNorth()) return 180 - alfa;
        else if (first.getEast() > second.getEast() && first.getNorth() > second.getNorth()) return 180 + alfa;
        else if (first.getEast() > second.getEast() && first.getNorth() < second.getNorth()) return 360 - alfa;
        else return alfa;
    }
}