/**@Matus Korman
 * Trieda reprezentuje polohu v hernom svete.
 */
public class Poloha {
    private final int x;      // Súradnica x
    private final int y;      // Súradnica y
    private final String cesta;     // Cesta k obrázku s konkretnou touto polohou

    /**
     * Konštruktor pre vytvorenie polohy na základe súradníc a cesty k obrázku.
     *
     * parameter     Súradnica x
     * parameter     Súradnica y
     * parameter     Cesta k obrázku
     */
    public Poloha(int x, int y, String cesta) {
        this.x = x;
        this.y = y;
        this.cesta = cesta;
    }

    /**
     * Získa súradnicu x polohy.
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Získa cestu k obrazku.
     */
    public String getCesta() {
        return this.cesta;
    }
    
    /**
     * Získa súradnicu y polohy.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Vytvorí novú polohu posunutú v zadanom smere a kroku.
     *
     * parameter Krok posunutia
     * parameter Smer posunutia
     * Vracia novu polohu po posunutí
     * !Tato metoda je z ulohy na hodine menom "Vlacik"!
     * !Metodu vytvoril Ing. Štefan Toth, PhD. !
     */
    public Poloha getPosunutuPolohu(int krok, Smer smer) {
        return new Poloha(this.x + krok * smer.getX(), this.y + krok * smer.getY(), smer.getCesta());
    }
}
