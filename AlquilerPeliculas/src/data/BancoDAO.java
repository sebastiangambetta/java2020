/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import entities.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class BancoDAO extends Conexion {

    Connection conn = null;

    public Boolean addBanco(Banco tc) throws SQLException {
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("insert into tarjetacredito values(?, ?)");

        stmt.setInt(5, tc.getIdBanco());
        stmt.setString(1, tc.getNombreBanco());

        int value = stmt.executeUpdate();
        
        stmt.close();
        conn.close();
        
        return value == 0 ? false : true;        	
        
    }

    public Boolean deleteBanco(int idBanco) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("delete from banco where idBanco = ? ");
        stmt.setInt(1, idBanco);
        
        stmt.close();
        conn.close();

        int rta = stmt.executeUpdate();
        return rta == 0 ? false : true;
    }

    public Boolean editBanco(Banco bank) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("update banco set nombreBanco = ?"
                + " where idBanco = ?");

        stmt.setString(1, bank.getNombreBanco());
        stmt.setInt(2, bank.getIdBanco());
        int rta = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return rta == 0 ? false : true;
    }

    public Banco getBanco(int idBanco) throws ClassNotFoundException, SQLException {
        Banco tc = new Banco();
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from banco where idBanco = ?");
        stmt.setInt(1, idBanco);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            do {
                tc.setNombreBanco(rs.getString("nombreBanco"));
                tc.setIdBanco(rs.getInt("idBanco"));

            } while (rs.next());
        }

        rs.close();
        conn.close();
        return tc;
    }

    public ArrayList<Banco> getBanco() throws SQLException, ClassNotFoundException {
        ArrayList<Banco> bancos = new ArrayList<Banco>();

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from banco");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Banco tc = new Banco();

            tc.setNombreBanco(rs.getString("nombreBanco"));
            tc.setIdBanco(rs.getInt("idBanco"));
            bancos.add(tc);
        }
        rs.close();
        conn.close();

        return bancos;
    }

}
