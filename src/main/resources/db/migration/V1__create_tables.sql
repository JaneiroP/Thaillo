-- V1__init.sql

CREATE TABLE users (
id SERIAL PRIMARY KEY,
name VARCHAR(255),
email VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL
);

CREATE TABLE boards (
id SERIAL PRIMARY KEY,
title VARCHAR(255),
description TEXT,
background VARCHAR(50)
);

CREATE TABLE task_list (
id SERIAL PRIMARY KEY,
title VARCHAR(255),
background VARCHAR(50)
);
