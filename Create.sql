create database movieDb;
use movieDb;


CREATE TABLE IF NOT EXISTS country (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX name (name ASC) VISIBLE
  );


CREATE TABLE IF NOT EXISTS city (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  country_id INT NULL DEFAULT NULL,
  PRIMARY KEY (id),
  INDEX country_id (country_id ASC) VISIBLE,
  CONSTRAINT city_ibfk_1
    FOREIGN KEY (country_id)
    REFERENCES country(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS director (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  city_id INT NULL DEFAULT NULL,
  PRIMARY KEY (id),
  INDEX FK_city_id_ (city_id ASC) VISIBLE,
  CONSTRAINT FK_city_id_
    FOREIGN KEY (city_id)
    REFERENCES city (id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    );


CREATE TABLE IF NOT EXISTS genre (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX name (name ASC) VISIBLE
  );


CREATE TABLE IF NOT EXISTS movie (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  year INT NOT NULL,
  runtime INT NOT NULL,
  rating FLOAT NOT NULL,
  votes INT NOT NULL,
  image_url VARCHAR(255) NOT NULL,
  city_id INT NULL DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX title (title ASC) VISIBLE,
  INDEX FK_city_id__ (city_id ASC) VISIBLE,
  CONSTRAINT FK_city_id__
    FOREIGN KEY (city_id)
    REFERENCES city(id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    );


CREATE TABLE IF NOT EXISTS movie_director (
  movie_id INT NOT NULL,
  director_id INT NOT NULL,
  PRIMARY KEY (movie_id, director_id),
  INDEX director_id (director_id ASC) VISIBLE,
  CONSTRAINT movies_director_ibfk_1
    FOREIGN KEY (movie_id)
    REFERENCES movie (id)
    ON DELETE CASCADE,
  CONSTRAINT movie_director_ibfk_2
    FOREIGN KEY (director_id)
    REFERENCES director (id)
    ON DELETE CASCADE
    );


CREATE TABLE IF NOT EXISTS movie_genre (
  movie_id INT NOT NULL,
  genre_id INT NOT NULL,
  PRIMARY KEY (movie_id, genre_id),
  INDEX genre_id (genre_id ASC) VISIBLE,
  CONSTRAINT movie_genre_ibfk_1
    FOREIGN KEY (movie_id)
    REFERENCES movie (id)
    ON DELETE CASCADE,
  CONSTRAINT movie_genre_ibfk_2
    FOREIGN KEY (genre_id)
    REFERENCES genre(id)
    ON DELETE CASCADE
    );


CREATE TABLE IF NOT EXISTS star(
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  city_id INT NULL DEFAULT NULL,
  PRIMARY KEY (id),
  INDEX FK_city_id (city_id ASC) VISIBLE,
  CONSTRAINT FK_city_id
    FOREIGN KEY (city_id)
    REFERENCES city(id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS movie_star (
  movie_id INT NOT NULL,
  star_id INT NOT NULL,
  PRIMARY KEY (movie_id, star_id),
  INDEX star_id (star_id ASC) VISIBLE,
  CONSTRAINT movie_star_ibfk_1
    FOREIGN KEY (movie_id)
    REFERENCES movie(id)
    ON DELETE CASCADE,
  CONSTRAINT movie_star_ibfk_2
    FOREIGN KEY (star_id)
    REFERENCES star(id)
    ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS user (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  username VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  city_id INT NULL DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX username (username ASC) VISIBLE,
  UNIQUE INDEX email (email ASC) VISIBLE,
  INDEX FK_city (city_id ASC) VISIBLE,
  CONSTRAINT FK_city
    FOREIGN KEY (city_id)
    REFERENCES city(id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    );


CREATE TABLE IF NOT EXISTS order_info (
  id INT NOT NULL AUTO_INCREMENT,
  customer_id INT NOT NULL,
  movie_id INT NOT NULL,
  order_date DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  INDEX customer_id (customer_id ASC) VISIBLE,
  INDEX movie_id (movie_id ASC) VISIBLE,
  CONSTRAINT order_ibfk_1
    FOREIGN KEY (customer_id)
    REFERENCES user(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT order_ibfk_2
    FOREIGN KEY (movie_id)
    REFERENCES movie(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS rating(
  user_id INT NOT NULL,
  movie_id INT NOT NULL,
  rating FLOAT NOT NULL,
  PRIMARY KEY (user_id, movie_id),
  INDEX movie_id (movie_id ASC) VISIBLE,
  CONSTRAINT rating_ibfk_1
    FOREIGN KEY (user_id)
    REFERENCES user(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT rating_ibfk_2
    FOREIGN KEY (movie_id)
    REFERENCES movie(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );

