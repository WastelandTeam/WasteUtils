package org.wasteutils.WasteUtils;

import java.sql.*;
import org.wasteutils.WasteUtils.Main;

public class SQLiteAPI {
    private final Main plugin;
    public Statement statement = null;

    public SQLiteAPI(Main plugin) {
        this.plugin = plugin;
    }

    public Connection connectDb() {
        Connection db = null;

        boolean connected = false;
        try {
            Class.forName("org.sqlite.JDBC");
            db = DriverManager.getConnection("jdbc:sqlite:"+this.plugin.getDataFolder()+"/db/database.db");
            statement = db.createStatement();
            if (db != null) {connected = true;}
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (connected) {return db;} else {this.plugin.onSevereError("Cannot connect database!");}
        }
        return db;
    }
    public void updateDb(String sql){
        try {
            this.statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}