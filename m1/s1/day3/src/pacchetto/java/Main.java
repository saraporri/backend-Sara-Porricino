package pacchetto.java;

public class Main {

    public static void main(String[] args) {

        Rettangolo r = new Rettangolo(3,4);
        System.out.println("Il perimetro del rettangolo è " + r.perimetroRettangolo());
        System.out.println("L'Area del rettangolo è " + r.areaRettangolo());

        Rettangolo r1 = new Rettangolo(5,2);
        Rettangolo r2 = new Rettangolo(6,7);

        System.out.println("Il perimetro del rettangolo 1 è " + r1.perimetroRettangolo());
        System.out.println("L'Area del rettangolo 1 è " + r1.areaRettangolo());

        System.out.println("Il perimetro del rettangolo 2 è " + r2.perimetroRettangolo());
        System.out.println("L'Area del rettangolo 2 è " + r2.areaRettangolo());



        System.out.println("La somma delle aree dei due rettangoli è " + Rettangolo.sommaAree(r1, r2));
        System.out.println("La somma dei perimetri dei due rettangoli è " + Rettangolo.sommaPerimetri(r1, r2));



    }
}