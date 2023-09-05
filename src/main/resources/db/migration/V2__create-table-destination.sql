CREATE TABLE DESTINATION(
    id bigint not null auto_increment,
    name varchar(100) not null,
    value decimal(22) not null,
    picture_url MEDIUMTEXT,

    primary key(id)
)