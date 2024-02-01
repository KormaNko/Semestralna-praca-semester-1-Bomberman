/**@Matus Korman
*Enum reprezentuje rôzne smerové vektory a k nim patrace obrazky.
*Smer HORE vracia x suradnicu 0 y suradnicu -1 a obrazok hraca iduceho hore.
*Smer DOLE vracia x suradnicu 0 y suradnicu 1 a obrazok hraca iduceho dole.
*Smer VPRAVO vracia x suradnicu 1 y suradnicu 0 a obrazok hraca iduceho vpravo.
*Smer VLAVO vracia x suradnicu -1 y suradnicu  a obrazok hraca iduceho vlavo.
*Smer STOJ vracia x suradnicu 0 y suradnicu 0 
*SMRTHRACA;  //Smer SMRTHRACA vracia x suradnicu 0 y suradnicu 0 a obrazok mrtveho hraca.
*/
public enum Smer {
    HORE, //Smer HORE vracia x suradnicu 0 y suradnicu -1 a obrazok hraca iduceho hore
    DOLE,   //Smer DOLE vracia x suradnicu 0 y suradnicu 1 a obrazok hraca iduceho dole
    VPRAVO, //Smer VPRAVO vracia x suradnicu 1 y suradnicu 0 a obrazok hraca iduceho vpravo
    VLAVO,  //Smer VLAVO vracia x suradnicu -1 y suradnicu  a obrazok hraca iduceho vlavo
    STOJ,   //Smer STOJ vracia x suradnicu 0 y suradnicu 0 
    SMRTHRACA;  //Smer SMRTHRACA vracia x suradnicu 0 y suradnicu 0 a obrazok mrtveho hraca
    /**
     * Vracia suradnicu X
     */
    public int getX() {
        switch (this) {
            case VPRAVO: 
                return 1;
            case VLAVO:
                return -1;
            default: 
                return 0;

        }
    }
    /**
     * Vracia suradnicu Y
     */
    public int getY() {
        switch (this) {
            case HORE: 
                return -1;
            case DOLE:
                return 1;
            default: 
                return 0;
        }
    }
    /**
     * Vracia obrazok
     */
    public String getCesta() {
        switch (this) {
            case HORE:
                return "HORE";
            case DOLE:
                return "DOLE";
            case VPRAVO:
                return "VPRAVO";
            case VLAVO:
                return "VLAVO";
            case SMRTHRACA:
                return "MRTVOLAHRACA";
            default:
                return null;
        }
         
    }
}
