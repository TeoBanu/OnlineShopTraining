create sequence hibernate_sequence start with 1000 increment by 1;
create table customer (id integer not null, first_name varchar(255), last_name varchar(255), username varchar(255), primary key (id));
create table location (id integer not null, city varchar(255), country varchar(255), county varchar(255), name varchar(255), street varchar(255), primary key (id));
create table product_category (id integer not null, description varchar(255), name varchar(255), primary key (id));
create table supplier (id integer not null, name varchar(255), primary key (id));
create table orders (id integer not null, city varchar(255), country varchar(255), county varchar(255), street varchar(255), customer_id integer, shipped_from integer, primary key (id), FOREIGN KEY (customer_id) references customer(id), FOREIGN KEY (shipped_from) references location(id));
create table product (id integer not null, description varchar(255), name varchar(255), price decimal(19,2), weight double not null, category_id integer, supplier_id integer, primary key (id), FOREIGN KEY (category_id) references product_category(id), foreign key (supplier_id) references supplier(id));
create table stock (location_id integer not null, product_id integer not null, quantity integer not null, primary key (location_id, product_id), FOREIGN KEY (location_id) references location(id), FOREIGN KEY (product_id) references product(id));
create table order_detail (order_id integer not null, product_id integer not null, quantity integer not null, primary key (order_id, product_id), FOREIGN KEY (order_id) references orders(id), FOREIGN KEY (product_id) references product(id));
