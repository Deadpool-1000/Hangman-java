package handlers.leaderboard;

import models.LeaderboardUser;
import utils.database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LeaderboardHandler {
    private static final Connection conn = DatabaseConnectionManager.getInstance().conn;
    public static final String GET_LEADERBOARD_QUERY = "SELECT a.username,p.high_score,p.hs_scored_on from players_data p INNER JOIN auth a ON p.user_id=a.user_id ORDER BY high_score DESC,hs_scored_on DESC LIMIT 10";


    public static ArrayList<LeaderboardUser> getLeaderboard(){
        try{
            PreparedStatement leaderboardQuery = conn.prepareStatement(GET_LEADERBOARD_QUERY);
            ResultSet rs = leaderboardQuery.executeQuery();
            ArrayList<LeaderboardUser> leaderboard = new ArrayList<>();
            while(rs.next()){
                leaderboard.add(new LeaderboardUser(rs.getString("username"), rs.getDouble("high_score"), rs.getTimestamp("hs_scored_on")));
            }
            return leaderboard;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
