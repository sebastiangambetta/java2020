/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.SocioUI;
import business.BancoUI;
import business.UsuarioUI;
import entities.Socio;
import entities.Banco;
import entities.Usuario;
import javax.servlet.RequestDispatcher;

/**
 * @author giuli
 */
@WebServlet(name = "srvLstUsuarios", urlPatterns = { "/srvLstUsuarios" })
public class srvLstUsuarios extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "./vista/usuario/Usuario.jsp";
	private static String LIST_USER = "./vista/usuario/lstUsuarios.jsp";
	private static String INDEX = "./index.jsp";
	private static String ERROR = "Error.jsp";

	UsuarioUI usuarioUI = new UsuarioUI();
	SocioUI socioUI = new SocioUI();
	BancoUI bancoUI = new BancoUI();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = "lstUser";

		try {

			if (request.getParameter("action") != null) {
				action = request.getParameter("action");
			}

			if (action.equalsIgnoreCase("delete")) {
				int userId = Integer.parseInt(request.getParameter("id"));
				try {

					if (usuarioUI.deleteUsuario(userId)) {
						request.setAttribute("lstUsuarios", usuarioUI.getUsuarios());

					}

					RequestDispatcher rd = request.getRequestDispatcher(INSERT_OR_EDIT);
					rd.forward(request, response);

				} catch (SQLException ex) {
					request.setAttribute("Error", "Error al eliminar el usuario.");
					request.setAttribute("lstUsuarios", usuarioUI.getUsuarios());
					RequestDispatcher rd = request.getRequestDispatcher(LIST_USER);
					rd.forward(request, response); 
					} 
				} else if (action.equalsIgnoreCase("edit")) {

				Usuario user = new Usuario();
				Socio socio = new Socio();
				ArrayList<Banco> bancos;
				try {
					bancos = bancoUI.getBancos();

					if (request.getParameter("id") != null) {
						Integer id = Integer.parseInt(request.getParameter("id"));						
						
						request.setAttribute("action", "edit");
						request.setAttribute("id", id);

						RequestDispatcher rd = request.getRequestDispatcher("/srvUsuario");
						rd.forward(request, response);

					} else {						

						request.setAttribute("action", "add");						
						RequestDispatcher rd = request.getRequestDispatcher(INSERT_OR_EDIT);
						rd.forward(request, response);
					}

				} catch (Exception e) {
					RequestDispatcher rd = request.getRequestDispatcher(ERROR);
					rd.forward(request, response);

				}
			}

			else if (action.equalsIgnoreCase("Agregar")) {			
				Usuario user = new Usuario();
				Socio socio = new Socio();
				try {
					ArrayList<Banco> bancos = bancoUI.getBancos();

					request.setAttribute("usuario", user);
					request.setAttribute("socio", socio);
					request.setAttribute("bancos", bancos);
					
					RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
					view.forward(request, response);					
					
				} catch (Exception e) {					
					RequestDispatcher rd = request.getRequestDispatcher(ERROR);
					rd.forward(request, response);
				}

//			} else if (action.equalsIgnoreCase("lstUser")) {
			} else {
				request.setAttribute("lstUsuarios", usuarioUI.getUsuarios());
				RequestDispatcher rd = request.getRequestDispatcher(LIST_USER);
				rd.forward(request, response);

			}

//			RequestDispatcher view = request.getRequestDispatcher(forward);
//			view.forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			request.setAttribute("Error", "Hubo un error al recuperar los socios");
			RequestDispatcher rd = request.getRequestDispatcher(INDEX);
			rd.forward(request, response);

		}

	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
