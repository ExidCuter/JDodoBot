CREATE DATABASE IF NOT EXISTS `dodo-bot` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

DROP USER IF EXISTS 'dodo-bot'@'localhost';
CREATE USER IF NOT EXISTS 'dodo-bot'@'localhost' IDENTIFIED BY 'thisisdodobot';
GRANT SELECT, INSERT, DELETE, UPDATE ON `dodo-bot`.* TO 'dodo-bot'@'localhost';