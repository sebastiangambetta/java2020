/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.GeneroDAO;
import entities.Genero;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class GeneroUI {
    
    GeneroDAO generoDAO = new GeneroDAO();
    
     public Genero getGenero(int id) throws ClassNotFoundException, SQLException {
        Genero gen = new Genero();
        gen = generoDAO.getGenero(id);
        if (gen == null) {
            return null;
        }

        return gen;
    }

    public ArrayList<Genero> getGenero() throws SQLException, ClassNotFoundException {
        ArrayList<Genero> lstGeneros = new ArrayList<Genero>();
        //getUsuarios
        lstGeneros = generoDAO.getGeneros();
        return lstGeneros;
    }

    public boolean deleteGenero(int id) throws SQLException, ClassNotFoundException {

        int rta = generoDAO.deleteGenero(id);

        return rta == 1 ? true : false;
    }
    
    public boolean updateGenero(Genero gen) throws SQLException, ClassNotFoundException {

        int rta = generoDAO.editGenero(gen);
        return rta == 1 ? true : false;
    }
    
}
