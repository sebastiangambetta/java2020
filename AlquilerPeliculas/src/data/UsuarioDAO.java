/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import entities.Usuario;
import entities.Socio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class UsuarioDAO extends Conexion {

    Connection conn = null;

    public void addUsuario(Usuario user, Socio socio) throws SQLException {
        conn = this.getConnection();
        PreparedStatement st = null;
        int rowAffected;
        ResultSet rs = null;
        try {
            
            
            conn.setAutoCommit(false); 
            
            String query = "insert into socio(nombre, apellido, domicilio, telefono, nroTarjeta, estado) "
                    + "values(?, ?, ?, ?, ?, ?)";
            
            st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, socio.getNombre());
            st.setString(2, socio.getApellido());
            st.setString(3, socio.getDomicilio());
            st.setString(4, socio.getTelefono());
            if(socio.getNroTarjeta() == null)
            {
                st.setNull(5, java.sql.Types.INTEGER);
            }
            else
            {
                st.setInt(5, socio.getNroTarjeta());
            }
            
            st.setString(6, socio.getEstado());
            
            rowAffected = st.executeUpdate();

            //get candidate id
            rs = st.getGeneratedKeys();
            
            //Verificar
            st.close();
            int candidateId = 0;
            if (rs.next()) {
                candidateId = rs.getInt(1);
            }

            // in case the insert operation successes, assign skills to candidate
            if (rowAffected == 1) {

                user.setIdUsuario(candidateId);

                st = conn.prepareStatement("insert into usuario(idUsuario, email, contrasena) values(?, ?, ?)");

                st.setInt(1, user.getIdUsuario());
                st.setString(2, user.getEmail());
                st.setString(3, user.getContrasena());
                
                rowAffected = st.executeUpdate();
                
                if (rowAffected != 1)
                    throw new SQLException();                
                
            }

            conn.commit();
            st.close();
            conn.close();

        } catch (SQLException ex) {
            conn.rollback();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException ex) {
                System.out.printf(ex.getMessage());
            }
        }

    }

    public int deleteUsuario(int idUsuario) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("delete from usuario where idUsuario = ? ");
        stmt.setInt(1, Integer.valueOf(idUsuario));
        int rta = stmt.executeUpdate();
        
        if(rta == 1)
        {
            stmt = conn.prepareStatement("delete from socio where nroSocio = ? ");
            stmt.setInt(1, Integer.valueOf(idUsuario));
            rta = stmt.executeUpdate();
        }
        
        return rta;

    }

    public int editUsuario(Usuario user) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("update usuario set email = ?, contrasena = ?"
                + " where idUsuario = ?");
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getContrasena());
        stmt.setInt(3, Integer.valueOf(user.getIdUsuario()));
        int rta = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return rta;
    }

    public Usuario Login(String email, String contrasena) throws ClassNotFoundException, SQLException {
        Usuario user = new Usuario();
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from usuario where email = ? and contrasena=?");
        stmt.setString(1, email);
        stmt.setString(2, contrasena);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            do {
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setEmail(rs.getString("email"));
                user.setContrasena(rs.getString("contrasena"));
            } while (rs.next());
        }

        rs.close();
        conn.close();
        return user;

    }

    public Usuario getUsuario(int id) throws ClassNotFoundException, SQLException {
        Usuario user = new Usuario();
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from usuario where idUsuario = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            do {
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setEmail(rs.getString("email"));
                user.setContrasena(rs.getString("contrasena"));
            } while (rs.next());
        }

        rs.close();
        conn.close();
        return user;

    }

    public ArrayList<Usuario> getUsuarios() throws SQLException, ClassNotFoundException {
        ArrayList<Usuario> lstUsuarios = new ArrayList<Usuario>();

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from usuario");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Usuario user = new Usuario();

            user.setIdUsuario(rs.getInt("idUsuario"));
            user.setEmail(rs.getString("email"));
            user.setContrasena(rs.getString("contrasena"));

            lstUsuarios.add(user);
        }

        rs.close();
        conn.close();

        return lstUsuarios;
    }

}
