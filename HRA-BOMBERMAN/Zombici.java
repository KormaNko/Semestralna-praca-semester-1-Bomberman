import fri.shapesge.Obrazok;
import fri.shapesge.Manazer;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**@Matus Korman
 * Trieda reprezentuje balonik v hre.
 */
public class Zombici {
    
    private final Manazer manazer;  //Manazer ktory sluzi na ovladanie pohybu balonika
    private Obrazok balonik;        //Obrazok balonika
    private int balonikX;           // Suradnica balonika X
    private int balonikY;           // Suradnica balonika Y
    private boolean hore;           //Kontrola ci balonik dosiel uz dokonca a ma sa otocit
    private boolean vertikalne;     //Urcuje ci sa ma balonik pohybovat vertikalne alebo horizontalne
    private boolean isBalonikZivy;  //Kontrola ci je balonik nazive
    private String cesta;           //Cesta ku obrazku balonika   
    private Mapa mapa;              //Mapa po ktorej sa balonik pohybuje
    private boolean vpravo;
    /**
     * Konštruktor pre vytvorenie zombíka na základe súradníc, mapy a orientácie pohybu.
     *
     * parameter   Súradnica x balonika
     * parameter   Súradnica y balonika
     * parameter   Mapa, na ktorej sa balonik pohybuje
     * parameter   Určuje, či sa balonik pohybuje vertikálne (true) alebo horizontálne (false)
     * 
     */
    public Zombici(int x, int y, Mapa mapa, boolean vertikalne) throws IOException {
        this.mapa = mapa;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.balonikX = x;
        this.balonikY = y;
        this.vertikalne = vertikalne;
        this.cesta = ("balonik2.png");
        this.balonik = new Obrazok(this.cesta, x, y);
        this.hore = false;
        this.balonik.zobraz();
        this.isBalonikZivy = true;
        
    }

    /**
     * Pohyb balonika v smere osi Y (vertikálny pohyb).
     */
    public void pohybBalonikaY() {
        if (this.vertikalne && this.isBalonikZivy) {
            if (!this.hore) {
                if (this.mapa.getPolicko(this.getBalonikaX(), this.getBalonikaY() - 2) == StavPolicka.TRAVA) {
                    this.setPolohuBalonika(this.getBalonikaX(), this.getBalonikaY() - 2);
                    this.balonikY = this.getBalonikaY() - 2;
                    if (this.mapa.getPolicko(this.getBalonikaX(), this.getBalonikaY() - 2) != StavPolicka.TRAVA) {
                        this.hore = true;
                    }
                }
            } else if (this.mapa.getPolicko(this.getBalonikaX(), this.getBalonikaY() + 42) == StavPolicka.TRAVA) {
                this.setPolohuBalonika(this.getBalonikaX(), this.getBalonikaY() + 2);
                this.balonikY = this.getBalonikaY() + 2;
                if (this.mapa.getPolicko(this.getBalonikaX(), this.getBalonikaY() + 42) != StavPolicka.TRAVA) {
                    this.hore = false;
                }
            }
        }
    }

    /**
     * Pohyb balonika v smere osi X (horizontálny pohyb).
     */
    public void pohybBalonikaX() {
        if (!this.vertikalne && this.isBalonikZivy) {
            if (!this.hore) {
                if (this.mapa.getPolicko(this.getBalonikaX() - 2, this.getBalonikaY()) == StavPolicka.TRAVA) {
                    this.setPolohuBalonika(this.getBalonikaX() - 2, this.getBalonikaY());
                    this.balonikX = this.getBalonikaX() - 2;
                    if (this.mapa.getPolicko(this.getBalonikaX() - 2, this.getBalonikaY()) != StavPolicka.TRAVA) {
                        this.hore = true;
                    }
                }
            } else if (this.mapa.getPolicko(this.getBalonikaX() + 42, this.getBalonikaY()) == StavPolicka.TRAVA) {
                this.setPolohuBalonika(this.getBalonikaX() + 2, this.getBalonikaY());
                this.balonikX = this.getBalonikaX() + 2;
                if (this.mapa.getPolicko(this.getBalonikaX() + 42, this.getBalonikaY()) != StavPolicka.TRAVA) {
                    this.hore = false;
                }
            }
        }
    }

    /**
     * Zobrazí obrázok balonika na hracej ploche.
     */
    public void zobrazBalonik() {
        this.balonik.zobraz();
    }

    /**
     * Vráti súradnicu x balonika.
     */
    public int getBalonikaX() {
        return this.balonikX;
    }

    /**
     * Vráti súradnicu y balonika.
     */
    public int getBalonikaY() {
        return this.balonikY;
    }

    /**
     * Nastaví novú polohu pre balonik.
     *
     * parameter x Nová súradnica x
     * parameter y Nová súradnica y
     */
    private void setPolohuBalonika(int x, int y) {
        if (this.balonik != null && this.isBalonikZivy) {
            this.balonik.zmenPolohu(x, y);
            this.balonik.skry();
            this.balonik.zobraz();
        }
    }
    
    /**
     * Zabije balonik
     */
    public void zabiBalonik() {
        this.isBalonikZivy = false;
        this.balonik.zmenObrazok("MRTVOLABALONIKA.png");
        this.balonik.skry();
        this.balonik.zobraz();
    }
    
    /**
     * Skryje balonik.
     */
    public void skryBalonika() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(() -> {
            this.balonik.skry();
        }, 2, TimeUnit.SECONDS);
        executor.shutdown();
    }
    
    /**
     * Meni obrazky balonika, vytvara animaciu.
     */
    public void animaciaBalonika() {
        if (this.isBalonikZivy) {
            switch (this.cesta) {
                case ("balonik2.png"):
                    this.cesta = ("balonik3.png");
                    this.balonik.zmenObrazok("balonik3.png");
                    break;
                case ("balonik3.png"):
                    this.cesta = ("balonik2.png");
                    this.balonik.zmenObrazok("balonik2.png");
                    break;
            }
        }
    }
    
    /**
     * Manazer prestava spravovat tento objekt.
     */
    public void prestanSpravovat() {
        this.manazer.prestanSpravovatObjekt(this);
    }
}