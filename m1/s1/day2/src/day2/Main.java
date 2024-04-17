package day2;

public class Main {
    public static void main(String[] args) {


        String test = "esempio";
        int testYear = 2024;
        System.out.println("Lunghezza pari?" + stringPariDispari(test));
        System.out.println("l'anno "+testYear + "è bisestile? "+bisestile(testYear));
    }
    public static boolean stringPariDispari(String str){
        return str.length() % 2 == 0;
    }
    public static  boolean bisestile (int year) {
        if((year%4==0 && year %100 !=0)||(year %400==0)){return true;}else{return false;}}

}
//ciao