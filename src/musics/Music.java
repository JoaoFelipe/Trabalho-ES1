/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package musics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import users.Producer;

/**
 *
 * @author Joao
 */
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
            Integer.parseInt(price);
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
        this.name = name;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
        this.price = Integer.parseInt(price);
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

    public void increasePopularity() {
        this.popularity += 1;
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
    
    public static List<Music> filterMusicList(List<Music> list, String field, String keywords) {
        List<Music> result = new ArrayList<Music>();
        for (Music music : list) {
            if (music.match(field, keywords)) {
                result.add(music);
            }
        }
        return result;
    }
    
    public static List<Music> sortMusicList(List<Music> list, final String field) {
        List<Music> result = new ArrayList(list);
        Collections.sort(result, new Comparator<Music>(){

            public int compare(Music o1, Music o2) {
                return o1.mapField(field).compareTo(o2.mapField(field));
            }
     
        });
        return result;
    }
    
}
