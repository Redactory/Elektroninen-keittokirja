package Paketit.Mallit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author teematve
 *
 * Luokka huolehtii CRUD-nelikon mukaisista toiminnoista eli reseptien listauk-
 * sesta, muokkaamisesta, lisäämisestä ja poistamisesta.
 */
public class MuokkausToiminnot {

    static PreparedStatement kysely = null;
    static Connection yhteys = null;
    static ResultSet tulokset = null;

    /* ************************************** */
    //Kaikkien reseptien listaus.    
    public static void Listaus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        List<Ruokalaji> lista = new ArrayList<Ruokalaji>();    // Tulosten talletukseen taulukko
        Ruokalaji r;

        String sql = "SELECT Nimi, Raaka_aineet, Resepti, Kuvaus FROM Ruokalaji";
        kysely = yhteys.prepareStatement(sql);
        tulokset = kysely.executeQuery();

        // Varmistetaan ettei tyhjät tulokset pääse eteenpäin.
        if (tulokset == null) {
            ServlettiIsa.naytaVirhe(request, "Tietokannassa ei ole yhtään reseptiä.");
            ServlettiIsa.naytaJSP("Etusivu_ennen.jsp", request, response);
        }

        while (tulokset.next() == true) {
            String nimi = tulokset.getString("Nimi");
            String rA = tulokset.getString("Raaka_aineet");
            String resepti = tulokset.getString("Resepti");
            String kuvaus = tulokset.getString("Kuvaus");
            r = new Ruokalaji(nimi, rA, resepti, kuvaus);
            lista.add(r);
//            lista.add(tulokset.getString("Nimi"));
        }

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        request.setAttribute("ruokalajit", lista);
    }

    /* ************************************** */
    //Reseptin poistaminen tietokannasta.     
    public static void Poista(String reseptinNimi) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "DELETE FROM Ruokalaji WHERE Nimi = ?";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, reseptinNimi);
        kysely.executeUpdate();

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.  
    }

    /* ************************************** */
    //Reseptin lisääminen tietokantaan.     
    public static void Lisaa(String nimi, String kehittaja, String tyyppi, String raakaAineet, String ohjeet, String kuvaus) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "INSERT INTO Ruokalaji VALUES (?, ?, ?, ?, ?, ?)";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, nimi);
        kysely.setString(2, kehittaja);
        kysely.setString(3, tyyppi);
        kysely.setString(4, raakaAineet);
        kysely.setString(5, ohjeet);
        kysely.setString(6, kuvaus);
        kysely.executeUpdate();

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.         
    }

    /* ************************************** */
    //Tietokannassa olevan reseptin muokkaaminen.     
    public static void Muokkaa(String nimi, String raakaAineet, String ohjeet) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "UPDATE Ruokalaji SET Raaka_aineet = ?, Resepti = ? WHERE Nimi = ?";        
        
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, raakaAineet);
        kysely.setString(2, ohjeet);
        kysely.setString(3, nimi);
        kysely.executeUpdate();
        
        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.          
    }
}
