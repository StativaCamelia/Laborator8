package model;

public class Album {
    String name;
    int artistId;
    int releaseYear;

    public Album(String name, int artistId, int releaseYear){
        this.name = name;
        this.artistId = artistId;
        this.releaseYear = releaseYear;
    }

    public Album(String name, int artistId){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
