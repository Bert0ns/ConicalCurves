package org.com;

import java.util.Arrays;

public class Point3D {
    Fraction[] coord;
    public Point3D()
    {
        coord = new Fraction[3];
    }

    /**
     * Creates a Point3D Object
     * @param coordinates homogeneous coordinates of a point [o,x,y]
     */
    public Point3D(Fraction[] coordinates) {
        if(coord.length != 3)
        {
            throw new IllegalArgumentException("Wrong number of coordinates for a Point3D");
        }
        this.coord = coordinates;
        reduceToMinimalForm();
    }

    /**
     * Simplifies the homogeneous coordinates in their minimal form
     */
    public void reduceToMinimalForm()
    {
        if(coord[0].doubleValue() == 0)
        {
            return;
        }

        coord[1].divide(coord[0]);
        coord[2].divide(coord[0]);
        coord[0].divide(coord[0]);
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "coordinates=" + Arrays.toString(coord) +
                '}';
    }
}
