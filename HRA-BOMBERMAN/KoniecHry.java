import fri.shapesge.Obrazok;
import fri.shapesge.BlokTextu;
import java.util.ArrayList;
import fri.shapesge.StylFontu;

/**
 * Trieda reprezentuje obrazovku konca hry.
 */
public class KoniecHry {
    private  static Obrazok prehraMenu;
    private  BlokTextu pocetZvysnychBalonikov;
    private  ArrayList<Zombici> baloniky;
    private  StylFontu bold;

    /**
     * Konštruktor pre vytvorenie obrazovky konca hry.
     *
     * parameter   Zoznam zombíkov, ktory su este na zive
     * parameter   Cesta k obrázku pre obrazovku konca hry (vyhra, prehra)
     */
    public KoniecHry(ArrayList<Zombici> baloniky, String cesta) {
        this.prehraMenu = new Obrazok(cesta, 80, -50);
        this.prehraMenu.zobraz();

        this.pocetZvysnychBalonikov = new BlokTextu("Stačilo ti už zabiť iba\n" + "       " + String.valueOf(baloniky.size()) + " balónikov.", 200, 600);
        this.pocetZvysnychBalonikov.zmenFont("Times New Roman", this.bold, 60);
        this.pocetZvysnychBalonikov.zmenFarbu("yellow");
        this.pocetZvysnychBalonikov.zobraz();
    }

    /**
     * Skryje napis  s počtom  balónikov ktore su este na zive(v pripade vyhry aby tam nebola 0).
     */
    public void skryZvysneBaloniky() {
        this.pocetZvysnychBalonikov.skry();
    }
}
