/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import business.GeneroUI;
import entities.Genero;
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
@WebServlet(name = "srvGenero", urlPatterns = {"/srvGenero"})
public class srvGenero extends HttpServlet {

    private static String INSERT_OR_EDIT = "./vista/genero/genero.jsp";
    private static String LIST_GENERO = "./vista/genero/lstGenero.jsp";

    GeneroUI generoUI = new GeneroUI();
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
            out.println("<title>Servlet srvGenero</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet srvGenero at " + request.getContextPath() + "</h1>");
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
                String action = "lstGenero";
                if(request.getParameter("action") != null)
                    action = request.getParameter("action");
        
                if (action.equalsIgnoreCase("delete")) {
        
                    boolean exito = false;
                    int id = Integer.parseInt(request.getParameter("Id"));
                    try {
                        exito = generoUI.deleteGenero(id);
                        forward = LIST_GENERO;
                        request.setAttribute("generos", generoUI.getGenero());
                    } catch (SQLException ex) {
                        Logger.getLogger(srvGenero.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(srvGenero.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
                } else if (action.equalsIgnoreCase("edit")) {
        
                    forward = INSERT_OR_EDIT;
                    int id = Integer.parseInt(request.getParameter("id"));
                    Genero genero;
                    try {
                        genero = generoUI.getGenero(id);
                        request.setAttribute("genero", genero);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(srvGenero.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(srvGenero.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
                    
                    forward = INSERT_OR_EDIT;
                    RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
                    view.forward(request, response);
                    
        
                } else if (action.equalsIgnoreCase("lstTarjetas")) {
        
                    ArrayList<Genero> lstGenero = new ArrayList<Genero>();
                    try {
                        lstGenero = generoUI.getGenero();
                    } catch (SQLException ex) {
                        Logger.getLogger(srvGenero.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(srvGenero.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
                    forward = LIST_GENERO;
                    request.setAttribute("lstGenero", lstGenero);
        
                } else {
                    Genero gen = new Genero();
                    request.setAttribute("gen", gen);
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
