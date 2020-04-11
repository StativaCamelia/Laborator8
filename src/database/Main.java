package database;

import com.github.javafaker.Faker;
import controlers.AlbumController;
import controlers.ArtistController;
import controlers.ChartController;
import model.Album;
import model.Artist;
import model.Chart;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public class Main {
    public static void main(String[] args){
        try {
            Database musicAlbumDb = Database.getInstance();
            Statement stmt = musicAlbumDb.con.createStatement();

            ArtistController artistController = new ArtistController(musicAlbumDb.con);
            AlbumController albumController = new AlbumController(musicAlbumDb.con);
            String name = new String();
             for (int i = 1; i <=20; i++) {
                        Faker faker = new Faker();
                        Artist newArtist =new Artist(faker.name().name(), faker.address().country());
                        artistController.create(newArtist.getName(), newArtist.getCountry());
                        name = newArtist.getName();
             }
             int minIdArtist = artistController.getMinId();
             int maxIdArtist = artistController.getMaxId();
             int artistIdSearch = 1;

            for(int i = 1; i<=35; i++){
                Faker faker = new Faker();
                int artistId = faker.number().numberBetween(minIdArtist, maxIdArtist);
                Album newAlbum = new Album(faker.pokemon().name(), artistId, faker.number().numberBetween(1980,2020));
                albumController.create(newAlbum.getName(), newAlbum.getArtistId(), newAlbum.getReleaseYear());
                artistIdSearch = artistId;
            }
            ChartController chartController = new ChartController(musicAlbumDb.con);

            for(int i = 0; i<=10 ;i++) {
                Chart newChart = new Chart(albumController.getListOfAlbums());
                chartController.create(newChart.getAlbumList());
            }
            System.out.println("Chartul cu ID-ul:3");
            chartController.printChart(3);

            System.out.println("Rank-ul bazat de chart-ul cu ID-ul: 3");
            List<String> rank = chartController.artistRanking(3);
            int position = 1;
            for(String artist: rank){
                System.out.println("Locul " + position + ":" + artist);
                position++;
            }
            musicAlbumDb.closeConnection();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
}
