/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import entities.VideoClub;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class VideoClubDAO extends Conexion {

    Connection conn = null;

    public void addVideo(VideoClub video) throws SQLException {

        conn = this.getConnection();
        PreparedStatement st = conn.prepareStatement("insert into videoclub values(?, ?, ?)");

        st.setFloat(1, video.getImportePorDia());
        st.setInt(2, video.getPlazoMaxADevolver());
        st.setInt(3, video.getCantMaxPelPendientes());

        st.executeUpdate();

        st.close();
        conn.close();
    }

    public int deleteVideo(int Id) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("delete from videoclub where id = ? ");
        stmt.setInt(1, Integer.valueOf(Id));
        int rta = stmt.executeUpdate();
        return rta;

    }

    public int editVideo(VideoClub video) throws ClassNotFoundException, SQLException {

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("update videoclub set importePorDia = ?, plazoMaxDevolver = ?"
                + ", cantMaxPelPendientes = ? where id = ?");
        stmt.setFloat(1, video.getImportePorDia());
        stmt.setInt(2, video.getPlazoMaxADevolver());
        stmt.setInt(3, video.getCantMaxPelPendientes());
        stmt.setFloat(4, video.getId());
        int rta = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return rta;
    }

    public VideoClub getVideo(int id) throws ClassNotFoundException, SQLException {
        VideoClub video = new VideoClub();
        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from videoclub where id = ?");
        stmt.setFloat(1, id);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            do {
                video.setId(rs.getInt("id"));
                video.setImportePorDia(rs.getFloat("importePorDia"));
                video.setCantMaxPelPendientes(rs.getInt("cantMaxPelPendientes"));
                video.setPlazoMaxADevolver(rs.getInt("plazoMaxADevolver"));
            } while (rs.next());
        }

        rs.close();
        conn.close();
        return video;

    }

    public ArrayList<VideoClub> getVideos() throws SQLException, ClassNotFoundException {
        ArrayList<VideoClub> lstVideos = new ArrayList<VideoClub>();

        conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from usuario");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            VideoClub video = new VideoClub();

            video.setId(rs.getInt("id"));
            video.setImportePorDia(rs.getFloat("importePorDia"));
            video.setPlazoMaxADevolver(rs.getInt("plazoMaxADevolver"));
            video.setCantMaxPelPendientes(rs.getInt("cantMaxPelPendientes"));

            lstVideos.add(video);
        }

        rs.close();
        conn.close();

        return lstVideos;
    }

}
