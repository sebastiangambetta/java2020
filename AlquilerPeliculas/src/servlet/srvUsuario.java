/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import business.UsuarioUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author giuli
 */
@WebServlet(name = "srvUsuario", urlPatterns = {"/srvUsuario"})
public class srvUsuario extends HttpServlet {

    private static String INSERT_OR_EDIT = "./vista/usuario/Usuario.jsp";
    private static String LIST_USER = "./vista/usuario/lstUsuarios.jsp";
    
    
    UsuarioUI usuarioUI = new UsuarioUI();
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
        //Usuario user = request.getParameter("user");
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
        String action = "lstUser";
        if(request.getParameter("action") != null)
            action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {

            boolean exito = false;
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                exito = usuarioUI.deleteUsuario(id);
                forward = LIST_USER;
                request.setAttribute("usuarios", usuarioUI.getUsuarios());
            } catch (SQLException ex) {
                Logger.getLogger(srvUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(srvUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equalsIgnoreCase("edit")) {

            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            Usuario usuario;
            try {
                usuario = usuarioUI.getUserbyId(userId);
                request.setAttribute("usuario", usuario);
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

            ArrayList<Usuario> lstUsuario = new ArrayList<Usuario>();
            try {
                lstUsuario = usuarioUI.getUsuarios();
            } catch (SQLException ex) {
                Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);
            }

            forward = LIST_USER;            
            request.setAttribute("lstUsuarios", lstUsuario);

        } else {
            Usuario usuario = new Usuario();
            request.setAttribute("usuario", usuario);
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
