package ohtu.intjoukkosovellus;

import java.util.function.Predicate;

public class IntJoukko {

    public final static int KAPASITEETTI = 5; // aloitustalukon koko
    // näin paljon isompi kuin vanha
    private int kasvatuskoko = 5; // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono; // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenMaara = 0; // Tyhjässä joukossa alkioiden_määrä on nolla.

    public IntJoukko() {
        lukujono = new int[KAPASITEETTI];
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukujono = new int[kapasiteetti];
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");// heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");// heitin vaan jotain :D
        }
        lukujono = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        if (alkioidenMaara >= lukujono.length) {
            laajenna();
        }
        lukujono[alkioidenMaara] = luku;
        alkioidenMaara++;
        return true;
    }

    private void laajenna() {
        int[] vanhaLukujono = lukujono;
        lukujono = new int[alkioidenMaara + kasvatuskoko];
        System.arraycopy(vanhaLukujono, 0, lukujono, 0, alkioidenMaara);
    }

    public boolean kuuluu(int luku) {
        return haeIndeksi(luku) != -1;
    }

    private int haeIndeksi(int luku) {
        for (int i = 0; i < alkioidenMaara; i++) {
            if (lukujono[i] == luku) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int kohta = haeIndeksi(luku);
        if (kohta == -1) {
            return false;
        }
        lukujono[kohta] = lukujono[alkioidenMaara - 1];
        alkioidenMaara--;
        return true;
    }

    public int mahtavuus() {
        return alkioidenMaara;
    }

    @Override
    public String toString() {
        var tuotos = new StringBuilder("{");
        for (int i = 0; i < alkioidenMaara; i++) {
            if (i > 0) {
                tuotos.append(", ");
            }
            tuotos.append(Integer.toString(lukujono[i]));
        }
        tuotos.append('}');
        return tuotos.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenMaara];
        System.arraycopy(lukujono, 0, taulu, 0, alkioidenMaara);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        return new IntJoukko().lisaaEhdolla(a, x -> true).lisaaEhdolla(b, x -> true);
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        return new IntJoukko().lisaaEhdolla(a, x -> b.kuuluu(x));
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        return new IntJoukko().lisaaEhdolla(a, x -> !b.kuuluu(x));
    }

    private IntJoukko lisaaEhdolla(IntJoukko x, Predicate<Integer> ehto) {
        for (var i = 0; i < x.alkioidenMaara; i++) {
            if (ehto.test(x.lukujono[i])) {
                lisaa(x.lukujono[i]);
            }
        }
        return this;
    }
}
