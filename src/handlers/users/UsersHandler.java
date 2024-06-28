package handlers.users;


import models.User;
import utils.password.Password;
import utils.database.DatabaseConnectionManager;

import java.sql.*;
import java.util.UUID;



public class UsersHandler {
    private static final Connection conn = DatabaseConnectionManager.getInstance().conn;
    private static final String LOGIN_QUERY = "SELECT * FROM auth WHERE username = ?";
    private static final String SIGNUP_AUTH_QUERY = "INSERT INTO auth VALUES(?,?,?,?,?)";
    private static final String SIGNUP_PLAYER_QUERY = "INSERT INTO players_data VALUES (?, 0.0, ?, 0, 0)";
    private static final String PROFILE_QUERY = "SELECT p.user_id , a.username, p.high_score, p.hs_scored_on, p.total_games, p.won_games, a.role  FROM players_data p NATURAL JOIN auth a WHERE user_id = ?";
    private static final String SET_PROFILE_QUERY = "UPDATE players_data p SET p.high_score=?, p.hs_scored_on=?, p.total_games=?, p.won_games=? WHERE p.user_id=?";

    public static String login(String username, String password) throws Exception{
        PreparedStatement stmt = conn.prepareStatement(LOGIN_QUERY);
        stmt.setObject(1, username);
        ResultSet rs = stmt.executeQuery();
        rs.next();

        String storedPassword = rs.getString("password");
        byte[] salt = rs.getBytes("salt");
        String user_id = rs.getString("user_id");

        boolean isCorrect = Password.verifyPassword(password, storedPassword, salt);
        return isCorrect ? user_id: null;
    }

    public static void signup(String username, String password){
        try{
            String user_id = UUID.randomUUID().toString();
            Object[] passwordComponents = Password.hashPassword(password);

            PreparedStatement authStmt = conn.prepareStatement(SIGNUP_AUTH_QUERY);
            authStmt.setObject(1, user_id);
            authStmt.setObject(2, username);
            authStmt.setObject(3, passwordComponents[0]);
            authStmt.setObject(4, passwordComponents[1]);
            authStmt.setObject(5, "player");

            PreparedStatement playerStmt = conn.prepareStatement(SIGNUP_PLAYER_QUERY);
            playerStmt.setObject(1, user_id);
            playerStmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

            authStmt.execute();
            playerStmt.execute();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static User getProfile(String user_id){
        try{
            PreparedStatement stmt = conn.prepareStatement(PROFILE_QUERY);
            stmt.setObject(1, user_id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            String userId = rs.getString("user_id");
            String role = rs.getString("role");
            String username = rs.getString("username");
            float high_score = rs.getFloat("high_score");
            int totalGames = rs.getInt("total_games");
            int wonGames = rs.getInt("won_games");
            Timestamp hs_scored_on = rs.getTimestamp("hs_scored_on");

            return new User(userId, username, high_score, totalGames, wonGames, hs_scored_on, role);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void saveProfile(User user){
        try{
            PreparedStatement profileUpdateQuery = conn.prepareStatement(SET_PROFILE_QUERY);
            profileUpdateQuery.setDouble(1, user.getHighScore());
            profileUpdateQuery.setTimestamp(2, user.getHsScoredOn());
            profileUpdateQuery.setInt(3, user.getTotalGames());
            profileUpdateQuery.setInt(4, user.getGamesWon());
            profileUpdateQuery.setString(5, user.getUserId());
            profileUpdateQuery.execute();
        } catch(Exception e){
            System.out.println(e);
        }

    }
}
