package dao;

import java.util.List;

import Entity.Artwork;

public interface IVirtualArtGallery {

	// Artwork Management
	 public Boolean addArtwork(Artwork Object);
	 
	 
	public boolean updateArtwork(Artwork object);
	 
	public boolean removeArtwork(int artworkdID);
	 
	
	 public Artwork getArtworkById(int artworkdID);
	 
	 public List<Artwork> searchArtworks(int artworkdID);
	 
	 // User Favorites
			 
	 public boolean addArtworkToFavorite(int userID,int artworkID);
	 
	
	
	 public boolean removeArtworkFromFavorite(int userID,int artworkID);
	 
	 
	 
	 public boolean getUserFavoriteArtworks(int userID);
}
