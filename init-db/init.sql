CREATE DATABASE IF NOT EXISTS dbjava;
USE dbjava;
SOURCE /docker-entrypoint-initdb.d/dbjava_dump.sql;
