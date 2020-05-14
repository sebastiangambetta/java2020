/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.TarjetaCredito;
import data.TarjetaCreditoDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class TarjetaCreditoUI {
    
     TarjetaCreditoDAO tarjetaDAO = new TarjetaCreditoDAO();

    public TarjetaCredito getTarjetaC(int id) throws ClassNotFoundException, SQLException {
        TarjetaCredito tarjetaC = new TarjetaCredito();
        tarjetaC = tarjetaDAO.getAlquiler(id);
        if (tarjetaC == null) {
            return null;
        }

        return tarjetaC;
    }

    public ArrayList<TarjetaCredito> getTarjetaC() throws SQLException, ClassNotFoundException {
        ArrayList<TarjetaCredito> lstTarjetaC = new ArrayList<TarjetaCredito>();
        //getUsuarios
        lstTarjetaC = tarjetaDAO.getAlquileres();
        return lstTarjetaC;
    }

    public boolean deleteTarjetaC(int id) throws SQLException, ClassNotFoundException {

        int rta = tarjetaDAO.deleteAlquiler(id);

        return rta == 1 ? true : false;
    }

    public boolean updateTarjetaC(TarjetaCredito tarjeta) throws SQLException, ClassNotFoundException {

        int rta = tarjetaDAO.editAlquiler(tarjeta);
        return rta == 1 ? true : false;
    }

    
}
