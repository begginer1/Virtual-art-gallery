package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Entity.Gallery;
import dao.VirtualArtGalleryServiceImpl;
import util.DBConnection;

class GalleryManagementTest {
	private static Connection connection;
	private static VirtualArtGalleryServiceImpl service;

	@BeforeAll
	public static void Connection()
	{
		connection=DBConnection.getConnection();
		service=new VirtualArtGalleryServiceImpl();
	}

	@Test
	public void TestAddingNewGallery() {
		Gallery gallery =new Gallery(4,"Collection of Art","Collection of Medivual Suff","France",1,"10 Am - 12 PM");
		ResultSet rs=null;
		try {
			PreparedStatement ps=connection.prepareStatement("insert into gallery values(?,?,?,?,?,?)");
			ps.setInt(1, gallery.getGalleryID());
			ps.setString(2, gallery.getName());
		ps.setString(3, gallery.getDescription());
		ps.setString(4, gallery.getLocation());
		ps.setInt(5, gallery.getCurator());
		ps.setString(6, gallery.getOpeningHours());
		ps.execute();
		rs=ps.executeQuery("select * from gallery where galleryID=4");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	assertEquals(true,equal(gallery,rs));
	}
	
	@Test
	public void TestUpdatingNewGallery() {
		Gallery gallery =new Gallery(2," Art of Wars","Collection of Medival Suff","France",1,"11 Am - 12 PM");
		ResultSet rs=null;
		try {
			PreparedStatement ps=connection.prepareStatement("update gallery set Name=?,Description_=?,Location=?,Curator=?,OpenningHours=? where galleryId=?");
			
			ps.setString(1, gallery.getName());
		ps.setString(2, gallery.getDescription());
		ps.setString(3, gallery.getLocation());
		ps.setInt(4, gallery.getCurator());
		ps.setString(5, gallery.getOpeningHours());
		ps.setInt(6, gallery.getGalleryID());
		ps.execute();
		rs=ps.executeQuery("select * from gallery where galleryID=2");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	assertEquals(true,equal(gallery,rs));
	}
	
	@Test
	public void TestRemoveGallery()
	{
		int rows_affected=0;
		Statement s;
		try {
			s = connection.createStatement();
			rows_affected=s.executeUpdate("Delete from gallery where galleryID=4");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(1,rows_affected);
	}
	
	
	
	
	
	
	
	public boolean equal(Gallery gallery,ResultSet rs)
	{
		try {
			if(rs.next()){
			if(gallery.getGalleryID()==rs.getInt(1)&& gallery.getCurator()==rs.getInt(5))
			{
			if(gallery.getName().compareTo(rs.getString(2))==0&&(gallery.getLocation().compareTo(rs.getString(4))==0)&&gallery.getDescription().compareTo(rs.getString(3))==0&&gallery.getOpeningHours().compareTo(rs.getString(6))==0)
			return true;
			}
			}
		} catch (SQLException e) {
			
			System.out.print(e.getMessage());
		}	
		return false;
		}

}
