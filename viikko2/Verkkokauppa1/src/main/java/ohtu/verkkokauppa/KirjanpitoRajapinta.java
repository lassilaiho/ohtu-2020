package ohtu.verkkokauppa;

import java.util.*;

public interface KirjanpitoRajapinta {
    void lisaaTapahtuma(String tapahtuma);

    ArrayList<String> getTapahtumat();
}
