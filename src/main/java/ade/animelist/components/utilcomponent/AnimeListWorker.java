package ade.animelist.components.utilcomponent;

import ade.animelist.components.fhd.CardCollection;
import ade.animelist.controller.Controller;
import ade.animelist.components.entity.CardCollectionEntity;
import ade.animelist.database.repository.AddAnimeToDbRepository;
import ade.animelist.database.repository.AddAnimeToDbRepositoryImpl;
import net.sandrohc.jikan.model.anime.Anime;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Class untuk mendapakan list collection card dari database kemudian
 * ditampilkan ke gui secara async yang mana extends dari class SwingWorker
 */
public class AnimeListWorker extends SwingWorker<Void, Anime> {
    List<CardCollectionEntity> cardList = new ArrayList<>();

    private final List<CompletableFuture<Anime>> animeFutures;

    public AnimeListWorker(List<CompletableFuture<Anime>> animeFutures) {
        this.animeFutures = animeFutures;
    }

    /**
     * Method untuk mendapakan anime future
     * @return null
     */
    @Override
    protected Void doInBackground() {
        animeFutures.forEach(future -> {
            try {
                Anime anime = future.join();
                publish(anime);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return null;
    }

    /**
     * Method untuk mengprocess hasil do in background
     * @param chunks intermediate results to process
     *
     */
    @Override
    protected void process(List<Anime> chunks) {
        if (CardCollection.isOpened) {
            int i = 1;
            Controller.removeComponent(CardCollection.panel);
            CardCollection.removeCardPanel();


            CardCollection.panel = CardCollection.getCardPanel();
            Controller.addComponent(CardCollection.panel);

            CardCollection.cardPanel.removeAll();
            for (Anime anime : chunks) {
                // Update GUI components here
                ImageIcon tes = ImageRenderer.createImageIconByURL(anime.images.getJpg().largeImageUrl);
                cardList.add(new CardCollectionEntity(anime.title, tes, anime.malId));
                ++i;
            }

            AddAnimeToDbRepository listAnimeuser = new AddAnimeToDbRepositoryImpl();
            CardCollection.totalAnime = 0;
            listAnimeuser.getAllAnimeListInDatabaseUser().forEach(
                    maguire -> {
                        CardCollection.addCard(
                                maguire.title,
                                null,
                                maguire.malId
                        );
                        ++CardCollection.totalAnime;
                    }
            );
        }
    }

    /**
     * method untuk melakukan hal yang sudah diprocess
     * Jika process sudah maka bole tampilkan gambar
     */
    @Override
    protected void done() {
        // manfaatkan ini untuk resolve and fix gambar

        if (CardCollection.isOpened) {
            Controller.removeComponent(CardCollection.panel);
            CardCollection.removeCardPanel();


            CardCollection.panel = CardCollection.getCardPanel();
            Controller.addComponent(CardCollection.panel);

            CardCollection.cardPanel.removeAll();

            Counter.getStartedUsingIncrement();

            cardList.forEach(e -> {

                if (Counter.a <= 5) {
                    CardCollection.panel.getComponent(2).setPreferredSize(new Dimension(1920, 400));
                    CardCollection.panel.revalidate();
                    CardCollection.panel.repaint();
                } else {
                    CardCollection.panel.getComponent(2).setPreferredSize(new Dimension(1920, 840));
                    CardCollection.panel.revalidate();
                    CardCollection.panel.repaint();
                }

                CardCollection.addCard(e.getTitle(), e.getLargeImageOfAnime(), e.getId());
                Counter.incremennt();
            });
            CardCollection.setIndex(0);
            CardCollection.countRefreshClicked = 0;
            CardCollection.isOpened = true;
        }

    }
}
