package ade.myanimelist.anime.entity;

public class AnimeStatsUser {
    private Anime anime;
    private StatusWatching statusWatching;

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
}
