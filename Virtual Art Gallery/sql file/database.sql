

-- Artist Table
CREATE TABLE Artist (
    ArtistID INT PRIMARY KEY,
    Name VARCHAR(255),
    Biography TEXT,
    BirthDate DATE,
    Nationality VARCHAR(255),
    Website VARCHAR(255),
    ContactInformation VARCHAR(255)
);


CREATE TABLE Artwork (
    ArtworkID INT PRIMARY KEY,
    Title VARCHAR(255),
    Description TEXT,
    CreationDate DATE,
    Medium VARCHAR(255),
    ImageURL VARCHAR(255),
    ArtistID int
);
-- User Table
CREATE TABLE User (
    UserID INT PRIMARY KEY,
    Username VARCHAR(255) UNIQUE,
    Password VARCHAR(255),
    Email VARCHAR(255) UNIQUE,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    DOB DATE,
    ProfilePicture BLOB,
    FavoriteArtworks INT
);

-- Gallery Table
CREATE TABLE Gallery (
    GalleryID INT PRIMARY KEY,
    Name VARCHAR(255),
    Description_ TEXT,
    Location VARCHAR(255),
    Curator INT,
    OpeningHours VARCHAR(255)
);

-- User_Favorite_Artwork Junction Table
CREATE TABLE User_Favorite_Artwork (
    UserID INT,
    ArtworkID INT,
    PRIMARY KEY (UserID, ArtworkID)
);

-- Artwork_Gallery Junction Table
CREATE TABLE Artwork_Gallery (
    ArtworkID INT,
    GalleryID INT,
    PRIMARY KEY (ArtworkID, GalleryID)
);
alter table artwork add constraint foreign key(ArtistID) references artist(ArtistID) on delete cascade;
alter table artwork_gallery add constraint foreign key (ArtworkID) references artwork(ArtworkID) on delete cascade; 
alter table artwork_gallery add constraint foreign key(GalleryID) references gallery(GalleryID) on delete cascade;
alter table gallery add constraint foreign key(Curator) references artist(ArtistID) on delete cascade;
alter table user_favorite_artwork add constraint foreign key(UserID) references user(UserID);
alter table user_favorite_artwork add constraint foreign key(ArtworkID) references  artwork(ArtworkID);

INSERT INTO Artist VALUES
(2, 'Frida Kahlo', 'Mexican painter known for self-portraits', '1907-07-06', 'Mexican', 'https://fridakahlo.org', 'info@fridakahlo.org'),
(3, 'Leonardo da Vinci', 'Renaissance polymath', '1452-04-15', 'Italian', 'https://leonardodavinci.com', 'inquiries@leonardo.com'),
(4, 'Georgia O''Keeffe', 'American modernist artist', '1887-11-15', 'American', 'https://okeeffemuseum.org', 'support@okeeffeart.com'),
(5, 'Pablo Picasso', 'Spanish painter and sculptor', '1881-10-25', 'Spanish', 'https://picasso.fr', 'info@picassoartfoundation.org');

-- Insert data into Artwork Table
INSERT INTO Artwork VALUES

(2, 'The Two Fridas', 'Depicts two versions of Frida Kahlo', '1939-09-04', 'Oil on canvas', 'https://fridakahlo.org/two-fridas.jpg', 2),
(3, 'Mona Lisa', 'Famous portrait with mysterious smile', '1503-08-21', 'Oil on poplar panel', 'https://leonardodavinci.com/monalisa.jpg', 3),
(4, 'Black Iris', 'Close-up view of a flower', '1926-06-01', 'Oil on canvas', 'https://okeeffemuseum.org/black-iris.jpg', 4),
(5, 'Guernica', 'Anti-war masterpiece by Picasso', '1937-04-26', 'Oil on canvas', 'https://picasso.fr/guernica.jpg', 5);

-- Insert data into Artwork_Gallery Table
INSERT INTO Artwork_Gallery VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

Alter table gallery modify OpenningHours varchar(255);
-- Insert data into Gallery Table
INSERT INTO Gallery VALUES
(1, 'Museum of Modern Art', 'Leading modern art museum', 'New York, USA', 1, '10 AM - 6 PM'),
(2, 'Frida Kahlo Museum', 'Dedicated to Frida Kahlo', 'Mexico City, Mexico', 2, '9 AM - 5 PM'),
(3, 'Louvre Museum', 'World''s largest art museum', 'Paris, France',3,'12 AM - 5 PM');

INSERT INTO User VALUES
(1, 'user1', 'password1', 'user1@example.com', 'John', 'Doe', '1990-05-15', NULL, '3,5'),
(2, 'user2', 'password2', 'user2@example.com', 'Jane', 'Smith', '1985-10-20', NULL, '1,2,4'),
(3, 'curator1', 'curatorpw', 'curator@example.com', 'Curator', 'One', '1975-03-08', NULL, NULL);

-- Insert data into User_Favorite_Artwork Table
INSERT INTO User_Favorite_Artwork VALUES
(1, 3),
(1, 5),
(2, 1),
(2, 2),
(2, 4);

INSERT INTO Artwork_Gallery VALUES
(1, 1),
(2, 2),
(3, 3);
