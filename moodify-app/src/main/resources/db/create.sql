 SET MODE PostgreSQL;

CREATE DATABASE moodify IF NOT EXISTS;

\c moodify;

 CREATE TABLE users (id  SERIAL PRIMARY KEY, name TEXT);

 CREATE TABLE moods (id  SERIAL PRIMARY KEY appname TEXT, appinfo TEXT,downloads TEXT,rating TEXT,moodtype TEXT);

 CREATE TABLE admins (id  SERIAL PRIMARY KEY,moodname TEXT,popularity TEXT,userid INTEGER);


