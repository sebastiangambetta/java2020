/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import entities.Alquiler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author giuli
 */
public class AlquilerDAO extends Conexion {
    
    Connection conn = null;
    
    public void addAlquiler(Alquiler alq) throws SQLException {
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("insert into alquiler values(?, ?, ?, ?)");

        stmt.setInt(1, alq.getNroAlquiler());
        stmt.setDate(2, alq.getFechaAlquiler());
        stmt.setFloat(3, alq.getImporteAlquiler());
        stmt.setInt(4, alq.getSocio());

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public int deleteAlquiler(int idAlquiler) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("delete from alquiler where nroAlquiler = ? ");
        stmt.setInt(1, Integer.valueOf(idAlquiler));
        int rta = stmt.executeUpdate();
        return rta;

    }

    public int editAlquiler(Alquiler alq) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("update alquiler set fechaAlquiler = ?, importeAlquiler = ?"
                + ", socio = ? where nroAlquiler = ?");
        
        stmt.setDate(1, alq.getFechaAlquiler());
        stmt.setFloat(2, alq.getImporteAlquiler());
        stmt.setInt(3, alq.getSocio());
        stmt.setInt(4, alq.getNroAlquiler());
        int rta = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return rta;
    }

    public Alquiler getAlquiler(int nroAlquiler) throws ClassNotFoundException, SQLException {
        Alquiler alq = new Alquiler();
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from alquiler where nroAlquiler = ?");
        stmt.setInt(1, nroAlquiler);
        //stmt.setDate(2, fecha);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            do {
            alq.setNroAlquiler(rs.getInt("nroAlquiler"));
            alq.setFechaAlquiler(rs.getDate("fechaAlquiler"));
            alq.setImporteAlquiler(rs.getFloat("importeAlquiler"));
            alq.setSocio(rs.getInt("socio"));
            } while (rs.next());
        }

        rs.close();
        conn.close();
        return alq;

    }

    public ArrayList<Alquiler> getAlquileres() throws SQLException, ClassNotFoundException {
        ArrayList<Alquiler> lstAlquileres = new ArrayList<Alquiler>();

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from alquiler");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Alquiler alq = new Alquiler();

            alq.setNroAlquiler(rs.getInt("nroAlquiler"));            
            alq.setFechaAlquiler(rs.getDate("fechaAlquiler"));
            alq.setImporteAlquiler(rs.getFloat("importeAlquiler"));
            alq.setSocio(rs.getInt("socio"));            

            lstAlquileres.add(alq);
        }

        rs.close();
        conn.close();

        return lstAlquileres;
    }
}
