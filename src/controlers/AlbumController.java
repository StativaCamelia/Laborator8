package controlers;

import java.sql.*;

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
            System.out.println("Nu s-a putut realiza adaugarea unui nou artist");
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
            System.out.println("Nu s-a putut realiza adaugarea unui nou artist");
            e.printStackTrace();
        }
    }


}
