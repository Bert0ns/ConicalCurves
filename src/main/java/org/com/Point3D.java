package org.com;

import org.jetbrains.annotations.NotNull;
import org.apache.commons.numbers.fraction.Fraction;
import java.util.Arrays;

public class Point3D {
    Fraction[] coordinates;

    public Point3D() {
        coordinates = new Fraction[3];
    }

    /**
     * Creates a Point3D Object
     *
     * @param coordinates homogeneous coordinates of a point [o,x,y]
     */
    public Point3D(@NotNull Fraction[] coordinates) {
        if (this.coordinates.length != 3) {
            throw new IllegalArgumentException("Wrong number of coordinates for a Point3D");
        }
        this.coordinates = coordinates;
        reduceToMinimalForm();
    }

    public Point3D(Fraction o, Fraction x, Fraction y) {
        coordinates = new Fraction[3];
        coordinates[0] = o;
        coordinates[1] = x;
        coordinates[2] = y;

        reduceToMinimalForm();
    }

    /**
     * Simplifies the homogeneous coordinates in their minimal form
     */
    public void reduceToMinimalForm() {
        if (coordinates[0].doubleValue() == 0) {
            return;
        }

        coordinates[1].divide(coordinates[0]);
        coordinates[2].divide(coordinates[0]);
        coordinates[0].divide(coordinates[0]);
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "coordinates=" + Arrays.toString(coordinates) +
                '}';
    }

    public String toString(boolean risToDouble) {
        if (!risToDouble) {
            return toString();
        }
        return "Point3D{" +
                "coordinates=" + "[" + coordinates[0].toString() + ", " + coordinates[1].toString() + ", " + coordinates[2].toString() + "]" +
                '}';
    }
}
