package ade.animelist.util;

import ade.animelist.components.CardCollection;
import net.sandrohc.jikan.model.anime.Anime;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AnimeListWorker extends SwingWorker<Void, Anime> {

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
        // This method is invoked on the EDT
        for (Anime anime : chunks) {
            // Update GUI components here
            ImageIcon tes = ImageRenderer.createImageIconByURL(anime.images.getJpg().largeImageUrl);
            System.out.println(tes.getIconWidth());
            System.out.println(anime.title);

            CardCollection.addCard(anime.title, tes, anime.malId);
        }
    }
}
