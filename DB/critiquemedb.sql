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
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (4, 'test@sd.com', 'test', '$2a$10$4WvWHVwZ6Vuhq9/zY85ZbOs/OYHY07ZC5df7/t2GzgFv//HRvnV6G', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (5, 'mjones@gmail.com', 'mikejones', 'who', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (6, 'jeffy@yahoo.com', 'myusernameisjeff', 'mynameisjeff', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (7, 'mbyrde@aol.com', 'martybyrde', 'moneylaundering', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (8, 'wwchemist@hotmail.com', 'waltw', 'skylar', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (9, 'mrfring@sbcbusiness.com', 'managergus', 'lightblue', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (10, 'saullaw@gmail.com', 'bettercallsaul', 'laundering', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (11, 'sdssthompson@sd.com', 'sdthompson', 'codingrules', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (1, 'gregG@gmail.com', 'gregory12', '$2a$10$yWhbTjZHBJMPVykwxPL9Oe0GfmCms7EdkCKWWVz9qP28ZACW03esS', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (2, 'eddie@yahoo.com', 'guitarEddie', '$2a$10$4S/Y7sYb6o80r7Op5pmR.OvgH1zYnHcmFHonB3Mgl7EtNCI2SkJrC', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (3, 'ellieBear@msn.com', 'decoratorEllie', '$2a$10$D9z0N7jMNk9a/LbL/HD09ucRGHhNEZ8RD6Pgl6jee.R.1HqTncFGu', 1, DEFAULT);
INSERT INTO `user` (`id`, `email`, `username`, `password`, `active`, `role`) VALUES (12, 'johnWick@gmail.com', 'johnWick', '$2a$10$CL7/.hewzPp1eKICegGBc.nKp0UNuorZcxZk7qvYOMCXvk.4Y5PXm', 1, DEFAULT);

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
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (4, 'test', 'test', 'test', 1, 4, 'https://cdn2.vectorstock.com/i/1000x1000/20/76/question-mark-vector-972076.jpg');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (5, 'Mike', 'Jones', 'who is mike jones?', 2, 5, 'https://marriedwiki.com/uploads/bio/mike-jones.jpg');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (6, 'Jeff', 'Jefferson', 'my name is jeff', 3, 6, 'https://i.kym-cdn.com/entries/icons/original/000/016/894/mynameehhjeff.jpg');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (7, 'Marty', 'Byrde', 'come check out my lakehouse', 4, 7, 'https://static.wikia.nocookie.net/2a6c845c-c6e9-472e-abed-24863817b795/scale-to-width/300');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (8, 'Walter', 'White', 'chemisty teacher', 5, 8, 'https://www.indiewire.com/wp-content/uploads/2018/07/breakingbadformon_wide-22d0f0aa716956d518b391936d3bc323dd7a3848-s900-c85.jpg?w=780');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (9, 'Gustavo', 'Fring', 'Small business owner here to serve you.', 5, 9, 'https://upload.wikimedia.org/wikipedia/en/thumb/7/7b/Gustavo_fring_breaking_bad.jpg/220px-Gustavo_fring_breaking_bad.jpg');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (10, 'Saul', 'Goodman', 'Attorney at law. Having any legal trouble? Better call Saul. 505-503-4455', 5, 10, 'http://images.amcnetworks.com/blogs.amctv.com/wp-content/uploads/2010/04/BB-S3-Bob-Odenkirk-325.jpg');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (11, 'Steve', 'Thompson', 'Java developer and teacher', 1, 11, 'https://naqyr37xcg93tizq734pqsx1-wpengine.netdna-ssl.com/wp-content/uploads/2017/11/10-Things-We-Can-Learn-From-the-Incredible-Steve-Jobs.jpg');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (1, 'Greg', 'Lewis', 'I love piano, goats, and hockey', 4, 1, 'https://pbs.twimg.com/profile_images/513750073881018370/dllbq93-_400x400.jpeg');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (2, 'Eddie', 'Bauer', 'Guitar and cinnamon and MIXED MARTIAL ARTS', 1, 2, 'https://yt3.ggpht.com/a-/AN66SAx3d93XAtECHGSN1bANIyE1GexLwt-HgLDu=s288-mo-c-c0xffffffff-rj-k-no');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (3, 'Ellie', 'Reynolds', 'Hi there! I love interior design, cooking, and my dog Mickey', 2, 3, 'https://rachelbthriftstudio.files.wordpress.com/2013/08/ellie-visconti-e1485279915522.jpg');
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `bio`, `location_id`, `user_id`, `image`) VALUES (12, 'John', 'Wick', 'I enjoy spending time with my dog', 1, 12, 'https://thumbor.forbes.com/thumbor/1280x868/https%3A%2F%2Fblogs-images.forbes.com%2Ferikkain%2Ffiles%2F2017%2F05%2Fjohn-wick-2.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (5, 5, DEFAULT, DEFAULT, 'I really enjoy classical music so I made this compilation of my favorite works from Beethoven. My favorite track is Symphony No. 3 \"Eroica\" because of the robust woodwind solo. Please listen to it and share what you all think.', 'My New Album', 'https://images-na.ssl-images-amazon.com/images/I/51aPPEV-AjL.jpg');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (6, 6, DEFAULT, DEFAULT, 'my name is jeff', 'hello', 'https://www.youtube.com/watch?v=AfIOBLr1NDU');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (7, 7, DEFAULT, DEFAULT, 'Anyone want to invest with me?', 'investing', 'none');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (8, 8, DEFAULT, DEFAULT, 'How does my carwash look?', 'A1 Car Wash', 'https://www.breakingbad-locations.com/wp-content/uploads/2014/09/E07-Problem-Dog.mkv_000823371.jpg');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (9, 9, DEFAULT, DEFAULT, 'Hello everyone, here at Los Pollos Hermanos we are looking for your feedback. Our customers are everything to us and we would like to know how we can better serve you. Please leave your comments below. Thank you!', 'Improving Los Pollos Hermanos', 'https://images.amcnetworks.com/amc.com/wp-content/uploads/2017/01/25926-BCS-S3-Los-Pollos-Hermanos-wLOGO_01.jpg');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (10, 10, DEFAULT, DEFAULT, 'How would you all rate the service you have gotten with me and my law firm? Please share your experience in the comments below.', 'How would you rate our service?', 'https://news.newonnetflix.info/wp-content/uploads/2018/06/better-call-saul.jpg');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (1, 1, DEFAULT, DEFAULT, 'Hey everyone, any thoughts on my performance?', 'Schubert - Impromptu No. 3, Op. 90', 'https://www.youtube.com/watch?v=Ybq6Ea79nZ4');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (2, 2, DEFAULT, DEFAULT, 'What\'s up guys. Check out my new cover. Thx', 'Believer Guitar Cover', 'https://www.youtube.com/watch?v=hXQxSi34GWY');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (3, 3, DEFAULT, DEFAULT, 'How cool does this interior design look??', 'New Style Design!!', 'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/ed-best-decor-santa-monica3-1520362749.jpg?crop=1xw:1xh;center,top&resize=480:*');
INSERT INTO `post` (`id`, `profile_id`, `create_date`, `update_date`, `content`, `title`, `media`) VALUES (4, 12, DEFAULT, DEFAULT, 'These guys did an awesome job on this site. Thoughts?', 'Trail Mixer', 'http://18.216.51.206:8080/MVCTrailMixer/');

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
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (1, 20);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (2, 20);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (3, 3);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (3, 12);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (4, 10);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (4, 4);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (5, 20);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (6, 19);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (7, 7);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (7, 8);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (8, 7);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (9, 7);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (10, 7);
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES (10, 17);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `critiquemedb`;
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (1, 'no', DEFAULT, DEFAULT, 3, 1);
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (2, 'Hello, mike. I find the the woodwind solo to be exuberant and very stimulating. A nicely put together album indeed.', DEFAULT, DEFAULT, 7, 5);
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (3, 'I might be interested', DEFAULT, DEFAULT, 8, 7);
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (4, 'Looks good, I might stop by ;)', DEFAULT, DEFAULT, 7, 8);
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (5, 'I really enjoyed the performance.', DEFAULT, DEFAULT, 8, 1);
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (6, 'I found it very relaxing', DEFAULT, DEFAULT, 12, 1);
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (7, 'I found myself singing along! Lol', DEFAULT, DEFAULT, 3, 2);
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (8, 'jeff', DEFAULT, DEFAULT, 6, 3);
INSERT INTO `comment` (`id`, `content`, `created_at`, `updated_at`, `profile_id`, `post_id`) VALUES (9, 'jeff', DEFAULT, DEFAULT, 6, 4);

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
INSERT INTO `expertise` (`id`, `title`) VALUES (1, 'None');
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
INSERT INTO `expertise` (`id`, `title`) VALUES (20, 'Education');

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
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (7, 9);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (8, 10);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (8, 14);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (8, 15);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (9, 2);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (9, 20);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (1, 1);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (6, 1);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (10, 1);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (11, 1);
INSERT INTO `profile_expertise` (`profile_id`, `expertise_id`) VALUES (12, 1);

COMMIT;

