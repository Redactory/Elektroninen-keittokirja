/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paketit.Mallit;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author teematve
 */
public class ServlettiIsa extends HttpServlet {

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
    /* ******************************** */
    //Metodi sivujen esittämistä varten.    
    public static void naytaJSP(String sivu, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Näytetään JSP-sivu.*/
        if(!response.isCommitted()) {                   //Huomauta asiasta Davidille
        RequestDispatcher dispatcher = request.getRequestDispatcher(sivu);
        dispatcher.forward(request, response);
        }
    }

    /* ******************************** */
    //Metodi virheiden esittämistä varten.    
    public static void naytaVirhe(HttpServletRequest request, String virheViesti) throws ServletException, IOException {
        /* Asetetaan virheviesti */
        request.setAttribute("Virhe", virheViesti);
    }

    /* ******************************** */
    //Metodi kirjautumisen tarkistamista varten.
    public static boolean onkoKirjautunut(HttpServletRequest request) {
        
        Kayttaja k = new Kayttaja();
        
        HttpSession session = request.getSession(false);
        k = (Kayttaja)session.getAttribute("Kirjautunut");
        
        if (k == null) {
            return false;
        } else {
            return true;
        }
    }
    
    /* ******************************** */
    //Metodi oikeuksien tarkistamista varten.
    public static int onkoOikeudet(HttpServletRequest request) {
        
        Kayttaja k = new Kayttaja();
        
        HttpSession session = request.getSession(false);
        k = (Kayttaja)session.getAttribute("Kirjautunut");
        
        if (k == null) {
            return -1;
        } else {
            return k.getOikeudet();
        }
    }    

    /* ******************************** */
    //Metodi uloskirjautumiseen.
    public static void kirjaudu_Ulos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("Kirjautunut");
    }

    
    /* ******************************** */
    //Auto-generoitu koodi.
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServlettiIsa</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServlettiIsa at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
