package player.java;

public abstract class Media {
    // Questa è la nostra classe Genitore, astratta, in quanto l'elemento in comune tra tutti i nostri media (Classi figlie)
    // sarà solamente il titolo del media stesso.

    protected String titolo;

    public abstract void inizia();

    public Media(String titolo) {
        this.titolo = titolo;
    }

}
