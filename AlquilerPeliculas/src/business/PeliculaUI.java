/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.PeliculaDAO;
import entities.Pelicula;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class PeliculaUI {
    
    PeliculaDAO peliculaDAO = new PeliculaDAO();
    
     public Pelicula getPelicula(int id) throws ClassNotFoundException, SQLException {
        Pelicula socio = new Pelicula();
        socio = peliculaDAO.getPelicula(id);
        if (socio == null) {
            return null;
        }

        return socio;
    }

    public ArrayList<Pelicula> getPelicula() throws SQLException, ClassNotFoundException {
        ArrayList<Pelicula> lstPeliculas = new ArrayList<Pelicula>();
        //getUsuarios
        lstPeliculas = peliculaDAO.getPeliculas();
        return lstPeliculas;
    }

    public boolean deletePelicula(int id) throws SQLException, ClassNotFoundException {

        int rta = peliculaDAO.deletePelicula(id);

        return rta == 1 ? true : false;
    }
    
    public boolean updatePelicula(Pelicula peli) throws SQLException, ClassNotFoundException {

        int rta = peliculaDAO.editPelicula(peli);
        return rta == 1 ? true : false;
    }
    
}
