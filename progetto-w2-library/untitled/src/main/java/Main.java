

import library.Catalogue;
import library.Books;
import library.Magazines;
import library.Periodicity;
import services.Archive;

import java.util.ArrayList;


public class Main {

        public static void main(String[] args) {

                Books book1 = new Books("Il Signore degli Anelli", 1954, 1170, "J.R.R. Tolkien", "Fantasy");
                Books book2 = new Books("Ema", 1990, 328, "Emanuel", "Daje");Magazines rivista1 = new Magazines("National Geographic", 1888, 200, Periodicity.MONTHLY);
Magazines rivista2 = new Magazines("Scientific American", 1845, 150, Periodicity.MONTHLY);

Archive archive = new Archive();

        archive.add(book1);
        archive.add(book2);
        archive.add(magazine1);
        archive.add(magazine2);

        System.out.println("Complete archive:");
        archive.getList().forEach(System.out::println);

        System.out.println("\nRicerca per ISBN:");
        archive.getByISBN(1).ifPresentOrElse(System.out::println, () -> System.out.println("Element not found"));

        System.out.println("\nSearching author:");
        archive.getAuthor("George Orwell");

        System.out.println("\nSearching publication year:");
        archive.getByYear(1949).forEach(System.out::println);

        archive.deleteISBN(1);

        System.out.println("Complete archive:");
        archive.getList().forEach(System.out::println);
    }
            }