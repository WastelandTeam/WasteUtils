package org.wasteutils.WasteUtils;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class SQLiteAPI {
    private static final String constructUID = """
            CREATE TABLE IF NOT EXISTS player (
            \tUUID TEXT,
            \tUID INTEGER PRIMARY KEY AUTOINCREMENT,
            \tplayername TEXT
            );""";
    private static final String constructPoints = """
            CREATE TABLE IF NOT EXISTS points (
            \tUID INTEGER,
            \tPoints INTEGER DEFAULT (1000)
            );
            """;
    private static final String constructMail = """
            CREATE TABLE IF NOT EXISTS mail (
            \tUID INTEGER,
            \tmails TEXT DEFAULT ('{"security":[],"notification":[],"system":[]}')
            );
            """;
    private static final String constructSocial = """
            CREATE TABLE IF NOT EXISIS social (
            \tuid INTEGER,
            \ttitles TEXT DEFAULT ([{"name":"玩家","expireTime":"never"}]),
            \tskins TEXT DEFAULT ([{"id":"0","expire":"never"}])
            );
            """;
    private final Main plugin;
    public static Statement statement = null;
    public static Connection db = null;
    private static boolean connected = false;
    public SQLiteAPI(Main plugin) {
        this.plugin = plugin;
    }

    public static Connection connectDb(Main plugin) {
        try {
            Class.forName("org.sqlite.JDBC");
            File dbFile = new File(plugin.getDataFolder() + File.separator + "db", "playerdata.db");
            File dbDir = new File(plugin.getDataFolder() + File.separator + "db");

            if (!dbDir.exists()) {
                dbDir.mkdir();
            }
            if (!dbFile.exists()) {
                dbFile.createNewFile();
            }

            db = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder() + "/db/playerdata.db");
            statement = db.createStatement();

            if (db != null) {connected = true;}
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (connected) {
                updateDb(constructUID);
                updateDb(constructPoints);
                updateDb(constructMail);
                updateDb(constructSocial);
            } else {
                plugin.onSevereError("Cannot connect database!");
            }
        }
        return db;
    }

    public static void updateDb(String sql) {
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static ResultSet queryDb(String sql) {
        ResultSet result;
        try {
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}