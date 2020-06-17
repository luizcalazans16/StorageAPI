CREATE TABLE customer_addresses (
	id bigint not null auto_increment,
    customer_cpf varchar(11) not null,
    address_Line varchar(150) not null,
    building bigint not null,
    complement bigint,
    postal_Code varchar(10) not null,
    district varchar(30) not null,
    city varchar(60) not null,
    state_Province varchar (2),
    country varchar(30) not null,
    main_Address bit not null,
    
    primary key(id)
);

alter table customer_addresses add constraint fk_customer_addresses_customer
foreign key (customer_cpf) references customer (cpf);