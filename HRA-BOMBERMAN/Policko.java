import fri.shapesge.Obrazok;

/**@Matus Korman
 * Trieda reprezentuje políčko na hracej ploche.
 */
public class Policko {
    private Obrazok[] policka;  // Pole obrázkov pre políčko

    /**
     * Konštruktor pre vytvorenie políčka so zadanou polohou a cestou k obrázku.
     *
     * parameter  Súradnica x polohy políčka
     * parameter  Súradnica y polohy políčka
     * parameter  Cesta k obrázku políčka
     */
    public Policko(int x, int y, String cesta) {
        this.policka = new Obrazok[3];  // Vytvoríme pole troch obrázkov pre políčko
        for (int i = 0; i < this.policka.length; i++) {
            this.policka[i] = new Obrazok(cesta, 0, 0);  // Vytvori každý obrázok
            this.policka[i].posunVodorovne(x * 50);      // Posunieme vodorovne na základe súradnice x, (50) velkost policka
            this.policka[i].posunZvisle(y * 50);         // Posunieme zvisle na základe súradnice y, (50) velkost policka
            this.policka[i].zobraz();                    // Zobrazí obrázok
        }
    }
}


