package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entity.Artwork;
import dao.VirtualArtGalleryServiceImpl;
import myexceptions.ArtworkNotFoundException;
import myexceptions.UserNotFoundException;

public class MainModule {

	public static void main(String args[])
	{
	List<Artwork> list=null;
	VirtualArtGalleryServiceImpl Serviceobj=null;
	Scanner input;
	int choice,UserID,ArtworkID;;
	Artwork artwork=null;
	boolean loop=true,check=false;
	while(loop) {
	System.out.println("Welcome!");
	System.out.println("Press:\n1.To Add Artwork");
	System.out.println("2.Update Artwork");
	System.out.println("3.Remove Artwork");
	System.out.println("4.View Artwork");
	System.out.println("5.Search Artwork");
	System.out.println("6.Add Artwork To Favorite");
	System.out.println("7.Remove Artwork From Favorite");
	System.out.println("8.Get User Favorite Artwork");
	System.out.println("9.Exit");
	System.out.println("Enter your Choice");
	input=new Scanner(System.in);
	choice=input.nextInt();
	switch(choice)
	{
		
	case 1:
		artwork=new Artwork();
		System.out.println("Enter Artwork ID: ");
		input=new Scanner(System.in);
		artwork.setArtworkID(input.nextInt());
		System.out.println("Enter Artwork Title: ");
		input=new Scanner(System.in);
		artwork.setTitle(input.nextLine());
		System.out.println("Enter Description: ");
		input=new Scanner(System.in);
		artwork.setDescription(input.nextLine());
		System.out.println("Enter Creation Date in "+"yyyy-mm-dd Format :");
		input=new Scanner(System.in);
	    artwork.setCreationDate(input.nextLine());
		System.out.println("Enter Medium: ");
		input=new Scanner(System.in);
		artwork.setMedium(input.nextLine());
		System.out.println("Enter ImageURL: ");
		input=new Scanner(System.in);
		artwork.setImageURL(input.nextLine());
		System.out.println("Enter Artist ID");
		input= new Scanner(System.in);
		artwork.setArtistID(input.nextInt());
		//Add Artwork
		Serviceobj=new VirtualArtGalleryServiceImpl();
		check=Serviceobj.addArtwork(artwork);
		if(check)
		    System.out.println("Artwork Successfully Added");
		break;
		
	case 2:
		artwork=new Artwork();
		System.out.println("Enter Artwork ID: ");
		input=new Scanner(System.in);
		artwork.setArtworkID(input.nextInt());
		System.out.println("Enter Artwork Title: ");
		input=new Scanner(System.in);
		artwork.setTitle(input.nextLine());
		System.out.println("Enter Description: ");
		input=new Scanner(System.in);
		artwork.setDescription(input.nextLine());
		System.out.println("Enter Creation Date in "+"yyyy-mm-dd Format :");
		input=new Scanner(System.in);
	    artwork.setCreationDate(input.nextLine());
		System.out.println("Enter Medium: ");
		input=new Scanner(System.in);
		artwork.setMedium(input.nextLine());
		System.out.println("Enter ImageURL: ");
		input=new Scanner(System.in);
		artwork.setImageURL(input.nextLine());
		System.out.println("Enter Artist ID");
		input= new Scanner(System.in);
		artwork.setArtistID(input.nextInt());
		//Update Artwork
		Serviceobj=new VirtualArtGalleryServiceImpl();
		try 
		{	
			check=Serviceobj.updateArtwork(artwork);
		}
		catch (ArtworkNotFoundException e) {
			System.out.println(e.getMessage());
		}
		if(check)
			System.out.println("Artwork Successfully Updated");
		break;
		
	
	case 3:
		System.out.println("Enter Artwork ID: ");
		input=new Scanner(System.in);
		//Remove Artwork
		Serviceobj=new VirtualArtGalleryServiceImpl();
		try 
		{
			check=Serviceobj.removeArtwork(input.nextInt());
		}
		catch (ArtworkNotFoundException e) {
			System.out.println(e.getMessage());
		}
		if(check)
			System.out.println("Artwork Successfully Removed");
		
		break;
		
	case 4:
		System.out.println("Enter Artwork ID: ");
		input=new Scanner(System.in);
		//View Artwork
		Serviceobj=new VirtualArtGalleryServiceImpl();
		try 
		{	
			artwork=Serviceobj.getArtworkById(input.nextInt());
		}
		catch (ArtworkNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(artwork);
		break;
		
	case 5:
		list=new ArrayList<>();
		System.out.println("Enter Artwork ID: ");
		input=new Scanner(System.in);
		//Search Artworks
		Serviceobj=new VirtualArtGalleryServiceImpl();
		try 
		{
			list=Serviceobj.searchArtworks(input.nextInt());
		} 
		catch (ArtworkNotFoundException e) {
			
			System.out.println(e.getMessage());
		}
		
		for(Artwork obj:list)
		System.out.println(obj);
		break;
		
	case 6:
		System.out.println("Enter User ID: ");
		input=new Scanner(System.in);
		UserID=input.nextInt();
		System.out.println("Enter Artwork ID: ");
		input=new Scanner(System.in);
		ArtworkID=input.nextInt();
	    //Add Artwork TO Favorite
		Serviceobj=new VirtualArtGalleryServiceImpl();
		check=Serviceobj.addArtworkToFavorite(UserID,ArtworkID);
		if(check)
			System.out.println("Artwork Successfully Added To Favorite");
		break;
		
	case 7:
		System.out.println("Enter User ID: ");
		input=new Scanner(System.in);
		UserID=input.nextInt();
		System.out.println("Enter Artwork ID: ");
		input=new Scanner(System.in);
		ArtworkID=input.nextInt();
		//Remove Favorite Artwork
		Serviceobj=new VirtualArtGalleryServiceImpl();
		try {
			Serviceobj.removeArtworkFromFavorite(UserID,ArtworkID);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		if(check)
			System.out.println("Artwork Successfully Removed From Favorite");
		break;
		
	case 8:
		System.out.println("Enter User ID: ");
		input=new Scanner(System.in);
		UserID=input.nextInt();
		//Print User Favorite Artworks
		Serviceobj=new VirtualArtGalleryServiceImpl();
		try {
			Serviceobj.getUserFavoriteArtworks(UserID);
		} 
		catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		break;
		
	case 9:
		loop=false;
		break;
	default:
		System.out.println("Invalid Choice");
	}
	
	}
}
}
