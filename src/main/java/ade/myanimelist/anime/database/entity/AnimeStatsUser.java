package ade.myanimelist.anime.database.entity;

public class AnimeStatsUser {
    private Anime anime;
    private StatusWatching statusWatching;
    private int currentEpisode;
    private int maxEpisode;

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public StatusWatching getStatusWatching() {
        return statusWatching;
    }

    public void setStatusWatching(StatusWatching statusWatching) {
        this.statusWatching = statusWatching;
    }

    public int getCurrentEpisode() {
        return currentEpisode;
    }

    public void setCurrentEpisode(int currentEpisode) {
        this.currentEpisode = currentEpisode;
    }

    public int getMaxEpisode() {
        return maxEpisode;
    }

    public void setMaxEpisode(int maxEpisode) {
        this.maxEpisode = maxEpisode;
    }
}
