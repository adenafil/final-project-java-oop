package ade.myanimelist.anime.entity;

public enum StatusWatching {
    WATCHING("Watching"),
    COMPLETED("Completed"),
    DROPPED("Dropped"),
    PLANTOWATCH("Plan to Watch");


    private final String description;

    StatusWatching(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

