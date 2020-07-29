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

			String query = "insert into socio(nombre, apellido, domicilio, telefono, mail, nroTarjeta, estado) "
					+ "values(?, ?, ?, ?, ?, ?)";

			insertSocio = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			insertSocio.setString(1, socio.getNombre());
			insertSocio.setString(2, socio.getApellido());
			insertSocio.setString(3, socio.getDomicilio());
			insertSocio.setString(4, socio.getTelefono());
			insertSocio.setString(5, socio.getMail());
			if (socio.getNroTarjeta() == null) {
				insertSocio.setNull(6, java.sql.Types.INTEGER);
			} else {
				insertSocio.setInt(6, socio.getNroTarjeta());
			}

			insertSocio.setString(7, socio.getEstado());

			rowAffected = insertSocio.executeUpdate();

			// get candidate id
			rs = insertSocio.getGeneratedKeys();

			// Verificar
			// st.close();
			
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
			conn.setAutoCommit(true);			
		}
	}

	public int deleteUsuario(int idUsuario) throws ClassNotFoundException, SQLException {

		conn = this.getConnection();
		PreparedStatement stmt = conn.prepareStatement("delete from usuario where idUsuario = ? ");
		stmt.setInt(1, Integer.valueOf(idUsuario));
		int rta = stmt.executeUpdate();

		if (rta == 1) {
			stmt = conn.prepareStatement("delete from socio where nroSocio = ? ");
			stmt.setInt(1, Integer.valueOf(idUsuario));
			rta = stmt.executeUpdate();
		}

		return rta;

	}

	public int editUsuario(Usuario user) throws ClassNotFoundException, SQLException {

		conn = this.getConnection();
		PreparedStatement stmt = conn
				.prepareStatement("update usuario set email = ?, contrasena = ?, nivelAcceso = ?" + " where idUsuario = ?");
		stmt.setString(1, user.getEmail());
		stmt.setString(2, user.getContrasena());
		stmt.setString(3, user.getAcceso());
		stmt.setInt(4, Integer.valueOf(user.getIdUsuario()));
		int rta = stmt.executeUpdate();

		stmt.close();
		conn.close();

		return rta;
	}

	public Usuario Login(String email, String contrasena) throws ClassNotFoundException, SQLException {
		Usuario user = new Usuario();
		conn = this.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from usuario where email = ? and contrasena=?");
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
			} while (rs.next());
		}

		rs.close();
		conn.close();
		return user;

	}

	public Usuario getUsuario(int id) throws ClassNotFoundException, SQLException {
		Usuario user = new Usuario();
		conn = this.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from usuario where idUsuario = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (!rs.next()) {
			return null;
		} else {
			do {
				user.setIdUsuario(rs.getInt("idUsuario"));
				user.setEmail(rs.getString("email"));
				user.setContrasena(rs.getString("contrasena"));
			} while (rs.next());
		}

		rs.close();
		conn.close();
		return user;

	}

	public ArrayList<Usuario> getUsuarios() throws SQLException, ClassNotFoundException {
		ArrayList<Usuario> lstUsuarios = new ArrayList<Usuario>();

		conn = this.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from usuario");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Usuario user = new Usuario();

			user.setIdUsuario(rs.getInt("idUsuario"));
			user.setEmail(rs.getString("email"));
			user.setContrasena(rs.getString("contrasena"));

			lstUsuarios.add(user);
		}

		rs.close();
		conn.close();

		return lstUsuarios;
	}

}
