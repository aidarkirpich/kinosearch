CREATE TABLE IF NOT EXISTS `testschema`.`films_table`  (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `film_id` INT NULL,
                                      `name` VARCHAR(250) NULL,
                                      `year` INT NULL,
                                      `rating` FLOAT NULL,
                                      `description` VARCHAR(2500) NULL,
                                       `genres` JSON NULL,
                                      PRIMARY KEY (`id`));
CREATE TABLE IF NOT EXISTS `testschema`.`film_genres` (
                                               `genres_id` INT NOT NULL,
                                               `genres_name` VARCHAR(100) NULL,
                                               PRIMARY KEY (`genres_id`));
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('1', 'триллер');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('2', 'драма');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('3', 'криминал');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('4', 'мелодрама');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('5', 'детектив');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('6', 'фантастика');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('7', 'приключения');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('8', 'биография');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('9', 'фильм-нуар');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('10', 'вестерн');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('11', 'боевик');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('12', 'фэнтези');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('13', 'комедия');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('14', 'военный');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('15', 'история');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('16', 'музыка');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('17', 'ужасы');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('18', 'мультфильм');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('19', 'семейный');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('20', 'мюзикл');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('21', 'спорт');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('22', 'документальный');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('23', 'короткометражка');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('24', 'аниме');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('26', 'новости');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('27', 'концерт');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('28', 'для взрослых');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('29', 'церемония');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('30', 'реальное ТВ');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('31', 'игра');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('32', 'ток-шоу');
INSERT INTO `testschema`.`film_genres` (`genres_id`, `genres_name`) VALUES ('33', 'детский');
