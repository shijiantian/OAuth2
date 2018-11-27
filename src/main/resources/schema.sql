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
create table if not exists resource(
    id bigint(20) not null DEFAULT 0,
    url varchar(128) not null,
    http_method varchar(10) not null,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(id)
)engine=InnoDB;
create table if not exists role_resource(
    id bigint(20) not NULL auto_increment,
    role_id bigint(20) not null,
    resource_id int not null,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(id)
)engine=InnoDB;

create table if not exists expense_history(
    id bigint(20) not null auto_increment,
    water_count smallint,
    water_price decimal(4,2),
    elec_count smallint,
    elec_price decimal(4,2),
    expense_date date not null,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(id)
)engine=InnoDB;