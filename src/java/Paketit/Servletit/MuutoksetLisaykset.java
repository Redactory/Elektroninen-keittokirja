/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paketit.Servletit;

import Paketit.Mallit.MuokkausToiminnot;
import Paketit.Mallit.ServlettiIsa;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author teematve
 */
public class MuutoksetLisaykset extends ServlettiIsa {

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

        // Parametrien alustus.
        String nimi = "";
        String tunnus = "";
        String tyyppi = "";
        String ainekset = "";
        String tekoOhjeet = "";
        String kuvaus = "";

        String nimenMuutos = "";
        String aineksetMuutos = "";
        String reseptiMuutos = "";
        
        
        // Parametrit uuden tietueen luomista varten.
        nimi = request.getParameter("name");
        tunnus = request.getParameter("maker");
        tyyppi = request.getParameter("type");
        ainekset = request.getParameter("ingredients");
        tekoOhjeet = request.getParameter("recipe");
        kuvaus = request.getParameter("description");

        if (request.getMethod().equals("POST") == false) {
            naytaJSP("Muutokset.jsp", request, response);
        } else {        // Ongelmia kun yrittää tehdä poistoa
            if (!nimi.isEmpty() && !tunnus.isEmpty() && !tyyppi.isEmpty() && !ainekset.isEmpty() && !tekoOhjeet.isEmpty() && !kuvaus.isEmpty()) {
                try {
                    MuokkausToiminnot.Lisaa(nimi, tunnus, tyyppi, ainekset, tekoOhjeet, kuvaus);
                    naytaJSP("Muutokset.jsp", request, response);
                } catch (Exception ex) {
                    Logger.getLogger(MuutoksetLisaykset.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            naytaVirhe(request, "Nyt hommat ei menneet ihan putkeen...");
            naytaJSP("Muutokset.jsp", request, response);
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
