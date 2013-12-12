package Paketit.Servletit;

import Paketit.Mallit.Haut;
import Paketit.Mallit.MuokkausToiminnot;
import Paketit.Mallit.ServlettiIsa;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author teematve
 */
public class LisukkeetLisays extends ServlettiIsa {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // Parametrien alustus.
        String nimi = "";
        String ruoka = "";
        String kuvaus = "";



        // Parametrit uuden tietueen luomista varten.
        nimi = request.getParameter("name");
        ruoka = request.getParameter("food");
        kuvaus = request.getParameter("description");



        if (request.getMethod().equals("POST") == false) {
            naytaJSP("Lisukkeet.jsp", request, response);
        } else {
            try {

                if (Haut.TarkistaLisuke(request, response, nimi) == true) {
                    naytaVirhe(request, "Tietokannassa on jo olemassa lisuke tuolla nimellä. Yritä uudelleen.");
                    naytaJSP("Lisukkeet.jsp", request, response);
                    return;
                } else if (Haut.TarkistaResepti(request, response, ruoka) == false) {
                    naytaVirhe(request, "kyseistä ruokalajia ei ole tietokannassa. Syötä tietokannassa jo olemassa oleva ruokalaji.");
                    naytaJSP("Lisukkeet.jsp", request, response);
                    return;
                }
                if (!nimi.isEmpty() && !ruoka.isEmpty() && !kuvaus.isEmpty()) {
                    MuokkausToiminnot.LisaaLisuke(nimi, ruoka, kuvaus);
                    naytaJSP("Lisukkeet.jsp", request, response);
                } else {
                    naytaVirhe(request, "Menee tähän haaraan.");
                    naytaJSP("Lisukkeet.jsp", request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MuutoksetLisaykset.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(MuutoksetLisaykset.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(MuutoksetLisaykset.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
