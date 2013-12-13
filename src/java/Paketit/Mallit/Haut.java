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
    public static Kayttaja getKayttaja(String tunnus, String salasana) throws SQLException, NamingException, Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "SELECT Nimi, Tunnus, Salasana, Oikeudet from Kayttaja WHERE Tunnus = ? and Salasana = ?";

        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, tunnus);
        kysely.setString(2, salasana);
        ResultSet rs = kysely.executeQuery();


        //Alustetaan muuttuja, joka sisältää löydetyn käyttäjän
        Kayttaja kayttaja = null;

        if (rs.next() == true) {
            kayttaja = new Kayttaja(rs.getString("Nimi"), rs.getString("Tunnus"), rs.getString("Salasana"), rs.getInt("Oikeudet")); //Hakutuloksen sisältävä olio.
        }

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        return kayttaja;
    }

    /* ************************************** */
    //Tietokannan kaikkien käyttäjien esittäminen.    
    public static void getKayttajat(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException, Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "SELECT Nimi, Tunnus, Salasana, Oikeudet from Kayttaja";

        kysely = yhteys.prepareStatement(sql);
        ResultSet rs = kysely.executeQuery();

        List<Kayttaja> kayttajat = new ArrayList<Kayttaja>(); //Hakutulokset sisältävä olio.

        while (rs.next() == true) {
            Kayttaja k = new Kayttaja(rs.getString("Nimi"), rs.getString("Tunnus"), rs.getString("Salasana"), rs.getInt("Oikeudet"));
            kayttajat.add(k);
        }

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        request.setAttribute("kayttajat", kayttajat);
    }

    /* ************************************** */
    //Tietyn reseptin haku tietokannasta sen nimellä.
    public static void getResepti(HttpServletRequest request, HttpServletResponse response, String ruokaNimi) throws SQLException, NamingException, Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        Ruokalaji ruoka = null;

        String sql = "SELECT Nimi, Raaka_aineet, Resepti, Kuvaus FROM Ruokalaji WHERE Nimi = ?";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, ruokaNimi);
        tulokset = kysely.executeQuery();

        // Varmistetaan ettei tyhjät tulokset pääse eteenpäin.
        if (tulokset == null) {
            TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
            TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
            TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

            ServlettiIsa.naytaVirhe(request, "Kyseistä ruokalajia ei löydy tietokannasta.");
            ServlettiIsa.naytaJSP("Etusivu_jalkeen.jsp", request, response);
        }

        if (tulokset.next() == true) {
            // Olioon sijoitettavat parametrit.
            String ruokalaji = tulokset.getString("Nimi");
            String rA = tulokset.getString("Raaka_aineet");
            String resepti = tulokset.getString("Resepti");
            String kuvaus = tulokset.getString("Kuvaus");

            // Tuloksen sisältävän olion luominen.
            ruoka = new Ruokalaji(ruokalaji, rA, resepti, kuvaus);
        }

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        request.setAttribute("ruokalaji", ruoka);       // Lähetetään kysytyn ruokalajin tiedot eteenpäin
    }
    
    /* ************************************** */
    //Tietyn lisukkeen haku tietokannasta.
    public static Lisuke getLisuke(String nimi) throws SQLException, NamingException, Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "SELECT Nimi, Ruokalaji, Kuvaus FROM Lisukkeet WHERE Nimi = ?";

        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, nimi);
        ResultSet rs = kysely.executeQuery();


        //Alustetaan muuttuja, joka sisältää löydetyn käyttäjän
        Lisuke lisuke = null;

        if (rs.next() == true) {
            lisuke = new Lisuke(rs.getString("Nimi"), rs.getString("Ruokalaji"), rs.getString("Kuvaus")); //Hakutuloksen sisältävä olio.
        }

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        return lisuke;
    }    

    /* ************************************** */
    //Ruokalajin lisukkeiden listaus ruokalajin nimen perusteella.
    public static void Lisukeet(HttpServletRequest request, HttpServletResponse response, String ruokaNimi) throws SQLException, NamingException, Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        List<Lisuke> lista = new ArrayList<Lisuke>();    // Tulosten talletukseen taulukko
        Lisuke l;

        String sql = "SELECT Ruokalaji_nimi, Lisuk_nimi FROM Ruokalajin_lisukkeet WHERE Ruokalaji_nimi = ?";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, ruokaNimi);
        tulokset = kysely.executeQuery();

        // Varmistetaan ettei tyhjät tulokset pääse eteenpäin.
        if (tulokset == null) {
            TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
            TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
            TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

            ServlettiIsa.naytaVirhe(request, "Tietokannassa ei ole yhtään reseptiä.");
            ServlettiIsa.naytaJSP("Etusivu_ennen.jsp", request, response);
        }

        while (tulokset.next() == true) {
            String ruoka = tulokset.getString("Ruokalaji_nimi");
            String lisuke = tulokset.getString("Lisuk_nimi");
            l = new Lisuke(lisuke, ruoka);
            lista.add(l);
//            lista.add(tulokset.getString("Nimi"));
        }

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        request.setAttribute("lisukkeet", lista);
    }

    /* ************************************** */
    //Ruokalajin kaikkien lisukkeiden listaus.    
    public static void KaikkiLisukkeet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        List<Lisuke> lisukkeet = new ArrayList<Lisuke>();    // Tulosten talletukseen taulukko
        Lisuke l;

        String sql = "SELECT Nimi, Ruokalaji, Kuvaus FROM Lisukkeet";
        kysely = yhteys.prepareStatement(sql);
        tulokset = kysely.executeQuery();

        // Varmistetaan ettei tyhjät tulokset pääse eteenpäin.
        if (tulokset == null) {
            TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
            TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
            TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

            ServlettiIsa.naytaVirhe(request, "Tietokannassa ei ole yhtään reseptiä.");
            ServlettiIsa.naytaJSP("Etusivu_ennen.jsp", request, response);
        }

        while (tulokset.next() == true) {
            String nimi = tulokset.getString("Nimi");
            String ruoka = tulokset.getString("Ruokalaji");
            String kuvaus = tulokset.getString("Kuvaus");
            l = new Lisuke(nimi, ruoka, kuvaus);
            lisukkeet.add(l);
        }
        request.setAttribute("lisukkeita", lisukkeet);
    }

    /* ************************************** */
    //Tarkistetaan, että onko kyseistä käyttäjää olemassa tietokannassa.
    public static boolean TarkistaKayttaja(HttpServletRequest request, HttpServletResponse response, String nimi) throws SQLException, NamingException, Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "SELECT Nimi FROM Kayttaja WHERE Nimi = ?";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, nimi);
        tulokset = kysely.executeQuery();

        // Varmistetaan ettei tyhjät tulokset pääse eteenpäin.
        if (!tulokset.next()) {
            TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
            TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
            TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

            return false;
        }

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        return true;
    }
    
    /* ************************************** */
    //Tarkistetaan, että onko tunnusta olemassa tietokannassa.
    public static boolean TarkistaKayttajatunnus(HttpServletRequest request, HttpServletResponse response, String tunnus) throws SQLException, NamingException, Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "SELECT Tunnus FROM Kayttaja WHERE Tunnus = ?";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, tunnus);
        tulokset = kysely.executeQuery();

        // Varmistetaan ettei tyhjät tulokset pääse eteenpäin.
        if (!tulokset.next()) {
            TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
            TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
            TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

            return false;
        }

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        return true;
    }      

    /* ************************************** */
    //Tarkistetaan, että onko kyseistä käyttäjää olemassa tietokannassa.
    public static boolean TarkistaResepti(HttpServletRequest request, HttpServletResponse response, String nimi) throws SQLException, NamingException, Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "SELECT count(*) FROM Ruokalaji WHERE Nimi = ?";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, nimi);
        tulokset = kysely.executeQuery();
        tulokset.next();

        // Varmistetaan ettei tyhjät tulokset pääse eteenpäin.
        if (tulokset.getInt(1) > 0) {
            TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
            TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
            TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

            return true;
        }

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        return false;
    }
    
    /* ************************************** */
    //Tarkistetaan, että onko kyseistä lisuketta olemassa tietokannassa.
    public static boolean TarkistaLisuke(HttpServletRequest request, HttpServletResponse response, String nimi) throws SQLException, NamingException, Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "SELECT count(*) FROM Lisukkeet WHERE Nimi = ?";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, nimi);
        tulokset = kysely.executeQuery();
        tulokset.next();

        // Tarkistetaan, että löytyikö hakukriteerillä tuloksia. Jos löytyy niin metodi palauttaa true-arvon.
        if (tulokset.getInt(1) > 0) {
            TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
            TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
            TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

            return true;
        }

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        return false;
    }    
}
