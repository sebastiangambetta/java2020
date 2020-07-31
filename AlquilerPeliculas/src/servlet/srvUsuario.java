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
import entities.TarjetaCredito;
import entities.Usuario;
import business.SocioUI;
import business.TarjetaCreditoUI;
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
@WebServlet(name = "srvUsuario", urlPatterns = {"/srvUsuario"})
public class srvUsuario extends HttpServlet {

    private static String INSERT_OR_EDIT = "./vista/usuario/Usuario.jsp";
    private static String LIST_USER = "./vista/usuario/lstUsuarios.jsp";
    
    
    UsuarioUI usuarioUI = new UsuarioUI();    
    SocioUI socioUI = new SocioUI();
    TarjetaCreditoUI tarjetaCreditoUI = new TarjetaCreditoUI(); 
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
        //Usuario user = request.getParameter("user");
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
        
        String forward = "";
        String action = "lstUser";
        if(request.getParameter("action") != null)
            action = request.getParameter("action");

        	if (action.equalsIgnoreCase("edit")) {

            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));            
            Socio socio;
            Usuario usuario;
            ArrayList<TarjetaCredito> tarjetas;
            try {
            	usuario = usuarioUI.getUserbyId(id);
                socio = socioUI.getSocio(usuario.getIdUsuario());
                tarjetas = tarjetaCreditoUI.getTarjetaC();
                request.setAttribute("socio", socio);
                request.setAttribute("usuario", usuario);
                request.setAttribute("tarjetas", tarjetas);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            forward = INSERT_OR_EDIT;
            RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
            view.forward(request, response);            

        } else if (action.equalsIgnoreCase("lstUser")) {

            ArrayList<Usuario> lstUsuario = new ArrayList<Usuario>();
            try {
                lstUsuario = usuarioUI.getUsuarios();
            } catch (SQLException ex) {
                Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(srvSocio.class.getName()).log(Level.SEVERE, null, ex);
            }

            forward = LIST_USER;            
            request.setAttribute("lstUsuarios", lstUsuario);
            
        } else {
            Usuario usuario = new Usuario();
            request.setAttribute("usuario", usuario);
            forward = INSERT_OR_EDIT;   
            
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
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
        //processRequest(request, response);        
        
        Usuario usuario = new Usuario();
		Socio socio = new Socio();

		socio.setNroSocio(Integer.parseInt(request.getParameter("id")));
		socio.setNombre(request.getParameter("nombre"));
		socio.setApellido(request.getParameter("apellido"));
		socio.setDomicilio(request.getParameter("domicilio"));		
		socio.setTelefono(request.getParameter("telefono"));
		socio.setMail(request.getParameter("uEmail"));
		socio.setEstado(request.getParameter("estado").toString() != ""? request.getParameter("estado").toString(): null);
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
			} 
			catch (SQLException ex) {
				Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
				
				request.setAttribute("Error", "Ocurrio un error.");
	        	RequestDispatcher rd = request.getRequestDispatcher("lstUsuarios.jsp"); 
	            rd.forward(request, response);
			}

		} else

			try {
				
				usuarioUI.updateUsuario(usuario, socio);
				RequestDispatcher rd = request.getRequestDispatcher("lstUsuarios.jsp"); 
				rd.forward(request, response);
			} 
		catch (ClassNotFoundException ex) {
				//Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
				
				request.setAttribute("Error", "Ocurrio un error al actualizar.");
	        	RequestDispatcher rd = request.getRequestDispatcher("lstUsuarios.jsp"); 
	            rd.forward(request, response);
			} 
		catch (SQLException ex) {
				//Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
				
				request.setAttribute("Error", "Ocurrio un error al actualizar.");
	        	RequestDispatcher rd = request.getRequestDispatcher("lstUsuarios.jsp"); 
	            rd.forward(request, response);
			}

		try {
			request.setAttribute("lstUsuarios", usuarioUI.getUsuarios());
		}
		catch (SQLException ex) {
			//Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			
			request.setAttribute("Error", "Ocurrio un error.");
        	RequestDispatcher rd = request.getRequestDispatcher("lstUsuarios.jsp"); 
            rd.forward(request, response);
            
		} 
		catch (ClassNotFoundException ex) {
			//Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			
			request.setAttribute("Error", "Ocurrio un error.");
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
