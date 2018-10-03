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
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(1000) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `role` VARCHAR(45) NOT NULL DEFAULT 'standard',
  PRIMARY KEY (`id`))
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

CREATE INDEX `fk_profile_location_idx` ON `profile` (`location_id` ASC);

CREATE INDEX `fk_user_profile_idx` ON `profile` (`user_id` ASC);


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
  CONSTRAINT `fk_post_profile`
    FOREIGN KEY (`profile_id`)
    REFERENCES `profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_post_profile_idx` ON `post` (`profile_id` ASC);


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

CREATE INDEX `fk_post_category_post_idx` ON `post_category` (`post_id` ASC);

CREATE INDEX `fk_post_category_category_idx` ON `post_category` (`category_id` ASC);


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

CREATE INDEX `fk_comment_post_idx` ON `comment` (`post_id` ASC);

CREATE INDEX `fk_comment_profile_idx` ON `comment` (`profile_id` ASC);


-- -----------------------------------------------------
-- Table `friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `friend` ;

CREATE TABLE IF NOT EXISTS `friend` (
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
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

CREATE INDEX `fk_Friend_User1_idx` ON `friend` (`user_id` ASC);

CREATE INDEX `fk_Friend_User2_idx` ON `friend` (`friend_id` ASC);


-- -----------------------------------------------------
-- Table `vote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vote` ;

CREATE TABLE IF NOT EXISTS `vote` (
  `comment_id` INT NOT NULL,
  `profile_id` INT NOT NULL,
  `vote` TINYINT NULL,
  `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
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

CREATE INDEX `fk_vote_profile_idx` ON `vote` (`profile_id` ASC);

CREATE INDEX `fk_vote_comment_idx` ON `vote` (`comment_id` ASC);


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

CREATE INDEX `fk_profile_expertise_expertise_idx` ON `profile_expertise` (`expertise_id` ASC);

CREATE INDEX `fk_profile_expertise_profile_idx` ON `profile_expertise` (`profile_id` ASC);

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
INSERT INTO `user` (`id`, `username`, `password`, `email`, `active`, `role`) VALUES (1, 'test', 'test', 'test@test.com', 1, DEFAULT);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `active`, `role`) VALUES (2, 'test2', 'test2', 'test2@test.com', 1, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (1, 'Denver', 'CO', 'US');
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (2, 'test', 'test', 'test');

COMMIT;


-- -----------------------------------------------------
-- Data for table `profile`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`) VALUES (DEFAULT, 'test', 'test', 'test', 1, 1);
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`) VALUES (DEFAULT, 'test2', 'test2', 'test2', 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`) VALUES (1, 1, DEFAULT, DEFAULT, 'test post 1');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`) VALUES (2, 1, DEFAULT, DEFAULT, 'test post 2 ');

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'Travel');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'Music');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'Sports');
INSERT INTO `category` (`id`, `name`) VALUES (4, 'Fashion');
INSERT INTO `category` (`id`, `name`) VALUES (5, 'Technology');
INSERT INTO `category` (`id`, `name`) VALUES (6, 'Food');
INSERT INTO `category` (`id`, `name`) VALUES (7, 'Politics');
INSERT INTO `category` (`id`, `name`) VALUES (8, 'Business');
INSERT INTO `category` (`id`, `name`) VALUES (9, 'Finance');
INSERT INTO `category` (`id`, `name`) VALUES (10, 'Social');
INSERT INTO `category` (`id`, `name`) VALUES (11, 'Home Improvement');
INSERT INTO `category` (`id`, `name`) VALUES (12, 'Event Planning');
INSERT INTO `category` (`id`, `name`) VALUES (13, 'Medical');
INSERT INTO `category` (`id`, `name`) VALUES (14, 'Fitness');
INSERT INTO `category` (`id`, `name`) VALUES (15, 'Lifestyle');
INSERT INTO `category` (`id`, `name`) VALUES (16, 'Pets');
INSERT INTO `category` (`id`, `name`) VALUES (17, 'Art');
INSERT INTO `category` (`id`, `name`) VALUES (18, 'Collectables');
INSERT INTO `category` (`id`, `name`) VALUES (19, 'Jobs');
INSERT INTO `category` (`id`, `name`) VALUES (20, 'Literature');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (1, 2);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (2, 4);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (1, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (1, 'what a great post ', DEFAULT, DEFAULT, 1, 2);
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (2, 'this post sucks', DEFAULT, DEFAULT, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `expertise`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `expertise` (`id`, `title`) VALUES (1, 'Medicine');
INSERT INTO `expertise` (`id`, `title`) VALUES (2, 'Art');
INSERT INTO `expertise` (`id`, `title`) VALUES (3, 'Education');
INSERT INTO `expertise` (`id`, `title`) VALUES (4, 'Botany');
INSERT INTO `expertise` (`id`, `title`) VALUES (5, 'Zoology');
INSERT INTO `expertise` (`id`, `title`) VALUES (6, 'Law');
INSERT INTO `expertise` (`id`, `title`) VALUES (7, 'Software Development');
INSERT INTO `expertise` (`id`, `title`) VALUES (8, 'Engineering');
INSERT INTO `expertise` (`id`, `title`) VALUES (9, 'Mechanic');
INSERT INTO `expertise` (`id`, `title`) VALUES (10, 'Design');
INSERT INTO `expertise` (`id`, `title`) VALUES (11, 'Culinary');
INSERT INTO `expertise` (`id`, `title`) VALUES (12, 'Athletics');
INSERT INTO `expertise` (`id`, `title`) VALUES (13, 'Lingustics ');
INSERT INTO `expertise` (`id`, `title`) VALUES (14, 'Anthropology');
INSERT INTO `expertise` (`id`, `title`) VALUES (15, 'Psychology');
INSERT INTO `expertise` (`id`, `title`) VALUES (16, 'Theology');
INSERT INTO `expertise` (`id`, `title`) VALUES (17, 'Philosophy');
INSERT INTO `expertise` (`id`, `title`) VALUES (18, 'Cartographer');
INSERT INTO `expertise` (`id`, `title`) VALUES (19, 'Electrician');
INSERT INTO `expertise` (`id`, `title`) VALUES (20, 'Java Architecture');

COMMIT;

