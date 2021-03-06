DROP TABLE IF EXISTS `payment_account`;
CREATE TABLE `payment_account`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `balance` decimal(15, 2) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_id` varchar(255) NOT NULL,
  `amount` decimal(15, 2) NOT NULL,
  `payment_account_id` bigint NOT NULL,
  `receive_account_id` bigint NOT NULL,
  `transaction_time` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

ALTER TABLE `transaction` ADD UNIQUE INDEX `transaction_id_IDX`(`transaction_id`) USING BTREE;

DROP TABLE IF EXISTS `third_party_client`;
CREATE TABLE `third_party_client`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client_name` varchar(255) NOT NULL,
  `client_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

insert into third_party_client (id, client_name, client_id) values
(1, 'test_client_name', 'qazwsxedc');

insert into payment_account (id, name, balance, version) values
(1, 'A', 100, 1);