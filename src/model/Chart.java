package model;

public class Chart {
    int id;
    String[] albumList;

    Chart(int id, String[] albumList){
        this.id = id;
        this.albumList = albumList;
    }

    public Chart(String[] albumList){
        this.albumList = albumList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String[] getAlbumList() {
        return albumList;
    }

    public void setAlbumList(String[] albumList) {
        this.albumList = albumList;
    }
}
