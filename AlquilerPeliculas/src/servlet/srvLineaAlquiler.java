/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import business.AlquilerUI;
import entities.Alquiler;
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
@WebServlet(name = "srvLineaAlquiler", urlPatterns = {"/srvLineaAlquiler"})
public class srvLineaAlquiler extends HttpServlet {


    private static String INSERT_OR_EDIT = "./vista/alquiler/Alquiler.jsp";
    private static String LIST_ALQUILER = "./vista/alquiler/lstAlquiler.jsp";

    AlquilerUI alquilerUI = new AlquilerUI();

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
            out.println("<title>Servlet srvLineaAlquiler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet srvLineaAlquiler at " + request.getContextPath() + "</h1>");
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
                
                String forward = "";
                String action = "lstLineaAlquiler";
                if(request.getParameter("action") != null)
                    action = request.getParameter("action");
        
                if (action.equalsIgnoreCase("delete")) {
        
                    boolean exito = false;
                    int id = Integer.parseInt(request.getParameter("Id"));
                    try {
                        exito = alquilerUI.deleteAlquiler(id);
                        forward = LIST_ALQUILER;
                        request.setAttribute("alquileres", alquilerUI.getAlquiler());
                    } catch (SQLException ex) {
                        Logger.getLogger(srvLineaAlquiler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(srvLineaAlquiler.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
                } else if (action.equalsIgnoreCase("edit")) {
        
                    forward = INSERT_OR_EDIT;
                    int id = Integer.parseInt(request.getParameter("id"));
                    Alquiler alquiler;
                    try {
                        alquiler = alquilerUI.getAlquiler(id);
                        request.setAttribute("alquiler", alquiler);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(srvLineaAlquiler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(srvLineaAlquiler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
                    
                    forward = INSERT_OR_EDIT;
                    RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
                    view.forward(request, response);
                    
        
                } else if (action.equalsIgnoreCase("lstLineaAlquiler")) {
        
                    ArrayList<Alquiler> lstAlquiler = new ArrayList<Alquiler>();
                    try {
                        lstAlquiler = alquilerUI.getAlquiler();
                    } catch (SQLException ex) {
                        Logger.getLogger(srvLineaAlquiler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(srvLineaAlquiler.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
                    forward = LIST_ALQUILER;
                    request.setAttribute("lstTarjetas", lstAlquiler);
        
                } else {
                    Alquiler alq = new Alquiler();
                    request.setAttribute("alq", alq);
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
