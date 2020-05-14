/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.SocioDAO;
import entities.Socio;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class SocioUI {
    
    SocioDAO socioDAO = new SocioDAO();
    
     public void addSocio(Socio user) throws SQLException{                
        socioDAO.addSocio(user);        
    }
    
     public Socio getSocio(int id) throws ClassNotFoundException, SQLException {
        Socio socio = new Socio();
        socio = socioDAO.getSocio(id);
        if (socio == null) {
            return null;
        }

        return socio;
    }

    public ArrayList<Socio> getSocio() throws SQLException, ClassNotFoundException {
        ArrayList<Socio> lstSocios = new ArrayList<Socio>();
        //getUsuarios
        lstSocios = socioDAO.getSocios();
        return lstSocios;
    }

    public boolean deleteSocio(int id) throws SQLException, ClassNotFoundException {

        int rta = socioDAO.deleteSocio(id);

        return rta == 1 ? true : false;
    }
    
    public boolean updateSocio(Socio soc) throws SQLException, ClassNotFoundException {

        int rta = socioDAO.editSocio(soc);
        return rta == 1 ? true : false;
    }
    
}
