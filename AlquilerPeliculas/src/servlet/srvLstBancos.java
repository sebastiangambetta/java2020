package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.BancoUI;
import entities.Banco;

/**
 * Servlet implementation class srvLstBancos
 */
@WebServlet(name = "srvLstBancos", urlPatterns = { "/srvLstBancos" })
public class srvLstBancos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String INSERT_OR_EDIT = "./vista/banco/Banco.jsp";
	private static String LIST = "./vista/banco/lstBancos.jsp";

	BancoUI bancoUI = new BancoUI();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public srvLstBancos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = "lstBancos";
		if (request.getParameter("action") != null)
			action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {
			ArrayList<Banco> bancos = new ArrayList<Banco>();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				if (bancoUI.deleteBanco(id)) {
					request.setAttribute("Succes", "Se elimino el banco con exito");
					bancos = bancoUI.getBancos();
					bancos = bancos.stream().filter(x -> x.getIdBanco() != 1).collect(Collectors.toCollection(() -> new ArrayList<Banco>()));
					request.setAttribute("bancos", bancos);
					RequestDispatcher view = request.getRequestDispatcher(LIST);
					view.forward(request, response);					

				} else {
					request.setAttribute("Error", "No se pudo eliminar el banco");
					RequestDispatcher view = request.getRequestDispatcher(LIST);
					view.forward(request, response);
					
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (action.equalsIgnoreCase("edit")) {
			Banco banco = new Banco();
			int id = Integer.parseInt(request.getParameter("id"));

			if (id != 0) {
				try {
					banco = bancoUI.getBanco(id);
					request.setAttribute("banco", banco);
					RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
					view.forward(request, response);

				} catch (ClassNotFoundException | SQLException e) {					
					request.setAttribute("Error", "No se pudo obtener el ");
					RequestDispatcher view = request.getRequestDispatcher(LIST);
					view.forward(request, response);
					
				}
			} else {
				request.setAttribute("banco", banco);
				RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
				view.forward(request, response);

			}

		} else {
			ArrayList<Banco> bancos = new ArrayList<Banco>();
			
			try {
				bancos = bancoUI.getBancos();
				bancos = bancos.stream().filter(x -> x.getIdBanco() != 1).collect(Collectors.toCollection(() -> new ArrayList<Banco>()));
				
			} catch (ClassNotFoundException e) {
				request.setAttribute("bancos", bancos);
				RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
				view.forward(request, response);
				
			} catch (SQLException e) {
				request.setAttribute("bancos", bancos);
				RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
				view.forward(request, response);
				
			}			
			bancos = bancos.stream().filter(x -> x.getIdBanco() != 1).collect(Collectors.toCollection(() ->  new ArrayList<Banco>()));
			request.setAttribute("bancos", bancos);
			RequestDispatcher view = request.getRequestDispatcher(LIST);
			view.forward(request, response);		
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
