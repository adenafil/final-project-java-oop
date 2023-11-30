package ade.myanimelist.anime.entity;

import java.time.OffsetDateTime;

public class Aired {
    OffsetDateTime from;
    OffsetDateTime to;

    public OffsetDateTime getFrom() {
        return from;
    }

    public void setFrom(OffsetDateTime from) {
        this.from = from;
    }

    public OffsetDateTime getTo() {
        return to;
    }

    public void setTo(OffsetDateTime to) {
        this.to = to;
    }

    public String getAired() {
        return from.getMonth() + " " + from.getDayOfMonth() + ", " + from.getYear() +
                " to " + to.getMonth() + " " + to.getDayOfMonth() + ", " + to.getYear();
    }
}
