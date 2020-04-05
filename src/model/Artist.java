package model;

public class Artist {
    String name;
    String country;

    public Artist(String name, String country){
        this.name = name;
        this.country = country;
    }

    Artist(String name){
        this.name = name;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
