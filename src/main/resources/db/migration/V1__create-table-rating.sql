CREATE TABLE RATING(
    id bigint not null auto_increment,
    name varchar(100) not null,
    text varchar(100) not null,
    picture_url MEDIUMTEXT,

    primary key(id)
)