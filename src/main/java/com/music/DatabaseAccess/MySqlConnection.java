//package com.music.DatabaseAccess;

//import com.music.MusicDetails.*;
import java.sql.*;
import java.util.ArrayList;

public class MySqlConnection {
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://104.131.12.112/music?useSSL=false";

	// Database credentials
	private static final String USER = "music_user";
	private static final String PASS = "Engineering13";

	public static void main(String[] args) {
		sendArtist(new Artist("Balance and Composure"));

		ArrayList<Artist> SavedArtists = getArtists();

		for(Artist art: SavedArtists) {
			System.out.println("Stored Artist: " + art.getName());
		}
	}

	public static void sendArtist(Artist artist) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		int recordCount = -1;

		String sql_check_artist = "SELECT COUNT(*) AS total FROM artist WHERE name = '" + artist.getName() + "'";
		String sql_insert_artist = "INSERT INTO artist(name) VALUES('" + artist.getName() + "')";

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			// Check if the artist already exists in the DB
			rs = stmt.executeQuery(sql_check_artist);

			while(rs.next()) {
				recordCount = rs.getInt("total");
				System.out.println("recordCount: " + recordCount);
			}

			if(recordCount != 0) {
				System.out.println("Artist already exists");
			} else {
				// Insert artist
				stmt.executeUpdate(sql_insert_artist);
				System.out.println("Inserted");
			}
		} catch(SQLException se) {
			// Handle error for JDBC
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if(rs != null) {
					rs.close();
					System.out.println("Closed rs");
				}
			} catch(SQLException se1) {
				se1.printStackTrace();
			}
			
			try {
				if(stmt != null) {
					stmt.close();
					System.out.println("Closed stmt");
				}
			} catch(SQLException se2) {
				se2.printStackTrace();
			}
			
			try {
				if(conn != null) {
					conn.close();
					System.out.println("Closed conn");
				}
			} catch(SQLException se3) {
				se3.printStackTrace();
			}
		}
	}

	public static void sendAlbum(Artist artist, Album album) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		int recordCount = -1;
		int artistId = -1;

		String sql_get_artist_id = "SELECT artist_id FROM artist WHERE name = '" + artist.getName() + "'";
		String sql_check_album = "";
		String sql_insert_album = "";

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			// Check for artist id and get it from DB
			rs = stmt.executeQuery(sql_get_artist_id);

			while(rs.next()) {
				artistId = rs.getInt("artist_id");
				System.out.println("artist_id: " + artistId);
			}

			if(artistId <= 0) {
				System.out.println("Artist does not exist");
			} else {
				// Check if album already exists
				sql_check_album = "SELECT COUNT(*) AS total FROM album WHERE artist_id = " + artistId  + " AND title = '" + album.getTitle() + "' AND release_year = " + album.getReleaseYear() ;
				rs = stmt.executeQuery(sql_check_album);
				
				while(rs.next()) {
					recordCount = rs.getInt("total");
					System.out.println("recordCount: " + recordCount);
				}

				if(recordCount != 0) {
					System.out.println("Album already exists");
				} else {
					sql_insert_album = "INSERT INTO album(artist_id, title, release_year) VALuES(" + artistId + ", '" + album.getTitle() + "', " + album.getReleaseYear() + ")";
					stmt.executeUpdate(sql_insert_album);
					System.out.println("Inserted album");
				}
			}
		} catch(SQLException se) {
			// Handle error for JDBC
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if(rs != null) {
					rs.close();
					System.out.println("Closed rs");
				}
			} catch(SQLException se1) {
				se1.printStackTrace();
			}
			
			try {
				if(stmt != null) {
					stmt.close();
					System.out.println("Closed stmt");
				}
			} catch(SQLException se2) {
				se2.printStackTrace();
			}
			
			try {
				if(conn != null) {
					conn.close();
					System.out.println("Closed conn");
				}
			} catch(SQLException se3) {
				se3.printStackTrace();
			}
		}
	}

	public static ArrayList<Artist> getArtists() {	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql_get_all_artists = "SELECT name FROM artist";
		
		ArrayList<Artist> StoredArtists = new ArrayList<Artist>();

		String name = "";

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			// Get all artists from DB
			rs = stmt.executeQuery(sql_get_all_artists);

			while(rs.next()) {
				name = rs.getString("name");

				StoredArtists.add(new Artist(name));
			}
		} catch(SQLException se) {
			// Handle error for JDBC
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if(rs != null) {
					rs.close();
					System.out.println("Closed rs");
				}
			} catch(SQLException se1) {
				se1.printStackTrace();
			}
			
			try {
				if(stmt != null) {
					stmt.close();
					System.out.println("Closed stmt");
				}
			} catch(SQLException se2) {
				se2.printStackTrace();
			}
			
			try {
				if(conn != null) {
					conn.close();
					System.out.println("Closed conn");
				}
			} catch(SQLException se3) {
				se3.printStackTrace();
			}
		}

		return StoredArtists;
	}

	/*public static Album getAlbum() {
	}*/
}	
