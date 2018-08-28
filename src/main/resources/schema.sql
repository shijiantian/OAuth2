 create table if not exists user(
    id bigint(20) not NULL auto_increment,
    name VARCHAR(8) not null,
    password VARCHAR(64) not null,
    primary key(id)
)engine=InnoDB;
create table if not exists role(
    id bigint(20) not NULL auto_increment,
    name VARCHAR(8) not null,
    primary key(id)
)engine=InnoDB;
create table if not exists user_role(
    id bigint(20) not NULL auto_increment,
    user_id bigint(20) not NULL,
    role_id bigint(20) not NULL,
    primary key(id)
)engine=InnoDB;