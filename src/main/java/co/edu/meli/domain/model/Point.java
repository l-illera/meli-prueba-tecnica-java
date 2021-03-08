package co.edu.meli.domain.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class Point {

    private final double east;
    private final double north;

    public Point(double east, double north) {
        this.east = BigDecimal.valueOf(east).setScale(1, RoundingMode.HALF_DOWN).doubleValue();
        this.north = BigDecimal.valueOf(north).setScale(1, RoundingMode.HALF_DOWN).doubleValue();
    }


    @Override
    public String toString() {
        return "{" +
                "east=" + east +
                ", north=" + north +
                '}';
    }
}
