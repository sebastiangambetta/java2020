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
        stmt.setInt(4, s.getNroTarjeta());
        stmt.setString(4, s.getEstado());

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
        PreparedStatement stmt = conn.prepareStatement("update socio set nombre = ?, apellido = ?, domicilio = ?, telefono = ?, mail = ?, nroTarjeta = ?"
                + ", estado = ? where nroSocio = ?");

        stmt.setString(1, s.getNombre());
        stmt.setString(2, s.getApellido());
        stmt.setString(3, s.getDomicilio());
        stmt.setString(4, s.getTelefono());        
        stmt.setInt(4, s.getNroTarjeta());
        stmt.setString(4, s.getEstado());
        stmt.setInt(5, s.getNroSocio());
        int rta = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return rta;
    }

    public Socio getSocio(int codPelicula) throws ClassNotFoundException, SQLException {
        Socio s = new Socio();
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from socio where nroSocio = ?");
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
                s.setEstado(rs.getString("estado"));
            } while (rs.next());
        }

        rs.close();
        conn.close();
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
