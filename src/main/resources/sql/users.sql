create table govorunclinic.users
(
    id int auto_increment,
    name varchar(255) not null,
    uniqkey varchar(255) not null,
    password varchar(255) not null,
    role varchar(20) not null,
    adminId int not null,
    constraint users_pk
        primary key (id),
    constraint users_users_id_fk
        foreign key (adminId) references users (id)
);