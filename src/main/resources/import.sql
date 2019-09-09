// create schema gallerydb;

DROP TABLE IF EXISTS file;
CREATE TABLE file (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      data  BLOB NOT NULL,
                      file_name  VARCHAR(250) NOT NULL,
                      file_type  VARCHAR(250) NOT NULL,
                      file_size  LONG NOT NULL,
                      description  VARCHAR(250) NOT NULL

);

DROP TABLE IF EXISTS file_tag;
CREATE TABLE file_tag (
                     file_id INT AUTO_INCREMENT PRIMARY KEY,
                     tag_id  VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS tag;
CREATE TABLE tag (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      tag_name  VARCHAR(250) NOT NULL
);

INSERT INTO tag (id, tag_name) VALUES
(1, 'testTag');
/* ,
('2352'),
('NEWTag') */

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  address VARCHAR(250) DEFAULT NULL
);

INSERT INTO user (first_name, last_name, address) VALUES
  ('Aliko', 'Dangote', 'Billionaire Industrialist'),
  ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
  ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');

/*
DROP TABLE IF EXISTS images;

CREATE TABLE images (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      tag_id INT,
                      data BLOB NOT NULL,
                      file_name VARCHAR(250),
                      file_type VARCHAR(250),
                      description VARCHAR(250)
);

DROP TABLE IF EXISTS tags;

CREATE TABLE tags (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       tag_name  VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS note;

CREATE TABLE note (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        title  VARCHAR(250) ,
                        content  VARCHAR(250),
                        createdAt  VARCHAR(250),
                        updatedAt  VARCHAR(250)
);
INSERT INTO note (title, content, createdAt, updatedAt) VALUES
('Aliko', 'Dangote', 'created at', 'updated1at'),
('Bill', 'Gates', 'created at', 'updated1at'),
('Folrunsho', 'Alakija', 'created at', 'updated1at');


DROP TABLE IF EXISTS student;

CREATE TABLE student (
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     first_name  VARCHAR(250) NOT NULL,
                     last_name  VARCHAR(250) NOT NULL,
                     email  VARCHAR(250) NOT NULL
);
INSERT INTO student (first_name, last_name, email) VALUES
('Aliko', 'Dangote', 'asdsad'),
('Bill', 'Gates', '5as1d'),
('Folrunsho', 'Alakija', '65s1das');

 */

