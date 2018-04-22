CREATE TABLE `t_movie_desc` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`movie_name` VARCHAR(255) NOT NULL,
	`movie_href` VARCHAR(255) NOT NULL,
	`movie_actors` VARCHAR(255) NULL DEFAULT NULL,
	`movie_img` VARCHAR(255) NULL DEFAULT NULL,
	`crawler_job_url` VARCHAR(255) NULL DEFAULT NULL,
	`movie_tvid` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`, `movie_name`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1620
;
