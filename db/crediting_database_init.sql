DROP TABLE IF EXISTS `receive_account`;
CREATE TABLE `receive_account`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `balance` decimal(15, 2) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

insert into receive_account (id, name, balance, version) values
(1, 'B', 10, 1);