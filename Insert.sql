use movieDb;

insert into country(name)
values
('France'),
('USA'),
('Bosnia and Herzegovina'),
('Croatia'),
('Serbia'),
('England'),
('Italy'),
('Mexico'),
('Germany'),
('Albania'),
('Greece'),
('Portugal'),
('Poland'),
('Russia'),
('Macedonia');



insert into genre(name)
values
('Comedy'),
('Thriller'),
('Horror'),
('Action'),
('Science'),
('Fantasy'),
('Tragedy');

insert into city(name,country_id) values
('Tuzla',3),('Sarajevo',3),('Lukavac',3),('Liverpool',6),('Manchester',6),('London',6),('Newcastle',6),('Zagreb',4),('Varazdin',4),('Paris',1),('Lyon',1),('Berlin',9),('Hamburg',9),
('Stuttgart',9),('Dortmund',9),('Munchen',9),('Milano',11),('Torino',11),('Belgrade',5),('Krakow',13),('Lisbon',12),('Cartaxo',12),('Braga',12),('Guarda',12),('Moscow',14),('Kazan',14),
('Omsk',14),('New York',2),('Miami',2),('Los Angeles',2),('Houston',2),('Dallas',2),('Atlanta',2),('Boston',2),('Charlotte',2),('Milwaukee',2),('Bolton',6),('Cleveland',2),('Chicago',2);



insert into movie(title,year,runtime,rating,votes,image_url,city_id)
values
('The dark knight',2008,150,9.8,3000000,'No url1',28),
('The dark knight rises',2012,160,9.0,300000,'No url2',28),
('The Joker',2019,100,10.0,5000000,'No url3',29),
('Suicide squad',2017,120,6.5,1000000,'No url4',33);

insert into movie(title,year,runtime,rating,votes,image_url,city_id)
values
('Parasite',2019,200,9.4,450000,'No image url',30),
('Avenger:Endgame',2019,172,9.4,450000,'No image url',30),
('Us',2019,200,8.4,450000,'No image url',31),
('Knives Out',2019,118,9.1,450000,'No image url',29),
('Toy Story 4',2019,133,7.4,450000,'No image url',35),
('Irishman',2019,150,6.9,450000,'No image url',36),
('Little Women',2019,120,8.7,450000,'No image url',37);

insert into movie(title,year,runtime,rating,votes,image_url,city_id)
values
('Casablanca',1942,200,9.4,450000,'No image url',39),
('Get Out',2017,172,9.4,450000,'No image url',38),
('Lady Bird',2017,200,8.4,450000,'No image url',32),
('Black Panther',2018,118,9.1,450000,'No image url',33),
('Citizen Kane',1941,133,7.4,450000,'No image url',34),
('Monsters',2001,150,6.9,450000,'No image url',29),
('Chicken Run',2000,120,8.7,450000,'No image url',30);


insert into movie(title,year,runtime,rating,votes,image_url,city_id)
values
('The Shwanshank Redemption',1994,200,9.2,450000,'No image url',29),
('The Godfather',1972,400,9.2,450000,'No image url',30),
('The Godfather:Part II',1974,270,9.0,450000,'No image url',31),
('Angry Men',1957,118,8.9,450000,'No image url',32),
('Schindlers List',1993,133,8.9,450000,'No image url',33),
('The Lord of the Rings:The return of the King',2003,150,8.9,450000,'No image url',34),
('Pulp Fiction',1994,120,8.8,450000,'No image url',30);

insert into user(first_name,last_name,username,email,city_id) values
('Meho','Mehic','MehoM','Meho@gmail.com',1),
('Zlatko','Zlatkic','ZZlaja','ZZlaja@gmail.com',2),
('Bajro','Bajric','BajroB','BajroB@hotmail.com',3),
('John','Doe','JohnDoe','JohnDoe@yahoo.com',6),
('Ramo','Ramic','RamoR','RamoR@gmail.com',5),
('Bego','Begic','BBego','BBego@outlook.com',6),
('Mujo','Mujic','MujoM','MujoM@gmail.com',4),
('Saban','Sabanovic','SabanS','SabanS@gmail.com',31),
('Sajo','Sajcung','SSajo','SSajo@gmail.com',31),
('Hakala','Hakalic','Hakala01','Hakala01@gmail.com',33);

insert into star(first_name,last_name,city_id)
values
('Jack','Nicholson',31),
('Marlon','Brando',33),
('Robert','De Niro',35),
('Al','Pacino',36),
('Dustin','Hoffman',31),
('Tom','Hanks',28),
('Anthony','Hopkins',29),
('Paul','Newman',30),
('Denzel','Washington',34),
('Spencer','Tracy',37);

insert into star(first_name,last_name,city_id)
values
('Joaquin','Phoenix',30),
('Heath','Ledger',31),
('Christian','Bale',35);

insert into director(first_name,last_name,city_id)
values
('Christopher','Nolan',30),
('Todd','Philips',28);

insert into director(first_name,last_name,city_id)
values
('Stanley','Kubrick',28),
('Ingmar','Bergman',29),
('Alfred','Hitchcock',30),
('Akira','Kurosawa',31),
('Orson','Welles',32),
('Federico','Fellini',33),
('John','Ford',34),
('Jean-Luc','Godard',35),
('Martin','Scorsese',36),
('Jean','Renoir',29);

insert into rating(user_id,movie_id,rating)values
(1,1,9.5),(2,2,8.5),(3,3,7.5),(1,3,6.8),(4,4,10.0),(5,2,8.3);

insert into order_info(customer_id,movie_id) values
(1,1),
(5,1),
(4,1),
(3,2),
(2,3),
(1,4),
(1,5);

select * from movie_director;

insert into movie_director  values(1,1),(1,2),(3,6),(4,1),(8,2),(8,1),(9,5),(11,2),(11,7);
insert into movie_star values (1,1),(1,2),(3,6),(4,1),(8,2),(8,1),(9,5),(11,2),(11,7);
insert into movie_genre values(1,2),(1,4),(3,2),(4,7),(8,3),(8,5),(9,1),(11,1),(11,3);

alter table user add column password varchar(255) after email;

alter table user add column reset_key varchar(255) unique after password;
alter table user add reset_date date after reset_key;
