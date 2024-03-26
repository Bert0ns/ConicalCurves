package org.com;

public class ConicalCurve {
    private final Matrix matrix;
    public ConicalCurveType type;
    public enum ConicalCurveType
    {
        ELLIPSE,
        HYPERBOLE,
        PARABOLA
    }


    /**
     * Returns a ConicalCurve Object
     * @param matrix Associated Conical Curve' matrix
     */
    public ConicalCurve(Matrix matrix) {
        this.matrix = matrix;
        this.type = determineType(this.matrix);
    }

    /**
     * Returns a ConicalCurve Object
     * @param coefficients coefficients in this order ax^2 + bxy + cy^2 + dx + ey + f = 0
     */
    public ConicalCurve(Fraction[] coefficients)
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

        matrix = new Matrix(dataMatrix);
        this.type = determineType(this.matrix);
    }

    ConicalCurveType determineType(Matrix matrix)
    {
        if(matrix.getNrows() != matrix.getNcols())
        {
            throw new IllegalArgumentException("The matrix provided is not squared");
        }
        else if(matrix.getNcols() != 3)
        {
            throw new IllegalArgumentException("The matrix provided is not 3x3");
        }

        Fraction[][] a00Data = new Fraction[2][2];
        a00Data[0][0] = matrix.getData()[1][1];
        a00Data[1][0] = matrix.getData()[2][1];
        a00Data[0][1] = matrix.getData()[1][2];
        a00Data[1][1] = matrix.getData()[2][2];

        Matrix a00 = new Matrix(a00Data);
        Fraction det_a00 = a00.determinant();

        if(det_a00.doubleValue() > 0)
        {
            return ConicalCurveType.ELLIPSE;
        }
        else if(det_a00.doubleValue() < 0)
        {
            return ConicalCurveType.HYPERBOLE;
        }

        return ConicalCurveType.PARABOLA;
    }

    public Matrix getMatrix() {
        return matrix;
    }
}
