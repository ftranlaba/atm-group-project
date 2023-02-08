-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phonenumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`primary_accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`primary_accounts` (
  `id_primary_account` INT NOT NULL AUTO_INCREMENT,
  `id_user` INT NOT NULL,
  `pin` INT NOT NULL,
  PRIMARY KEY (`id_primary_account`),
  INDEX `fk_primary_accounts_users1_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `fk_primary_accounts_users1`
    FOREIGN KEY (`id_user`)
    REFERENCES `mydb`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`accounts` (
  `id_account` INT NOT NULL AUTO_INCREMENT,
  `id_primary_account` INT NOT NULL,
  `balance` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_account`),
  INDEX `fk_accounts_primary_accounts1_idx` (`id_primary_account` ASC) VISIBLE,
  CONSTRAINT `fk_accounts_primary_accounts1`
    FOREIGN KEY (`id_primary_account`)
    REFERENCES `mydb`.`primary_accounts` (`id_primary_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`transfers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`transfers` (
  `id_transfer` INT NOT NULL AUTO_INCREMENT,
  `id_account1` INT NOT NULL,
  `id_account2` INT NOT NULL,
  `old_balance_acc1` INT NOT NULL,
  `new_balance_acc1` INT NOT NULL,
  `old_balance_acc2` INT NOT NULL,
  `new_balance_acc2` INT NOT NULL,
  `time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id_transfer`),
  INDEX `fk_transactions_accounts1_idx` (`id_account1` ASC) VISIBLE,
  INDEX `fk_transactions_accounts2_idx` (`id_account2` ASC) VISIBLE,
  CONSTRAINT `fk_transactions_accounts1`
    FOREIGN KEY (`id_account1`)
    REFERENCES `mydb`.`accounts` (`id_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transactions_accounts2`
    FOREIGN KEY (`id_account2`)
    REFERENCES `mydb`.`accounts` (`id_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`debit_cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`debit_cards` (
  `id_card` INT NOT NULL AUTO_INCREMENT,
  `id_account` INT NOT NULL,
  `number` VARCHAR(45) NOT NULL,
  `expiration_date` VARCHAR(45) NOT NULL,
  `cvc` INT NOT NULL,
  `block` TINYINT NOT NULL,
  PRIMARY KEY (`id_card`),
  INDEX `fk_cards_accounts1_idx` (`id_account` ASC) VISIBLE,
  CONSTRAINT `fk_cards_accounts1`
    FOREIGN KEY (`id_account`)
    REFERENCES `mydb`.`accounts` (`id_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`account_access_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`account_access_history` (
  `id_history` INT NOT NULL AUTO_INCREMENT,
  `id_account` INT NOT NULL,
  `time` TIMESTAMP NOT NULL,
  `mac_address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_history`),
  CONSTRAINT `fk_account_access_history_accounts1`
    FOREIGN KEY (`id_account`)
    REFERENCES `mydb`.`accounts` (`id_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`deposits`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`deposits` (
  `id_deposit` INT NOT NULL AUTO_INCREMENT,
  `id_account` INT NOT NULL,
  `time` TIMESTAMP NOT NULL,
  `old_balance` INT NOT NULL,
  `new_balance` INT NOT NULL,
  PRIMARY KEY (`id_deposit`),
  INDEX `fk_deposit_history_accounts1_idx` (`id_account` ASC) VISIBLE,
  CONSTRAINT `fk_deposit_history_accounts1`
    FOREIGN KEY (`id_account`)
    REFERENCES `mydb`.`accounts` (`id_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`withdraws`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`withdraws` (
  `id_withdraw` INT NOT NULL AUTO_INCREMENT,
  `id_account` INT NOT NULL,
  `time` TIMESTAMP NOT NULL,
  `old_balance` INT NOT NULL,
  `new_balance` INT NOT NULL,
  PRIMARY KEY (`id_withdraw`),
  INDEX `fk_withdraw_history_accounts1_idx` (`id_account` ASC) VISIBLE,
  CONSTRAINT `fk_withdraw_history_accounts1`
    FOREIGN KEY (`id_account`)
    REFERENCES `mydb`.`accounts` (`id_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`credit_cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`credit_cards` (
  `id_card` INT NOT NULL AUTO_INCREMENT,
  `id_primary_account` INT NOT NULL,
  `card_number` VARCHAR(45) NOT NULL,
  `expiration_date` VARCHAR(45) NOT NULL,
  `cvc` INT NOT NULL,
  `block` TINYINT NOT NULL,
  PRIMARY KEY (`id_card`),
  INDEX `fk_credit_cards_primary_accounts1_idx` (`id_primary_account` ASC) VISIBLE,
  CONSTRAINT `fk_credit_cards_primary_accounts1`
    FOREIGN KEY (`id_primary_account`)
    REFERENCES `mydb`.`primary_accounts` (`id_primary_account`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
