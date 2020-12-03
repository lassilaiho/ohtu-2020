package ohtu.kivipaperisakset;

public class Paaohjelma {
    private static final IO io = new ConsoleIO();

    public static void main(String[] args) {

        while (true) {
            io.putLine("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            KPSPeli peli;
            String vastaus = io.getLine();
            if (vastaus.endsWith("a")) {
                peli = KPSPeli.pelaajaVsPelaaja(io);
            } else if (vastaus.endsWith("b")) {
                peli = KPSPeli.tekoaly(io);
            } else if (vastaus.endsWith("c")) {
                peli = KPSPeli.parempiTekoaly(io);
            } else {
                break;
            }
            io.putLine("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            peli.pelaa();
        }
    }
}
