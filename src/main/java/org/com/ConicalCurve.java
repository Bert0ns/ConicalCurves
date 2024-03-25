package org.com;

public class ConicalCurve {
    Matrix matrix;

    public ConicalCurve(Matrix matrix) {
        this.matrix = matrix;
    }

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
    }
}
