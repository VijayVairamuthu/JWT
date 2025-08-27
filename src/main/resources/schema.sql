-- Schema from the assessment prompt
CREATE TABLE IF NOT EXISTS runner (
  id int(11) NOT NULL AUTO_INCREMENT,
  task_id int(11) DEFAULT 0,
  username varchar(50) DEFAULT NULL,
  latitude decimal(11,8) DEFAULT 0.00000000,
  longitude decimal(11,8) DEFAULT 0.00000000,
  create_time datetime DEFAULT CURRENT_TIMESTAMP,
  modify_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS task (
  id int(11) NOT NULL AUTO_INCREMENT,
  cust_id int(11) DEFAULT 0,
  current_runner varchar(50) DEFAULT NULL,
  status varchar(50) DEFAULT NULL,
  latitude decimal(11,8) DEFAULT 0.00000000,
  longitude decimal(11,8) DEFAULT 0.00000000,
  alloc_time datetime DEFAULT NULL,
  accept_time datetime DEFAULT NULL,
  create_time datetime DEFAULT CURRENT_TIMESTAMP,
  modify_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
