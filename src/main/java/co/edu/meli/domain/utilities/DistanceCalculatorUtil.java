package co.edu.meli.domain.utilities;

import co.edu.meli.domain.model.Point;

public final class DistanceCalculatorUtil {

    public static double calculate(Point first, Point second) {
        double deltaX = findDelta(second.getEast(), first.getEast());
        double deltaY = findDelta(second.getNorth(), first.getNorth());
        return Math.sqrt(deltaX + deltaY);
    }

    public static double findDelta(double a1, double a2) {
        return Math.pow((a1 - a2), 2);
    }
}
