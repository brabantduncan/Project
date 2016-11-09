package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Duncan on 9/11/2016.
 */
public class projectDB {
    private static final String URL = "jdbc:mysql://localhost/projectgeowars";
    private static final String USER = "duncan";
    private static final String PASSWORD = "duncan";

    private static projectDB instance;
    private Connection connection;


    public void registerDriver() throws ClassNotFoundException {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex)
        {
            throw new ClassNotFoundException("not registered", ex);
        }
    }

    public void openConnection() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("no connection opened", e);
        }
    }

    public static projectDB getInstance()
    {
        if (instance == null)
        {
            instance = new projectDB();
        }

        return instance;
    }

    public void addPlayer(String playername, String password, Integer highestScore) throws SQLException {
        try
        {
            String sql = "insert into player(pName, password, highestScore) values(?)";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setString(1, playername);
            prep.setString(1, password);
            prep.setInt(1, highestScore);

            prep.executeUpdate();

            prep.close();

            connection.close();
        }
        catch (SQLException ex)
        {
            throw new SQLException("player couldn't be added", ex);
        }
    }


}
