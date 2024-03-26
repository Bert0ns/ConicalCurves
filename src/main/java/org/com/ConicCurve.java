package org.com;

public class ConicCurve {
    private final Matrix matrix;
    public ConicCurveType type;
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
        this.type = determineType(this.matrix);
    }

    /**
     * Returns a ConicalCurve Object
     * @param coefficients coefficients in this order ax^2 + bxy + cy^2 + dx + ey + f = 0
     */
    public ConicCurve(Fraction[] coefficients)
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

    public static ConicCurveType determineType(Matrix matrix)
    {
        if(matrix.getNrows() != matrix.getNcols())
        {
            throw new IllegalArgumentException("The matrix provided is not squared");
        }
        else if(matrix.getNcols() != 3)
        {
            throw new IllegalArgumentException("The matrix provided is not 3x3");
        }

        if(matrix.determinant().doubleValue() == 0)
        {
            return ConicCurveType.DEGENERATE;
        }

        Matrix a00 = getAxxMatrix(matrix, 0, 0);
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

    /**
     * From Matrix src removes the row at indexRow and the column at indexCol and returns what's left
     * @param src source matrix
     * @param indexRow index row to remove
     * @param indexCol index col to remove
     * @return new Matrix [n-1] * [m-1]
     */
    public static Matrix getAxxMatrix(Matrix src, int indexRow, int indexCol)
    {
        if(src.getNcols() < 2 || src.getNrows() < 2)
        {
            throw new IllegalArgumentException("The Matrix src has to be at least 2x2");
        }
        if(indexRow >= src.getNrows())
        {
            throw  new IllegalArgumentException("The indexRow provided is out of matrix bounds");
        }
        if(indexCol >= src.getNcols())
        {
            throw  new IllegalArgumentException("The indexCol provided is out of matrix bounds");
        }

        Matrix axx = new Matrix(src.getNcols() - 1, src.getNrows() - 1);

        int x = 0,y = 0;
        for(int i = 0; i < src.getNcols(); i++)
        {
            if(i == indexCol)
            {
                continue;
            }

            for(int j = 0; j < src.getNrows(); j++)
            {
                if(j == indexRow)
                {
                    continue;
                }

                axx.getData()[x][y++] = new Fraction(src.getData()[i][j]);
            }
            x++;
            y = 0;
        }

        return axx;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        return "ConicCurve{\n" +
                "matrix:\n" + matrix +
                "type=" + type +
                " }";
    }

    public String toString(boolean risToDouble) {
        return "ConicCurve{\n" +
                "matrix:\n" + matrix.toString(risToDouble) +
                "type=" + type +
                " }";
    }
}
