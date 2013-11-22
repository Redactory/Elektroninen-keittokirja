/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paketit.Mallit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author teematve
 */
public class Haut {

    static PreparedStatement kysely = null;
    static Connection yhteys = null;
    static ResultSet tulokset = null;

    /* ************************************** */
    //Tietyn käyttäjän haku tietokannasta.
    public static Kayttaja getKayttaja(String tunnus, String salasana) throws SQLException, NamingException {
        InitialContext cxt = new InitialContext();
        DataSource yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tietokanta");

        String sql = "SELECT Nimi, Tunnus, Salasana, Oikeudet from Kayttaja WHERE Tunnus = ? and Salasana = ?";


        Connection yhteys = yhteysVarasto.getConnection();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, tunnus);
        kysely.setString(2, salasana);
        ResultSet rs = kysely.executeQuery();


        //Alustetaan muuttuja, joka sisältää löydetyn käyttäjän
        Kayttaja kayttaja = null;

        if (rs.next() == true) {
            kayttaja = new Kayttaja(rs.getString("Nimi"), rs.getString("Tunnus"), rs.getString("Salasana"), rs.getInt("Oikeudet")); //Hakutuloksen sisältävä olio.
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

        return kayttaja;
    }

    /* ************************************** */
    //Tietokannan kaikkien käyttäjien esittäminen.    
    public static List<Kayttaja> getKayttajat(String tunnus, String salasana) throws SQLException, NamingException {
        InitialContext cxt = new InitialContext();
        DataSource yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tietokanta");

        String sql = "SELECT Nimi, Tunnus, Salasana, Oikeudet from Kayttaja";

        Connection yhteys = yhteysVarasto.getConnection();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();

        List<Kayttaja> kayttajat = new ArrayList<Kayttaja>(); //Hakutulokset sisältävä olio.

        while (rs.next() == true) {
            Kayttaja k = new Kayttaja(rs.getString("Nimi"), rs.getString("Tunnus"), rs.getString("Salasana"), rs.getInt("Oikeudet"));
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
    
    /* ************************************** */
    //Tietyn reseptin haku tietokannasta sen nimellä.
    public static Kayttaja getResepti(HttpServletRequest request, HttpServletResponse response, String nimi) throws SQLException, NamingException, Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        List<String> lista = new ArrayList<String>();    // Tulosten talletukseen taulukko

        String sql = "SELECT * FROM Ruokalaji WHERE Nimi = ?";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, nimi);
        tulokset = kysely.executeQuery();

        // Varmistetaan ettei tyhjät tulokset pääse eteenpäin.
        if (tulokset == null) {
            ServlettiIsa.naytaVirhe(request, "Kyseistä ruokalajia ei löydy tietokannasta.");
            ServlettiIsa.naytaJSP("Etusivu_jalkeen.jsp", request, response);
        }

        while (tulokset.next() == true) {
            lista.add(tulokset.getString("Nimi"));
        }

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        request.setAttribute("ruokalaji", lista);
        return null;
    }    
}
