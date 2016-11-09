package database;


import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Duncan on 9/11/2016.
 */

public class projectDB {
    private static final String URL = "jdbc:mysql://localhost/projectgeowars";
    private static final String USER = "duncan";
    private static final String PASSWORD = "duncan";

    private static projectDB instance;
    private Connection connection;


    public static void main (String[] arg) {
        try {
            /*getInstance().addPlayer("duncan", "duncan", 987654321);*/
            getInstance().getAllPlayerInfo();
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






    private void addPlayer(String playername, String password, Integer highestScore) throws SQLException {
        try
        {
            String sql = "insert into player(pName, password, highestScore) values(?,?,?)";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setString(1, playername);
            prep.setString(2, password);
            prep.setInt(3, highestScore);

            prep.executeUpdate();

            prep.close();

            connection.close();
        }
        catch (SQLException ex)
        {
            throw new SQLException("player couldn't be added", ex);
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

    
}