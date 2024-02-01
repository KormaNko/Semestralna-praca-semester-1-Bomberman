import java.io.IOException;
import fri.shapesge.Manazer;
import java.util.ArrayList;
import java.awt.Rectangle;

/**@Matus Korman
 * Trieda Hra reprezentuje herné prostredie stara sa o ovladanie hraca.Kontroluje koliziu balonikov s hracom.
 * Taktiez ma nastarosti stav hry a koniec hry.
 */
public class Hra {
    private static final int VELKOST_KROKU = 7;
    private final Manazer manazer;
    private Mapa mapa;
    private Hrac hrac;
    private Smer smer;
    private Poloha poloha;
    private Bomba bomba;
    private boolean bombaAktivovana;
    private ArrayList<Zombici> baloniky;
    private Kolizie kolizie;
    private boolean vysledokKolizie;
    private Rectangle obdlznikHraca;
    private int aktualnaPolohaBombyX;
    private int aktualnaPolohaBombyY;
    private KoniecHry finish;

    /**
     * 
     * Konštruktor inicializuje herné prostredie a objekty v ňom.
     *
     * throws IOException Ak nastane chyba pri načítaní mapy.
     */
    public Hra() throws IOException {
        // Inicializácia premenných
        this.smer = Smer.DOLE;
        this.hrac = new Hrac(new Poloha(250, 250, this.smer.getCesta()));
        this.poloha = new Poloha(250, 250, this.smer.getCesta());
        this.mapa = new Mapa("m.txt");
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.baloniky = new ArrayList<>();
        this.baloniky.add(new Zombici(355, 800, this.mapa, true));
        this.baloniky.add(new Zombici(555, 600, this.mapa, true));
        this.baloniky.add(new Zombici(700, 755, this.mapa, false));
        this.baloniky.add(new Zombici(55, 555, this.mapa, false));
        this.baloniky.add(new Zombici(155, 155, this.mapa, false));
        this.baloniky.add(new Zombici(355, 55, this.mapa, false));
        this.baloniky.add(new Zombici(850, 850, this.mapa, true));
        this.obdlznikHraca = new Rectangle(this.hrac.getPolohaX(), this.hrac.getPolohaY(), 32, 38);
        this.kolizie = new Kolizie(this.obdlznikHraca, this.vytvorObdlznikyBalonikov());
    }

    /**
     * Vytvorí obdĺžniky(hitboxy) pre všetky balóniky.

     */
    private ArrayList<Rectangle> vytvorObdlznikyBalonikov() {
        ArrayList<Rectangle> obdlzniky = new ArrayList<>();
        for (Zombici balonik : this.baloniky) {
            obdlzniky.add(new Rectangle(balonik.getBalonikaX(), balonik.getBalonikaY(), 36, 40));
        }
        return obdlzniky;
    }

    /**
     * Posunie hráča smerom hore, ak je to možné.
     */
    public void posunHore() {
        if (this.hrac.isHracZivy() && this.mapa.getPolicko(this.hrac.getPolohaX(), this.hrac.getPolohaY() - 5) == StavPolicka.TRAVA && this.mapa.getPolicko(this.hrac.getPolohaX() + 27, this.hrac.getPolohaY() - 5) == StavPolicka.TRAVA) {
            this.hrac.vynulujCisloAnimacieHrace(Smer.HORE, this.smer);
            this.smer = Smer.HORE;
            this.hrac.animaciaHraca(Smer.HORE);                   
            this.hrac.setPoloha(this.getNovaPoloha(Smer.HORE));          
        }
    }

    /**
     * Posunie hráča smerom dole, ak je to možné.
     */
    public void posunDole() {
        if (this.hrac.isHracZivy() && this.mapa.getPolicko(this.hrac.getPolohaX() + 20, this.hrac.getPolohaY() + 44) == StavPolicka.TRAVA) {
            this.hrac.vynulujCisloAnimacieHrace(Smer.DOLE, this.smer);
            this.smer = Smer.DOLE;
            this.hrac.animaciaHraca(Smer.DOLE);                
            this.hrac.setPoloha(this.getNovaPoloha(Smer.DOLE));         
        }
    }

