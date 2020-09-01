package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
@WebServlet(name = "srvVideoClub", urlPatterns = { "/srvVideoClub" })
public class srvVideoClub extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String LIST = "./vista/lstParametroVideoClub.jsp";
	private String INSERT_OR_EDIT = "./vista/ParametroVideoClub.jsp";
	private String ERROR = "./ERROR.jsp";

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

		String forward = this.LIST;
		String action = "list";
		try {
			if (request.getParameter("action") != null) {
				action = request.getParameter("action");

			}

			if (action == "add") {
				VideoClub parametroConfig = new VideoClub();
				request.setAttribute("parametro", parametroConfig);
				RequestDispatcher rd = request.getRequestDispatcher(INSERT_OR_EDIT);
				rd.forward(request, response);

			} else if (action == "edit") {
				VideoClub parametroConfig = new VideoClub();
				parametroConfig.setId(Integer.parseInt(request.getParameter("id")));
				parametroConfig.setPlazoMaxADevolver(Integer.parseInt(request.getParameter("plazoMaxADevolver")));
				parametroConfig.setCantMaxPelPendientes(Integer.parseInt(request.getParameter("cantMaxPelPendientes")));
				parametroConfig.setImportePorDia(Integer.parseInt(request.getParameter("importePorDia")));

				request.setAttribute("parametro", parametroConfig);
				RequestDispatcher rd = request.getRequestDispatcher(INSERT_OR_EDIT);
				rd.forward(request, response);

			} else if (action == "delete") {
				int id = Integer.parseInt(request.getParameter("id"));
				videoUI.deleteVideo(id);

			} else {
				ArrayList<VideoClub> parametros = this.videoUI.getVideos();
				request.setAttribute("parametros", parametros);
				RequestDispatcher view = request.getRequestDispatcher(forward);
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

		VideoClub parametroConfig = new VideoClub();
		String action = this.LIST;

		try {

			parametroConfig.setPlazoMaxADevolver(Integer.parseInt(request.getParameter("plazoMax")));
			parametroConfig.setCantMaxPelPendientes(Integer.parseInt(request.getParameter("cantMaxPend")));
			parametroConfig.setImportePorDia(Integer.parseInt(request.getParameter("importeDiario")));

			if (request.getParameter("id") != null) {
				parametroConfig.setId(Integer.parseInt(request.getParameter("id")));

				if (this.videoUI.addVideo(parametroConfig)) {
					request.setAttribute("Success", "Se han guardado los parámetros.");
					RequestDispatcher view = request.getRequestDispatcher(this.LIST);
					view.forward(request, response);

				} else {
					request.setAttribute("Error", "Error al guardar los parámetros.");
					RequestDispatcher view = request.getRequestDispatcher(this.INSERT_OR_EDIT);
					view.forward(request, response);

				}

			} else {

				if (this.videoUI.updateVideo(parametroConfig)) {
					request.setAttribute("Success", "Se han actualizado los parámetros.");
					RequestDispatcher view = request.getRequestDispatcher(this.LIST);
					view.forward(request, response);

				} else {
					request.setAttribute("Error", "Error al actualizar los parámetros.");
					RequestDispatcher view = request.getRequestDispatcher(this.INSERT_OR_EDIT);
					view.forward(request, response);
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();			
			RequestDispatcher view = request.getRequestDispatcher(ERROR);
			view.forward(request, response);
		}

	}

}
