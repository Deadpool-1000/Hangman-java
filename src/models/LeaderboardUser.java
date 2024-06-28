package models;

import java.sql.Timestamp;

public class LeaderboardUser {
    private final String username;
    private final double high_score;
    private final Timestamp hsScoredOn;

    public LeaderboardUser(String username, double high_score, Timestamp hsScoredOn) {
        this.username = username;
        this.high_score = high_score;
        this.hsScoredOn = hsScoredOn;
    }

    public String getUsername() {
        return username;
    }

    public double getHigh_score() {
        return high_score;
    }

    public Timestamp getHsScoredOn() {
        return hsScoredOn;
    }
}
