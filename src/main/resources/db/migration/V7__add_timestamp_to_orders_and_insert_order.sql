insert into orders (id, city, country, county, street, customer_id, shipped_from, date) values (3, 'Timisoara', 'Romania', 'Timis', 'Calea Buziasului', 1, 1, current_timestamp);
update orders set date=current_timestamp;
