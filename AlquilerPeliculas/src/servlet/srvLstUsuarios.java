/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.SocioUI;
import business.UsuarioUI;
import entities.Socio;
import entities.Usuario;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author giuli
 */
@WebServlet(name = "srvLstUsuarios", urlPatterns = { "/srvLstUsuarios" })
public class srvLstUsuarios extends HttpServlet {

	private static String INSERT_OR_EDIT = "./vista/usuario/Usuario.jsp";
	private static String LIST_USER = "./vista/usuario/lstUsuarios.jsp";

	UsuarioUI usuarioUI = new UsuarioUI();
	SocioUI socioUI = new SocioUI();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";
		String action = "lstUser";
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
				Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			}

		} else if (action.equalsIgnoreCase("edit")) {

			forward = INSERT_OR_EDIT;
			Usuario user = new Usuario();
			Socio socio = new Socio();

			if (request.getParameter("id") != null) {
				Integer id = Integer.parseInt(request.getParameter("id"));
				try {
					user = usuarioUI.getUserbyId(id);
					socio = socioUI.getSocio(id);
				} catch (ClassNotFoundException ex) {
					Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
				} catch (SQLException ex) {
					Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			request.setAttribute("user", user);
			request.setAttribute("socio", socio);

			forward = INSERT_OR_EDIT;
			RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
			view.forward(request, response);

		} else if (action.equalsIgnoreCase("lstUser")) {

			try {
				forward = LIST_USER;
				request.setAttribute("lstUsuarios", usuarioUI.getUsuarios());
			} catch (SQLException ex) {
				Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			}

		} else {
			Usuario user = new Usuario();
			Socio socio = new Socio();
			request.setAttribute("user", user);
			request.setAttribute("socio", socio);
			forward = INSERT_OR_EDIT;
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

		Usuario usuario = new Usuario();
		Socio socio = new Socio();

		Integer id;

		socio.setNombre(request.getParameter("nombre"));
		socio.setApellido(request.getParameter("apellido"));
		socio.setDomicilio(request.getParameter("domicilio"));
		socio.setEstado(request.getParameter("estado"));
		socio.setTelefono(request.getParameter("telefono"));

		usuario.setEmail(request.getParameter("email"));
		usuario.setContrasena(request.getParameter("contrasena"));

		if (request.getParameter("nroTarjeta") == null || request.getParameter("nroTarjeta") == "") {
			socio.setNroTarjeta(null);
		} else {
			String tarjeta = request.getParameter("nroTarjeta");
			socio.setNroTarjeta(Integer.parseInt(tarjeta));
		}

		if ("null".equals(request.getParameter("id"))) {

			try {
				usuarioUI.addUsuario(usuario, socio);
			} catch (SQLException ex) {
				Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			}

		} else

			try {

				socioUI.updateSocio(socio);
				usuarioUI.updateUsuario(usuario);
				request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
				
				request.setAttribute("Error", "Ocurrio un error al actualizar el usuario.");
				RequestDispatcher rd = request.getRequestDispatcher("lstUsuarios.jsp"); 
                rd.forward(request, response);
			} catch (SQLException ex) {
				Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
				
				request.setAttribute("Error", "Ocurrio un error al actualizar el usuario.");
            	RequestDispatcher rd = request.getRequestDispatcher("lstUsuarios.jsp"); 
                rd.forward(request, response);
			}

		try {
			request.setAttribute("lstUsuarios", usuarioUI.getUsuarios());
		} catch (SQLException ex) {
			Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
		}
		request.getRequestDispatcher(LIST_USER).forward(request, response);

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
