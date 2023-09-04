DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    id       integer NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    age      integer NOT NULL,
    PRIMARY KEY (id)
);