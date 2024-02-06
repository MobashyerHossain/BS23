CREATE DATABASE IF NOT EXISTS testspringbootdb;

use testspringbootdb;

CREATE TABLE users(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    address VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS log_cruds (
     id INT NOT NULL AUTO_INCREMENT,
     data_type varchar(50) NOT NULL,
     data_id INT NOT NULL,
     action VARCHAR(20) NOT NULL,
     timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY(id)
);