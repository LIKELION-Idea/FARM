CREATE DATABASE IF NOT EXISTS farmdb
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;

USE farmdb;

CREATE TABLE IF NOT EXISTS farm_profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    region VARCHAR(100),
    soil_type VARCHAR(50),
    soil_type_etc VARCHAR(100),
    environment VARCHAR(50),
    environment_etc VARCHAR(100),
    drainage VARCHAR(20),
    drainage_etc VARCHAR(100),
    level VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
