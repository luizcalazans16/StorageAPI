CREATE TABLE customer (
	cpf varchar(11) not null,
    name varchar(255) not null,
    email varchar(255) not null,
    birth_Date date not null,
    gender varchar(255) not null,
    phone varchar(20) not null,
    active bit not null,
    
    primary key (cpf)
);