package player.java;

public class Audio extends Media implements Riproducibili {
    private int volume;
    private int durata;
    private static final int minimo = 0;
    private static final int massimo = 10;

    public Audio(String titolo, int volume, int durata){
        super(titolo);
        this.volume = inverti(volume);
        setVolume(volume);
        this.durata = inverti(durata);

    }
    public int inverti(int valore){
        if(valore < 0) valore = -valore;
        return valore;
    }


    private void setVolume(int volume) {
        if (volume >= minimo && volume <= massimo) {
            this.volume = volume;
        } else {
            System.out.println("Il volume deve essere compreso tra " + minimo + " e " + massimo);
        }
    }


    @Override
    public void play() {
        String livelloVolume = "";

        for (int i=0; i<volume; i++) {
            livelloVolume += "!";
        };
        for (int i=0; i<durata; i++){
            System.out.println(titolo + ": " + livelloVolume);
        }


    }

    @Override
    public void alza() {
        if (volume < 10) {
            volume++;
            System.out.println("Volume alzato a " + volume);
        } else {
            System.out.println("Volume già al massimo");
        }
    }


    @Override
    public void abbassa() {
        if (volume > 0) {
            volume--;
            System.out.println("Volume abbassato a " + volume);
        } else {
            System.out.println("Volume già al minimo");
        }
    }

    @Override
    public void inizia() {
        play();
    }
}