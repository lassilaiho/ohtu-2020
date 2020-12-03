package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPSPeli {
    private Tekoaly tekoaly;

    protected KPSTekoaly(IO io) {
        super(io);
    }

    @Override
    protected void pelinAluksi() {
        tekoaly = new Tekoaly();
    }

    @Override
    protected String toisenSiirto() {
        var siirto = tekoaly.annaSiirto();
        io.putLine("Tietokone valitsi: " + siirto);
        return siirto;
    }
}