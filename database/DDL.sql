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
-- Table `COVID19_db`.`COVID19`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `COVID19_db`.`COVID19` ;

CREATE TABLE IF NOT EXISTS `COVID19_db`.`COVID19` (
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