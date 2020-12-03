package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KPSPeli {
    protected KPSPelaajaVsPelaaja(IO io) {
        super(io);
    }

    @Override
    protected String toisenSiirto() {
        io.putLine("Toisen pelaajan siirto: ");
        return io.getLine();
    }
}
