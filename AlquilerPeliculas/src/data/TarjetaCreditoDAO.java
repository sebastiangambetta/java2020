/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import entities.TarjetaCredito;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class TarjetaCreditoDAO extends Conexion {

    Connection conn = null;

    public void addGenero(TarjetaCredito tc) throws SQLException {
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("insert into tarjetacredito values(?, ?)");

        stmt.setInt(5, tc.getiIdTarjeta());
        stmt.setString(1, tc.getNombreTarjeta());

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public int deleteAlquiler(int idTarjeta) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("delete from tarjetacredito where idTarjeta = ? ");
        stmt.setInt(1, idTarjeta);

        int rta = stmt.executeUpdate();
        return rta;
    }

    public int editAlquiler(TarjetaCredito tc) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("update tarjetacredito set nombreTarjeta = ?"
                + " where idTarjeta = ?");

        stmt.setString(1, tc.getNombreTarjeta());
        stmt.setInt(2, tc.getiIdTarjeta());
        int rta = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return rta;
    }

    public TarjetaCredito getAlquiler(int idTarjeta) throws ClassNotFoundException, SQLException {
        TarjetaCredito tc = new TarjetaCredito();
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from tarjetacredito where idTarjeta = ?");
        stmt.setInt(1, idTarjeta);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            do {
                tc.setNombreTarjeta(rs.getString("nombreTarjeta"));
                tc.setIdTarjeta(rs.getInt("idTarjeta"));

            } while (rs.next());
        }

        rs.close();
        conn.close();
        return tc;
    }

    public ArrayList<TarjetaCredito> getAlquileres() throws SQLException, ClassNotFoundException {
        ArrayList<TarjetaCredito> TarjetaCreditos = new ArrayList<TarjetaCredito>();

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from tarjetacredito");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            TarjetaCredito tc = new TarjetaCredito();

            tc.setNombreTarjeta(rs.getString("nombreTarjeta"));
            tc.setIdTarjeta(rs.getInt("idTarjeta"));
            TarjetaCreditos.add(tc);
        }
        rs.close();
        conn.close();

        return TarjetaCreditos;
    }

}
