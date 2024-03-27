package org.com;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class Mathematics {
    /**
     * Solves the second grade equation provided in the form: coefa*x^2 + coefb*x + coefc = 0
     * @param coefa coefficient a of ax^2
     * @param coefb coefficient b of bx
     * @param coefc coefficient c of c
     * @return double[3] Array with 3 doubles [0],[1] contain the solution(if any), [2] consider this number as an int if = 0 -> no solution, 1-> 1 solution, 2 -> 2 solutions, 3 -> the equation provided is a first grade equation that means it has 1 solution
     */
    public static double[] solSecondGradeEquation(final double coefa, final double coefb, final double coefc)
    {
        double[] sol = {0, 0, 0};

        double discriminant = coefb * coefb - 4 * coefa * coefc;
        if(coefa == 0)
        {
            sol[0] = 0;
            sol[1] = -coefb/coefc;
            sol[2] = 3;
        }
        else if(discriminant < 0)
        {
            System.out.println("Error, Equation solutions complex");
        }
        else if(discriminant == 0)
        {
            sol[0] = sol[1] = (-coefb - Math.sqrt(discriminant))/(2*coefa);
            sol[2] = 1;
        } else if (discriminant > 0)
        {
            sol[0] = (-coefb - Math.sqrt(discriminant))/(2*coefa);
            sol[1] = (-coefb + Math.sqrt(discriminant))/(2*coefa);
            sol[2] = 2;
        }
        return sol;
    }

    /**
     * Solves the second grade equation provided in the form: coefa*x^2 + coefb*x + coefc = 0
     * @param coefa coefficient a of ax^2
     * @param coefb coefficient b of bx
     * @param coefc coefficient c of c
     * @return Fraction[3] Array with 3 Fraction Object [0],[1] contain the solution(if any), [2] consider this number as an int if = 0 -> no solution, 1-> 1 solution, 2 -> 2 solutions, 3 -> the equation provided is a first grade equation that means it has 1 solution
     */
    public static Fraction @NotNull [] solSecondGradeEquation(final @NotNull Fraction coefa, final @NotNull Fraction coefb, final @NotNull Fraction coefc)
    {
        Fraction[] sol = new Fraction[3];
        sol[0] = new Fraction(0);
        sol[1] = new Fraction(0);
        sol[2] = new Fraction(0);
        double a = coefa.doubleValue();
        double b = coefb.doubleValue();
        double c = coefc.doubleValue();
        double discriminant = b * b - 4 * a * c;
        /*
        ax^2 + bxy + cy^2 = 0
        fix y = 1
        ax^2 + bx + c = 0
        */
        if(a == 0)
        {
            sol[0] = new Fraction(0);
            sol[1] = new Fraction(-c/b);
            sol[2] = new Fraction(3);
        }
        else if(discriminant < 0)
        {
            System.out.println("Error, Equation solutions complex");
            sol[2] = new Fraction(0);
        }
        else if(discriminant == 0)
        {
            sol[0] = sol[1] =new Fraction((-b - Math.sqrt(discriminant))/(2*a));
            sol[2] = new Fraction(1);
        }
        else if (discriminant > 0)
        {
            sol[0] = new Fraction((-b - Math.sqrt(discriminant))/(2*a));
            sol[1] = new Fraction((-b + Math.sqrt(discriminant))/(2*a));
            sol[2] = new Fraction(2);
        }
        return sol;
    }
}
