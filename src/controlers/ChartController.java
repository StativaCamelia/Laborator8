package controlers;

import oracle.jdbc.internal.OraclePreparedStatement;
import oracle.jdbc.internal.OracleResultSet;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ChartController{

    private Connection con;
    public ChartController(Connection con){
        this.con = con;
    }

    public void create(String[] albumList){
        try {
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor
                    ("ALBUMS_LIST", con);
            ARRAY albums = new ARRAY(desc, con, albumList);
            PreparedStatement ps =
                    con.prepareStatement ("insert into charts (CHART) values (?)");
            ((OraclePreparedStatement)ps).setARRAY (1, albums);
            ps.execute ();
        }
        catch (SQLException e){
            System.out.println("Nu s-a putut realiza adaugarea unui nou chart");
            e.printStackTrace();
        }
    }
    public void printChart(int id){
        try {
            AlbumController albumController = new AlbumController(this.con);
            PreparedStatement pstm = con.prepareStatement("Select * from Charts where id = ?");
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor
                    ("ALBUMS_LIST", con);
            ARRAY albums = new ARRAY(desc, con, null);
            while(rs.next()) {
                albums = ((OracleResultSet)rs).getARRAY (2);
            }
            String[] albumsList = (String[]) albums.getArray();
            for(int i = 0 ; i < albumsList.length;i++){
                System.out.println(albumsList[i] + " al artistului: " + albumController.getArtistIdbyAlbum(albumsList[i]));
            }
        }
        catch (SQLException e){
            System.out.println("Nu s-a putut gasi chart-ul");
            e.printStackTrace();
        }
    }

    public String[] findChartById(int id){
        try {
            PreparedStatement pstm = con.prepareStatement("Select * from Charts where id = ?");
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor
                    ("ALBUMS_LIST", con);
            ARRAY albums = new ARRAY(desc, con, null);
            while(rs.next()) {
                albums = ((OracleResultSet)rs).getARRAY (2);
            }
            String[] albumsList = (String[]) albums.getArray();
            rs.close();
            return albumsList;
        }
        catch (SQLException e){
            System.out.println("Nu s-a putut gasi chart-ul");
            e.printStackTrace();
        }
        return null;
    }

    public List<String> artistRanking(int id){
        String[] chartRanked = this.findChartById(id);
        List<String> rank = new ArrayList<>();
        AlbumController albumController = new AlbumController(this.con);
        String artist;
        for(int i = 0 ; i < chartRanked.length;i++){
               artist = albumController.getArtistIdbyAlbum(chartRanked[i]);
               if(!rank.contains(artist))
                rank.add(artist);
        }
        return rank;
    }

}
