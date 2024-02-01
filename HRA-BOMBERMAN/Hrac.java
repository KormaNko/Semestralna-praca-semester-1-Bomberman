import fri.shapesge.Obrazok;
import fri.shapesge.Manazer;
/**@Matus Korman
 * Trieda reprezentuje hráča v hre.
 */
public class Hrac {
    private  Poloha poloha;          // Aktuálna poloha hráča
    private final Obrazok postava;   // Obrázok hráča               // Mapa, na ktorej sa hráč nachádza
    private boolean hracJeZivy;      // Kontrola ci je hrac stale na zive
    private Manazer manazer;        
    private int cisloAnimaciaHraca;
    /**
     * Konštruktor pre vytvorenie hráča na základe počiatočnej polohy.
     * parameter Počiatočná poloha hráča
     */
    public Hrac(Poloha poloha) {
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.hracJeZivy = true;
        this.poloha = poloha;       
        this.postava = new Obrazok(poloha.getCesta() + "1.png" );
        this.postava.zmenPolohu(poloha.getX(), poloha.getY());      
        this.postava.zobraz();
        this.cisloAnimaciaHraca = 0;
    }

    /**
     * Získa aktuálnu polohu hráča.
     *
     */
    public Poloha getPoloha() {
        return this.poloha;
    }
    
    /**
     * Získa súradnicu x aktuálnej polohy hráča.
     */
    public int getPolohaX() {
        return this.poloha.getX();
    }

    /**
     * Získa súradnicu y aktuálnej polohy hráča.
     */
    public int getPolohaY() {
        return this.poloha.getY();
    }

    /**
     * Nastaví obrázok pre hráča po jeho smrti.
     * Nastavi kontrolu zivota hraca ako mrtvi.
     */
    public void smrtHrca() {
        this.postava.zmenObrazok("MRTVOLAHRACA.png");
        this.hracJeZivy = false;
    }

    /**
     * Overí, či je hráč nažive.
     */
    public boolean isHracZivy() {
        return this.hracJeZivy;
    }

    /**
     * Nastaví novú polohu pre hráča a aktualizuje obrázok.
     */
    public void setPoloha(Poloha poloha) {
        this.postava.zmenPolohu(poloha.getX(), poloha.getY());      
        this.poloha = poloha;
    }
    
    /**
     * Vytvara animaciu hraca
     */
    public void animaciaHraca(Smer smer) {
        this.cisloAnimaciaHraca = (this.cisloAnimaciaHraca % 3) + 1;           
        this.postava.zmenObrazok(smer.getCesta() + this.cisloAnimaciaHraca + ".png");
             
    }
    
    /**
     * Skryje a znovu zobrazí hráča.
     */
    public void zobrazHraca() {
        this.postava.skry();
        this.postava.zobraz();
    }
    /**
     * Vynuluje cislo animacie hraca z dovodu aby ked hrac zmeni smer
     * aby je animacia znova zacinala od jednotky.
     */
    public void vynulujCisloAnimacieHrace(Smer novySmer, Smer smer) {
        if (smer != novySmer) {
            this.cisloAnimaciaHraca = 0;  
        }   
    }     
}
