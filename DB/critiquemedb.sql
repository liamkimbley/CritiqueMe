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
  `image` TEXT NULL,
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
  `title` VARCHAR(100) NOT NULL,
  `media` TEXT NULL,
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
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (DEFAULT, 'test@sd.com', 'test', '$2a$10$4WvWHVwZ6Vuhq9/zY85ZbOs/OYHY07ZC5df7/t2GzgFv//HRvnV6G', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (DEFAULT, 'mjones@gmail.com', 'mjones', 'who', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (DEFAULT, 'jeffy@yahoo.com', 'myusernameisjeff', 'mynameisjeff', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (DEFAULT, 'mbyrde@aol.com', 'martybyrde', 'moneylaundering', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (DEFAULT, 'wwchemist@hotmail.com', 'waltw', 'skylar', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (DEFAULT, 'test', 'test12', 'test', 1, DEFAULT);

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
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (DEFAULT, 'test', 'test', 'test', 1, 1, 'https://cdn2.vectorstock.com/i/1000x1000/20/76/question-mark-vector-972076.jpg');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (DEFAULT, 'Mike', 'Jones', 'who is mike jones?', 2, 2, 'https://marriedwiki.com/uploads/bio/mike-jones.jpg');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (DEFAULT, 'Jeff', 'Jefferson', 'my name is jeff', 3, 3, 'https://i.kym-cdn.com/entries/icons/original/000/016/894/mynameehhjeff.jpg');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (DEFAULT, 'Marty', 'Byrde', 'come check out my lakehouse', 4, 4, 'https://static.wikia.nocookie.net/2a6c845c-c6e9-472e-abed-24863817b795/scale-to-width/300');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (DEFAULT, 'Walter', 'White', 'chemisty teacher', 5, 5, 'https://www.indiewire.com/wp-content/uploads/2018/07/breakingbadformon_wide-22d0f0aa716956d518b391936d3bc323dd7a3848-s900-c85.jpg?w=780');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (DEFAULT, 'test', 'test', 'test', 1, 6, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (DEFAULT, 2, DEFAULT, DEFAULT, 'buy my album', 'yo', 'https://images-na.ssl-images-amazon.com/images/I/51aPPEV-AjL.jpg');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (DEFAULT, 3, DEFAULT, DEFAULT, 'my name is jeff', 'hello', 'https://www.youtube.com/watch?v=AfIOBLr1NDU');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (DEFAULT, 4, DEFAULT, DEFAULT, 'Anyone want to invest with me?', 'investing', 'data:image/jpeg;base64,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');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (DEFAULT, 5, DEFAULT, DEFAULT, 'How does my carwash look?', 'A1 Car Wash', 'https://vignette.wikia.nocookie.net/breakingbad/images/6/67/5x09_WalterAndLydia.jpg/revision/latest?cb=20130814205346');

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'All');
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
INSERT INTO `category` (`id`, `name`) VALUES (20, 'Music');

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

