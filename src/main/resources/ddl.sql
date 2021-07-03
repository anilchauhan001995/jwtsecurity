create table user (
user_id int(10) NOT NULL AUTO_INCREMENT primary key,
name varchar(20) not null,
username varchar(20) not null unique key,
password varchar(20) not null
);

create table roles (
role_id int not null auto_increment primary key,
name varchar(45) not null
);

create table user_role (
	user_id int not null,
    role_id int not null,
    constraint role_fk foreign key (role_id) references roles (role_id),
    constraint user_fk foreign key (user_id) references user (user_id)
);