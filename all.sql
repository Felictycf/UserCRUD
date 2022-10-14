create database cruddemo;
use cruddemo;
CREATE TABLE `user` (
                        `id` bigint NOT NULL COMMENT 'id',
                        `name` varchar(255) DEFAULT NULL COMMENT 'name',
                        `age` int DEFAULT NULL COMMENT 'age',
                        `sex` varchar(255) DEFAULT NULL COMMENT 'sex',
                        `phone` varchar(255) DEFAULT NULL COMMENT 'telephone',
                        `city` varchar(255) DEFAULT NULL COMMENT 'city',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sys_user` (
                        `id` bigint NOT NULL COMMENT 'id',
                        `name` varchar(255) DEFAULT NULL COMMENT 'name',
                        `sys_name` varchar(255) DEFAULT NULL COMMENT 'sys_name',
                        `age` int DEFAULT NULL COMMENT 'age',
                        `sex` varchar(255) DEFAULT NULL COMMENT 'sex',
                        `phone` varchar(255) DEFAULT NULL COMMENT 'telephone',
                        `city` varchar(255) DEFAULT NULL COMMENT 'city',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
