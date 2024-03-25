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

        ConicalCurve conicalCurve1 = new ConicalCurve(m);
        System.out.println(conicalCurve1.matrix);


        //3 -2 2 4 6 1
        /*
        3 -1 2
        -1 2 3
        2 3 1

         */
        Fraction[] coef = {new Fraction(3),new Fraction(-2),new Fraction(2),new Fraction(4),new Fraction(6),new Fraction(1) };
        ConicalCurve conicalCurve2 = new ConicalCurve(coef);
        System.out.println(conicalCurve2.matrix);
    }
}
