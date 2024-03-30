package org.com;

import org.jetbrains.annotations.NotNull;
import org.oldfiles.Fraction;

public class Matrix {
    private final Fraction[][] data;
    private final int ncols, nrows;

    /**
     * Creates a Matrix Object, with a specified num of rows and columns, while the data in the matrix remains empty
     *
     * @param numCols number of columns
     * @param numRows number of rows
     */
    public Matrix(int numCols, int numRows) {
        this.ncols = numCols;
        this.nrows = numRows;
        this.data = new Fraction[this.ncols][this.nrows];
    }

    public Matrix(Fraction[][] data) {
        if (data == null) {
            throw new IllegalArgumentException("Data array is null");
        }
        this.ncols = data[0].length;
        this.nrows = data.length;
        this.data = new Fraction[this.ncols][this.nrows];

        for (int i = 0; i < ncols; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, nrows);
        }
    }

    public Matrix(double[][] data) {
        if (data == null) {
            throw new IllegalArgumentException("Data array is null");
        }
        this.ncols = data[0].length;
        this.nrows = data.length;
        this.data = new Fraction[this.ncols][this.nrows];
        for (int i = 0; i < ncols; i++) {
            for (int j = 0; j < nrows; j++) {
                this.data[i][j] = new Fraction(data[i][j]);
            }
        }
    }

    public Fraction determinant() {
        if (nrows != ncols) {
            return null;
        } else if (nrows == 1) {
            return new Fraction(data[0][0]);
        } else if (nrows == 2) {
            Fraction det = new Fraction(data[0][0]);
            det.multiply(data[1][1]);
            det.subtractFraction(new Fraction(data[1][0]).multiply(data[0][1]));
            return det;
        } else if (nrows == 3) {
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
    public String toString() {
        StringBuilder matrix = new StringBuilder();
        for (int i = 0; i < ncols; i++) {
            matrix.append("[ ");
            for (int j = 0; j < nrows; j++) {
                matrix.append(data[i][j].toString()).append(" ");
            }
            matrix.append(" ]\n");
        }

        return matrix.toString();
    }

    public String toString(boolean risToDouble) {
        if (risToDouble) {
            StringBuilder matrix = new StringBuilder();
            for (int i = 0; i < ncols; i++) {
                matrix.append("[ ");
                for (int j = 0; j < nrows; j++) {
                    matrix.append(data[i][j].doubleValue()).append(" ");
                }
                matrix.append(" ]\n");
            }

            return matrix.toString();
        } else {
            return toString();
        }

    }

    /**
     * From Matrix src removes the row at indexRow and the column at indexCol and returns what's left
     *
     * @param src      source matrix
     * @param indexRow index row to remove
     * @param indexCol index col to remove
     * @return new Matrix [n-1] * [m-1]
     */
    public static @NotNull Matrix getMxxMatrix(@NotNull Matrix src, int indexRow, int indexCol) {
        if (src.getNcols() < 2 || src.getNrows() < 2) {
            throw new IllegalArgumentException("The Matrix src has to be at least 2x2");
        }
        if (indexRow >= src.getNrows()) {
            throw new IllegalArgumentException("The indexRow provided is out of matrix bounds");
        }
        if (indexCol >= src.getNcols()) {
            throw new IllegalArgumentException("The indexCol provided is out of matrix bounds");
        }

        Matrix axx = new Matrix(src.getNcols() - 1, src.getNrows() - 1);

        int x = 0, y = 0;
        for (int i = 0; i < src.getNcols(); i++) {
            if (i == indexCol) {
                continue;
            }

            for (int j = 0; j < src.getNrows(); j++) {
                if (j == indexRow) {
                    continue;
                }

                axx.getData()[x][y++] = new Fraction(src.getData()[i][j]);
            }
            x++;
            y = 0;
        }

        return axx;
    }
}
