import fri.shapesge.Obrazok;
/**@Matus Korman
 * Trieda reprezentuje explóziu so stredom a vetvami v rôznych smeroch.
 */
public class Explozia {
    private   Obrazok stred;  // Obrázok stredovej časti explózie
    private   Obrazok vPravo;  // Obrázok explózie smerujúcej doprava 
    private   Obrazok vLavo;   // Obrázok explózie smerujúcej doľava 
    private   Obrazok hore;    // Obrázok explózie smerujúcej hore 
    private   Obrazok dole;    // Obrázok explózie smerujúcej dole 

    /**
     * Konštruktor pre vytvorenie explózie na zadaných súradniciach.
     *
     * parameter je x Súradnica stredu explózie
     * parameter je y Súradnica stredu explózie
     */
    public Explozia(int x, int y) {       
        this.stred = new Obrazok("stred.png", x, y);
        this.stred.zobraz();

        // Inicializácia obrázkov pre jednotlivé vetvy explózie
        this.vPravo = new Obrazok("kraj.png", x + 45, y + 11);
        this.vLavo = new Obrazok("kraj.png", x - 45, y + 11);
        this.vLavo.zmenUhol(180);
        this.hore = new Obrazok("kraj.png", x, y - 30);
        this.hore.zmenUhol(270);
        this.dole = new Obrazok("kraj.png", x, y + 50);
        this.dole.zmenUhol(90);   
    }

    /**
     * Zobrazí vetvu explózie smerujúcu doprava od stredu.
     */
    public void zobrazVpravo() {
        this.vPravo.zobraz();
    }
    
    /**
     * Zobrazí vetvu explózie smerujúcu doľava od stredu.
     */
    public void zobrazVlavo() {
        this.vLavo.zobraz();
    }
    
    /**
     * Zobrazí vetvu explózie smerujúcu hore od stredu.
     */
    public void zobrazHore() {
        this.hore.zobraz();
    }
    
    /**
     * Zobrazí vetvu explózie smerujúcu dole od stredu.
     */
    public void zobrazDole() {
        this.dole.zobraz();
    }
    
    /**
     * Skryje všetky časti explózie.
     */
    public void skryExploziu() {
        this.stred.skry();
        this.vPravo.skry();
        this.vLavo.skry();
        this.hore.skry();
        this.dole.skry();
    }  
}
