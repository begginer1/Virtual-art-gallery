package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Entity.Artwork;
import dao.VirtualArtGalleryServiceImpl;
import util.DBConnection;


public class ArtworkManagementTest {
private static Connection connection;
private static VirtualArtGalleryServiceImpl service;

@BeforeAll
public static void Connection()
{
	connection=DBConnection.getConnection();
	service=new VirtualArtGalleryServiceImpl();
}


@Test
public void TestArtworkAdded()
{Statement st;
ResultSet rs=null;
	Artwork artwork=new Artwork(9,"Mono Lisa","French Painting of Woman without Eyebrows","1889-07-31","oil paining","https://monalisa.com",1);
	try {
		service.addArtwork(artwork);
		st=connection.createStatement();
		rs=st.executeQuery("Select * from Artwork where ArtworkID=9");	
} catch (SQLException e) {
	System.out.println(e.getMessage());
	assertEquals(true,equal(artwork,rs));
}
	
}

public boolean equal(Artwork artwork,ResultSet rs)
{
	try {
		if(rs.next()){
		if(artwork.getArtistID()==rs.getInt(1)&& artwork.getArtworkID()==rs.getInt(7))
		{
		if(artwork.getTitle().compareTo(rs.getString(2))==0&&(artwork.getCreationDate().compareTo(rs.getString(4))==0)&&artwork.getDescription().compareTo(rs.getString(3))==0&&artwork.getMedium().compareTo(rs.getString(5))==0&&artwork.getImageURL().compareTo(rs.getString(6))==0);
		return true;
		}
		}
	} catch (SQLException e) {
		
		System.out.print(e.getMessage());
	}	
	return false;
	}

@Test
public void TestupdateArtwork()
{
	Statement st;
	ResultSet rs=null;
		Artwork artwork=new Artwork(2,"Mickey Mouse","Cartoon Painting","1889-07-31","Oil paining","https://mickeymouse.com",1);
		try {
			service.updateArtwork(artwork);
			st=connection.createStatement();
			rs=st.executeQuery("Select * from Artwork where ArtworkID=2");	
	} catch (Throwable e) {
		System.out.println(e.getMessage());
		assertEquals(true,equal(artwork,rs));
	}
		
	}

@Test 
public void TestRemoveArtwork()
{
	Statement st;
	int rows=0;
		int artworkid=9;
		try {
			service.removeArtwork(artworkid);
			st=connection.createStatement();
			rows=st.executeUpdate("Select * from Artwork where ArtworkID=9");	
	} catch (Throwable e) {
		System.out.println(e.getMessage());
		assertEquals(0,rows);
	}
		
}

@Test
public void TestSearchArtwork()
{
	Statement st;
	ResultSet rs=null;
	Artwork artwork=null;
		int artworkid=2;
		try {
			artwork= service.getArtworkById(artworkid);
			st=connection.createStatement();
			rs=st.executeQuery("Select * from Artwork where ArtworkID=2");	
	} catch (Throwable e) {
		System.out.println(e.getMessage());
		assertEquals(true,equal(artwork,rs));
	}
}
}