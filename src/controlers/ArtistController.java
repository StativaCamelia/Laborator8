package controlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistController {
    private Connection con;
    public ArtistController(Connection con){
        this.con = con;
    }

    public void create(String name, String country){
        try {
            PreparedStatement pstm = con.prepareStatement("Insert into Artists (name, country) values(?,?)");
            pstm.setString(1,name);
            pstm.setString(2,country);
            pstm.execute();
            pstm.close();
        }
        catch (SQLException e){
            System.out.println("Nu s-a putut realiza adaugarea unui nou artist");
            e.printStackTrace();
        }
    }

    public void findByName(String name){
        try {
            PreparedStatement pstm = con.prepareStatement("Select * from Artists where name = ?");
            pstm.setString(1,name);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
                String artistName = rs.getString("name");
                String artistCountry  = rs.getString("country");
                System.out.println("Artistul cu numele: " + artistName + " din tara:" + artistCountry);
            }
            rs.close();
        }
        catch (SQLException e){
            System.out.println("Nu s-a putut realiza adaugarea unui nou artist");
            e.printStackTrace();
        }
    }

    public int getMinId(){
        int minId = 1;
        try {
            PreparedStatement pstm = con.prepareStatement("Select min(id) from Artists");
            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                minId = rs.getInt(1);
            }

            rs.close();

        }
        catch (SQLException e){
            System.out.println("Nu s-a putut realiza adaugarea unui nou artist");
            e.printStackTrace();
        }
        return minId;
    }

    public int getMaxId(){
        int minId = 1;
        try {
            PreparedStatement pstm = con.prepareStatement("Select max(id) from Artists");
            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                minId = rs.getInt(1);
            }

            rs.close();

        }
        catch (SQLException e){
            System.out.println("Nu s-a putut realiza adaugarea unui nou artist");
            e.printStackTrace();
        }
        return minId;
    }
}
