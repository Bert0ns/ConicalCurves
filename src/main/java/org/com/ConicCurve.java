package org.com;

import org.jetbrains.annotations.NotNull;
import org.oldfiles.Fraction;

import java.util.Arrays;

import static org.com.Mathematics.solSecondGradeEquation;
import static org.com.Matrix.getMxxMatrix;

public class ConicCurve {
    private final Matrix matrix;
    private final ConicCurveType type;
    private final Point3D center;
    private final Point3D[] pointsAtInfinity;
    private final PolarLine[] asymptotes;
    /**
     * Returns a ConicalCurve Object
     *
     * @param matrix Associated Conical Curve' matrix
     */
    public ConicCurve(Matrix matrix) {
        this.matrix = matrix;
        type = determineType(this);
        center = determineCenter(this);

        if (type == ConicCurveType.HYPERBOLA) {
            pointsAtInfinity = determinePointsToTheInfinity(this);
            asymptotes = determineAsymptotes(this);
        } else {
            pointsAtInfinity = null;
            asymptotes = null;
        }
    }

    /**
     * Returns a ConicalCurve Object
     *
     * @param coefficients coefficients in this order ax^2 + bxy + cy^2 + dx + ey + f = 0
     */
    public ConicCurve(Fraction @NotNull [] coefficients) {
        matrix = determineMatrix(coefficients);
        type = determineType(this);
        center = determineCenter(this);

        if (type == ConicCurveType.HYPERBOLA) {
            pointsAtInfinity = determinePointsToTheInfinity(this);
            asymptotes = determineAsymptotes(this);
        } else {
            pointsAtInfinity = null;
            asymptotes = null;
        }
    }

    /**
     * Returns the Matrix associated to the Conic Curve with the specified coefficients
     *
     * @param coefficients coefficients in this order ax^2 + bxy + cy^2 + dx + ey + f = 0
     */
    public static Matrix determineMatrix(Fraction @NotNull [] coefficients) {
        if (coefficients.length != 6) {
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
     *
     * @param conicCurve Conic curve
     * @return enum that has the type of the conic curve;
     */
    public static ConicCurveType determineType(final @NotNull ConicCurve conicCurve) {
        if (conicCurve.matrix.getNrows() != conicCurve.matrix.getNcols()) {
            throw new IllegalArgumentException("The matrix provided is not squared");
        } else if (conicCurve.matrix.getNcols() != 3) {
            throw new IllegalArgumentException("The matrix provided is not 3x3");
        }

        if (conicCurve.matrix.determinant().doubleValue() == 0) {
            return ConicCurveType.DEGENERATE;
        }

        Matrix a00 = getMxxMatrix(conicCurve.matrix, 0, 0);
        Fraction det_a00 = a00.determinant();

        if (det_a00.doubleValue() > 0) {
            return ConicCurveType.ELLIPSE;
        } else if (det_a00.doubleValue() < 0) {
            return ConicCurveType.HYPERBOLA;
        }

        return ConicCurveType.PARABOLA;
    }

    public static @NotNull Point3D determineCenter(final @NotNull ConicCurve conicCurve) {
        Point3D center = new Point3D();
        center.coord[0] = getMxxMatrix(conicCurve.matrix, 0, 0).determinant();
        center.coord[1] = getMxxMatrix(conicCurve.matrix, 0, 1).determinant().multiply(new Fraction(-1));
        center.coord[2] = getMxxMatrix(conicCurve.matrix, 0, 2).determinant();
        center.reduceToMinimalForm();
        return center;
    }

    public static Point3D[] determinePointsToTheInfinity(final @NotNull ConicCurve conicCurve) {
        if (conicCurve.type != ConicCurveType.HYPERBOLA) {
            //throw new IllegalArgumentException("The conic curve has to be an HYPERBOLE");
            return null;
        }
        /*
        ax^2 + bxy + cy^2 + dx + ey + f = 0

	    f	 d/2  e/2
	    d/2	  a	  b/2
	    e/2  b/2   c

	    [1][1] -> a
	    [2][2] -> c
	    [1][2] * 2 -> b
	    */
        Point3D[] inifinityPoints = new Point3D[2];
        Fraction coefa = new Fraction(conicCurve.matrix.getData()[1][1]);
        Fraction coefb = new Fraction(conicCurve.matrix.getData()[1][2]).multiply(new Fraction(2));
        Fraction coefc = new Fraction(conicCurve.matrix.getData()[2][2]);

        Fraction[] sol = solSecondGradeEquation(coefa, coefb, coefc);
        //double[] sol2 = solSecondGradeEquation(coefa.doubleValue(), coefb.doubleValue(), coefc.doubleValue());
        if (sol[2].doubleValue() == 3) {
            inifinityPoints[0] = new Point3D(new Fraction(0), new Fraction(1), new Fraction(0));
        } else {
            inifinityPoints[0] = new Point3D(new Fraction(0), new Fraction(sol[0]), new Fraction(1));
        }
        inifinityPoints[1] = new Point3D(new Fraction(0), new Fraction(sol[1]), new Fraction(1));
        return inifinityPoints;
    }

    public static PolarLine[] determineAsymptotes(final @NotNull ConicCurve conicCurve) {
        if (conicCurve.type != ConicCurveType.HYPERBOLA) {
            return null;
        }
        if (conicCurve.pointsAtInfinity == null) {
            throw new IllegalArgumentException("The Hyperbola has no points to infinity but yopu tried to find their Polar Line");
        }
        PolarLine[] asymptotes = new PolarLine[2];

        asymptotes[0] = new PolarLine(conicCurve.matrix, conicCurve.pointsAtInfinity[0]);
        asymptotes[1] = new PolarLine(conicCurve.matrix, conicCurve.pointsAtInfinity[1]);
        return asymptotes;
    }

    @Override
    public String toString() {
        return "ConicCurve{\n" +
                "matrix:\n" + matrix +
                "type=" + type +
                "\ncenter=" + center +
                "\npoints at infinity=" + Arrays.toString(pointsAtInfinity) +
                "\nasymptotes=" + Arrays.toString(asymptotes) +
                " }";
    }

    public String toString(boolean risToDouble) {
        if (!risToDouble) {
            return toString();
        }
        StringBuilder tostring = new StringBuilder("ConicCurve{\n" +
                                                    "matrix:\n" + matrix.toString(true) +
                                                    "type=" + type +
                                                    "\ncenter=" + center.toString(true));

        if(pointsAtInfinity == null)
        {
            tostring.append("\npoints at infinity=null");
        }
        else
        {
            tostring.append("\npoints at infinity=[").append(pointsAtInfinity[0].toString(true)).append(",").append(pointsAtInfinity[1].toString(true)).append("]");
        }

        if(asymptotes == null)
        {
            tostring.append("\nasymptotes=null");
        }
        else
        {
            tostring.append("\nasymptotes=[").append(asymptotes[0].toString(true)).append(", ").append(asymptotes[1].toString(true)).append("]");
        }

        return tostring.append("\n}").toString();
    }

    public enum ConicCurveType {
        ELLIPSE,
        HYPERBOLA,
        PARABOLA,
        DEGENERATE
    }
}
