 create table if not exists user(
    id bigint(20) not NULL auto_increment,
    name VARCHAR(8) not null,
    password VARCHAR(64) not null,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(id)
)engine=InnoDB;
create table if not exists role(
    id bigint(20) not NULL auto_increment,
    name VARCHAR(8) not null,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(id)
)engine=InnoDB;
create table if not exists user_role(
    id bigint(20) not NULL auto_increment,
    user_id bigint(20) not NULL,
    role_id bigint(20) not NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(id)
)engine=InnoDB;
create table if not exists permission(
    id bigint(20) not null DEFAULT 0,
    url varchar(128) not null,
    http_method varchar(10) not null,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)engine=InnoDB;
create table if not exists role_permission(
    id bigint(20) not NULL auto_increment,
    role_id bigint(20) not null,
    permission_id int not null,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(id)
)engine=InnoDB;