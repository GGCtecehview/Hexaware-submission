create database pract2;
use pract2;

create table artist(
	art_id int auto_increment primary key ,
	name varchar(50), 
	bio varchar(250),
	Birth_date date, Nationality varchar(50),
    weburlURL varchar(250), 
    contact_infomation int );
select * from artist;

create table artwork(
	artw_id int auto_increment primary key ,
	title varchar(50), 
	Description varchar(100),
	creation_date date, medium varchar(50),
	ImageURL varchar(250) ,
    art_id int,
    foreign key(art_id)
    references artist(art_id));
    
    
select * from artwork;

 create table user (
	User_id int auto_increment primary key, 
	Username varchar(50),
	Password varchar(50),
    email varchar(50), 
    first_name varchar(50), 
	last_name varchar(50),
    dateBirth date,
    Profile_p varchar(100),
    FavoriteArtwork int,
    foreign key(favoriteArtwork)
    references artwork(artw_id));
    
create table gallery (
	gallery_id int auto_increment primary key,
    name varchar(50),
    description varchar(250), 
    location varchar(100),
    curator int,
    foreign key(curator)
    references artist(art_id),
    Opening_time time
);

create table User_fav(
	user_id int,
    artw_id int,
    foreign key(user_id)
    references user(user_id),
    foreign key(artw_id)
    references artwork(artw_id)
    );
    
create table artwork_gallery(
	artw_id int,
    gallery_id int,
    foreign key(artw_id)
    references artwork(artw_id),
    foreign key(gallery_id)
    references gallery(gallery_id));

ALTER TABLE artist MODIFY contact_infomation bigint;

    
INSERT INTO artist (name, bio, Birth_date, Nationality, weburlURL, contact_infomation) VALUES 
('Aarav Kumar', 'Contemporary abstract artist known for vibrant colors', '1975-03-10', 'Indian', 'http://www.aaravkumarart.com', 9876543210),
('Isha Patel', 'Famous for traditional Indian folk art', '1982-07-22', 'Indian', 'http://www.ishapatelart.com', 9876543211),
('Raj Mehta', 'Landscape artist inspired by rural India', '1968-11-15', 'Indian', 'http://www.rajmehtaart.com', 9876543212),
('Anjali Rao', 'Modern artist with a focus on digital media', '1990-05-30', 'Indian', 'http://www.anjaliraoart.com', 9876543213),
('Suresh Singh', 'Painter specializing in portraits', '1971-02-14', 'Indian', 'http://www.sureshsinghart.com', 9876543214),
('Deepika Das', 'Artist known for her surrealistic paintings', '1985-09-09', 'Indian', 'http://www.deepikadasart.com', 9876543215),
('Amit Shah', 'Abstract expressionist with a bold use of color', '1979-12-03', 'Indian', 'http://www.amitshahart.com', 9876543216),
('Neha Gupta', 'Creates artwork inspired by Indian mythology', '1983-04-18', 'Indian', 'http://www.nehaguptaart.com', 9876543217),
('Vijay Kumar', 'Specializes in watercolor landscapes', '1972-06-25', 'Indian', 'http://www.vijaykumarart.com', 9876543218),
('Priya Singh', 'Famous for mixed media and installation art', '1978-08-20', 'Indian', 'http://www.priyasinghart.com', 9876543219);

INSERT INTO artwork (title, Description, creation_date, medium, ImageURL, art_id) VALUES 
('Eternal Spring', 'An abstract representation of spring season', '2021-03-15', 'Acrylic on Canvas', 'http://imageurl.com/eternalspring.jpg', 1),
('Folklore', 'Capturing the essence of traditional Indian stories', '2020-07-22', 'Oil on Canvas', 'http://imageurl.com/folklore.jpg', 2),
('Rural Serenity', 'Landscape depicting Indian rural life', '2019-11-11', 'Watercolor', 'http://imageurl.com/ruralserenity.jpg', 3),
('Digital Dreams', 'Exploring the intersection of technology and art', '2022-05-20', 'Digital Art', 'http://imageurl.com/digitaldreams.jpg', 4),
('The Thinker', 'Portrait of a man lost in thought', '2018-02-14', 'Oil on Canvas', 'http://imageurl.com/thethinker.jpg', 5),
('Dreamscape', 'Surrealistic interpretation of dreams', '2021-09-09', 'Mixed Media', 'http://imageurl.com/dreamscape.jpg', 6),
('Colorful Emotions', 'Expressing emotions through colors', '2022-12-01', 'Acrylic', 'http://imageurl.com/coloremotions.jpg', 7),
('Mythical Echoes', 'Artwork inspired by Indian mythology', '2020-04-18', 'Acrylic on Canvas', 'http://imageurl.com/mythicalechoes.jpg', 8),
('Morning Hues', 'Watercolor landscape of a sunrise', '2018-06-25', 'Watercolor', 'http://imageurl.com/morninghues.jpg', 9),
('Urban Melody', 'Mixed media representation of urban life', '2021-08-20', 'Mixed Media', 'http://imageurl.com/urbanmelody.jpg', 10);

