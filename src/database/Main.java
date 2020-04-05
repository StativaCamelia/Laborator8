package database;

import com.github.javafaker.Faker;
import controlers.AlbumController;
import controlers.ArtistController;
import model.Album;
import model.Artist;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

            artistController.findByName(name);

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
            albumController.findByArtistId(artistIdSearch);
                musicAlbumDb.closeConnection();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
}