    /**
     * Posunie hráča smerom vľavo, ak je to možné.
     */
    public void posunVlavo() {
        if (this.hrac.isHracZivy() && this.mapa.getPolicko(this.hrac.getPolohaX() - 8, this.hrac.getPolohaY() + 38) == StavPolicka.TRAVA && this.mapa.getPolicko(this.hrac.getPolohaX(), this.hrac.getPolohaY()) == StavPolicka.TRAVA) {
            this.hrac.vynulujCisloAnimacieHrace(Smer.VLAVO, this.smer);
            this.smer = Smer.VLAVO;
            this.hrac.animaciaHraca(Smer.VLAVO);             
            this.hrac.setPoloha(this.getNovaPoloha(Smer.VLAVO));

        }
    }

    /**
     * Posunie hráča smerom vpravo, ak je to možné.
     */
    public void posunVpravo() {
        if (this.hrac.isHracZivy() && this.mapa.getPolicko(this.hrac.getPolohaX() + 40, this.hrac.getPolohaY() + 38) == StavPolicka.TRAVA && this.mapa.getPolicko(this.hrac.getPolohaX() + 32, this.hrac.getPolohaY()) == StavPolicka.TRAVA) {
            this.hrac.vynulujCisloAnimacieHrace(Smer.VPRAVO, this.smer);
            this.smer = Smer.VPRAVO;
            this.hrac.animaciaHraca(Smer.VPRAVO);          
            this.hrac.setPoloha(this.getNovaPoloha(Smer.VPRAVO));
        }
    }

    /**
     * Aktivuje bombu, ak ešte nebola aktivovaná a hráč je nažive.
     */
    public void aktivujBombu() {
        if (!this.bombaAktivovana && this.hrac.isHracZivy()) {
            this.bomba = new Bomba((this.hrac.getPolohaX() + 15) / 50 * 50 + 12, (this.hrac.getPolohaY() + 15) / 50 * 50 + 12, this.mapa, this.hrac, this.baloniky);
            this.bombaAktivovana = true;
            this.bomba.vybuchBombySOneskorenim();
        }
    }

    /**
     * Sluzi na to aby nebolo mozne aktivovat novu bombu pokial aktualna este nevybuchla.
     */
    public void vybuchBomby() {
        if (this.bombaAktivovana) {
            this.bombaAktivovana = false;
        }
    }

    /**
     * Vráti novú polohu hráča na základe zvoleného smeru.
     *
     * parameter Zvolený smer pohybu hráča.    
     */
    private Poloha getNovaPoloha(Smer smer) {
        Poloha novaPoloha = this.hrac.getPoloha();
        return novaPoloha.getPosunutuPolohu(VELKOST_KROKU, smer);
    }

    /**
     * Pohne hráča a aktualizuje Obdlznik ktory je okolo hraca na novu poziciu hraca.
     */
    public void vytvaranieHitboxov() {
        this.hrac.zobrazHraca();
        if (this.hrac.isHracZivy() && this.kolizie != null) {
            Rectangle novyObdlznikHraca = new Rectangle(this.hrac.getPolohaX(), this.hrac.getPolohaY(), 35, 40);
            this.kolizie.aktualizujObdlznikHraca(novyObdlznikHraca);
        }
    }

    /**
     * Kontroluje kolíziu medzi hráčom a balónikmi.
     */
    public void kontrolaKolizie() {
        if (this.kolizie != null) {
            ArrayList<Rectangle> obdlznikyBalonikov = new ArrayList<>();
            for (Zombici balonik : this.baloniky) {
                Rectangle noveObdlznikBalonika = new Rectangle(balonik.getBalonikaX(), balonik.getBalonikaY(), 40, 46);
                obdlznikyBalonikov.add(noveObdlznikBalonika);
            }
            this.kolizie.aktualizujObdlznikyBalonikov(obdlznikyBalonikov);
            if (this.hrac.isHracZivy() && this.kolizie.kontrolaKolizie()) {
                this.smer = Smer.SMRTHRACA;
                this.hrac.smrtHrca();
            }
        }
    }

    /**
     * Kontroluje stav hry - ukončenie v prípade víťazstva alebo prehry.
     */
    public  void  kontrolaStavuHry() {
        if (this.baloniky.size() <= 0) {
            this.finish = new KoniecHry(this.baloniky, "win.png");
            this.finish.skryZvysneBaloniky();
            this.manazer.prestanSpravovatObjekt(this); 
        }
        if (!this.hrac.isHracZivy()) {
            this.hrac.zobrazHraca();
            this.finish = new KoniecHry(this.baloniky, "gameover.png");

            for (Zombici balonik : this.baloniky) {
                balonik.prestanSpravovat();               
            }
            this.manazer.prestanSpravovatObjekt(this);
        }
    }   
}
