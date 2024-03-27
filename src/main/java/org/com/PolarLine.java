package org.com;

import org.jetbrains.annotations.NotNull;

public class PolarLine {
    final Fraction[] coefficients;
    final Point3D polar;


    public PolarLine(@NotNull Fraction[] coefficients, @NotNull Point3D polar) {
        this.coefficients = coefficients;
        this.polar = polar;
    }

    public PolarLine(@NotNull Matrix matrix, @NotNull Point3D polar)
    {
        this.polar = polar;
        this.coefficients = null;



    }


}
