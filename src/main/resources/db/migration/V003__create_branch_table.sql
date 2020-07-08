CREATE TABLE branch (
	cnae varchar(7) not null,
    name varchar(255) not null,
    updated_At date not null,
    active bit not null,
    
    primary key(cnae)
)
