package Paketit.Mallit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author teematve
 */
public class Kayttaja {

    private String nimi;
    private String tunnus;
    private String salasana;
    private int oikeudet;

    public Kayttaja() {
        this.nimi = "tyhjä";
        this.tunnus = "guest";
        this.salasana = "guest";
        this.oikeudet = 0;
    }

    public Kayttaja(String nimi) { //Väliaik. konstruktori
        this.nimi = nimi;
    }

    public Kayttaja(String tunnus, String salasana) { //Väliaik. konstruktori
        this.tunnus = tunnus;
        this.salasana = salasana;
    }

    public Kayttaja(String nimi, String tunnus, String salasana) { //Väliaik. konstruktori
        this.nimi = nimi;
        this.tunnus = tunnus;
        this.salasana = salasana;
    }

    public Kayttaja(String nimi, String tunnus, String salasana, int oikeudet) {
        this.nimi = nimi;
        this.tunnus = tunnus;
        this.salasana = salasana;
        this.oikeudet = oikeudet;
    }

    /* ********************************************** */
    // Luokan getterit.
    public String getNimi() {
        return this.nimi;
    }

    public String getTunnus() {
        return this.tunnus;
    }

    public String getSalasana() {
        return this.salasana;
    }

    public int getOikeudet() {
        return this.oikeudet;
    }

    /* ********************************************** */
    // Luokan setterit.    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setTunnus(String tunnus) {
        this.tunnus = tunnus;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public void setOikeudet(int oikeudet) {
        this.oikeudet = oikeudet;
    }

    /* ************************************** */
    //Lista-esimerkki. (EI VALMIISEEN PROJEKTIIN!!!!)
    public static List<Kayttaja> getKayttajat() throws SQLException, NamingException {
        InitialContext cxt = new InitialContext();
        DataSource yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tietokanta");

        String sql = "SELECT Nimi,Tunnus, Salasana from Kayttaja";
        Connection yhteys = yhteysVarasto.getConnection();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();

        ArrayList<Kayttaja> kayttajat = new ArrayList<Kayttaja>();
        while (rs.next()) {
            //Kutsutaan sopivat tiedot vastaanottavaa konstruktoria ja palautetaan olio:
            Kayttaja k = new Kayttaja(rs.getString("Nimi"), rs.getString("Tunnus"), rs.getString("Salasana"));
            kayttajat.add(k);
        }
        //Suljetaan kaikki resurssit:
        try {
            rs.close();
        } catch (Exception e) {
        }
        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }

        return kayttajat;
    }
    /* *********** */
    //
}
