CREATE table if not exists files
(
    id int not null auto_increment primary key,
    file_name varchar(128),
    file_path varchar(256),
    url varchar(256),
    is_empty boolean
);