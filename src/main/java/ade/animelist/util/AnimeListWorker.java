package ade.animelist.util;

import ade.animelist.components.CardCollection;
import ade.animelist.components.Dashboard;
import ade.animelist.controller.Controller;
import ade.animelist.database.entity.CardCollectionEntity;
import ade.animelist.database.repository.AddAnimeToDbRepository;
import ade.animelist.database.repository.AddAnimeToDbRepositoryImpl;
import net.sandrohc.jikan.model.anime.Anime;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AnimeListWorker extends SwingWorker<Void, Anime> {
    List<CardCollectionEntity> cardList = new ArrayList<>();

    private final List<CompletableFuture<Anime>> animeFutures;

    public AnimeListWorker(List<CompletableFuture<Anime>> animeFutures) {
        this.animeFutures = animeFutures;
    }

    @Override
    protected Void doInBackground() {
        animeFutures.forEach(future -> {
            try {
                Anime anime = future.join();
                publish(anime);
            } catch (Exception ex) {
                ex.printStackTrace(); // Handle exceptions appropriately
            }
        });
        return null;
    }

    @Override
    protected void process(List<Anime> chunks) {
        if (CardCollection.isOpened) {
            int i = 1;
            // This method is invoked on the EDT
            Controller.removeComponent(CardCollection.panel);
            CardCollection.removeCardPanel();

            System.out.println("APakah ke hapus");

            CardCollection.panel = CardCollection.getCardPanel();
            Controller.addComponent(CardCollection.panel);

            CardCollection.cardPanel.removeAll();
            System.out.println("Ngehapus card panel nih");
//        CardCollection.panel.removeAll();
            for (Anime anime : chunks) {
                // Update GUI components here
                ImageIcon tes = ImageRenderer.createImageIconByURL(anime.images.getJpg().largeImageUrl);
                System.out.println(tes.getIconWidth());
                System.out.println(anime.title);

//            CardCollection.addCard(anime.title, tes, anime.malId);
                cardList.add(new CardCollectionEntity(anime.title, tes, anime.malId));
                ++i;
            }
            System.out.println("lagi set index ke 0 nih");
            System.out.println(i);
//        if (CardCollection.totalAnime == i) {
//            System.out.println("yang bener ngeset index ke 0 ? " + CardCollection.totalAnime);
//            CardCollection.setIndex(0);
//        }

            AddAnimeToDbRepository listAnimeuser = new AddAnimeToDbRepositoryImpl();
            CardCollection.totalAnime = 0;
            listAnimeuser.getAllAnimeListInDatabaseUser().forEach(
                    maguire -> {
                        System.out.println();
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

    @Override
    protected void done() {
        // manfaatkan ini untuk resolve and fix gambar
        System.out.println("siuuuu");

        if (CardCollection.isOpened) {
            Controller.removeComponent(CardCollection.panel);
            CardCollection.removeCardPanel();

            System.out.println("APakah ke hapus");

            CardCollection.panel = CardCollection.getCardPanel();
            Controller.addComponent(CardCollection.panel);

            CardCollection.cardPanel.removeAll();

            cardList.forEach(e -> {
                CardCollection.addCard(e.getTitle(), e.getLargeImageOfAnime(), e.getId());
            });
            CardCollection.setIndex(0);
            CardCollection.countRefreshClicked = 0;
            CardCollection.isOpened = true;

            System.out.println("Logika in done");
        }

//        Dashboard.dashboardDiv.removeAll();
//        Controller.removeComponent(Dashboard.dashboardDiv);
//        Dashboard.isOpened = false;
    }
}
