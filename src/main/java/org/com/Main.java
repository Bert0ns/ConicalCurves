package org.com;

public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(12, 4);
        Fraction f2 = new Fraction(15);
        Fraction f3 = new Fraction( 1.5f);
        Fraction f4 = new Fraction(-4, 18);
        Fraction f5 = new Fraction(8, -150);
        Fraction f6 = new Fraction((float)Math.sqrt(2));


        System.out.println(f1.toString() + f2 + f3 + f4 + f5 +" "+ f6.doubleValue());

        Fraction [][] d = new Fraction[2][2];
        d[0][0] = new Fraction(1);
        d[0][1] = new Fraction(3);
        d[1][0] = new Fraction(4);
        d[1][1] = new Fraction(5);

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
        Fraction[] coef1 = {new Fraction(1),new Fraction(6),new Fraction(1),new Fraction(0),new Fraction(0),new Fraction(-3) };
        ConicCurve conicCurve2 = new ConicCurve(coef1);
        System.out.println(conicCurve2);
        /* parabola
        0 2 0
        2 1 1
        0 1 1
         */
        Fraction[] coef2 = {new Fraction(1),new Fraction(2),new Fraction(1),new Fraction(4),new Fraction(0),new Fraction(0) };
        ConicCurve conicCurve3 = new ConicCurve(coef2);
        System.out.println(conicCurve3);
        /* ellipse
        -8 0 0
        0 3 1
        0 1 3
         */
        Fraction[] coef3 = {new Fraction(3),new Fraction(2),new Fraction(3),new Fraction(0),new Fraction(0),new Fraction(-8) };
        ConicCurve conicCurve4 = new ConicCurve(coef3);
        System.out.println(conicCurve4);
        /* hyperbole
        0 -6 8 6 -4 -13

        -13 3 -2
        3 0 -3
        -2 -3 8
         */
        Fraction[] coef5 = {new Fraction(0), new Fraction(-6), new Fraction(8), new Fraction(6), new Fraction(-4), new Fraction(-13)};
        ConicCurve conicCurve5 = new ConicCurve(coef5);
        System.out.println(conicCurve5.toString(true));
        System.out.println(conicCurve5.toString(false));
    }
}
