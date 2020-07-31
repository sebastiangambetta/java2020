/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import entities.Usuario;
import servlet.srvLstUsuarios;
import entities.Socio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giuli
 */
public class UsuarioDAO extends Conexion {

	Connection conn = null;

	public void addUsuario(Usuario user, Socio socio) throws SQLException {
		conn = this.getConnection();
		PreparedStatement insertSocio = null;
		PreparedStatement insertUsuario = null;
		int rowAffected;
		ResultSet rs = null;
		try {

			conn.setAutoCommit(false);

			String query = "insert into socio(nombre, apellido, domicilio, telefono, mail, banco, nroTarjeta, estado ) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?)";

			insertSocio = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			insertSocio.setString(1, socio.getNombre());
			insertSocio.setString(2, socio.getApellido());
			insertSocio.setString(3, socio.getDomicilio());
			insertSocio.setString(4, socio.getTelefono());
			insertSocio.setString(5, socio.getMail());
			if (socio.getNroTarjeta() == null || socio.getBanco() == 0 ) {
				insertSocio.setNull(6, java.sql.Types.INTEGER);
				insertSocio.setNull(7, java.sql.Types.INTEGER);
			} else {
				insertSocio.setInt(6, socio.getBanco());
				insertSocio.setInt(7, socio.getNroTarjeta());
				
			}

			insertSocio.setString(8, socio.getEstado());

			rowAffected = insertSocio.executeUpdate();
			
			rs = insertSocio.getGeneratedKeys();
			
			int candidateId = 0;
			if (rs.next()) {
				candidateId = rs.getInt(1);
			}
			
			if (rowAffected == 1) {

				user.setIdUsuario(candidateId);

				insertUsuario = conn.prepareStatement(
						"insert into usuario(idUsuario, email, contrasena, nivelAcceso) values(?, ?, ?, ?)");

				insertUsuario.setInt(1, user.getIdUsuario());
				insertUsuario.setString(2, user.getEmail());
				insertUsuario.setString(3, user.getContrasena());
				insertUsuario.setString(4, user.getAcceso());

				rowAffected = insertUsuario.executeUpdate();

				if (rowAffected != 1)
					throw new SQLException();
			}

			conn.commit();			
			insertSocio.close();
			insertUsuario.close();
			conn.close();

		} 
		catch (SQLException ex) {
			Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			conn.rollback();
		} finally {

			if (insertSocio != null) {
				insertSocio.close();
			}
			if (insertUsuario != null) {
				insertUsuario.close();
			}
			if (conn != null) {				
				conn.close();
			}			
		}
		
	}

	public int deleteUsuario(int idUsuario) throws ClassNotFoundException, SQLException {

		conn = this.getConnection();
		//PreparedStatement stmt = conn.prepareStatement("delete from usuario where idUsuario = ? ");
		PreparedStatement stmt = conn.prepareStatement("update usuario set estado = 3 where idUsuario = ? ");
		stmt.setInt(1, Integer.valueOf(idUsuario));
		int rta = stmt.executeUpdate();

		if (rta == 1) {
			stmt = conn.prepareStatement("delete from socio where nroSocio = ? ");
			stmt.setInt(1, Integer.valueOf(idUsuario));
			rta = stmt.executeUpdate();
		}

		return rta;

	}

	public int editUsuario(Usuario user, Socio s) throws ClassNotFoundException, SQLException {

			conn = this.getConnection();
			PreparedStatement stmt = null;
			PreparedStatement st = null;
			int rta = 0;
    	
    	try {    		
    		
    		conn.setAutoCommit(false);
    		
    		stmt = conn.prepareStatement(
    				"update usuario set email = ?, estado = 3, contrasena = ?, nivelAcceso = ?" + " where idUsuario = ?");
    		stmt.setString(1, user.getEmail());
    		stmt.setString(2, user.getContrasena());
    		stmt.setString(3, user.getAcceso());
    		stmt.setInt(4, Integer.valueOf(user.getIdUsuario()));
    		rta = stmt.executeUpdate();

    		st = conn.prepareStatement("update socio set nombre = ?, apellido = ?, domicilio = ?, telefono = ?, mail = ?, banco = ?, "
    				+ "nroTarjeta = ? , estado = ? where nroSocio = ?");

            st.setString(1, s.getNombre());
            st.setString(2, s.getApellido());
            st.setString(3, s.getDomicilio());
            st.setString(4, s.getTelefono());
            st.setString(5, s.getMail());
            st.setInt(6, s.getBanco());
            st.setInt(7, s.getNroTarjeta());
            st.setString(8, s.getEstado());
            st.setInt(9, s.getNroSocio());
            rta = st.executeUpdate();
            
            conn.commit();
            st.close();            
    		stmt.close();
    		conn.close();
    		
    		return rta;

    	}

    	catch (SQLException ex) {
    			Logger.getLogger(srvLstUsuarios.class.getName()).log(Level.SEVERE, null, ex);
    			conn.rollback();
    		} finally {
    			
    			if (stmt != null) {
    				stmt.close();
    			}
    			if (st != null) {
    				st.close();
    			}
    			if (conn != null) {    				
    				conn.close();
    			}
    					
    		}
		return rta;    	
	}

	public Usuario Login(String email, String contrasena) throws ClassNotFoundException, SQLException {
		Usuario user = new Usuario();
		conn = this.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select idUsuario, email, contrasena, nivelAcceso, estado from usuario where email = ? and contrasena=?");
		stmt.setString(1, email);
		stmt.setString(2, contrasena);
		ResultSet rs = stmt.executeQuery();
		if (!rs.next()) {
			return null;
		} else {
			do {
				user.setIdUsuario(rs.getInt("idUsuario"));
				user.setEmail(rs.getString("email"));
				user.setContrasena(rs.getString("contrasena"));
				user.setAcceso(rs.getString("nivelAcceso"));
				user.setEstado(rs.getString("estado"));
			} while (rs.next());
		}

		rs.close();
		conn.close();
		return user;

	}

	public Usuario getUsuario(int id) throws ClassNotFoundException, SQLException {
		Usuario user = new Usuario();
		conn = this.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select idUsuario, email, contrasena, nivelAcceso, estado from usuario where idUsuario = ? and estado <> '3'");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (!rs.next()) {
			return null;
		} else {
			do {
				user.setIdUsuario(rs.getInt("idUsuario"));
				user.setEmail(rs.getString("email"));
				user.setContrasena(rs.getString("contrasena"));
				user.setAcceso(rs.getString("nivelAcceso"));
				user.setEstado(rs.getString("estado"));
			} while (rs.next());
		}

		rs.close();
		conn.close();
		return user;

	}

	public ArrayList<Usuario> getUsuarios() throws SQLException, ClassNotFoundException {
		ArrayList<Usuario> lstUsuarios = new ArrayList<Usuario>();

		conn = this.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select idUsuario, email, contrasena, nivelAcceso, estado from usuario where nivelAcceso <> 'admin' ");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Usuario user = new Usuario();

			user.setIdUsuario(rs.getInt("idUsuario"));
			user.setEmail(rs.getString("email"));
			user.setContrasena(rs.getString("contrasena"));
			user.setAcceso(rs.getString("nivelAcceso"));
			user.setEstado(rs.getString("estado"));

			lstUsuarios.add(user);
		}

		rs.close();
		conn.close();

		return lstUsuarios;
	}

}
