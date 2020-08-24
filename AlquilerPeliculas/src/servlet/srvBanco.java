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
import java.util.stream.Collectors;

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
	private static String INSERT_OR_EDIT = "./vista/banco/Banco.jsp";
	private static String LIST = "./vista/banco/lstBancos.jsp";

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
			ArrayList<Banco> lstbancos = new ArrayList<Banco>();
			Banco banc = new Banco();
			try {

				banc.setIdBanco((Integer.parseInt(request.getParameter("id"))));
				banc.setNombreBanco(request.getParameter("nombre"));
				forward = INSERT_OR_EDIT;

				if (banc.getIdBanco() != 0) {
					if (BancoUi.updateBanco(banc)) {						
						lstbancos = BancoUi.getBancos();
						lstbancos = lstbancos.stream().filter(x -> x.getIdBanco() != 1).collect(Collectors.toCollection(() -> new ArrayList<Banco>()));
						request.setAttribute("bancos", lstbancos);
						RequestDispatcher view = request.getRequestDispatcher(LIST);
						view.forward(request, response);

					} else {
						lstbancos = BancoUi.getBancos();
						lstbancos = lstbancos.stream().filter(x -> x.getIdBanco() != 1).collect(Collectors.toCollection(() -> new ArrayList<Banco>()));
						request.setAttribute("bancos", lstbancos);
						RequestDispatcher view = request.getRequestDispatcher(LIST);
						view.forward(request, response);

					}

				} else {
					if (BancoUi.addBanco(banc)) {
						request.setAttribute("bancos", lstbancos);
						RequestDispatcher view = request.getRequestDispatcher(LIST);
						view.forward(request, response);

					} else {
						request.setAttribute("bancos", lstbancos);
						RequestDispatcher view = request.getRequestDispatcher(LIST);
						view.forward(request, response);

					}
				}

			} catch (ClassNotFoundException ex) {
				request.setAttribute("bancos", lstbancos);
				RequestDispatcher view = request.getRequestDispatcher(LIST);
				view.forward(request, response);

			} catch (SQLException ex) {
				request.setAttribute("bancos", lstbancos);
				RequestDispatcher view = request.getRequestDispatcher(LIST);
				view.forward(request, response);

			}

			RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
			view.forward(request, response);

		} else if (action.equalsIgnoreCase("lstTarjetas")) {

			ArrayList<Banco> bancos = new ArrayList<Banco>();
			try {
				bancos = BancoUi.getBancos();
				bancos = bancos.stream().filter(x -> x.getIdBanco() != 1).collect(Collectors.toCollection(() -> new ArrayList<Banco>()));
				
			} catch (SQLException ex) {
				request.setAttribute("bancos", bancos);
				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.forward(request, response);

			} catch (ClassNotFoundException ex) {
				request.setAttribute("bancos", bancos);
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
		Banco bank = new Banco();

		bank.setIdBanco(Integer.parseInt(request.getParameter("id")));
		bank.setNombreBanco(request.getParameter("nombre"));

		try {
			if (bank.getIdBanco() != 0) {

				if (BancoUi.updateBanco(bank)) {
					ArrayList<Banco> bancos = BancoUi.getBancos();
					bancos = bancos.stream().filter(x -> x.getIdBanco() != 1).collect(Collectors.toCollection(() -> new ArrayList<Banco>()));
					request.setAttribute("bancos", bancos);
					RequestDispatcher view = request.getRequestDispatcher(LIST);
					view.forward(request, response);

				} else {
					// Completar con jsp de error
				}
			} else {
				if (BancoUi.addBanco(bank)) {
					ArrayList<Banco> bancos = BancoUi.getBancos();
					bancos = bancos.stream().filter(x -> x.getIdBanco() != 1).collect(Collectors.toCollection(() -> new ArrayList<Banco>()));
					request.setAttribute("bancos", bancos);
					RequestDispatcher view = request.getRequestDispatcher(LIST);
					view.forward(request, response);

				} else {
					// Completar con jsp de error
					
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
