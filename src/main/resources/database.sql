ALTER DATABASE britenet DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;

CREATE TABLE `britenet`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `age` SMALLINT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `britenet`.`contacts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_customer` INT NOT NULL,
  `type` TINYINT NOT NULL,
  `contact` VARCHAR(255),
  PRIMARY KEY (`id`),
  FOREIGN KEY(`id_customer`)
  REFERENCES `britenet`.`customers`(`id`));