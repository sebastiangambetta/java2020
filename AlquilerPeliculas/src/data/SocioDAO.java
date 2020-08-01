/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import entities.Socio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class SocioDAO extends Conexion {

	Connection conn = null;

	public void addSocio(Socio s) throws SQLException {
		conn = this.getConnection();
		PreparedStatement stmt = conn.prepareStatement("insert into socio values(?, ?, ?, ?, ?, ?, ?)");

		stmt.setInt(5, s.getNroSocio());
		stmt.setString(1, s.getNombre());
		stmt.setString(2, s.getApellido());
		stmt.setString(3, s.getDomicilio());
		stmt.setString(4, s.getTelefono());
		stmt.setInt(5, s.getNroTarjeta());
		stmt.setString(6, s.getEstado());

		stmt.executeUpdate();
		stmt.close();
		conn.close();
	}

	public int deleteSocio(int idSocio) throws ClassNotFoundException, SQLException {

		conn = this.getConnection();
		PreparedStatement stmt = conn.prepareStatement("delete from socio where nroSocio = ? ");
		stmt.setInt(1, idSocio);

		int rta = stmt.executeUpdate();
		return rta;
	}

	public int editSocio(Socio s) throws ClassNotFoundException, SQLException {

		conn = this.getConnection();

		PreparedStatement stmt = conn.prepareStatement(
				"update socio set nombre = ?, apellido = ?, domicilio = ?, telefono = ?, mail = ?, nroTarjeta = ?"
						+ ", estado = ? where nroSocio = ?");

		stmt.setString(1, s.getNombre());
		stmt.setString(2, s.getApellido());
		stmt.setString(3, s.getDomicilio());
		stmt.setString(4, s.getTelefono());
		stmt.setString(5, s.getMail());
		stmt.setInt(6, s.getNroTarjeta());
		stmt.setString(7, s.getEstado());
		stmt.setInt(8, s.getNroSocio());
		int rta = stmt.executeUpdate();

		stmt.close();
		conn.close();

		return rta;
	}

	public Socio getSocio(int codPelicula) throws Exception {

		Socio s = new Socio();
		PreparedStatement stmt = null;
		try {

			conn = this.getConnection();
			stmt = conn.prepareStatement("select nroSocio, nombre, apellido, domicilio, telefono, mail, nroTarjeta, envioMail, banco from socio where nroSocio = ?");
			stmt.setInt(1, codPelicula);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			} else {
				do {
					s.setNroSocio(rs.getInt("nroSocio"));
					s.setNombre(rs.getString("nombre"));
					s.setApellido(rs.getString("apellido"));
					s.setDomicilio(rs.getString("domicilio"));
					s.setTelefono(rs.getString("telefono"));
					s.setMail(rs.getString("mail"));
					s.setNroTarjeta(rs.getInt("nroTarjeta"));
					s.setEstado(rs.getString("envioMail"));
					s.setBanco(rs.getInt("banco"));
				} while (rs.next());
			}
		} catch (SQLException e) {
			// ver si envio el msj de excepcion
			throw new SQLException(e.getMessage());
			//return null;
		} finally {
			if (!stmt.isClosed()) {
				stmt.close();
			}
			if (!conn.isClosed()) {
				conn.close();
			}
		}

		return s;
	}

	public ArrayList<Socio> getSocios() throws SQLException, ClassNotFoundException {
		ArrayList<Socio> socios = new ArrayList<Socio>();

		conn = this.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from socio");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Socio s = new Socio();

			s.setNroSocio(rs.getInt("nroSocio"));
			s.setNombre(rs.getString("nombre"));
			s.setApellido(rs.getString("apellido"));
			s.setDomicilio(rs.getString("domicilio"));
			s.setTelefono(rs.getString("telefono"));
			s.setMail(rs.getString("mail"));
			s.setNroTarjeta(rs.getInt("nroTarjeta"));
			s.setEstado(rs.getString("estado"));
			socios.add(s);
		}
		rs.close();
		conn.close();

		return socios;
	}

}
