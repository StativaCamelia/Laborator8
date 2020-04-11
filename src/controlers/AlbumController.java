package controlers;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlbumController {
    private Connection con;
    public AlbumController(Connection con){
            this.con = con;
    }

    public void create(String name, int artistId, int releaseYear){
        try {
            PreparedStatement pstm = con.prepareStatement("Insert into Albums (name, artist_id, release_year) values(?,?,?)");
            pstm.setString(1,name);
            pstm.setInt(2,artistId);
            pstm.setInt(3,releaseYear);
            pstm.execute();
            pstm.close();
        }
        catch (SQLException e){
            System.out.println("Nu s-a putut realiza adaugarea unui nou album");
            e.printStackTrace();
        }
    }

    public void findByArtistId(int artist_id){
        try {
            PreparedStatement pstm = con.prepareStatement("Select * from Albums where artist_id = ?");
            pstm.setInt(1,artist_id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
                String albumName = rs.getString("name");
                int artistId  = rs.getInt("artist_id");
                int releaseYear = rs.getInt("release_year");
                System.out.println("Albumul cu numele: " + albumName + " al artistului cu Id-ul:" + artist_id + " aparut in data de:" + releaseYear);
            }
            rs.close();
        }
        catch (SQLException e){
            System.out.println("Nu s-a putut gasi albumul");
            e.printStackTrace();
        }
    }

    private int getNumberOfAlbums(){
        int numberOfAlbums = 1;
        try {
            PreparedStatement pstm = con.prepareStatement("Select count(*) from Albums");
            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                numberOfAlbums = rs.getInt(1);
            }

            rs.close();

        }
        catch (SQLException e){
            System.out.println("Nu s-a obtine numarul de albume");
            e.printStackTrace();
        }
        return numberOfAlbums;
    }

    public String getAlbumNameByID(int id){
        String albumName = new String();
        try {
            PreparedStatement pstm = con.prepareStatement("Select * from Albums where id = ?");
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
                albumName = rs.getString("name");

            }
            rs.close();
        }
        catch (SQLException e){
            System.out.println("Nu se poate extrage albumul cu id-ul dat");
            e.printStackTrace();
        }
        return albumName;
    }

    private int getMinId(){
        int minId = 1;
        try {
            PreparedStatement pstm = con.prepareStatement("Select min(id) from Albums");
            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                minId = rs.getInt(1);
            }

            rs.close();

        }
        catch (SQLException e){
            System.out.println("Nu se poate obtine id-ul minim");
            e.printStackTrace();
        }
        return minId;
    }

    private int getMaxId(){
    int maxId = 1;
        try {
            PreparedStatement pstm = con.prepareStatement("Select max(id) from Artists");
            ResultSet rs = pstm.executeQuery();
        while(rs.next()) {
            maxId = rs.getInt(1);
        }
        rs.close();
        }
            catch (SQLException e){
            System.out.println("Nu s-a putut obtine id-ul maxim");
            e.printStackTrace();
        }
            return maxId;
        }


    public String[] getListOfAlbums(){
        int numberOfAlbums = 10;
        int albumId;
        int minId = getMinId();
        int maxId = getMaxId();
        List<String> albums = new ArrayList<>();
        String[] albumList = new String[numberOfAlbums];
        int i=0;
        while(i<numberOfAlbums){
            albumId = (int)(Math.random() * (maxId - minId ));
            if(!albums.contains(this.getAlbumNameByID(albumId))) {
                albums.add(this.getAlbumNameByID(albumId));
                i++;
            }
        }
        Arrays.sort(albumList);
        return albumList;
    }

    public String getArtistIdbyAlbum(String albumName){
        int artistId = 0;
        String artistName = new String();
        try {
            PreparedStatement pstm = con.prepareStatement("Select * from Albums where name = ?");
            pstm.setString(1,albumName);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
                artistId  = rs.getInt("artist_id");
            }
            ArtistController artistController = new ArtistController(this.con);
            artistName = artistController.getNameById(artistId);
            rs.close();
        }
        catch (SQLException e){
            System.out.println("Nu s-a putut gasi artistul");
            e.printStackTrace();
        }
        return  artistName;
    }


}
