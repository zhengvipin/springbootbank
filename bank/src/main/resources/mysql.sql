/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.18 : Database - employee
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `role` */

CREATE TABLE `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values (1,'ROLE_ADMIN');
insert  into `role`(`id`,`name`) values (2,'ROLE_MANAGER');
insert  into `role`(`id`,`name`) values (3,'ROLE_CLERK');

/*Table structure for table `user` */

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `last_password_reset_date` datetime DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`email`,`enabled`,`last_password_reset_date`,`login_date`) values (1,'admin','$2a$10$AA50h29Na9G4arXSQOVi8e.lyiNBzjNlxcbrEguY1ENN2hj7js.gK','admin@gmail.com',1,'2018-04-21 18:38:12','2018-04-13 15:06:51');
insert  into `user`(`id`,`username`,`password`,`email`,`enabled`,`last_password_reset_date`,`login_date`) values (2,'king','$2a$10$XXOyZL6gjDItXBSV8svAYOs9WWGjpNjbnOMw0OIdG4xB0jE.qf03S','king@gmail.com',1,'2018-04-01 15:08:36','2018-04-23 15:08:40');
insert  into `user`(`id`,`username`,`password`,`email`,`enabled`,`last_password_reset_date`,`login_date`) values (3,'smith','$2a$10$KKPqMUI/hKIh0wvsqAiuw.DNZECMli6SMwjOgcbkgYmCSW3wEqKau','smith@gmail.com',0,'2018-04-02 15:09:11','2018-04-20 15:09:14');
insert  into `user`(`id`,`username`,`password`,`email`,`enabled`,`last_password_reset_date`,`login_date`) values (4,'jones','$2a$10$vxdN6hrlEWCxLg32qJbfdOWNR5nygDS3M9gsaxLAIvstjEtNQlN4q','jones@gmail.com',1,'2018-04-01 15:10:01','2018-04-25 15:10:04');
insert  into `user`(`id`,`username`,`password`,`email`,`enabled`,`last_password_reset_date`,`login_date`) values (5,'james','$2a$10$ni9a7GR1UFio5TWVn8aAf.PG3CXYRGruGha4g87B2MM2bYQn70i5C','james@gmail.com',1,'2018-04-01 15:10:30','2018-04-20 15:10:34');
insert  into `user`(`id`,`username`,`password`,`email`,`enabled`,`last_password_reset_date`,`login_date`) values (6,'blank','$2a$10$kTmIH.hoiT8Ztic5NccHG.2FknM/ratM6dLJUdxO.G8lW4CinK24a','blank@gmail.com',1,'2018-04-19 18:17:15','2018-04-19 18:17:15');

/*Table structure for table `user_role` */

CREATE TABLE `user_role` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`user_id`,`role_id`) values (1,1);
insert  into `user_role`(`user_id`,`role_id`) values (1,2);
insert  into `user_role`(`user_id`,`role_id`) values (1,3);
insert  into `user_role`(`user_id`,`role_id`) values (2,2);
insert  into `user_role`(`user_id`,`role_id`) values (3,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
