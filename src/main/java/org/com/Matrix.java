package org.com;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    private Fraction [][] data;
    private int ncols, nrows;

    public Matrix(Fraction[][] data, int ncols, int nrows) {
        this.data = new Fraction[ncols][nrows];
        this.ncols = ncols;
        this.nrows = nrows;

        for(int i = 0; i < ncols; i++)
        {
            System.arraycopy(data[i], 0, this.data[i], 0, nrows);
        }
    }

    public Matrix(double [][] data, int ncols, int nrows)
    {
        this.data = new Fraction[ncols][nrows];
        this.ncols = ncols;
        this.nrows = nrows;
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
        //Fraction

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
