DROP TABLE IF EXISTS `dodo-bot`.`t_banned_user`;
DROP TABLE IF EXISTS `dodo-bot`.`t_prefix`;
DROP TABLE IF EXISTS `dodo-bot`.`t_bank_account`;
DROP TABLE IF EXISTS `dodo-bot`.`t_bug_report`;
DROP TABLE IF EXISTS `dodo-bot`.`t_default_role`;
DROP TABLE IF EXISTS `dodo-bot`.`t_quote`;
DROP TABLE IF EXISTS `dodo-bot`.`t_user_stats`;
DROP TABLE IF EXISTS `dodo-bot`.`t_admin`;
DROP TABLE IF EXISTS `dodo-bot`.`t_user`;
DROP TABLE IF EXISTS `dodo-bot`.`t_server`;

CREATE TABLE `dodo-bot`.`t_user`(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  discord_id VARCHAR(20) NOT NULL
);

CREATE TABLE `dodo-bot`.`t_server`(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  discord_id VARCHAR(20) NOT NULL
);

CREATE TABLE `dodo-bot`.`t_bank_account`(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  balance DECIMAL(30,2) NOT NULL,
  last_pay DATETIME NOT NULL,
  user_id BIGINT NOT NULL,
  CONSTRAINT FK_UserBank FOREIGN KEY (user_id)
  REFERENCES `dodo-bot`.`t_user`(id)
);

CREATE TABLE `dodo-bot`.`t_bug_report`(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  bugType VARCHAR(20) NOT NULL,
  message VARCHAR(100) NOT NULL,
  stackTrace VARCHAR(5000) NOT NULL DEFAULT 'No stack trace',
  userInfo VARCHAR(500),
  submitedTime DATETIME NOT NULL
);

CREATE TABLE `dodo-bot`.`t_default_role`(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  discord_id VARCHAR(30) NOT NULL,
  server_id BIGINT NOT NULL,
  CONSTRAINT FK_ServerRole FOREIGN KEY (server_id)
  REFERENCES `dodo-bot`.`t_server`(id)
);

CREATE TABLE `dodo-bot`.`t_quote`(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  person VARCHAR(50) NOT NULL,
  wheno DATE NOT NULL DEFAULT '2018-08-08',
  quote VARCHAR (300) NOT NULL
);

CREATE TABLE `dodo-bot`.`t_user_stats`(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  num_of_messages BIGINT NOT NULL DEFAULT 0,
  num_of_files BIGINT NOT NULL DEFAULT 0,
  CONSTRAINT FK_UserStats FOREIGN KEY (user_id)
  REFERENCES `dodo-bot`.`t_user`(id)
);

CREATE TABLE `dodo-bot`.`t_admin`(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  server_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  CONSTRAINT FK_UserAdmin FOREIGN KEY (user_id)
  REFERENCES `dodo-bot`.`t_user`(id),
  CONSTRAINT FK_ServerAdmin FOREIGN KEY (server_id)
  REFERENCES `dodo-bot`.`t_server`(id)
);

CREATE TABLE `dodo-bot`.`t_prefix`(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  prefix VARCHAR(20),
  server_id BIGINT NOT NULL,
  CONSTRAINT FK_ServerPrefix FOREIGN KEY (server_id)
  REFERENCES `dodo-bot`.`t_server`(id)
);

CREATE TABLE `dodo-bot`.`t_banned_user` (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  server_id BIGINT NOT NULL,
  CONSTRAINT FK_BannedUser FOREIGN KEY (user_id)
  REFERENCES `dodo-bot`.`t_user`(id),
  CONSTRAINT FK_BannedServer FOREIGN KEY (server_id)
  REFERENCES `dodo-bot`.`t_server`(id)
)
