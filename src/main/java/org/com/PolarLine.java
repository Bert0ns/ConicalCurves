package org.com;

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

        Fraction a1 = new Fraction(matrix.getData()[1][0]).multiply(polar.coord[0]);
        Fraction a2 = new Fraction(matrix.getData()[1][1]).multiply(polar.coord[1]);
        Fraction a3 = new Fraction(matrix.getData()[1][2]).multiply(polar.coord[2]);

        Fraction b1 = new Fraction(matrix.getData()[2][0]).multiply(polar.coord[0]);
        Fraction b2 = new Fraction(matrix.getData()[2][1]).multiply(polar.coord[1]);
        Fraction b3 = new Fraction(matrix.getData()[2][2]).multiply(polar.coord[2]);

        Fraction c1 = new Fraction(matrix.getData()[0][0]).multiply(polar.coord[0]);
        Fraction c2 = new Fraction(matrix.getData()[0][1]).multiply(polar.coord[1]);
        Fraction c3 = new Fraction(matrix.getData()[0][2]).multiply(polar.coord[2]);

        coefficients[0] = c1.addFraction(c2).addFraction(c3);
        coefficients[1] = a1.addFraction(a2).addFraction(a3);
        coefficients[2] = b1.addFraction(b2).addFraction(b3);
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
        return "PolarLine{" + coefficients[0].toString(true) + "x0 + " + coefficients[1].toString(true) + "x1 + " + coefficients[2].toString(true) + "x2 = 0]" +
                          ", polar=" + polar.toString(true) +
                          "}";
    }
}
