/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import entities.Genero;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class GeneroDAO extends Conexion {

    Connection conn = null;

    public void addGenero(Genero gen) throws SQLException {
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("insert into Genero values(?, ?)");

        stmt.setInt(1, gen.getIdGenero());
        stmt.setString(2, gen.getDescripcion());

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public int deleteGenero(int idGenero) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("delete from Genero where idGenero = ? ");
        stmt.setInt(1, idGenero);
        int rta = stmt.executeUpdate();
        return rta;

    }

    public int editGenero(Genero gen) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("update Genero set descripcion = ?"
                + "where idGenero = ?");

        stmt.setString(1, gen.getDescripcion());
        stmt.setFloat(2, gen.getIdGenero());
        int rta = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return rta;
    }

    public Genero getGenero(int idGenero) throws ClassNotFoundException, SQLException {
        Genero gen = new Genero();
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from alquiler where nroAlquiler = ?");
        stmt.setInt(1, idGenero);
        //stmt.setDate(2, fecha);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            do {
                gen.setIdGenero(rs.getInt("idGenero"));
                gen.setDescripcion(rs.getString("descripcion"));
            } while (rs.next());
        }

        rs.close();
        conn.close();
        return gen;
    }

    public ArrayList<Genero> getGeneros() throws SQLException, ClassNotFoundException {
        ArrayList<Genero> generos = new ArrayList<Genero>();

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from Genero");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Genero alq = new Genero();

            alq.setIdGenero(rs.getInt("nroAlquiler"));
            alq.setDescripcion(rs.getString("fechaAlquiler"));
            generos.add(alq);
        }
        rs.close();
        conn.close();

        return generos;
    }

}
