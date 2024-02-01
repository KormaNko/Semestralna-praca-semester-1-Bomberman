import java.awt.Rectangle;
import java.util.ArrayList;

/**@Matus Korman
 * V tejto triede pouzivam Rectangle z Javadoc dokumentáciu(Java Class Libraries).Pouzivam tu tvar Rectangle kvoli tomu
 * ze tvar Stvorec neobsahuje metodu intersects ktorou zistujem ci sa hrac a balonik navzajom dotkli.
 * https://docs.oracle.com/javase/8/docs/api/java/awt/Rectangle.html
 * 
 * 
 * Trieda zabezpečuje kontrolu kolízií medzi hráčom a balonikmi.
 */
public class Kolizie {
    private Rectangle obdlznikHraca;
    private ArrayList<Rectangle> obdlznikyBalonikov;

    /**
     * Konštruktor pre inicializáciu kolízií.
     *
     * Parameter obdĺžnik reprezentujúci hráča
     * Parameter zoznam obdĺžnikov reprezentuje baloniky na mape
     */
    public Kolizie(Rectangle obdlznikHraca, ArrayList<Rectangle> obdlznikyBalonikov) {
        this.obdlznikHraca = obdlznikHraca;
        this.obdlznikyBalonikov = obdlznikyBalonikov;
    }

    /**
     * Kontroluje kolíziu medzi hráčom a balonimi.
     *
     * Vracia true, ak došlo ku kolízii, inak false
     */
    public boolean kontrolaKolizie() {
        for (Rectangle obdlznikBaloniku : this.obdlznikyBalonikov) {
            if (this.obdlznikHraca.intersects(obdlznikBaloniku)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Aktualizuje obdĺžnik reprezentujúci hráča.
     *
     * Parameter nový obdĺžnik hráča
     */
    public void aktualizujObdlznikHraca(Rectangle novyObdlznikHraca) {
        this.obdlznikHraca = novyObdlznikHraca;
    }

    /**
     * Aktualizuje zoznam obdĺžnikov reprezentujúcich baloniky.
     *
     * Parameter nový zoznam obdĺžnikov balonikov.
     */
    public void aktualizujObdlznikyBalonikov(ArrayList<Rectangle> noveObdlznikyBalonikov) {
        this.obdlznikyBalonikov.clear();
        this.obdlznikyBalonikov.addAll(noveObdlznikyBalonikov);
    }
}
