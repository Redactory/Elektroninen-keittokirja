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
        
        // Askel 1: Poistetaan mahdolliset indeksit Ruokalajin_lisukkeet-taulusta.
        String sql1 = "DELETE FROM Ruokalajin_lisukkeet WHERE Ruokalaji_nimi = ?";
        kysely = yhteys.prepareStatement(sql1);
        kysely.setString(1, reseptinNimi);
        kysely.executeUpdate();

        // Askel 2: Poistetaan asianmukainen indeksi Ruokalaji-taulukosta.
        String sql2 = "DELETE FROM Ruokalaji WHERE Nimi = ?";
        kysely = yhteys.prepareStatement(sql2);
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
    //Reseptin lisääminen tietokantaan.     
    public static void LisaaKayttaja(String nimi, String kehittaja, String tyyppi, String raakaAineet, String ohjeet, String kuvaus) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "INSERT INTO Kayttaja VALUES (?, ?, ?, ?, ?, ?)";
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

    /* ************************************** */
    //Tietokannassa olevan lisukkeen muokkaaminen.     
    public static void MuokkaaLisuketta(String nimi, String raakaAineet, String ohjeet) throws Exception {
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

    /* ************************************** */
    //Tietokannassa olevan reseptin nimen muokkaaminen.
    public static void MuokkaaReseptinNimi(String vanhaNimi, String uusiNimi) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "UPDATE Ruokalaji SET Nimi = ? WHERE Nimi = ?";

        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, uusiNimi);
        kysely.setString(2, vanhaNimi);
        kysely.executeUpdate();

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        MuokkaaRuokalajinLisukkeenRuoka(vanhaNimi, uusiNimi);
    }

    /* ************************************** */
    //Tietokannassa olevan reseptin nimen muokkaaminen (Ruokalajin_lisukkeet taulu).
    public static void MuokkaaRuokalajinLisukkeenRuoka(String vanhaNimi, String uusiNimi) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "UPDATE Ruokalajin_lisukkeet SET Ruokalaji_nimi = ? WHERE Ruokalaji_nimi = ?";

        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, uusiNimi);
        kysely.setString(2, vanhaNimi);
        kysely.executeUpdate();

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.          
    }

    /* ************************************** */
    //Tietokannassa olevan lisukkeen nimen muokkaaminen.
    public static void MuokkaaLisukkeenNimi(String vanhaNimi, String uusiNimi) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        Lisuke lisuke = Haut.getLisuke(vanhaNimi);  // Lisukkeen tiedot olion sisällä.

        // Lisukkeeseen liittyvä ruokalaji + kuvaus.
        String ruokalaji = lisuke.getRuokalajinNimi();
        String kuvaus = lisuke.getKuvaus();

        String sql1 = "INSERT INTO Lisukkeet VALUES (?, ?, ?)";

        // Luodaan uusi lisuke, joka muuten kuin nimen puolesta on sama kuin vanha.
        kysely = yhteys.prepareStatement(sql1);
        kysely.setString(1, uusiNimi);
        kysely.setString(2, ruokalaji);
        kysely.setString(3, kuvaus);
        kysely.executeUpdate();

        // Yhteyksien sulku välissä ettei vahingossa sunny ongelmia
        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        // Muokataan nyt indeksit lisukkeen nimi Ruokalajin_lisukeet-taulussa
        MuokkaaRuokalajinLisukkeenNimi(vanhaNimi, uusiNimi);

        // Viimeinen vaihe: Poistetaan vanhaksi käynyt indeksi Lisukkeet-taulusta.
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "DELETE FROM Lisukkeet WHERE Nimi = ?";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, vanhaNimi);
        kysely.executeUpdate();

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

    }

    /* ************************************** */
    //Tietokannassa olevan lisukkeen nimen muokkaaminen (Ruokalajin_lisukkeet taulu).
    public static void MuokkaaRuokalajinLisukkeenNimi(String vanhaNimi, String uusiNimi) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "UPDATE Ruokalajin_lisukkeet SET Lisuk_nimi = ? WHERE Lisuk_nimi = ?";

        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, uusiNimi);
        kysely.setString(2, vanhaNimi);
        kysely.executeUpdate();

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

    }

    /* ************************************** */
    //Lisukkeen poistaminen tietokannasta (Lisukkeet-taulu).     
    public static void PoistaLisuke(String lisukkeenNimi) throws Exception {

        //Ensimmäinen askel: Poistetaan asianmukaiset indeksit Ruokalajin_lisukkeet-taulusta.
        PoistaRuokalajinLisuke(lisukkeenNimi);

        // Poistetaan nyt vastaava indeksi Lisukkeet-taulusta.
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "DELETE FROM Lisukkeet WHERE Nimi = ?";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, lisukkeenNimi);
        kysely.executeUpdate();

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

    }

    /* ************************************** */
    //Ruokalajin lisukkeen poistaminen tietokannasta (Ruokalajin_lisukkeet taulu).    
    public static void PoistaRuokalajinLisuke(String lisukkeenNimi) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "DELETE FROM Ruokalajin_lisukkeet WHERE Lisuk_nimi = ?";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, lisukkeenNimi);
        kysely.executeUpdate();

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.  
    }

    /* ************************************** */
    //Lisukkeen lisääminen tietokantaan.     
    public static void LisaaLisuke(String lisukkeenNimi, String ruokalaji, String kuvaus) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "INSERT INTO Lisukkeet VALUES (?, ?, ?)";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, lisukkeenNimi);
        kysely.setString(2, ruokalaji);
        kysely.setString(3, kuvaus);
        kysely.executeUpdate();

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.

        LisaaRuokalajinLisuke(ruokalaji, lisukkeenNimi);    //Päivitetään Ruokalajin_Lisuke-taulu
    }

    /* ************************************** */
    //Ruokalajin lisukkeen lisääminen tietokantaan.    
    public static void LisaaRuokalajinLisuke(String ruokalaji, String lisuke) throws Exception {
        yhteys = TietokantaYhteys.yhteydenAvaus();  //Tietokantayht. avaus

        String sql = "INSERT INTO Ruokalajin_lisukkeet VALUES (?, ?)";
        kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, ruokalaji);
        kysely.setString(2, lisuke);
        kysely.executeUpdate();

        TietokantaYhteys.lauseSulku(kysely);           // Kyselyn sulku
        TietokantaYhteys.tulosSulku(tulokset);         // Lauseen sulku
        TietokantaYhteys.yhteydenSulku(yhteys);        // Tietokantayhteyden sulku.         
    }
}
