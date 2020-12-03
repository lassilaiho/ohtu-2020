package ohtu.kivipaperisakset;

// Tuomari pitää kirjaa ensimmäisen ja toisen pelaajan pisteistä sekä tasapelien määrästä.
public class Tuomari {

    private int ekanPisteet = 0;
    private int tokanPisteet = 0;
    private int tasapelit = 0;

    public void kirjaaSiirto(String ekanSiirto, String tokanSiirto) {
        if (tasapeli(ekanSiirto, tokanSiirto)) {
            tasapelit++;
        } else if (ekaVoittaa(ekanSiirto, tokanSiirto)) {
            ekanPisteet++;
        } else {
            tokanPisteet++;
        }
    }

    // sisäinen metodi, jolla tarkastetaan tuliko tasapeli
    private static boolean tasapeli(String eka, String toka) {
        return eka.equals(toka);
    }

    // sisäinen metodi joka tarkastaa voittaako eka pelaaja tokan
    private static boolean ekaVoittaa(String eka, String toka) {
        return ("k".equals(eka) && "s".equals(toka))
                || ("s".equals(eka) && "p".equals(toka))
                || ("p".equals(eka) && "k".equals(toka));
    }

    public String toString() {
        return "Pelitilanne: " + ekanPisteet + " - " + tokanPisteet + "\n" + "Tasapelit: " + tasapelit;
    }
}