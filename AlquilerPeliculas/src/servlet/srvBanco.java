/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import business.BancoUI;
import entities.Banco;
import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
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
@WebServlet(name = "srvBanco", urlPatterns = { "/srvBanco" })
public class srvBanco extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "./vista/tarjeta/Banco.jsp";
	private static String LIST = "./vista/tarjeta/lstBancos.jsp";

	BancoUI BancoUi = new BancoUI();

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
		response.setContentType("text/html;charset=UTF-8");
		
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
		String action = "lstTarjetas";
		if (request.getParameter("action") != null)
			action = request.getParameter("action");

		if (action.equalsIgnoreCase("edit")) {

			try {
				
				Banco banc = new Banco();
				banc.setIdBanco((Integer.parseInt(request.getParameter("id"))));
				banc.setNombreBanco(request.getParameter("nombre"));
				forward = INSERT_OR_EDIT;			
				
				if(banc.getIdBanco() != 0) {
					if(BancoUi.updateBanco(banc)) {
						RequestDispatcher view = request.getRequestDispatcher(LIST);
						view.forward(request, response);
						
					} else {
						RequestDispatcher view = request.getRequestDispatcher(LIST);
						view.forward(request, response);
						
					}
					request.setAttribute("banco", banc);
					
				} else {
					if(BancoUi.addBanco(banc)) {
						RequestDispatcher view = request.getRequestDispatcher(LIST);
						view.forward(request, response);
						
					} else {
						RequestDispatcher view = request.getRequestDispatcher(LIST);
						view.forward(request, response);
						
					}					
				}

			} catch (ClassNotFoundException ex) {
				RequestDispatcher view = request.getRequestDispatcher(LIST);
				view.forward(request, response);

			} catch (SQLException ex) {
				forward = INSERT_OR_EDIT;
				RequestDispatcher view = request.getRequestDispatcher(LIST);
				view.forward(request, response);
				
			}

			forward = INSERT_OR_EDIT;
			RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
			view.forward(request, response);

		} else if (action.equalsIgnoreCase("lstTarjetas")) {

			ArrayList<Banco> bancos = new ArrayList<Banco>();
			try {
				bancos = BancoUi.getBancos();
				
			} catch (SQLException ex) {

				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.forward(request, response);

			} catch (ClassNotFoundException ex) {
				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.forward(request, response);
				
			}
			forward = LIST;
			request.setAttribute("bancos", bancos);

		} else {
			Banco banco = new Banco();
			request.setAttribute("banco", banco);
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
