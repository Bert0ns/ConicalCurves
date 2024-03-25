package org.com;

import javax.swing.plaf.metal.MetalTabbedPaneUI;

public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(12, 4);
        Fraction f2 = new Fraction(15);
        Fraction f3 = new Fraction( 1.5);
        Fraction f4 = new Fraction(-4, 18);
        Fraction f5 = new Fraction(8, -150);
        Fraction f6 = new Fraction(Math.sqrt(2));


        System.out.println(f1.toString() + f2 + f3 + f4 + f5 + f6);

        Fraction [][] d = new Fraction[2][2];
        d[0][0] = new Fraction(0.5);
        d[0][1] = new Fraction(1);
        d[1][0] = new Fraction(0.5 + 1);
        d[1][1] = new Fraction(1 + 1);

        Matrix m = new Matrix(d, 2, 2);
        System.out.println(m);
        System.out.println(m.toString(true));
    }
}
