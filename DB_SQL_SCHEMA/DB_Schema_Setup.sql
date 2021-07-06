CREATE TABLE `bill_details` (
  `bill_id` int NOT NULL,
  `bill_sno` varchar(1000) DEFAULT NULL,
  `bill_payee` varchar(450) DEFAULT NULL,
  `bill_date` datetime DEFAULT NULL,
  `bill_description` varchar(1000) DEFAULT NULL,
  `bill_qty` varchar(1000) DEFAULT NULL,
  `bill_rate` varchar(45) DEFAULT NULL,
  `bill_amount` varchar(1000) DEFAULT NULL,
  `bill_total_amount` bigint DEFAULT NULL,
  `read_status` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `doctor` (
  `doctor_id` int NOT NULL,
  `doctor_name` varchar(400) DEFAULT NULL,
  `doctor_address` varchar(450) DEFAULT NULL,
  `doctor_contact` bigint DEFAULT NULL,
  PRIMARY KEY (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `user_det` (
  `user` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
