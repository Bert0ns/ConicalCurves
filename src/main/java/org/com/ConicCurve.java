package org.com;

import org.jetbrains.annotations.NotNull;

import static org.com.Matrix.getMxxMatrix;

public class ConicCurve {
    private final Matrix matrix;
    private final ConicCurveType type;
    private final Point3D center;
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

        Point3D[] p = determinePointstoTheInfinity(this);
    }
    @Override
    public String toString() {
        return "ConicCurve{\n" +
                "matrix:\n" + matrix +
                "type=" + type +
                "\ncenter=" + center +
                " }";
    }
    public String toString(boolean risToDouble) {
        return "ConicCurve{\n" +
                "matrix:\n" + matrix.toString(risToDouble) +
                "type=" + type +
                "\ncenter=" + center +
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
    public static ConicCurveType determineType(@NotNull final ConicCurve conicCurve)
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

        Matrix a00 = getMxxMatrix(conicCurve.matrix, 0, 0);
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
    public static @NotNull Point3D determineCenter(@NotNull final ConicCurve conicCurve)
    {
        Point3D center = new Point3D();
        center.coord[0] = getMxxMatrix(conicCurve.matrix, 0,0).determinant();
        center.coord[1] = getMxxMatrix(conicCurve.matrix, 0,1).determinant().multiply(new Fraction(-1));
        center.coord[2] = getMxxMatrix(conicCurve.matrix, 0,2).determinant();
        center.reduceToMinimalForm();
        return center;
    }

    public static Point3D @NotNull [] determinePointstoTheInfinity(final @NotNull ConicCurve conicCurve)
    {
        Point3D[] inifinityPoints = new Point3D[2];
        if(conicCurve.type != ConicCurveType.HYPERBOLE)
        {
            //throw new IllegalArgumentException("The conic curve has to be an HYPERBOLE");
            inifinityPoints[0] = new Point3D(new Fraction(0), new Fraction(0), new Fraction(0));
            inifinityPoints[1] = new Point3D(new Fraction(0), new Fraction(0), new Fraction(0));
            return inifinityPoints;
        }


        //ax^2 + bxy + cy^2
        /*
        ax^2 + bxy + cy^2 + dx + ey + f = 0

	    f	 d/2  e/2
	    d/2	  a	  b/2
	    e/2  b/2   c

	    [1][1] -> a
	    [2][2] -> c
	    [1][2] * 2 -> b
	    */
        Fraction coefa = new Fraction(conicCurve.matrix.getData()[0][0]);
        Fraction coefb = new Fraction(conicCurve.matrix.getData()[1][2]).multiply(new Fraction(2));
        Fraction coefc = new Fraction(conicCurve.matrix.getData()[2][2]);

        Fraction[] sol = solSecondGradeEquation(coefa, coefb, coefc);

        inifinityPoints[0] = new Point3D(new Fraction(0), new Fraction(sol[0]), new Fraction(1));
        inifinityPoints[1] = new Point3D(new Fraction(0), new Fraction(sol[1]), new Fraction(1));
        return inifinityPoints;
    }

    private static double[] solSecondGradeEquation(final double a,final double b,final double c)
    {
        double[] sol = {0, 0};

        double discriminant = b * b - 4 * a * c;
        if(discriminant < 0)
        {
            System.out.println("Error, Equation solutions complex");
        }
        else if(discriminant == 0)
        {
            sol[0] = sol[1] = (-b - Math.sqrt(discriminant))/(2*a);
        } else if (discriminant > 0)
        {
            sol[0] = (-b - Math.sqrt(discriminant))/(2*a);
            sol[1] = (-b + Math.sqrt(discriminant))/(2*a);
        }
        return sol;
    }

    public static Fraction @NotNull [] solSecondGradeEquation(final @NotNull Fraction coefa, final @NotNull Fraction coefb, final @NotNull Fraction coefc)
    {
        Fraction[] sol = new Fraction[2];
        sol[0] = new Fraction(0);
        sol[1] = new Fraction(0);

        double a = coefa.doubleValue();
        double b = coefb.doubleValue();
        double c = coefc.doubleValue();

        double discriminant = b * b - 4 * a * c;
        if(discriminant < 0)
        {
            System.out.println("Error, Equation solutions complex");
        }
        else if(discriminant == 0)
        {
            sol[0] = sol[1] =new Fraction((-b - Math.sqrt(discriminant))/(2*a));
        } else if (discriminant > 0)
        {
            sol[0] = new Fraction((-b - Math.sqrt(discriminant))/(2*a));
            sol[1] = new Fraction((-b + Math.sqrt(discriminant))/(2*a));
        }
        return sol;
    }
}
