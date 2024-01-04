package ade.animelist.database.entity;

import javax.swing.*;

public class CardCollectionEntity {
    String title;
    ImageIcon largeImageOfAnime;
    int id;
    public CardCollectionEntity() {
    }

    public CardCollectionEntity(String title, ImageIcon largeImageOfAnime, int id) {
        this.title = title;
        this.largeImageOfAnime = largeImageOfAnime;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageIcon getLargeImageOfAnime() {
        return largeImageOfAnime;
    }

    public void setLargeImageOfAnime(ImageIcon largeImageOfAnime) {
        this.largeImageOfAnime = largeImageOfAnime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
