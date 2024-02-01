import fri.shapesge.Obrazok;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
/**@Matus Korman
 * V tejto triede pouzivam casovac z Javadoc dokumentácie(Java Class Libraries). 
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html!
 *
 * 
 * Trieda reprezentuje bombu, ktorá môže explodovať a zabijat veci ktore ma v dosahu.
 */
public class Bomba {
    private static Obrazok bomba;              // Obrázok bomby
    private int bombaX;                 // Súradnica x bomby
    private int bombaY;                 // Súradnica y bomby
    private Explozia explozia;          // Objekt explózie spojenej s bombou
    private Mapa mapa;                  // Mapa, na ktorej sa bomba nachádza
    private Hrac hrac;                  // Hráč, ktorého môže bomba zabiť
    private ArrayList<Zombici> baloniky; // Zoznam zombíkov, ktorých môže bomba zabiť
    /**
     * Konštruktor pre vytvorenie bomby na zadaných súradniciach.
     *
     * parameter Súradnica x bomby
     * parameter Súradnica y bomby
     * parameter Mapa, na ktorej sa bomba nachádza
     * parameter Hráč, ktorého môže bomba zabiť
     * parameter Zoznam zombíkov, ktorých môže bomba zabiť
     */
    public Bomba(int x, int y, Mapa mapa, Hrac hrac, ArrayList<Zombici> baloniky) {
        this.bombaX = x; 
        this.bombaY = y; 
        this.bomba = new Obrazok("bomba.png", x, y);
        this.bomba.zobraz();
        this.mapa = mapa;
        this.hrac = hrac;
        this.baloniky = baloniky;
    }

    /**
     * Skryje obrázok bomby.
     */
    public void skry() {
        this.bomba.skry();
    }

    /**
     * Získa súradnicu x bomby.
     */
    public int getBombaX() {
        return this.bombaX;
    }

    /**
     * Získa súradnicu y bomby.
     */
    public int getBombaY() {
        return this.bombaY;
    }

    /**
     * Spustí explóziu bomby s oneskorením.
     *
     */
    public void vybuchBombySOneskorenim() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(() -> {
            this.bomba.skry();
            this.explozia = new Explozia(this.getBombaX() / 50 * 50, this.getBombaY() / 50 * 50);
            this.skryExploziu();
            if (this.mapa.getPolicko(this.getBombaX(), this.getBombaY() - 50) != StavPolicka.PEVNASTENA) {
                this.mapa.zmenaPolickoNaTravu(this.getBombaX(), this.getBombaY() - 50);
                this.explozia.zobrazHore();
            }
            if (this.mapa.getPolicko(this.getBombaX(), this.getBombaY() + 50) != StavPolicka.PEVNASTENA) {
                this.mapa.zmenaPolickoNaTravu(this.getBombaX(), this.getBombaY() + 50);
                this.explozia.zobrazDole();
            }
            if (this.mapa.getPolicko(this.getBombaX() + 60, this.getBombaY()) != StavPolicka.PEVNASTENA) {
                this.mapa.zmenaPolickoNaTravu(this.getBombaX() + 60, this.getBombaY());
                this.explozia.zobrazVpravo();
            }
            if (this.mapa.getPolicko(this.getBombaX() - 30, this.getBombaY()) != StavPolicka.PEVNASTENA) {
                this.mapa.zmenaPolickoNaTravu(this.getBombaX() - 30, this.getBombaY());
                this.explozia.zobrazVlavo();
            }
            this.balonikZabiVsetko();
        }, 5, TimeUnit.SECONDS);
        executor.shutdown();
    }

    /**
     * Skryje explóziu s oneskorením. 
     */
    private void skryExploziu() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(() -> {
            this.explozia.skryExploziu();
        }, 1, TimeUnit.SECONDS);
        executor.shutdown();             
    }

    /**
     * Zabije hráča a zombíkov, ktorí sú v blízkosti bomby.
     */
    private void balonikZabiVsetko() {
        int hracRiadok = this.hrac.getPolohaX() / 50;
        int hracStlpec = this.hrac.getPolohaY() / 50;
        int bombaRiadok = this.getBombaX() / 50;
        int bombaStlpec = this.getBombaY() / 50;
        if (this.suRovnakePozicie(hracRiadok, hracStlpec, bombaRiadok, bombaStlpec) ||
            this.suRovnakePozicie(hracRiadok, hracStlpec, bombaRiadok, (bombaStlpec - 1)) ||
            this.suRovnakePozicie(hracRiadok, hracStlpec, bombaRiadok, (bombaStlpec + 1)) ||
            this.suRovnakePozicie(hracRiadok, hracStlpec, (bombaRiadok + 1), bombaStlpec) ||
            this.suRovnakePozicie(hracRiadok, hracStlpec, (bombaRiadok - 1), bombaStlpec)) {
            this.hrac.smrtHrca();
        }
        for (Zombici aktBalonik : this.baloniky) {
            int balonikRiadok = aktBalonik.getBalonikaX() / 50;
            int balonikStlpec = aktBalonik.getBalonikaY() / 50;
            if (this.suRovnakePozicie(bombaRiadok, bombaStlpec, balonikRiadok, balonikStlpec) ||
                this.suRovnakePozicie(bombaRiadok, bombaStlpec, balonikRiadok, balonikStlpec + 1) ||
                this.suRovnakePozicie(bombaRiadok, bombaStlpec, balonikRiadok, balonikStlpec - 1) ||
                this.suRovnakePozicie(bombaRiadok, bombaStlpec, balonikRiadok + 1, balonikStlpec) ||
                this.suRovnakePozicie(bombaRiadok, bombaStlpec, balonikRiadok - 1, balonikStlpec)) {
                aktBalonik.zabiBalonik();
                this.baloniky.remove(aktBalonik);
                aktBalonik.skryBalonika();
            }
        }
    }

    /**
     * Je to pomocna metoda ku metode balonikZabiVsetko.
     * Porovná, či sú dané pozície rovnaké.
     *
     * parameter   Riadok prvej pozície
     * parameter   Stĺpec prvej pozície
     * parameter   Riadok druhej pozície
     * parameter   Stĺpec druhej pozície
     * parameter   True, ak sú pozície rovnaké, inak false
     */
    private boolean suRovnakePozicie(int riadok1, int stlpec1, int riadok2, int stlpec2) {
        return riadok1 == riadok2 && stlpec1 == stlpec2;
    }
}