INSERT INTO user (Username, Password, email, first_name, last_name, dateBirth, Profile_p, FavoriteArtwork) VALUES 
('arjun123', 'password1', 'arjun123@example.com', 'Arjun', 'Kumar', '1990-01-01', 'http://profilepic.com/arjun123.jpg', 1),
('sita456', 'password2', 'sita456@example.com', 'Sita', 'Rao', '1985-02-02', 'http://profilepic.com/sita456.jpg', 2),
('rajesh789', 'password3', 'rajesh789@example.com', 'Rajesh', 'Singh', '1978-03-03', 'http://profilepic.com/rajesh789.jpg', 3),
('meena101', 'password4', 'meena101@example.com', 'Meena', 'Patel', '1992-04-04', 'http://profilepic.com/meena101.jpg', 4),
('vishal202', 'password5', 'vishal202@example.com', 'Vishal', 'Gupta', '1980-05-05', 'http://profilepic.com/vishal202.jpg', 5),
('priya303', 'password6', 'priya303@example.com', 'Priya', 'Shah', '1995-06-06', 'http://profilepic.com/priya303.jpg', 6),
('anil404', 'password7', 'anil404@example.com', 'Anil', 'Verma', '1983-07-07', 'http://profilepic.com/anil404.jpg', 7),
('neha505', 'password8', 'neha505@example.com', 'Neha', 'Kapoor', '1991-08-08', 'http://profilepic.com/neha505.jpg', 8),
('akash606', 'password9', 'akash606@example.com', 'Akash', 'Mehta', '1986-09-09', 'http://profilepic.com/akash606.jpg', 9),
('ritu707', 'password10', 'ritu707@example.com', 'Ritu', 'Bose', '1988-10-10', 'http://profilepic.com/ritu707.jpg', 10);


INSERT INTO gallery (name, description, location, curator, Opening_time) VALUES 
('Modern Art Gallery', 'A gallery featuring contemporary art from Indian artists', 'Mumbai, India', 1, '09:00:00'),
('Traditional Art Hub', 'Dedicated to traditional Indian art forms', 'Delhi, India', 2, '10:00:00'),
('Digital Art Space', 'A space for exploring digital and new media art', 'Bangalore, India', 4, '11:00:00'),
('Portrait Gallery', 'Exhibiting portraits from various Indian artists', 'Kolkata, India', 5, '09:30:00'),
('Surreal Art Gallery', 'Gallery showcasing surrealistic art by Indian artists', 'Chennai, India', 6, '10:30:00'),
('Expressionist Art Gallery', 'Featuring bold and colorful abstract art', 'Pune, India', 7, '11:30:00'),
('Mythological Art Center', 'Artworks inspired by Indian mythology', 'Jaipur, India', 8, '12:00:00'),
('Landscape Art Gallery', 'Dedicated to landscapes in various mediums', 'Hyderabad, India', 9, '09:15:00'),
('Mixed Media Gallery', 'A mix of different art mediums and styles', 'Ahmedabad, India', 10, '10:15:00'),
('Urban Art Exhibition', 'Art reflecting urban life and themes', 'Surat, India', 3, '11:15:00');

INSERT INTO User_fav (user_id, artw_id) VALUES 
(1, 10),
(2, 9),
(3, 8),
(4, 7),
(5, 6),
(6, 5),
(7, 4),
(8, 3),
(9, 2),
(10, 1);

INSERT INTO artwork_gallery (artw_id, gallery_id) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);



select * from artist;