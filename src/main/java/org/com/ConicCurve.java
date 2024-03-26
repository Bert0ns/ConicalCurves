package org.com;

import org.jetbrains.annotations.NotNull;

import static org.com.Matrix.getAxxMatrix;

public class ConicCurve {
    private final Matrix matrix;
    private final ConicCurveType type;
    private final Fraction[] center;
    public enum ConicCurveType
    {
        ELLIPSE,
        HYPERBOLE,
        PARABOLA,
        DEGENERATE
    }

    /**
     * Returns a ConicalCurve Object
     * @param matrix Associated Conical Curve' matrix
     */
    public ConicCurve(Matrix matrix)
    {
        this.matrix = matrix;
        type = determineType(this);
        center = determineCenter(this);
    }

    /**
     * Returns a ConicalCurve Object
     * @param coefficients coefficients in this order ax^2 + bxy + cy^2 + dx + ey + f = 0
     */
    public ConicCurve(Fraction @NotNull [] coefficients)
    {
        matrix = determineMatrix(coefficients);
        type = determineType(this);
        center = determineCenter(this);
    }
    @Override
    public String toString() {
        return "ConicCurve{\n" +
                "matrix:\n" + matrix +
                "type=" + type +
                "\ncenter=[" + center[0] + ", " + center[1] + ", " + center[2] + "]" +
                " }";
    }
    public String toString(boolean risToDouble) {
        return "ConicCurve{\n" +
                "matrix:\n" + matrix.toString(risToDouble) +
                "type=" + type +
                "\ncenter=[" + center[0].doubleValue() + ", " + center[1].doubleValue() + ", " + center[2].doubleValue() + "]" +
                " }";
    }

    /**
     * Returns the Matrix associated to the Conic Curve with the specified coefficients
     * @param coefficients coefficients in this order ax^2 + bxy + cy^2 + dx + ey + f = 0
     */
    public static Matrix determineMatrix(Fraction @NotNull [] coefficients)
    {
        if(coefficients.length != 6)
        {
            throw new IllegalArgumentException("Coefficient array has too many coefficients");
        }
        Fraction[][] dataMatrix = new Fraction[3][3];
        /*
        ax^2 + bxy + cy^2 + dx + ey + f = 0

	    f	 d/2  e/2
	    d/2	  a	  b/2
	    e/2  b/2   c
	    */
        dataMatrix[0][0] = coefficients[5];
        dataMatrix[1][1] = coefficients[0];
        dataMatrix[2][2] = coefficients[2];

        dataMatrix[0][1] = dataMatrix[1][0] = coefficients[3].divide(new Fraction(2));
        dataMatrix[0][2] = dataMatrix[2][0] = coefficients[4].divide(new Fraction(2));
        dataMatrix[1][2] = dataMatrix[2][1] = coefficients[1].divide(new Fraction(2));

        return new Matrix(dataMatrix);
    }

    /**
     * Given the conic curve it determines the type
     * @param conicCurve Conic curve
     * @return enum that has the type of the conic curve;
     */
    public static ConicCurveType determineType(@NotNull ConicCurve conicCurve)
    {
        if(conicCurve.matrix.getNrows() != conicCurve.matrix.getNcols())
        {
            throw new IllegalArgumentException("The matrix provided is not squared");
        }
        else if(conicCurve.matrix.getNcols() != 3)
        {
            throw new IllegalArgumentException("The matrix provided is not 3x3");
        }

        if(conicCurve.matrix.determinant().doubleValue() == 0)
        {
            return ConicCurveType.DEGENERATE;
        }

        Matrix a00 = getAxxMatrix(conicCurve.matrix, 0, 0);
        Fraction det_a00 = a00.determinant();

        if(det_a00.doubleValue() > 0)
        {
            return ConicCurveType.ELLIPSE;
        }
        else if(det_a00.doubleValue() < 0)
        {
            return ConicCurveType.HYPERBOLE;
        }

        return ConicCurveType.PARABOLA;
    }
    public static Fraction @NotNull [] determineCenter(@NotNull ConicCurve conicCurve)
    {
        Fraction[] center = new Fraction[3];

        center[0] = getAxxMatrix(conicCurve.matrix, 0,0).determinant();
        center[1] = getAxxMatrix(conicCurve.matrix, 0,1).determinant().multiply(new Fraction(-1));
        center[2] = getAxxMatrix(conicCurve.matrix, 0,2).determinant();

        if(center[0].doubleValue() != 0)
        {
            center[1].divide(center[0]);
            center[2].divide(center[0]);
            center[0].divide(center[0]);
        }

        return center;
    }
}
