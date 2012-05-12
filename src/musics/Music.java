package musics;

import users.Producer;

public class Music {
    
    private Producer producer;
    private String name;
    private String genre;
    private String album;
    private String artist;
    private int price;
    private int popularity;

    private static void validateInformations(String name, String genre, String album, String artist, String price) throws Exception {
        if (name.isEmpty() || genre.isEmpty() || album.isEmpty() || artist.isEmpty() || price.isEmpty()) {
            throw new Exception("Todos os atributos devem estar preenchidos");
        }
        try {
            if (Integer.parseInt(price) < 0) {
                throw new NumberFormatException("-");
            }
        } catch (NumberFormatException n) {
            throw new Exception("O preço é inválido");
        }
    }
    
    public Music(Producer producer, String name, String genre, String album, String artist, String price) throws Exception {
        validateInformations(name, genre, album, artist, price);
        this.popularity = 0;
        this.producer = producer;
        this.name = name;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
        this.price = Integer.parseInt(price);
    }
    
    public void change(String name, String genre, String album, String artist, String price) throws Exception {
        validateInformations(name, genre, album, artist, price);
        this.setName(name);
        this.setGenre(genre);
        this.setAlbum(album);
        this.setArtist(artist);
        this.setPrice(Integer.parseInt(price));
    }
    
    public Comparable mapField(String field) {
        return  field.equals("name")  ? this.getName()  :
                field.equals("album") ? this.getAlbum() :
                field.equals("artist")? this.getArtist():
                field.equals("genre") ? this.getGenre() :
                field.equals("price") ? new Integer(-this.getPrice()) :
                field.equals("popularity") ? new Integer(-this.getPopularity()) :
                "";
    }
    
    public String mapFieldForFilter(String field) {
        return mapField(field)+"";
    }
    
    public boolean match(String field, String keywords) {
        return mapFieldForFilter(field).toUpperCase().contains(keywords.toUpperCase());
    }
    
    public void increasePopularity() {
        this.setPopularity(this.getPopularity() + 1);
    }
    
    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getPrice() {
        return price;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
