package ohtu.kivipaperisakset;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public abstract class KPSPeli {
    protected final IO io;

    public static KPSPeli pelaajaVsPelaaja(IO io) {
        return new KPSPelaajaVsPelaaja(io);
    }

    public static KPSPeli tekoaly(IO io) {
        return new KPSTekoaly(io);
    }

    public static KPSPeli parempiTekoaly(IO io) {
        return new KPSParempiTekoaly(io, 20);
    }

    protected KPSPeli(IO io) {
        this.io = io;
    }

    public void pelaa() {
        var tuomari = new Tuomari();
        pelinAluksi();

        while (true) {
            var ekanSiirto = ensimmaisenSiirto();
            var tokanSiirto = toisenSiirto();
            if (!onkoOkSiirto(ekanSiirto) || !onkoOkSiirto(tokanSiirto)) {
                break;
            }
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            io.putLine(tuomari.toString());
            io.putLine("");
            eranLopuksi(ekanSiirto, tokanSiirto);
        }

        io.putLine("");
        io.putLine("Kiitos!");
        io.putLine(tuomari.toString());
    }

    protected String ensimmaisenSiirto() {
        io.putLine("Ensimmäisen pelaajan siirto: ");
        return io.getLine();
    }

    protected abstract String toisenSiirto();

    protected void pelinAluksi() {
    }

    protected void eranLopuksi(String ekanSiirto, String tokanSiirto) {
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
