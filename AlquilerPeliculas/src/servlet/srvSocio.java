/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import business.SocioUI;
import entities.Socio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giuli
 */
@WebServlet(name = "srvSocio", urlPatterns = {"/srvSocio"})
public class srvSocio extends HttpServlet {

    
    private static String INSERT_OR_EDIT = "./vista/usuario/Socio.jsp";
    private static String LIST_SOCIO = "./vista/usuario/lstSocio.jsp";
    
    SocioUI socioUI = new SocioUI();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet srvSocio</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet srvSocio at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String forward = "";
        String action = "lstUser";
        if(request.getParameter("action") != null)
            action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {

            boolean exito = false;
            int socioId = Integer.parseInt(request.getParameter("socioId"));
            try {
                exito = socioUI.deleteSocio(socioId);
                forward = LIST_SOCIO;
                request.setAttribute("socios", socioUI.getSocio());
            } catch (SQLException ex) {
                Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equalsIgnoreCase("edit")) {

            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            Socio socio;
            try {
                socio = socioUI.getSocio(userId);
                request.setAttribute("socio", socio);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);
            }
            //request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
            
            forward = INSERT_OR_EDIT;
            RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
            view.forward(request, response);
            

        } else if (action.equalsIgnoreCase("lstUser")) {

            ArrayList<Socio> lstSocios = new ArrayList<Socio>();
            try {
                lstSocios = socioUI.getSocio();
            } catch (SQLException ex) {
                Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);
            }

            forward = LIST_SOCIO;            
            request.setAttribute("lstUsuarios", lstSocios);            

        } else {
            Socio socio = new Socio();
            request.setAttribute("socio", socio);
            forward = INSERT_OR_EDIT;            
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
