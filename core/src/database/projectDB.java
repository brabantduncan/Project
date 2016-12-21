package database;



import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Duncan on 9/11/2016.
 */

public class projectDB {
    private static final String URL = "jdbc:mysql://79.170.44.81/cl39-groep18";
    private static final String USER = "cl39-groep18";
    private static final String PASSWORD = "groep181337";

    private static projectDB instance;
    private Connection connection;


    public static void main (String[] arg) {
        try {
            //getInstance().addPlayer("laurensSuckie", "laurens");
            getInstance().getAllPlayerInfo();
            getInstance().getEnemyByName("Brecht");
            //getInstance().addScore("Duncan", 2);
            getInstance().getHighScores();
            //getInstance().updateHighestScoreInPlayer("Duncan1", 2);
            getInstance().loginCheck("duncan","dicks");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private projectDB() throws ClassNotFoundException, SQLException {
        this.registerDriver();
        this.openConnection();
    }

    private void registerDriver() throws ClassNotFoundException {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex)
        {
            throw new ClassNotFoundException("not registered", ex);
        }
    }

    private void openConnection() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(URL,USER,PASSWORD);

        } catch (SQLException e) {

            throw new SQLException("no connection opened", e);
        }
    }

    public static projectDB getInstance(){
        if (instance == null)
        {
            try {
                instance = new projectDB();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    public void addPlayer(String playername, String password) throws SQLException {
        try
        {
            String sql = "insert into player(pName, password, highestScore) values(?,?,?)";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setString(1, playername);
            prep.setString(2, password);
            prep.setInt(3, 0);

            prep.executeUpdate();

            prep.close();

            //connection.close();
        }
        catch (SQLException ex)
        {
            throw new SQLException("player couldn't be added", ex);
        }
    }

    public ArrayList<String> getEnemyByName(String enemyname) throws SQLException {
        try
        {
            ArrayList<String> enemy = new ArrayList<String>();
            String sql = "select * from enemy where eName = '"+enemyname+"'";

            PreparedStatement prep = this.connection.prepareStatement(sql);

            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                enemy.add(rs.getString("eid"));
                enemy.add(rs.getString("eName"));
            }

            rs.close();
            prep.close();
            System.out.println(enemy);
            return enemy;
        }
        catch (SQLException ex)
        {
            throw new SQLException("enemy not found", ex);
        }
    }

    public ArrayList<String> getAllPlayerInfo() throws SQLException {
        try
        {
            ArrayList<String> players = new ArrayList<String>();
            String sql = "select * from player";

            PreparedStatement prep = this.connection.prepareStatement(sql);

            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                players.add(rs.getString("pName"));
                players.add(rs.getString("password"));
                players.add(rs.getString("highestScore"));
            }

            rs.close();
            prep.close();
            System.out.println(players);
            return players;
        }
        catch (SQLException ex)
        {
            throw new SQLException("players not found", ex);
        }
    }

    public Boolean loginCheck(String playername, String password) throws SQLException {
        try
        {
            String sql = "SELECT password FROM player WHERE pName = '"+playername+"'";

            PreparedStatement prep = this.connection.prepareStatement(sql);

            ResultSet rs = prep.executeQuery();
            rs.next();
            if(password.equals(rs.getString(1))){
                //System.out.println(rs.getString(1));
                rs.close();
                prep.close();
                return true;
            }
            else {
                //System.out.println(rs.getString(1));
                rs.close();
                prep.close();
                return false;
            }

        }
        catch (SQLException ex)
        {
            throw new SQLException("players not found", ex);
        }
    }

    public void addScore(String playername, Integer score) throws SQLException {
        try
        {
            String sql = "insert into score(pname, score) values(?,?)";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setString(1, playername);
            prep.setInt(2, score);

            prep.executeUpdate();

            prep.close();

           // connection.close();
        }
        catch (SQLException ex)
        {
            throw new SQLException("score couldn't be added", ex);
        }
    }

    public ArrayList<String> getHighScores() throws SQLException {
        try
        {
            ArrayList<String> highscores = new ArrayList<String>();
            String sql = "SELECT * " +
                    "FROM score " +
                    "ORDER BY score DESC " +
                    "LIMIT 10 ";

            PreparedStatement prep = this.connection.prepareStatement(sql);

            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                highscores.add(rs.getString("pname"));
                highscores.add(rs.getString("score"));
            }

            rs.close();
            prep.close();
            System.out.println(highscores);
            return highscores;
        }
        catch (SQLException ex)
        {
            throw new SQLException("players not found", ex);
        }

    }

    private void updateHighestScoreInPlayer(String playername, Integer score) throws SQLException {
        try
        {
            String sql = "UPDATE player " +
                    "    SET highestScore=?" +
                    "    WHERE pName=?";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setInt(1, score);
            prep.setString(2, playername);

            prep.executeUpdate();

            prep.close();

            connection.close();
        }
        catch (SQLException ex)
        {
            throw new SQLException("highestScore couldn't be changed", ex);
        }
    }



}