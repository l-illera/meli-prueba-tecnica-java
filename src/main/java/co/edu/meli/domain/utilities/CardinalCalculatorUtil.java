package co.edu.meli.domain.utilities;

import co.edu.meli.domain.model.Point;

public class CardinalCalculatorUtil {

    public static double north(double azimut, double distSpaceship, Point satellite) {
        return satellite.getNorth() + Math.cos(Math.toRadians(azimut)) * distSpaceship;
    }

    public static double east(double azimut, double distSpaceship, Point satellite) {
        return satellite.getEast() + Math.sin(Math.toRadians(azimut)) * distSpaceship;
    }
}
