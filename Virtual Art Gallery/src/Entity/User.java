package Entity;

import java.util.List;

public class User {
	private int userID;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String profilePicture;
    private List<Integer> favoriteArtworks;

    public User(int userID, String username, String password, String email, String firstName,
                String lastName, String dateOfBirth, String profilePicture, List<Integer> favoriteArtworks) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.profilePicture = profilePicture;
        this.favoriteArtworks = favoriteArtworks;
    }

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public List<Integer> getFavoriteArtworks() {
		return favoriteArtworks;
	}

	public void setFavoriteArtworks(List<Integer> favoriteArtworks) {
		this.favoriteArtworks = favoriteArtworks;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", profilePicture=" + profilePicture + ", favoriteArtworks=" + favoriteArtworks + "]";
	}
    
}
