CREATE TABLE supplier (
	id bigint not null auto_increment,
	branch_id bigint not null,
    cnpj varchar(14) not null,
    name varchar(255) not null,
    updated_At date not null,
    active bit not null,
    
    primary key(id)
);

alter table supplier add constraint fk_supplier_branch
foreign key (branch_id) references branch (id);