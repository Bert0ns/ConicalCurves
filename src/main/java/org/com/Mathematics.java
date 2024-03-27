package org.com;

import org.jetbrains.annotations.NotNull;

public abstract class Mathematics {
    /**
     * Solves the second grade equation provided in the form: coefa*x^2 + coefb*x + coefc = 0
     * @param coefa coefficient a of ax^2
     * @param coefb coefficient b of bx
     * @param coefc coefficient c of c
     * @return double[2] with the solutions, if there is no solution it returns {0,0}
     */
    public static double[] solSecondGradeEquation(final double coefa, final double coefb, final double coefc)
    {
        double[] sol = {0, 0};

        double discriminant = coefb * coefb - 4 * coefa * coefc;
        if(discriminant < 0)
        {
            System.out.println("Error, Equation solutions complex");
        }
        else if(discriminant == 0)
        {
            sol[0] = sol[1] = (-coefb - Math.sqrt(discriminant))/(2*coefa);
        } else if (discriminant > 0)
        {
            sol[0] = (-coefb - Math.sqrt(discriminant))/(2*coefa);
            sol[1] = (-coefb + Math.sqrt(discriminant))/(2*coefa);
        }
        return sol;
    }

    /**
     * Solves the second grade equation provided in the form: coefa*x^2 + coefb*x + coefc = 0
     * @param coefa coefficient a of ax^2
     * @param coefb coefficient b of bx
     * @param coefc coefficient c of c
     * @return Fraction[2] with the solutions, if there is no solution it returns {0,0}
     */
    public static Fraction @NotNull [] solSecondGradeEquation(final @NotNull Fraction coefa, final @NotNull Fraction coefb, final @NotNull Fraction coefc)
    {
        Fraction[] sol = new Fraction[2];
        sol[0] = new Fraction(0);
        sol[1] = new Fraction(0);

        double a = coefa.doubleValue();
        double b = coefb.doubleValue();
        double c = coefc.doubleValue();

        double discriminant = b * b - 4 * a * c;
        if(discriminant < 0)
        {
            System.out.println("Error, Equation solutions complex");
        }
        else if(discriminant == 0)
        {
            sol[0] = sol[1] =new Fraction((-b - Math.sqrt(discriminant))/(2*a));
        } else if (discriminant > 0)
        {
            sol[0] = new Fraction((-b - Math.sqrt(discriminant))/(2*a));
            sol[1] = new Fraction((-b + Math.sqrt(discriminant))/(2*a));
        }
        return sol;
    }
}
