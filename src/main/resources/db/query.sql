SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS heroes(
id int PRIMARY KEY auto_increment,
superName VARCHAR,
superPower VARCHAR,
weakness VARCHAR,
age INTEGER,
teamId INTEGER,
);

CREATE TABLE IF NOT EXISTS teams(
id int PRIMARY KEY auto_increment,
teamName VARCHAR,
teamCause VARCHAR,
teamSize INTEGER,
);