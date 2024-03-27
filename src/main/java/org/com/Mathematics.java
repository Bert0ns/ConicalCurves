package org.com;

import org.jetbrains.annotations.NotNull;

public abstract class Mathematics {
    private static double[] solSecondGradeEquation(final double a,final double b,final double c)
    {
        double[] sol = {0, 0};

        double discriminant = b * b - 4 * a * c;
        if(discriminant < 0)
        {
            System.out.println("Error, Equation solutions complex");
        }
        else if(discriminant == 0)
        {
            sol[0] = sol[1] = (-b - Math.sqrt(discriminant))/(2*a);
        } else if (discriminant > 0)
        {
            sol[0] = (-b - Math.sqrt(discriminant))/(2*a);
            sol[1] = (-b + Math.sqrt(discriminant))/(2*a);
        }
        return sol;
    }

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
