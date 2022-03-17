-- -----------------------------------------------------
-- Schema COVID19_db
-- -----------------------------------------------------
-- This is the database for the incidents reporting java application.
DROP SCHEMA IF EXISTS `COVID19_db` ;

-- -----------------------------------------------------
-- Schema COVID19_db
--
-- This is the database for the COVID19 java application.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `COVID19_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `COVID19_db` ;

-- -----------------------------------------------------
-- Table `COVID19_db`.`WorldData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `COVID19_db`.`World_Data` ;

CREATE TABLE IF NOT EXISTS `COVID19_db`.`World_Data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `FIPS` INT NULL,
  `Admin2` VARCHAR(255) ,
  `Province_State` VARCHAR(255) NULL,
  `Country_Region` VARCHAR(255) NULL,
  `Last_Update` TIMESTAMP NULL,
  `Lat` FLOAT NULL,
  `Long_` FLOAT NULL,
  `Confirmed` INT NULL,
  `Deaths` INT NULL,
  `Recovered` INT NULL,
  `Active` INT NULL,
  `Combined_Key` VARCHAR(255) NULL,
  `Incident_Rate` DOUBLE NULL,
  `Case_Fatality_Ratio` DOUBLE NULL,
  `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `updated` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `COVID19_db`.`USData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `COVID19_db`.`US_Data` ;

CREATE TABLE IF NOT EXISTS `COVID19_db`.`US_Data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Province_State` VARCHAR(255) NULL,
  `Country_Region` CHAR(2) NULL,
  `Last_Update` TIMESTAMP NULL,
  `Lat` FLOAT(4) NULL,
  `Long_` FLOAT(4) NULL,
  `Confirmed` INT NULL,
  `Deaths` INT NULL,
  `Recovered` INT NULL,
  `Active` INT NULL,
   `FIPS` INT NULL,
  `Incident_Rate` FLOAT(24) NULL,
  `Total_Test_Results` INT NULL,
  `People_Hospitalized` INT NULL,
  `Case_Fatality_Ratio` FLOAT(24) NULL,
  `UID` INT NOT NULL,
  `ISO3` CHAR(3) NOT NULL,
  `Testing_Rate` FLOAT(24) NULL,
  `Hospitalization_Rate` FLOAT(24) NULL,
  `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `updated` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;