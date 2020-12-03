package ohtu.kivipaperisakset;

public class KPSParempiTekoaly extends KPSPeli {
    private int muisti;
    private TekoalyParannettu tekoaly;

    protected KPSParempiTekoaly(IO io, int muisti) {
        super(io);
        this.muisti = muisti;
    }

    @Override
    protected String toisenSiirto() {
        var siirto = tekoaly.annaSiirto();
        io.putLine("Tietokone valitsi: " + siirto);
        return siirto;
    }

    @Override
    protected void pelinAluksi() {
        tekoaly = new TekoalyParannettu(muisti);
    }

    @Override
    protected void eranLopuksi(String ekanSiirto, String tokanSiirto) {
        tekoaly.asetaSiirto(ekanSiirto);
    }
}
