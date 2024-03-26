package org.com;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    private final Fraction [][] data;
    private final int ncols, nrows;

    /**
     * Creates a Matrix Object, with a specified num of rows and columns, while the data in the matrix remains empty
     * @param numCols number of columns
     * @param numRows number of rows
     */
    public Matrix(int numCols, int numRows) {
        this.ncols = numCols;
        this.nrows = numRows;
        this.data = new Fraction[this.ncols][this.nrows];
    }

    public Matrix(Fraction[][] data) {
        if(data == null)
        {
            throw new IllegalArgumentException("Data array is null");
        }
        this.ncols = data[0].length;
        this.nrows = data.length;
        this.data = new Fraction[this.ncols][this.nrows];

        for(int i = 0; i < ncols; i++)
        {
            System.arraycopy(data[i], 0, this.data[i], 0, nrows);
        }
    }

    public Matrix(double[][] data)
    {
        if(data == null)
        {
            throw new IllegalArgumentException("Data array is null");
        }
        this.ncols = data[0].length;
        this.nrows = data.length;
        this.data = new Fraction[this.ncols][this.nrows];
        for(int i = 0; i < ncols; i++)
        {
            for (int j = 0; j < nrows; j++)
            {
                this.data[i][j] = new Fraction(data[i][j]);
            }
        }
    }

    public Fraction determinant()
    {
        if(nrows != ncols)
        {
            return null;
        }
        else if(nrows == 1)
        {
            return new Fraction(data[0][0]);
        }
        else if(nrows == 2)
        {
            Fraction det = new Fraction(data[0][0]);
            det.multiply(data[1][1]);
            det.subtractFraction(new Fraction(data[1][0]).multiply(data[0][1]));
            return det;
        }
        else if(nrows == 3)
        {
            /*
            00 10 20
            01 11 21
            02 12 22
            */

            Fraction f1 = new Fraction(data[0][0]);
            f1.multiply(data[1][1]).multiply(data[2][2]);
            f1.addFraction(new Fraction(data[1][0]).multiply(data[2][1]).multiply(data[0][2]));
            f1.addFraction(new Fraction(data[2][0]).multiply(data[0][1]).multiply(data[1][2]));

            Fraction f2 = new Fraction(data[2][0]);
            f2.multiply(data[1][1]).multiply(data[0][2]);
            f2.addFraction(new Fraction(data[1][0]).multiply(data[0][1]).multiply(data[2][2]));
            f2.addFraction(new Fraction(data[0][0]).multiply(data[2][1]).multiply(data[1][2]));

            return f1.subtractFraction(f2);
        }

        return null;
    }

    public Fraction[][] getData() {
        return data;
    }

    public int getNcols() {
        return ncols;
    }

    public int getNrows() {
        return nrows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return getNcols() == matrix.getNcols() && getNrows() == matrix.getNrows() && Arrays.equals(getData(), matrix.getData());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getNcols(), getNrows());
        result = 31 * result + Arrays.hashCode(getData());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder matrix = new StringBuilder();
        for(int i = 0; i < ncols; i++)
        {
            matrix.append("{ ");
            for(int j = 0; j < nrows; j++)
            {
                matrix.append(data[i][j].toString()).append(" ");
            }
            matrix.append(" }\n");
        }

        return matrix.toString();
    }
    public String toString(boolean risToDouble) {
        if(risToDouble)
        {
            StringBuilder matrix = new StringBuilder();
            for(int i = 0; i < ncols; i++)
            {
                matrix.append("{ ");
                for(int j = 0; j < nrows; j++)
                {
                    matrix.append(data[i][j].doubleValue()).append(" ");
                }
                matrix.append(" }\n");
            }

            return matrix.toString();
        }
        else {
            return toString();
        }

    }
}
