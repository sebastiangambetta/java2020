/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.SocioUI;
import business.TarjetaCreditoUI;
import business.UsuarioUI;
import entities.Socio;
import entities.TarjetaCredito;
import entities.Usuario;
import javax.servlet.RequestDispatcher;

/**
 * @author giuli
 */
@WebServlet(name = "srvLstUsuarios", urlPatterns = { "/srvLstUsuarios" })
public class srvLstUsuarios extends HttpServlet {

	private static String INSERT_OR_EDIT = "./vista/usuario/Usuario.jsp";
	private static String LIST_USER = "./vista/usuario/lstUsuarios.jsp";

	UsuarioUI usuarioUI = new UsuarioUI();
	SocioUI socioUI = new SocioUI();
	TarjetaCreditoUI tarjetaCreditoUI = new TarjetaCreditoUI();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";
		String action = "lstUser";

		try {
			if (request.getParameter("action") != null) {
				action = request.getParameter("action");
			}

			if (action.equalsIgnoreCase("delete")) {

				boolean exito = false;
				int userId = Integer.parseInt(request.getParameter("id"));
				try {
					exito = usuarioUI.deleteUsuario(userId);
					forward = LIST_USER;
					request.setAttribute("lstUsuarios", usuarioUI.getUsuarios());

				} catch (SQLException ex) {
					request.setAttribute("Error", "Error al eliminar el usuario.");
					RequestDispatcher rd = request.getRequestDispatcher("lstUsuarios.jsp");
					rd.forward(request, response);
					
				}

			} else if (action.equalsIgnoreCase("edit")) {

				forward = INSERT_OR_EDIT;
				Usuario user = new Usuario();
				Socio socio = new Socio();
				ArrayList<TarjetaCredito> tarjetas;
				try {
					tarjetas = tarjetaCreditoUI.getTarjetaC();

					if (request.getParameter("id") != null) {
						Integer id = Integer.parseInt(request.getParameter("id"));

						user = usuarioUI.getUserbyId(id);
						socio = socioUI.getSocio(id);

						request.setAttribute("usuario", user);
						request.setAttribute("socio", socio);
						request.setAttribute("tarjetas", tarjetas);

					} else {						
						request.setAttribute("usuario", user);
						request.setAttribute("socio", socio);
						request.setAttribute("tarjetas", tarjetas);
						
						request.setAttribute("Error", "Error al obtener los datos del socio.");
						RequestDispatcher rd = request.getRequestDispatcher("./lstUsuarios.jsp");
						rd.forward(request, response);
					}

				} catch (Exception e) {
					request.setAttribute("Error", "Error al obtener los datos del socio.");
					RequestDispatcher rd = request.getRequestDispatcher("./lstUsuarios.jsp");
					rd.forward(request, response);
					
				}

				forward = INSERT_OR_EDIT;
				RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
				view.forward(request, response);
			}

			else if (action.equalsIgnoreCase("Agregar")) {
				forward = INSERT_OR_EDIT;
				Usuario user = new Usuario();
				Socio socio = new Socio();
				try {
					ArrayList<TarjetaCredito> tarjetas = tarjetaCreditoUI.getTarjetaC();

					request.setAttribute("usuario", user);
					request.setAttribute("socio", socio);
					request.setAttribute("tarjetas", tarjetas);

					forward = INSERT_OR_EDIT;
					RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
					view.forward(request, response);
				} catch (Exception e) {
					request.setAttribute("Error", "Ocurrio un error.");
					RequestDispatcher rd = request.getRequestDispatcher("./lstUsuarios.jsp");
					rd.forward(request, response);
				}

			} else if (action.equalsIgnoreCase("lstUser")) {

				forward = LIST_USER;
				ArrayList<Usuario> lstUsuarios = usuarioUI.getUsuarios();
				request.setAttribute("lstUsuarios", lstUsuarios);

			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			request.setAttribute("Error", "Hubo un error al recuperar los socios");
			RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);

		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

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
