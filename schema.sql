CREATE DATABASE IF NOT EXISTS farmdb
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;

USE farmdb;

DROP TABLE IF EXISTS farm_profile;

CREATE TABLE IF NOT EXISTS farm_profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    region VARCHAR(100) NOT NULL,
    soil_type VARCHAR(50) NOT NULL,
    soil_type_etc VARCHAR(100),
    environment VARCHAR(50) NOT NULL,
    environment_etc VARCHAR(100),
    drainage VARCHAR(20) NOT NULL,
    drainage_etc VARCHAR(100),
    level VARCHAR(10) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
