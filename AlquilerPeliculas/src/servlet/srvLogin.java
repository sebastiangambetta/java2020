/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UsuarioUI usuarioUI = new UsuarioUI();
	private String ERROR = "./ERROR.jsp";
	private String HOME = "./index.jsp";
	

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
    
          String pass = request.getParameter("contrasena");
        String email = request.getParameter("email");        
                
        try {
            Usuario usuario = usuarioUI.Login(email, pass);
            if (usuario == null) {
            	request.setAttribute("Error", "Usuario o contraseña incorrecto!.");
            	RequestDispatcher rd = request.getRequestDispatcher(HOME); 
                rd.forward(request, response);
           
            } else {
            	HttpSession session = request.getSession(true);
            	session.setAttribute("user", usuario);
            	response.sendRedirect("srvLstUsuarios");
            	
            	//Para obtener los datos de session
            	//HttpSession misession= (HttpSession) request.getSession();
            	
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect(this.ERROR);
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
