package pl.inder00.drop.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pl.inder00.drop.rhDrop;
import pl.inder00.drop.objects.User;

public class SQLite {
	
	public static Connection conn;
	public static Statement s;
	
	private static String db_name;
	
	public static void setConnection(String dbname) {
		db_name = dbname;
		try {
			Class.forName("org.sqlite.JDBC");
			 
			conn = DriverManager.getConnection(dbname);
			 
			s = conn.createStatement();
		} catch(Exception ex) {
			rhDrop.error("Nie mozna sie polaczyc z baza danych", true);
		}
	}
	public static void openConnection() throws SQLException {
        if(!conn.isClosed()) return;
        conn = DriverManager.getConnection(db_name);
        s = conn.createStatement();
    }
	public static void closeConnection() throws SQLException{
        if(conn == null || (conn !=null && conn.isClosed())) return;
        conn.close();
    }
	public static Connection getConnection() {
		return conn;
	}
	public static Statement getStatement() throws SQLException {
		openConnection();
		return s;
	}
	public static void init() throws SQLException {
		String update = "CREATE TABLE IF NOT EXISTS `players` ( `name` VARCHAR(255) NOT NULL , `uuid` VARCHAR(255) NOT NULL , `xp` INT(128) NOT NULL , `level` INT(128) NOT NULL , PRIMARY KEY (`uuid`));";
		s.executeUpdate(update);
	}
	public static void save() throws SQLException {
		openConnection();
		init();
		for(User u : User.players) {
			ResultSet rs = s.executeQuery("SELECT * FROM `players` WHERE `uuid` = '"+u.getUuid()+"';");
			if(rs.next()) {
				s.executeUpdate("UPDATE `players` SET `name` = '"+u.getName()+"', `uuid` = '"+u.getUuid()+"', `xp` = '"+u.getXp()+"', `level` = '"+u.getLevel()+"' WHERE `uuid` = '"+u.getUuid()+"';");
			} else {
				s.executeUpdate("INSERT INTO `players` (`name`, `uuid`, `xp`, `level`) VALUES ('"+u.getName()+"', '"+u.getUuid()+"', '"+u.getXp()+"', '"+u.getLevel()+"');");
			}
			rs.close();
		}
		s.close();
		closeConnection();
	}
	public static void load() throws SQLException {
		openConnection();
		init();
		PreparedStatement  s = conn.prepareStatement("SELECT * FROM `players`");
		ResultSet rs = s.executeQuery();
		while(rs.next()) {
			new User(rs.getString("name"), rs.getString("uuid"), rs.getInt("xp"), rs.getInt("level"));
		}
		s.close();
		rs.close();
		closeConnection();
	}
	/*public static void save() throws SQLException {
		openConnection();
		init();
		for(User u : User.players) {
			PreparedStatement s = conn.prepareStatement("SELECT * FROM `players` WHERE `uuid` = '"+u.getUuid()+"';");
			ResultSet rs = s.getResultSet();
			if(rs.next()) {
				conn.prepareStatement("UPDATE `players` SET `name` = '"+u.getName()+"', `uuid` = '"+u.getUuid()+"', `xp` = '"+u.getXp()+"', `level` = '"+u.getLevel()+"' WHERE `uuid` = '"+u.getUuid()+"';");
			} else {
				conn.prepareStatement("INSERT INTO `players` (`id`, `name`, `uuid`, `xp`, `level`) VALUES (NULL, '"+u.getName()+"', '"+u.getUuid()+"', '"+u.getXp()+"', '"+u.getLevel()+"');");
			}
			rs.close();
		}
		s.close();
		closeConnection();
	}
	public static void load() throws SQLException {
		openConnection();
		init();
		PreparedStatement  s = conn.prepareStatement("SELECT * FROM `players`");
		ResultSet rs = s.executeQuery();
		while(rs.next()) {
			new User(rs.getString("name"), rs.getString("uuid"), rs.getInt("xp"), rs.getInt("level"));
		}
		s.close();
		rs.close();
		closeConnection();
	}*/

}
