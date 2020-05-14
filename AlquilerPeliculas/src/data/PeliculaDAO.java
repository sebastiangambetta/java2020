/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import entities.Pelicula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author giuli
 */
public class PeliculaDAO extends Conexion{
    
    Connection conn = null;

    public void addPelicula(Pelicula p) throws SQLException {
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("insert into pelicula values(?, ?, ?, ?, ?)");

        stmt.setInt(1, p.getCodPelicula());
        stmt.setString(2, p.getTitulo());
        stmt.setString(3, p.getDescripcion());
        stmt.setFloat(4, p.getDuracion());
        stmt.setInt(5, p.getStock());       

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public int deletePelicula(int codPelicula) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("delete from pelicula where CodPelicula = ? ");        
        stmt.setInt(1, codPelicula);        
        
        int rta = stmt.executeUpdate();
        return rta;
    }

    public int editPelicula(Pelicula p) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("update pelicula set titulo = ?, descripcion = ?, duracion = ?, stock = ?"
                + "where CodPelicula = ?");

        stmt.setString(1, p.getTitulo());
        stmt.setString(2, p.getDescripcion());
        stmt.setFloat(3, p.getDuracion());
        stmt.setInt(4, p.getStock());
        stmt.setFloat(5, p.getCodPelicula());
        int rta = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return rta;
    }

    public Pelicula getPelicula(int codPelicula) throws ClassNotFoundException, SQLException {
        Pelicula p = new Pelicula();
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from pelicula where CodPelicula = ?");
        stmt.setInt(1, codPelicula);        
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            do {
                p.setCodPelicula(rs.getInt("CodPelicula"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setTitulo(rs.getString("titulo"));
                p.setDuracion(rs.getFloat("duracion"));
                p.setStock(rs.getInt("stock"));
            } while (rs.next());
        }

        rs.close();
        conn.close();
        return p;
    }

    public ArrayList<Pelicula> getPeliculas() throws SQLException, ClassNotFoundException {
        ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from pelicula");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Pelicula p = new Pelicula();

            p.setCodPelicula(rs.getInt("CodPelicula"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setTitulo(rs.getString("titulo"));
            p.setDuracion(rs.getFloat("duracion"));
            p.setStock(rs.getInt("stock"));
            peliculas.add(p);
        }
        rs.close();
        conn.close();

        return peliculas;
    }
    
}
