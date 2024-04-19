package main.java;
import player.java.Audio;

// Un player multimediale deve riprodurre diversi tipi di Media
// Ogni Media può essere Video, Audio, Image
// Alcuni media possono essere riproducibili, alcuni hanno un volume ed una luminosità
// Costruire un player che sia in grado di riprodurre i diversi media, con istruzioni differenti in base alla loro natura

// -----------------------------------------------------------

// Creiamo due interfacce, per suddividere le istruzioni tra i metodi riproducibili e non riproducibili

// -----------------------------------------------------------

// Interfaccia per Media Riproducibili:


// Metodo play() => Riproduce il media
// Metodo alzaVolume()
// Metodo abbassaVolume()

// -----------------------------------------------------------

// Interfaccia per Media Non riproducibili (images):


// Metodo alzaLuminosità()
// Metodo abbassaLuminosità()
// Metodo Show() => Mostra l'immagine





    public class Main {

        public static void main(String[] args) {
            Audio audio = new Audio("prova", 5,5);
            audio.play();
        }
    }