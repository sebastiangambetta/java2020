/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.AlquilerDAO;
import entities.Alquiler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class AlquilerUI {
    
    AlquilerDAO alquilerDAO = new AlquilerDAO();
    
     public Alquiler getAlquiler(int id) throws ClassNotFoundException, SQLException {
        Alquiler alq = new Alquiler();
        alq = alquilerDAO.getAlquiler(id);
        if (alq == null) {
            return null;
        }

        return alq;
    }

    public ArrayList<Alquiler> getAlquiler() throws SQLException, ClassNotFoundException {
        ArrayList<Alquiler> lstAlquileres = new ArrayList<Alquiler>();
        //getUsuarios
        lstAlquileres = alquilerDAO.getAlquileres();
        return lstAlquileres;
    }

    public boolean deleteAlquiler(int id) throws SQLException, ClassNotFoundException {

        int rta = alquilerDAO.deleteAlquiler(id);

        return rta == 1 ? true : false;
    }
    
    public boolean updateAlquiler(Alquiler alq) throws SQLException, ClassNotFoundException {

        int rta = alquilerDAO.editAlquiler(alq);
        return rta == 1 ? true : false;
    }
    
}
