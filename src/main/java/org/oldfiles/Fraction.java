package org.oldfiles;

import java.util.Objects;

public class Fraction extends Number{
    private int numerator;
    private int denominator;

    public Fraction()
    {
        this.numerator = 0;
        this.denominator = 1;
    }

    public Fraction(Fraction fraction)
    {
        this.numerator = fraction.getNumerator();
        this.denominator = fraction.getDenominator();

        ConvertToMinimalForm();
    }

    /**
     * Creates an object Fraction, in minimal form
     * @param numerator numerator of the fraction
     * @param denominator denominator of the fraction
     */
    public Fraction(int numerator, int denominator) {
        if(denominator == 0) {
            throw new IllegalArgumentException("Denominator is zero");
        }

        this.numerator = numerator;
        this.denominator = denominator;

        ConvertToMinimalForm();
    }

    /**
     * Creates a Fraction Object with denominator = 1, in minimal form
     * @param numerator numerator of the fraction
     */
    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;

        ConvertToMinimalForm();
    }

    /**
     * Creates a Fraction Object converting the double value to a division of two int
     * @param n value of the fraction (float)
     */
    public Fraction(double n)
    {
        float number = (float) n;
        denominator = 1;

        do{
            denominator = denominator * 10;
        }while((number * denominator) % 10 != 0);

        denominator=denominator / 10;
        this.numerator = (int)(number*denominator);

        ConvertToMinimalForm();
    }

    private void ConvertToMinimalForm()
    {
        if(denominator < 0)
        {
            numerator *= -1;
            denominator *= -1;
        }

        int mcd = MCD(Math.abs(numerator), Math.abs(denominator));
        numerator /= mcd;
        denominator /= mcd;
    }


    private int MCD(int a, int b) {
        int t;
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    /**
     *
     * @return Fraction's numerator
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     *
     * @return Fraction's denominator
     */
    public int getDenominator() {
        return denominator;
    }

    public static Fraction ConvertFromString(String str)
    {


        return null;
    }

    public Fraction addFraction(Fraction f)
    {
        denominator *= f.getDenominator();
        numerator = numerator * f.getDenominator() + f.getNumerator()*denominator;
        ConvertToMinimalForm();
        return this;
    }
    public Fraction subtractFraction(Fraction f)
    {
        denominator *= f.getDenominator();
        numerator = numerator * f.getDenominator() - f.getNumerator()*denominator;
        ConvertToMinimalForm();
        return this;
    }
    public Fraction multiply(Fraction f)
    {
        numerator *= f.getNumerator();
        denominator *= f.getDenominator();
        ConvertToMinimalForm();
        return this;
    }
    public Fraction divide(Fraction f)
    {
        numerator *= f.getDenominator();
        denominator *= f.getNumerator();
        ConvertToMinimalForm();
        return this;
    }

    @Override
    public int intValue() {
        return numerator/denominator;
    }

    @Override
    public long longValue() {
        return (long)numerator/denominator;
    }

    @Override
    public float floatValue() {
        return  numerator/(float)denominator;
    }

    @Override
    public double doubleValue() {
        return numerator/(double)denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return getNumerator() == fraction.getNumerator() && getDenominator() == fraction.getDenominator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumerator(), getDenominator());
    }

    @Override
    public String toString() {
        return "("+numerator + "/" + denominator+")";
    }
    public String toString(boolean risToDouble) {
        if(!risToDouble)
        {
            return toString();
        }
        return "("+ numerator/(double)denominator +")";
    }
}
