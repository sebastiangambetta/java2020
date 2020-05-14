/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.VideoClubDAO;
import entities.VideoClub;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giuli
 */
public class VideoClubUI {

    VideoClubDAO videoDAO = new VideoClubDAO();

    public VideoClub getVideo(int id) throws ClassNotFoundException, SQLException {
        VideoClub video = new VideoClub();
        video = videoDAO.getVideo(id);
        if (video == null) {
            return null;
        }

        return video;
    }

    public ArrayList<VideoClub> getVideos() throws SQLException, ClassNotFoundException {
        ArrayList<VideoClub> lstVideos = new ArrayList<VideoClub>();
        //getUsuarios
        lstVideos = videoDAO.getVideos();
        return lstVideos;
    }

    public boolean deleteVideo(int id) throws SQLException, ClassNotFoundException {

        int rta = videoDAO.deleteVideo(id);

        return rta == 1 ? true : false;
    }

    public boolean updateVideo(VideoClub video) throws SQLException, ClassNotFoundException {

        int rta = videoDAO.editVideo(video);
        return rta == 1 ? true : false;
    }

}
