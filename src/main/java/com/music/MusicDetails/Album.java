package com.music.MusicDetails;

public class Album {
	private final String title;
	private final int releaseYear;
	
	// Constructor
	public Album(String title, int releaseYear) {
		this.title = title;
		this.releaseYear = releaseYear;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getReleaseYear() {
		return this.releaseYear;
	}
}
