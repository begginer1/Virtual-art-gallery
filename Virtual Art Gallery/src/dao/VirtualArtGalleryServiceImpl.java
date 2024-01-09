package dao;
import  myexceptions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entity.Artwork;
import util.DBConnection;

public class VirtualArtGalleryServiceImpl //implements IVirtualArtGallery
{
 static Connection connection;

public VirtualArtGalleryServiceImpl()
{
	connection=DBConnection.getConnection();
}

//@Override
public Boolean addArtwork(Artwork Object) {
	PreparedStatement ps;
	Boolean ch=false;
	try {
		ps=connection.prepareStatement("insert into Artwork values(?,?,?,?,?,?,?)");
		ps.setInt(1, Object.getArtworkID());
		ps.setString(2, Object.getTitle());
		ps.setString(3, Object.getDescription());
		ps.setString(4, Object.getCreationDate());
		ps.setString(5, Object.getMedium());
		ps.setString(6, Object.getImageURL());
		ps.setInt(7, Object.getArtistID());
		ps.execute();
		ch=true;
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	
	return ch;
}


//@Override
public boolean updateArtwork(Artwork Object) throws ArtworkNotFoundException {
	PreparedStatement ps;
	int row_affected=0;
	try {
		ps=connection.prepareStatement("UPDATE Artwork SET Title = ?, Description = ?, CreationDate = ?, Medium = ?, ImageURL = ?, ArtistID =? WHERE ArtworkID = ?");
		
		ps.setString(1, Object.getTitle());
		ps.setString(2, Object.getDescription());
		ps.setString(3, Object.getCreationDate());
		ps.setString(4, Object.getMedium());
		ps.setString(5, Object.getImageURL());
		ps.setInt(6, Object.getArtistID());
		ps.setInt(7, Object.getArtworkID());
		row_affected=ps.executeUpdate();
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	if(row_affected==0) {
		throw new ArtworkNotFoundException("Artwork Not Found");
	}
	return true;
}



public boolean removeArtwork(int artworkdID) throws ArtworkNotFoundException {
	PreparedStatement ps;
	int n=0;
	try {
		// delete from artwork
		ps=connection.prepareStatement("delete from Artwork WHERE ArtworkID = ?");
		ps.setInt(1, artworkdID);
		 n=ps.executeUpdate();
		//delete from artwork_gallery
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	if(n==0)
		throw new ArtworkNotFoundException("Artwork Not Found");
	// if 0 rows affected means no artwork found
	return true;
}


public Artwork getArtworkById(int artworkdID) throws ArtworkNotFoundException  {
	Artwork art=new Artwork();
	Boolean check=false;
	PreparedStatement ps;
	ResultSet rs=null;
	int column;
	try {
		// delete from artwork
		ps=connection.prepareStatement("select * from Artwork WHERE ArtworkID = ?");
		ps.setInt(1, artworkdID);
		 rs=ps.executeQuery();
		//delete from artwork_gallery
		 if(rs.next()) {
		column=rs.getMetaData().getColumnCount();
			art.setArtworkID(rs.getInt(1));
			art.setTitle(rs.getString(2));
			art.setDescription(rs.getString(3));
			art.setCreationDate(rs.getString(4));
			art.setMedium(rs.getString(5));
			art.setImageURL(rs.getString(6));
			art.setArtistID(rs.getInt(7));
		 }
		 else
			 throw new ArtworkNotFoundException("Artwork Not Found");
	} 
	catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	
	
	return art;
}


//@Override
public List<Artwork> searchArtworks(int artworkdID) throws ArtworkNotFoundException {
	List<Artwork> list=new ArrayList<>();
	Artwork art=null;
	Boolean check=false;
	PreparedStatement ps;
	ResultSet rs=null;
	int column;
	try {
		// delete from artwork
		ps=connection.prepareStatement("select * from Artwork WHERE ArtworkID = ?");
		ps.setInt(1, artworkdID);
		 rs=ps.executeQuery();
		//delete from artwork_gallery
		//delete from artwork_gallery
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	
	try {
		column=rs.getMetaData().getColumnCount();
		
		while(rs.next()) //rs.next move cursor by row return true if next row exit else false
		{art=new Artwork();
			art.setArtworkID(rs.getInt(1));
			art.setTitle(rs.getString(2));
			art.setDescription(rs.getString(3));
			art.setCreationDate(rs.getString(4));
			art.setMedium(rs.getString(5));
			art.setImageURL(rs.getString(6));
			art.setArtistID(rs.getInt(7));
		list.add(art);
		}
		
	} catch (SQLException e) { //throw not found exception
		System.out.println(e.getMessage());
	}
	if(list==null)
		throw new ArtworkNotFoundException("Artwork Not found");
	return list;
}

//@Override
public boolean addArtworkToFavorite(int userID, int artworkID) {
	Boolean check=false; 
	try
	{
		PreparedStatement ps=connection.prepareStatement("insert into user_favorite_Artwork values(?,?);");
		ps.setInt(1, userID);
		ps.setInt(2, artworkID);
		ps.execute();
		check=true;
	}
	catch(SQLException e)
	{
		System.out.println(e.getMessage());
	}
	return check;
}

//@Override
public boolean removeArtworkFromFavorite(int userID, int artworkID) throws UserNotFoundException {
	int row_affected=0; 
	try
	{
		PreparedStatement ps=connection.prepareStatement("Delete from user_favorite_Artwork where userID=? and artworkID=?;");
		ps.setInt(1, userID);
		ps.setInt(2, artworkID);
		row_affected=ps.executeUpdate();
	}
	catch(SQLException e)
	{
		System.out.println(e.getMessage());
	}
	if(row_affected==0)
		throw new UserNotFoundException("User or Arwork Not Found");
	return true;
}


//@Override
public boolean getUserFavoriteArtworks(int userID) throws UserNotFoundException {
	boolean check=false;
	try
	{
	PreparedStatement ps=connection.prepareStatement("Select * from Artwork where ArtworkID IN(select ArtworkID from user_favorite_Artwork where userID=?)");
	ps.setInt(1, userID);
	ResultSet rs=ps.executeQuery();
	while(rs.next())
	{
		check=true;
		System.out.println("Artwork [ArtworkID=" + rs.getInt(1) + ", Title=" +rs.getString(2) + ", Description=" + rs.getString(3)
				+ ", CreationDate=" + rs.getString(4) + ", Medium=" + rs.getString(5) + ", ImageURL=" + rs.getString(6) +", ArtistID=" +rs.getInt(7)+ "]");
	}
	}
	catch(SQLException e)
	{
		System.out.println(e.getMessage());
	}
	if(check==false)
		throw new UserNotFoundException("User Not Found");
	return true;
}
	

// Main for addArtwork
/*public static void main(String args[])
{
	
	Artwork Object=new Artwork();
	Scanner input;
	System.out.println("Enter Artwork ID: ");
	input=new Scanner(System.in);
	Object.setArtworkID(input.nextInt());
	System.out.println("Enter Artwork Title: ");
	input=new Scanner(System.in);
	Object.setTitle(input.nextLine());
	System.out.println("Enter Description: ");
	input=new Scanner(System.in);
	Object.setDescription(input.nextLine());
	System.out.println("Enter Creation Date in "+"yyyy-mm-dd Format :");
	input=new Scanner(System.in);
    Object.setCreationDate(input.nextLine());
	System.out.println("Enter Medium: ");
	input=new Scanner(System.in);
	Object.setMedium(input.nextLine());
	System.out.println("Enter ImageURL: ");
	input=new Scanner(System.in);
	Object.setImageURL(input.nextLine());
	System.out.println("Enter Artist ID");
	input= new Scanner(System.in);
	Object.setArtistID(input.nextInt());
	
	VirtualArtGalleryServiceImpl ob=new VirtualArtGalleryServiceImpl();
	ob.addArtwork(Object);
	
}
// update
public static void main(String args[])
{

	Artwork Object=new Artwork();
	Scanner input;
	System.out.println("Enter Artwork ID: ");
	input=new Scanner(System.in);
	Object.setArtworkID(input.nextInt());
	System.out.println("Enter Artwork Title: ");
	input=new Scanner(System.in);
	Object.setTitle(input.nextLine());
	System.out.println("Enter Description: ");
	input=new Scanner(System.in);
	Object.setDescription(input.nextLine());
	System.out.println("Enter Creation Date in "+"yyyy-mm-dd Format :");
	input=new Scanner(System.in);
    Object.setCreationDate(input.nextLine());
	System.out.println("Enter Medium: ");
	input=new Scanner(System.in);
	Object.setMedium(input.nextLine());
	System.out.println("Enter ImageURL: ");
	input=new Scanner(System.in);
	Object.setImageURL(input.nextLine());
	System.out.println("Enter Artist ID");
	input= new Scanner(System.in);
	Object.setArtistID(input.nextInt());
	
	VirtualArtGalleryServiceImpl ob=new VirtualArtGalleryServiceImpl();
	ob.updateArtwork(Object);
	
}

//for removing artwork used delete cascade to maintain referential integrity
public static void main(String args[])
{
	
	Scanner input;
	System.out.println("Enter Artwork ID: ");
	input=new Scanner(System.in);
	VirtualArtGalleryServiceImpl ob=new VirtualArtGalleryServiceImpl();
	ob.removeArtwork(input.nextInt());
}


//for removing artwork used delete cascade to maintain referential integrity

public static void main(String args[])
{
	Artwork object=null;
	Scanner input;
	System.out.println("Enter Artwork ID: ");
	input=new Scanner(System.in);
	VirtualArtGalleryServiceImpl ob=new VirtualArtGalleryServiceImpl();
	object=ob.getArtworkById(input.nextInt());
	System.out.println(object);
}

//for search artwork
public static void main(String args[])
{
	List<Artwork> object=new ArrayList<>();
	Scanner input;
	System.out.println("Enter Artwork ID: ");
	input=new Scanner(System.in);
	VirtualArtGalleryServiceImpl ob=new VirtualArtGalleryServiceImpl();
	object=ob.searchArtworks(input.nextInt());
	for(Artwork o:object)
	System.out.println(o);
}

//add artwork
public static void main(String args[])
{
 boolean check=false;
	int UserID,ArtworkID;
	Scanner input;
	System.out.println("Enter User ID: ");
	input=new Scanner(System.in);
	UserID=input.nextInt();
	System.out.println("Enter Artwork ID: ");
	input=new Scanner(System.in);
	ArtworkID=input.nextInt();
	VirtualArtGalleryServiceImpl ob=new VirtualArtGalleryServiceImpl();
	ob.addArtworkToFavorite(UserID,ArtworkID);
	if(check)
	System.out.print("Artwork Successfully Added to User Favorites");
else
	System.out.print("Artwork Not Added to User Favorites");
	}

//remove favorite
 * public static void main(String args[])
{
	boolean check=false;
	int UserID,ArtworkID;
	Scanner input;
	System.out.println("Enter User ID: ");
	input=new Scanner(System.in);
	UserID=input.nextInt();
	System.out.println("Enter Artwork ID: ");
	input=new Scanner(System.in);
	ArtworkID=input.nextInt();
	VirtualArtGalleryServiceImpl ob=new VirtualArtGalleryServiceImpl();
	check=ob.removeArtworkFromFavorite(UserID,ArtworkID);
if(check)
	System.out.print("Artwork Successfully Removed from User Favorites");
else
	System.out.print("Artwork Not Removed from User Favorites");
}


public static void main(String args[])
{
	boolean check=false;
	int UserID;
	Scanner input;
	System.out.println("Enter User ID: ");
	input=new Scanner(System.in);
	UserID=input.nextInt();
	VirtualArtGalleryServiceImpl ob=new VirtualArtGalleryServiceImpl();
	ob.getUserFavoriteArtworks(UserID);

}*/
}
