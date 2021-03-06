package database;



import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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
            //getInstance().addScore("Duncan", 2);
            getInstance().getHighScores();
            //getInstance().updateHighestScoreInPlayer("Duncan1", 2);
            getInstance().loginCheck("duncan","dicks");
            getInstance().getDescription("FrontWatcher");
            getInstance().getDifficultyParameter("HARD");
            getInstance().getEnemyMovementSpeed("Enemy");
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

    public String getDescription(String followername) throws SQLException {
        try
        {
            String description = "";
            String sql = "select description from support where sName = '"+followername+"'";

            PreparedStatement prep = this.connection.prepareStatement(sql);

            ResultSet rs = prep.executeQuery();

            rs.next();
            description += rs.getString(1);

            rs.close();
            prep.close();
            System.out.println(description);
            return description;
        }
        catch (SQLException ex)
        {
            throw new SQLException("description not found", ex);
        }
    }

    public int getDifficultyParameter(String difficultyFromState) throws SQLException {
        System.out.println(difficultyFromState);
        try
        {
            int difficulty = 0;

            String sql = "SELECT moreSpawn FROM difficulty WHERE dName = '"+difficultyFromState+"'";

            PreparedStatement prep = this.connection.prepareStatement(sql);

            ResultSet rs = prep.executeQuery();

            rs.next();
            difficulty += rs.getInt(1);

            rs.close();
            prep.close();
            System.out.println(difficulty);
            return difficulty;
        }
        catch (SQLException ex)
        {
            throw new SQLException("enemy not found", ex);
        }
    }

    public int getEnemyMovementSpeed(String enemyname) throws SQLException {
        try
        {
            int movspeed = 0;

            String sql = "SELECT moveSpeed FROM enemy WHERE eName = '"+enemyname+"'";

            PreparedStatement prep = this.connection.prepareStatement(sql);

            ResultSet rs = prep.executeQuery();

            rs.next();
            movspeed += rs.getInt(1);

            rs.close();
            prep.close();
            System.out.println(movspeed);
            return movspeed;
        }
        catch (SQLException ex)
        {
            throw new SQLException("enemy not found", ex);
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

    public ArrayList<HashMap<String, String>> getHighScores() throws SQLException {
        try
        {

            ArrayList<HashMap<String, String>> highscores = new ArrayList<HashMap<String, String>>();

            String sql = "SELECT * " +
                    "FROM score " +
                    "ORDER BY score DESC " +
                    "LIMIT 10 ";

            PreparedStatement prep = this.connection.prepareStatement(sql);

            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                HashMap<String, String> user= new HashMap<String, String>();
                user.put("username", rs.getString("pname"));
                user.put("score", rs.getString("score"));
                highscores.add(user);
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