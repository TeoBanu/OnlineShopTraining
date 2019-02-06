ALTER TABLE orders ADD date timestamp;
create table revenue (id integer not null, date timestamp, location_id integer, sum decimal(20,2), primary key (id), FOREIGN KEY (location_id) references location(id));
