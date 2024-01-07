package ade.animelist.components.entity;
import javax.swing.*;

/**
 * Class untuk menampung collection card pada JScrollPAne
 */
public class CardCollectionEntity {
    // Judul anime
    String title;
    // Gambar anime
    ImageIcon largeImageOfAnime;
    // id MAL
    int id;
    // Constructor
    public CardCollectionEntity() {
    }

    /**
     * Constructor
     * @param title -> judul anime
     * @param largeImageOfAnime -> gambar anime
     * @param id -> id anime mal
     */
    public CardCollectionEntity(String title, ImageIcon largeImageOfAnime, int id) {
        this.title = title;
        this.largeImageOfAnime = largeImageOfAnime;
        this.id = id;
    }

    /**
     * Method mengembalikan judul anime
     * @return -> judul anime
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method untuk mengset judul anime
     * @param title -> judul anime
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method untuk mengapakan gambar anime
     * @return -> Gambar anime
     */
    public ImageIcon getLargeImageOfAnime() {
        return largeImageOfAnime;
    }

    /**
     * Method untuk mengset gambar anime
     * @param largeImageOfAnime -> Gambar anime
     */
    public void setLargeImageOfAnime(ImageIcon largeImageOfAnime) {
        this.largeImageOfAnime = largeImageOfAnime;
    }

    /**
     * Method untuk mendapakan id anime
     * @return -> Id anime mal
     */
    public int getId() {
        return id;
    }

    /**
     * Method untuk mengset id mal
     * @param id -> id mal
     */
    public void setId(int id) {
        this.id = id;
    }
}
