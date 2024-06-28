package models;

public class GamePlaySummary {
    private final double score;
    private final int totalGames;
    private final int wonGames;

    public GamePlaySummary(double score, int totalGames, int wonGames) {
        this.score = score;
        this.totalGames = totalGames;
        this.wonGames = wonGames;
    }

    public double getScore() {
        return score;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public int getWonGames() {
        return wonGames;
    }
}
