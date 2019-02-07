insert into orders (id, city, country, county, street, customer_id, shipped_from, date) values (3, 'Timisoara', 'Romania', 'Timis', 'Calea Buziasului', 1, 3, current_timestamp);
insert into order_detail (order_id, product_id, quantity) values (3, 5, 3);
update orders set date=current_timestamp;
