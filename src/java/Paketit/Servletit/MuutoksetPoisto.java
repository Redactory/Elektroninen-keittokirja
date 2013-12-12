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
public class MuutoksetPoisto extends ServlettiIsa {

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

        String tietueenPoisto = "";

        // Parametrit tietueen poistoa varten.
        tietueenPoisto = request.getParameter("remove");

        if (request.getMethod().equals("POST") == false) {
            naytaJSP("Muutokset.jsp", request, response);
        } else {
            try {
                if (Haut.TarkistaResepti(request, response, tietueenPoisto) == false) {
                    naytaVirhe(request, "Kyseistä reseptiä ei ole tietokannassa. Yritä uudelleen.");
                    naytaJSP("Muutokset.jsp", request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MuutoksetPoisto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(MuutoksetPoisto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(MuutoksetPoisto.class.getName()).log(Level.SEVERE, null, ex);
            }


            if (!tietueenPoisto.isEmpty()) {
                try {
                    MuokkausToiminnot.Poista(tietueenPoisto);
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
