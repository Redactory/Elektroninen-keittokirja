/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paketit.Mallit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author teematve
 */
public class TietokantaYhteys {


    public static Connection yhteydenAvaus() throws Exception {
        Connection yhteys = null;
        try {
            //Etsitään PostgreSQL-ajuri ja otetaan yhteys tietokantaan
            InitialContext cxt = new InitialContext();
            DataSource yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tietokanta");
            yhteys = yhteysVarasto.getConnection();
        } catch (Exception e) {
            throw e;
        }
        return yhteys;
    }

    /* ****************************************** */
    //Suljetaan yhteys tietokantaan.
    public static void yhteydenSulku(Connection connection) throws Exception {
        try {
            connection.close();
        } catch (Exception e) {
        }
    }

    /* ****************************************** */
    //Suljetaan Stament-oli.    
    public static void lauseSulku(PreparedStatement query) throws Exception {
        //Suljetaan lopulta kaikki avatut resurssit
        try {
            query.close();
        } catch (Exception e) {
        }

    }

    /* ****************************************** */
    //Suljetaan ResultSet-oli.    
    public static void tulosSulku(ResultSet results) throws Exception {
        //Suljetaan lopulta kaikki avatut resurssit
        try {
            results.close();
        } catch (Exception e) {
        }

    }
}
