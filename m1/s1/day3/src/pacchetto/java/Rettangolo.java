package pacchetto.java;

public class Rettangolo {

    double base;
    double altezza;

    public Rettangolo(double a, double b) {

        this.base = a;
        this.altezza = b;

    }

    public double perimetroRettangolo(){

        return (base + altezza) * 2;
    }

    public double areaRettangolo(){

        return base*altezza;
    }

    public static double sommaAree(Rettangolo r1, Rettangolo r2){
        return r1.areaRettangolo() + r2.areaRettangolo();

    }

    public static double sommaPerimetri(Rettangolo r1, Rettangolo r2){
        return r1.perimetroRettangolo() + r2.perimetroRettangolo();

    }

}