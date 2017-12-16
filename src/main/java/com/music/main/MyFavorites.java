package com.music.main;

import com.music.MusicDetails.*;
import com.music.DatabaseAccess.*;
import java.util.ArrayList;

class MyFavorites {
	private static ArrayList<Artist> artists = new ArrayList<Artist>();
	
	private static void printArtistDetails(Artist artist) {
		System.out.println("Artist: " + artist.getName() + "\n");
		artist.printAlbums();
		System.out.println(); // Extra space after last album
	}
	
	public static void main(String[] args) {
		// Add favorite artists to ArrayList
		artists.add(new Artist("Turnover"));
		artists.add(new Artist("Circa Survive"));
		artists.add(new Artist("From Indian Lakes"));
		
		// Add albums to Turnover
		artists.get(0).addAlbum("Good Nature", 2017);
		artists.get(0).addAlbum("Peripheral Vision", 2015);
		
		// Add albums to Circa Survive
		artists.get(1).addAlbum("The Amulet", 2017);
		artists.get(1).addAlbum("Descensus", 2014);
		artists.get(1).addAlbum("Violent Waves", 2012);
		artists.get(1).addAlbum("Blue Sky Noise", 2010);
		artists.get(1).addAlbum("On Letting Go", 2007);
		artists.get(1).addAlbum("Juturna", 2005);
		
		// Add albums to From Indian Lakes
		artists.get(2).addAlbum("Everything Feels Better Now", 2016);
		artists.get(2).addAlbum("Absent Sounds", 2014);
		artists.get(2).addAlbum("Able Bodies", 2012);
		artists.get(2).addAlbum("The Man With Wooden Legs", 2016);
		
		// Print artist name and albums
		for (Artist art : artists) {
			printArtistDetails(art);
		}

		System.out.println("GET FROM DB\n");

		ArrayList<Artist> SavedArtists = MySqlConnection.getArtists();

		for(Artist art: SavedArtists) {
			System.out.println("Stored Artist: " + art.getName());
		}

		MySqlConnection.getAlbums(SavedArtists.get(0));
		SavedArtists.get(0).printAlbums();
	}

}
