package Paketit.Mallit;

/**
 *
 * @author teematve
 */
public class Lisuke {

    private String lisukkeenNimi;
    private String ruokalajinNimi;
    private String kuvaus;

    public Lisuke() {
        lisukkeenNimi = "tyhjä";
        ruokalajinNimi = "tyhjä";
    }

    public Lisuke(String lisuke) {
        lisukkeenNimi = lisuke;
        ruokalajinNimi = "tyhjä";
    }
    
    public Lisuke(String lisuke, String ruokalaji) {
        this.lisukkeenNimi = lisuke;
        this.ruokalajinNimi = ruokalaji;
    }    

    public Lisuke(String lisuke, String ruokalaji, String kuvaus) {
        this.lisukkeenNimi = lisuke;
        this.kuvaus = kuvaus;
        this.ruokalajinNimi = ruokalaji;
    }

    /* ********************************** */
    // Luokan getterit
    public String getLisukkeenNimi() {
        return this.lisukkeenNimi;
    }

    public String getRuokalajinNimi() {
        return this.ruokalajinNimi;
    }

    public String getKuvaus() {
        return this.kuvaus;
    }

    /* ********************************** */
    // Luokan setterit
    public void setLisukkeenNimi(String nimi) {
        this.lisukkeenNimi = nimi;
    }

    public void setRuokalajinNimi(String nimi) {
        this.ruokalajinNimi = nimi;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
}
