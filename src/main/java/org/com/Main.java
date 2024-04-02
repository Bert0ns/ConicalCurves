package org.com;

import org.apache.commons.numbers.fraction.Fraction;

public class Main {
    public static void main(String[] args) {
        Fraction f1 = Fraction.from(12, 4);
        Fraction f2 = Fraction.from(15);
        Fraction f3 = Fraction.from(1.5f);
        Fraction f4 = Fraction.from(-4, 18);
        Fraction f5 = Fraction.from(8, -150);
        Fraction f6 = Fraction.from(Math.sqrt(2));

        System.out.println(f1.toString() + f2 + f3 + f4 + f5 + " " + f6.doubleValue());

        Fraction[][] d = new Fraction[2][2];
        d[0][0] = Fraction.ONE;
        d[0][1] = Fraction.from(3);
        d[1][0] = Fraction.from(4);
        d[1][1] = Fraction.from(5);

        Matrix m = new Matrix(d);
        System.out.println(m);
        System.out.println(m.toString(true));

        System.out.println(m.determinant());
        //ax^2 + bxy + cy^2 + dx + ey + f = 0
        /* hyperbole
        -3 0 0
        0 1 3
        0 3 1
         */
        Fraction[] coef1 = {Fraction.ONE, Fraction.from(6), Fraction.from(1), Fraction.ZERO, Fraction.from(0), Fraction.from(-3)};
        ConicCurve conicCurve2 = new ConicCurve(coef1);
        System.out.println(conicCurve2);
        /* parabola
        0 2 0
        2 1 1
        0 1 1
         */
        Fraction[] coef2 = {Fraction.ONE, Fraction.from(2), Fraction.ONE, Fraction.from(4), Fraction.ZERO, Fraction.ZERO};
        ConicCurve conicCurve3 = new ConicCurve(coef2);
        System.out.println(conicCurve3);
        /* ellipse
        -8 0 0
        0 3 1
        0 1 3
         */
        Fraction[] coef3 = {Fraction.from(3), Fraction.from(2), Fraction.from(3), Fraction.ZERO, Fraction.ZERO, Fraction.from(-8)};
        ConicCurve conicCurve4 = new ConicCurve(coef3);
        System.out.println(conicCurve4.toString(true));
        /* hyperbole
        0 -6 8 6 -4 -13

        -13 3 -2
        3 0 -3
        -2 -3 8
         */
        Fraction[] coef5 = {Fraction.ZERO, Fraction.from(-6), Fraction.from(8), Fraction.from(6), Fraction.from(-4), Fraction.from(-13)};
        ConicCurve conicCurve5 = new ConicCurve(coef5);
        System.out.println(conicCurve5.toString(true));
        System.out.println(conicCurve5.toString(false));
    }
}
