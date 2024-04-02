package org.com;

import org.apache.commons.numbers.fraction.Fraction;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;

public class PolarLine {
    final Fraction[] coefficients;
    final Point3D polar;

    public PolarLine(@NotNull Fraction[] coefficients, @NotNull Point3D polar) {
        if (coefficients.length != 3) {
            throw new IllegalArgumentException("Wrong number of coefficients to create a PolarLine");
        }
        this.coefficients = coefficients;
        this.polar = polar;
    }

    public PolarLine(@NotNull Matrix matrix, @NotNull Point3D polar) {
        this.polar = polar;
        this.coefficients = new Fraction[3];

        Fraction a1 = matrix.getData()[1][0].multiply(polar.coordinates[0]);
        Fraction a2 = matrix.getData()[1][1].multiply(polar.coordinates[1]);
        Fraction a3 = matrix.getData()[1][2].multiply(polar.coordinates[2]);

        Fraction b1 = matrix.getData()[2][0].multiply(polar.coordinates[0]);
        Fraction b2 = matrix.getData()[2][1].multiply(polar.coordinates[1]);
        Fraction b3 = matrix.getData()[2][2].multiply(polar.coordinates[2]);

        Fraction c1 = matrix.getData()[0][0].multiply(polar.coordinates[0]);
        Fraction c2 = matrix.getData()[0][1].multiply(polar.coordinates[1]);
        Fraction c3 = matrix.getData()[0][2].multiply(polar.coordinates[2]);

        coefficients[0] = c1.add(c2).add(c3);
        coefficients[1] = a1.add(a2).add(a3);
        coefficients[2] = b1.add(b2).add(b3);
    }

    @Override
    public String toString() {
        return "PolarLine{" +
                "coefficients=" + Arrays.toString(coefficients) +
                ", polar=" + polar +
                '}';
    }
    public String toString(boolean risToDouble) {
        if(!risToDouble)
        {
            return toString();
        }
        return "PolarLine{" + coefficients[0].toString() + "x0 + " + coefficients[1].toString() + "x1 + " + coefficients[2].toString() + "x2 = 0]" +
                          ", polar=" + polar.toString(true) +
                          "}";
    }
}
