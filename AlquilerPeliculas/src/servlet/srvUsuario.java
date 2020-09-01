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

import entities.Socio;
import entities.Banco;
import entities.Usuario;
import business.SocioUI;
import business.BancoUI;
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
@WebServlet(name = "srvUsuario", urlPatterns = { "/srvUsuario" })
public class srvUsuario extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "./vista/usuario/Usuario.jsp";
	private static String LIST_USER = "./vista/usuario/lstUsuarios.jsp";

	UsuarioUI usuarioUI = new UsuarioUI();
	SocioUI socioUI = new SocioUI();
	BancoUI bancoUI = new BancoUI();

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Usuario user = request.getParameter("user");
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";
		String action = "lstUser";
		Socio socio;
		Usuario usuario;
		ArrayList<Banco> tarjetas;

		if (request.getParameter("action") != null)
			action = request.getParameter("action");

		if (action.equalsIgnoreCase("edit")) {

			forward = INSERT_OR_EDIT;
			int id = Integer.parseInt(request.getParameter("id"));

			try {
				usuario = usuarioUI.getUserbyId(id);
				socio = socioUI.getSocio(usuario.getIdUsuario());
				tarjetas = bancoUI.getBancos();
				request.setAttribute("socio", socio);
				request.setAttribute("usuario", usuario);
				request.setAttribute("tarjetas", tarjetas);

			} catch (ClassNotFoundException ex) {
				Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);

			} catch (SQLException ex) {
				Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);

			} catch (Exception e) {
				request.setAttribute("Error", "Error al obtener los datos del socio.");
				RequestDispatcher rd = request.getRequestDispatcher("./lstUsuarios.jsp");
				rd.forward(request, response);

			}

			forward = INSERT_OR_EDIT;
			RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
			view.forward(request, response);

		} else if (action.equalsIgnoreCase("lstUser")) {

			ArrayList<Usuario> lstUsuario = new ArrayList<Usuario>();
			try {
				lstUsuario = usuarioUI.getUsuarios();
				forward = LIST_USER;
				request.setAttribute("lstUsuarios", lstUsuario);

			} catch (SQLException ex) {
				Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);

			} catch (ClassNotFoundException ex) {
				Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);

			}

		} else {
			usuario = new Usuario();
			socio = new Socio();
			tarjetas = new ArrayList<Banco>();
			request.setAttribute("usuario", usuario);
			request.setAttribute("socios", socio);
			request.setAttribute("tarjetas", tarjetas);
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

		socio.setNroSocio(Integer.parseInt(request.getParameter("id")));
		socio.setNombre(request.getParameter("nombre"));
		socio.setApellido(request.getParameter("apellido"));
		socio.setDomicilio(request.getParameter("domicilio"));
		socio.setTelefono(request.getParameter("telefono"));
		socio.setMail(request.getParameter("uEmail"));
		socio.setEstado(request.getParameter("estado").toString() != "" ? request.getParameter("estado").toString() : null);
		socio.setBanco(Integer.parseInt(request.getParameter("banco")));

		usuario.setIdUsuario(Integer.parseInt(request.getParameter("Uid")));
		usuario.setEmail(request.getParameter("uEmail"));
		usuario.setContrasena(request.getParameter("contrasena"));
		usuario.setAcceso("usuario");

		if (request.getParameter("nroTarjeta") == null || request.getParameter("nroTarjeta") == "") {
			socio.setNroTarjeta(null);
		} else {
			String tarjeta = request.getParameter("nroTarjeta");
			Long i = new Long(tarjeta);
			socio.setNroTarjeta(i.intValue());

		}
		if (socio.getNroSocio() == 0) {

			try {
				usuarioUI.addUsuario(usuario, socio);

			} catch (SQLException ex) {
				Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);

				request.setAttribute("Error", "Ocurrio un error.");
				RequestDispatcher rd = request.getRequestDispatcher("lstUsuarios.jsp");
				rd.forward(request, response);
			}

		} else
			try {
				usuarioUI.updateUsuario(usuario, socio);
				ArrayList<Usuario> lstUsuarios = usuarioUI.getUsuarios();
				request.setAttribute("lstUsuarios", lstUsuarios);
				RequestDispatcher rd = request.getRequestDispatcher(LIST_USER);
				rd.forward(request, response);
				
			} catch (ClassNotFoundException ex) {
				request.setAttribute("Error", "Ocurrio un error al actualizar.");
				RequestDispatcher rd = request.getRequestDispatcher("lstUsuarios.jsp");
				rd.forward(request, response);
				
			} catch (SQLException ex) {
				request.setAttribute("Error", "Ocurrio un error al actualizar.");
				RequestDispatcher rd = request.getRequestDispatcher("lstUsuarios.jsp");
				rd.forward(request, response);
				
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
