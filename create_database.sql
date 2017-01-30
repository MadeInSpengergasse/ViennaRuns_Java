DROP TABLE IF EXISTS Run;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS FeelingAfterRun;

CREATE TABLE User
(
    u_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    u_version INT(11) DEFAULT 1,
    u_name VARCHAR(255),
    u_password VARCHAR(255),
    u_weight DECIMAL(3, 1),
    u_height DECIMAL(3, 2)
);
CREATE TABLE FeelingAfterRun
(
    far_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    far_version INT(11) DEFAULT 1,
    far_feeling VARCHAR(255)
);
CREATE TABLE Run
(
    r_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    r_version INT(11) DEFAULT 1,
    r_user INT(11),
    r_distance DECIMAL(6, 2),
    r_duration INT(11),
    r_date DATETIME,
    r_feeling INT(11),
    CONSTRAINT Run_User_u_id_fk FOREIGN KEY (r_user) REFERENCES User (u_id),
    CONSTRAINT Run_FeelingAfterRun_far_id_fk FOREIGN KEY (r_feeling) REFERENCES FeelingAfterRun (far_id)
);
CREATE INDEX Run_FeelingAfterRun_far_id_fk ON Run (r_feeling);
CREATE INDEX Run_User_u_id_fk ON Run (r_user);
