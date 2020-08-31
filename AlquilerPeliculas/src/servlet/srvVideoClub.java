package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.VideoClubUI;
import entities.VideoClub;

/**
 * Servlet implementation class srvVideoClub
 */
@WebServlet("/srvVideoClub")
public class srvVideoClub extends HttpServlet {
	private static final long serialVersionUID = 1L;

	VideoClubUI videoUI = new VideoClubUI();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public srvVideoClub() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String forward = "";
		String action = "home";
		try {
			if (request.getParameter("action") != null) {
				action = request.getParameter("action");
			}

			if (action == "add") {

				VideoClub videoC = new VideoClub();
				request.setAttribute("video", videoC);
				RequestDispatcher rd = request.getRequestDispatcher("");
				rd.forward(request, response);

			} else if (action == "edit") {
				VideoClub videoC = new VideoClub();
				videoC.setId(Integer.parseInt(request.getParameter("id")));
				videoC.setPlazoMaxADevolver(Integer.parseInt(request.getParameter("plazoMaxADevolver")));
				videoC.setCantMaxPelPendientes(Integer.parseInt(request.getParameter("cantMaxPelPendientes")));
				videoC.setImportePorDia(Integer.parseInt(request.getParameter("importePorDia")));

				request.setAttribute("video", videoC);
				RequestDispatcher rd = request.getRequestDispatcher("");
				rd.forward(request, response);

			} else if (action == "delete") {
				int id = Integer.parseInt(request.getParameter("id"));

				videoUI.deleteVideo(id);

			} else {

				RequestDispatcher view = request.getRequestDispatcher(action);
				view.forward(request, response);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
