package Paketit.Mallit;


/**
 *
 * @author teematve
 */
public class Ruokalaji {

    private String ruokalajinNimi;
    private String ruokalajinKehittaja;
    private String tyyppi;
    private String raaka_aineet;
    private String resepti;
    private String kuvaus;

    public Ruokalaji() {
        ruokalajinNimi = "tyhjä";
        ruokalajinKehittaja = "tyhjä";
        tyyppi = "tyhjä";
        raaka_aineet = "tyhjä";
        resepti = "tyhjä";
    }
    
    public Ruokalaji(String nimi) {
        ruokalajinNimi = nimi;
        ruokalajinKehittaja = "tyhjä";
        tyyppi = "tyhjä";
        raaka_aineet = "tyhjä";
        resepti = "tyhjä";
    }    

    public Ruokalaji(String nimi, String raaka_aineet, String resepti, String kuvaus) {
        this.ruokalajinNimi = nimi;
        this.raaka_aineet = raaka_aineet;
        this.resepti = resepti;
        this.kuvaus = kuvaus;
    }

    /* ********************************** */
    // Luokan getterit
    public String getRuokalajinNimi() {
        return this.ruokalajinNimi;
    }

    public String getRaaka_aineet() {
        return this.raaka_aineet;
    }

    public String getResepti() {
        return this.resepti;
    }

    public String getKuvaus() {
        return this.kuvaus;
    }

    /* ********************************** */
    // Luokan setterit
    public void setRuokalajinNimi(String nimi) {
        this.ruokalajinNimi = nimi;
    }

    public void setRaaka_aineet(String aineet) {
        this.raaka_aineet = aineet;
    }

    public void setResepti(String resepti) {
        this.resepti = resepti;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
}
