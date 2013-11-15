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
import javax.sql.DataSource;

/**
 *
 * @author teematve
 */
public class Haut {

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

        if (rs.next() == false) {
            return null;
        }

        kayttaja = new Kayttaja(rs.getString("Nimi"), rs.getString("Tunnus"), rs.getString("Salasana"), rs.getInt("Oikeudet")); //Hakutuloksen sisältävä olio.
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
}
