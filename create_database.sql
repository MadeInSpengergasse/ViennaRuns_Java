DROP TABLE Run;
DROP TABLE User;
DROP TABLE FeelingAfterRun;

CREATE TABLE User
(
    u_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    u_version INT(11),
    u_name VARCHAR(255),
    u_password VARCHAR(255),
    u_runninggroup INT(11),
    u_weight DECIMAL(18),
    u_username VARCHAR(255)
);
CREATE TABLE FeelingAfterRun
(
    far_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    far_version INT(11),
    far_feeling VARCHAR(255)
);
CREATE TABLE Run
(
    r_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    r_version INT(11),
    r_user INT(11),
    r_distance DECIMAL(18),
    r_duration INT(11),
    r_date DATETIME,
    r_feeling INT(11),
    CONSTRAINT Run_User_u_id_fk FOREIGN KEY (r_user) REFERENCES User (u_id),
    CONSTRAINT Run_FeelingAfterRun_far_id_fk FOREIGN KEY (r_feeling) REFERENCES FeelingAfterRun (far_id)
);
CREATE INDEX Run_FeelingAfterRun_far_id_fk ON Run (r_feeling);
CREATE INDEX Run_User_u_id_fk ON Run (r_user);
