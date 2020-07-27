/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import business.UsuarioUI;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author
 */
@WebServlet(name = "srvLogin", urlPatterns = {"/srvLogin"})
public class srvLogin extends HttpServlet {

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
        processRequest(request, response);
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
        //processRequest(request, response);              
        response.setContentType("text/html");  
    PrintWriter out = response.getWriter(); 
          String pass = request.getParameter("contrasena");
        String email = request.getParameter("email");        
                
        try {
            Usuario usuario = usuarioUI.Login(email, pass);
            if (usuario == null) {
            	request.setAttribute("Error", "Usuario o contraseña incorrecto!.");
            	RequestDispatcher rd = request.getRequestDispatcher("./index.jsp"); 
                rd.forward(request, response);            	
           
            } else {
            	response.sendRedirect("srvLstUsuarios");            	
            }

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.sendRedirect("./jsp/Error.jsp");
        }
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
