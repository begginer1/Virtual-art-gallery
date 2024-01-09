package Entity;

import java.sql.Date;

import org.w3c.dom.Text;

public class Artist {
	private int artistID;
	private String name;
	private Text biography;
	private Date birthDate;
	private String nationality;
	private Text website;
	private String contactInfo;
	
	public Artist(int artistID, String name, Text biography, Date birthDate,
            String nationality, Text website, String contactInfo) {
		this.artistID = artistID;
		this.name = name;
		this.biography = biography;
		this.birthDate = birthDate;
		this.nationality = nationality;
		this.website = website;
		this.contactInfo = contactInfo;
	}

	
	public int getArtistID() {
		return artistID;
	}


	public void setArtistID(int artistID) {
		this.artistID = artistID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Text getBiography() {
		return biography;
	}


	public void setBiography(Text biography) {
		this.biography = biography;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public Text getWebsite() {
		return website;
	}


	public void setWebsite(Text website) {
		this.website = website;
	}


	public String getContactInfo() {
		return contactInfo;
	}


	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}


	@Override
	public String toString() {
		return "Artist [ArtistID=" + artistID + ", Name=" + name + ", Biography=" + biography + ", BirthDate="
				+ birthDate + ", Nationality=" + nationality + ", Website=" + website + ", contactInfo=" + contactInfo
				+ "]";
	}
	
}
