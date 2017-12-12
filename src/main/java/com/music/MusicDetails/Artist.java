package com.music.MusicDetails;

import java.util.ArrayList;

public class Artist {
	private final String name;
	private ArrayList<Album> albums = new ArrayList<Album>(); // Example of composition
	private static int artistCount = 0; // Class variable
	
	public Artist(String name) {
		this.name = name;
		Artist.updateCount();
	}
	
	public void addAlbum(String title, int releaseYear) {
		albums.add(new Album(title, releaseYear));
	}
	
	public String getName() {
		return name;
	}
	
	public void printAlbums() {
		if (albums.isEmpty()) {
			System.out.println("No albums");
		} else {
			for (Album alb : albums) {
				System.out.println("Title: " + alb.getTitle());
				System.out.println("Release Year: " + alb.getReleaseYear() + "\n");
			}
		}
	}
	
	// Class method
	private static void updateCount() {
		Artist.artistCount = Artist.artistCount + 1;
	}
}
