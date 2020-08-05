/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Banco;
import data.BancoDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class BancoUI {

	BancoDAO bancoDAO = new BancoDAO();

	public Boolean addBanco(Banco banco) throws ClassNotFoundException, SQLException {

		if (bancoDAO.addBanco(banco)) {
			return true;

		} else {
			return false;
		}

	}

	public Banco getBanco(int id) throws ClassNotFoundException, SQLException {
		Banco tarjetaC = new Banco();
		tarjetaC = bancoDAO.getBanco(id);
		if (tarjetaC == null) {
			return null;
		}

		return tarjetaC;
	}

	public ArrayList<Banco> getBancos() throws SQLException, ClassNotFoundException {
		ArrayList<Banco> lstTarjetaC = new ArrayList<Banco>();
		lstTarjetaC = bancoDAO.getBanco();
		return lstTarjetaC;
	}

	public boolean deleteBanco(int id) throws SQLException, ClassNotFoundException {

		if (bancoDAO.deleteBanco(id)) {
			return true;

		} else {
			return false;
		}

	}

	public boolean updateBanco(Banco banco) throws SQLException, ClassNotFoundException {

		if(bancoDAO.editBanco(banco)) {
			return true;
			
		} else {
			return false;
		}		
	}

}
