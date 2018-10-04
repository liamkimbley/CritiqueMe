-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema critiquemedb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `critiquemedb` ;

-- -----------------------------------------------------
-- Schema critiquemedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `critiquemedb` DEFAULT CHARACTER SET utf8 ;
USE `critiquemedb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(70) NOT NULL,
  `username` VARCHAR(70) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `role` VARCHAR(45) NOT NULL DEFAULT 'standard',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `profile` ;

CREATE TABLE IF NOT EXISTS `profile` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(80) NOT NULL,
  `last_name` VARCHAR(80) NOT NULL,
  `bio` TEXT NULL,
  `location_id` INT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_profile_location_idx` (`location_id` ASC),
  INDEX `fk_user_profile_idx` (`user_id` ASC),
  CONSTRAINT `fk_profile_location`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_profile`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `profile_id` INT NOT NULL,
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_profile_idx` (`profile_id` ASC),
  CONSTRAINT `fk_post_profile`
    FOREIGN KEY (`profile_id`)
    REFERENCES `profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post_category` ;

CREATE TABLE IF NOT EXISTS `post_category` (
  `post_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  INDEX `fk_post_category_post_idx` (`post_id` ASC),
  INDEX `fk_post_category_category_idx` (`category_id` ASC),
  CONSTRAINT `fk_post_category_post`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_category_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `profile_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_post_idx` (`post_id` ASC),
  INDEX `fk_comment_profile_idx` (`profile_id` ASC),
  CONSTRAINT `fk_comment_profile`
    FOREIGN KEY (`profile_id`)
    REFERENCES `profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_post`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `friend` ;

CREATE TABLE IF NOT EXISTS `friend` (
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
  INDEX `fk_Friend_User1_idx` (`user_id` ASC),
  INDEX `fk_Friend_User2_idx` (`friend_id` ASC),
  CONSTRAINT `fk_user_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_friend`
    FOREIGN KEY (`friend_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vote` ;

CREATE TABLE IF NOT EXISTS `vote` (
  `comment_id` INT NOT NULL,
  `profile_id` INT NOT NULL,
  `vote` TINYINT NULL,
  INDEX `fk_vote_profile_idx` (`profile_id` ASC),
  INDEX `fk_vote_comment_idx` (`comment_id` ASC),
  PRIMARY KEY (`comment_id`, `profile_id`),
  CONSTRAINT `fk_vote_comment`
    FOREIGN KEY (`comment_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vote_profile`
    FOREIGN KEY (`profile_id`)
    REFERENCES `profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `expertise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `expertise` ;

CREATE TABLE IF NOT EXISTS `expertise` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `profile_expertise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `profile_expertise` ;

CREATE TABLE IF NOT EXISTS `profile_expertise` (
  `profile_id` INT NOT NULL,
  `expertise_id` INT NOT NULL,
  INDEX `fk_profile_expertise_expertise_idx` (`expertise_id` ASC),
  INDEX `fk_profile_expertise_profile_idx` (`profile_id` ASC),
  CONSTRAINT `fk_profile_expertise_profile`
    FOREIGN KEY (`profile_id`)
    REFERENCES `profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_profile_expertise_expertise`
    FOREIGN KEY (`expertise_id`)
    REFERENCES `expertise` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS critique@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'critique'@'localhost' IDENTIFIED BY 'critique';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'critique'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (DEFAULT, 'test@sd.com', 'test', 'test', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (DEFAULT, 'mjones@gmail.com', 'mjones', 'who', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (DEFAULT, 'jeffy@yahoo.com', 'myusernameisjeff', 'mynameisjeff', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (DEFAULT, 'mbyrde@aol.com', 'martybyrde', 'moneylaundering', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (DEFAULT, 'wwchemist@hotmail.com', 'waltw', 'skylar', 1, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (DEFAULT, 'Denver', 'CO', 'US');
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (DEFAULT, 'Houston', 'TX', 'US');
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (DEFAULT, 'Los Angeles', 'CA', 'US');
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (DEFAULT, 'Ozarks', 'MO', 'US');
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (DEFAULT, 'Albequerque', 'NM', 'US');

COMMIT;


-- -----------------------------------------------------
-- Data for table `profile`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`) VALUES (DEFAULT, 'test', 'test', 'test', 1, 1);
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`) VALUES (DEFAULT, 'Mike', 'Jones', 'who is mike jones?', 2, 2);
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`) VALUES (DEFAULT, 'Jeff', 'Jefferson', 'my name is jeff', 3, 3);
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`) VALUES (DEFAULT, 'Marty', 'Byrde', 'come check out my lakehouse', 4, 4);
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`) VALUES (DEFAULT, 'Walter', 'White', 'chemisty teacher', 5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`) VALUES (DEFAULT, 2, DEFAULT, DEFAULT, 'buy my album');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`) VALUES (DEFAULT, 3, DEFAULT, DEFAULT, 'my name is jeff');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`) VALUES (DEFAULT, 4, DEFAULT, DEFAULT, 'Anyone want to invest with me?');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`) VALUES (DEFAULT, 5, DEFAULT, DEFAULT, 'How does my carwash look?');

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'Music');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'Sports');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'Fashion');
INSERT INTO `category` (`id`, `name`) VALUES (4, 'Technology');
INSERT INTO `category` (`id`, `name`) VALUES (5, 'Food');
INSERT INTO `category` (`id`, `name`) VALUES (6, 'Political');
INSERT INTO `category` (`id`, `name`) VALUES (7, 'Business');
INSERT INTO `category` (`id`, `name`) VALUES (8, 'Finance');
INSERT INTO `category` (`id`, `name`) VALUES (9, 'Social');
INSERT INTO `category` (`id`, `name`) VALUES (10, 'Travel');
INSERT INTO `category` (`id`, `name`) VALUES (11, 'Event Planning');
INSERT INTO `category` (`id`, `name`) VALUES (12, 'Home Improvement');
INSERT INTO `category` (`id`, `name`) VALUES (13, 'Medical');
INSERT INTO `category` (`id`, `name`) VALUES (14, 'Fitness');
INSERT INTO `category` (`id`, `name`) VALUES (15, 'Pets');
INSERT INTO `category` (`id`, `name`) VALUES (16, 'Lifestyle');
INSERT INTO `category` (`id`, `name`) VALUES (17, 'Jobs');
INSERT INTO `category` (`id`, `name`) VALUES (18, 'Collectables');
INSERT INTO `category` (`id`, `name`) VALUES (19, 'Entertainment');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (1, 1);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (2, 19);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (3, 8);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (4, 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (1, 'no', DEFAULT, DEFAULT, 3, 1);
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (2, 'make me', DEFAULT, DEFAULT, 4, 1);
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (3, 'I might be interested', DEFAULT, DEFAULT, 5, 3);
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (4, 'Looks good, I might stop by ;)', DEFAULT, DEFAULT, 4, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `friend`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `friend` (`user_id`, `friend_id`) VALUES (4, 5);
INSERT INTO `friend` (`user_id`, `friend_id`) VALUES (5, 4);
INSERT INTO `friend` (`user_id`, `friend_id`) VALUES (2, 3);
INSERT INTO `friend` (`user_id`, `friend_id`) VALUES (3, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `vote`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `vote` (`comment_id`, `profile_id`, `vote`) VALUES (3, 5, 1);
INSERT INTO `vote` (`comment_id`, `profile_id`, `vote`) VALUES (4, 4, 1);
INSERT INTO `vote` (`comment_id`, `profile_id`, `vote`) VALUES (1, 1, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `expertise`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `expertise` (`id`, `title`) VALUES (1, 'Education');
INSERT INTO `expertise` (`id`, `title`) VALUES (2, 'Software Developer');
INSERT INTO `expertise` (`id`, `title`) VALUES (3, 'Medicine');
INSERT INTO `expertise` (`id`, `title`) VALUES (4, 'Athletics');
INSERT INTO `expertise` (`id`, `title`) VALUES (5, 'Music');
INSERT INTO `expertise` (`id`, `title`) VALUES (6, 'Art');
INSERT INTO `expertise` (`id`, `title`) VALUES (7, 'Photography');
INSERT INTO `expertise` (`id`, `title`) VALUES (8, 'Cartography');
INSERT INTO `expertise` (`id`, `title`) VALUES (9, 'Business');
INSERT INTO `expertise` (`id`, `title`) VALUES (10, 'Law');
INSERT INTO `expertise` (`id`, `title`) VALUES (11, 'Literature');
INSERT INTO `expertise` (`id`, `title`) VALUES (12, 'History');
INSERT INTO `expertise` (`id`, `title`) VALUES (13, 'Psychology');
INSERT INTO `expertise` (`id`, `title`) VALUES (14, 'Economics');
INSERT INTO `expertise` (`id`, `title`) VALUES (15, 'Government');
INSERT INTO `expertise` (`id`, `title`) VALUES (16, 'Automobiles');
INSERT INTO `expertise` (`id`, `title`) VALUES (17, 'Design');
INSERT INTO `expertise` (`id`, `title`) VALUES (18, 'Mechanical');
INSERT INTO `expertise` (`id`, `title`) VALUES (19, 'Pop Culture');

COMMIT;


-- -----------------------------------------------------
-- Data for table `profile_expertise`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (5, 9);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (4, 9);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (3, 19);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (2, 5);

COMMIT;

