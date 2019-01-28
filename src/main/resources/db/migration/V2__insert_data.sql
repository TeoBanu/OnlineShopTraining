insert into customer (id, first_name, last_name, username) values (1, 'Tip', 'Top', 'minitop');
insert into customer (id, first_name, last_name, username) values (2, 'Pic', 'Poc', 'picpoc');

insert into location (id, name, city, country, county, street) values (1, 'emag Sagului', 'Timisoara', 'Romania', 'Timis', 'Bulevardul Liviu Rebreanu, nr. 2-5');
insert into location (id, name, city, country, county, street) values (2, 'emag Central', 'Timisoara', 'Romania', 'Timis', 'Calea Aradului, nr. 18');
insert into location (id, name, city, country, county, street) values (3, 'Showroom eMAG', 'Cluj-Napoca', 'Romania', 'Cluj', 'Calea Manastur, nr. 2-6');

insert into product_category (id, name, description) values (1, 'Auto, Moto & RCA', 'Auto, Moto & RCA description');
insert into product_category (id, name, description) values (2, 'Carti, Birotica & Cadouri', 'Carti, Birotica & Cadouri description');
insert into product_category (id, name, description) values (3, 'Fashion', 'Fashion description');

insert into supplier (id, name) values (1, 'eMAG');
insert into supplier (id, name) values (2, 'Librex');
insert into supplier (id, name) values (3, 'Autohut');
insert into supplier (id, name) values (4, 'Dili Trading');

insert into product (id, name, description, price, weight, category_id, supplier_id) values (1, 'Polita RCA', 'Polita RCA description', 150, 0.01, 1, 1);
insert into product (id, name, description, price, weight, category_id, supplier_id) values (2, 'Jante', 'Jante auto ART 355 F.Lucios - 14''', 235.6, 4.4, 1, 3);
insert into product (id, name, description, price, weight, category_id, supplier_id) values (3, 'Hotul de carti', 'Este anul 1939. Germania nazista. Tara isi tine rasuflarea. Moartea nu a avut niciodata mai mult de lucru, si va deveni chiar mai ocupata.', 15.4, 0.2, 2, 1);
insert into product (id, name, description, price, weight, category_id, supplier_id) values (4, 'Fata din tren - Paula Hawkins', 'Dar ea te cunoaste. In fiecare zi e la fel. Pana astazi. Rachel ia in fiecare dimineata acelasi tren. Care asteapta la acelasi semnal defect. ', 20.4, 0.5, 2, 2);
insert into product (id, name, description, price, weight, category_id, supplier_id) values (5, 'Organizator Acte Personale', 'Mapa Acte Personale, este un produs premium realizat sa poata satisface cele mai exigente standarde de organizare.', 2, 0.01, 2, 1);
insert into product (id, name, description, price, weight, category_id, supplier_id) values (6, 'Portofel Messina', 'Portofel Messina, piele naturala, negru, compartimente multiple', 59.9, 0.3, 3, 4);
insert into product (id, name, description, price, weight, category_id, supplier_id) values (7, 'Botosi de casa Deluxx', 'Polita RCA description', 12.3, 0.6, 3, 1);

insert into stock (location_id, product_id, quantity) values (1, 1, 70);
insert into stock (location_id, product_id, quantity) values (3, 1, 20);
insert into stock (location_id, product_id, quantity) values (2, 2, 12);
insert into stock (location_id, product_id, quantity) values (3, 2, 10);
insert into stock (location_id, product_id, quantity) values (1, 3, 4);
insert into stock (location_id, product_id, quantity) values (3, 3, 1);
insert into stock (location_id, product_id, quantity) values (1, 4, 1);
insert into stock (location_id, product_id, quantity) values (2, 4, 1);
insert into stock (location_id, product_id, quantity) values (3, 4, 7);
insert into stock (location_id, product_id, quantity) values (1, 5, 2);
insert into stock (location_id, product_id, quantity) values (3, 5, 10);
insert into stock (location_id, product_id, quantity) values (1, 6, 3);
insert into stock (location_id, product_id, quantity) values (3, 6, 1);
insert into stock (location_id, product_id, quantity) values (2, 7, 1);
insert into stock (location_id, product_id, quantity) values (3, 7, 3);

insert into orders (id, city, country, county, street, customer_id, shipped_from) values (1, 'Lugoj', 'Romania', 'Timis', 'Calea Timisoarei, nr 148', 1, 1);
insert into orders (id, city, country, county, street, customer_id, shipped_from) values (2, 'Deta', 'Romania', 'Timis', 'Strada Lunii, nr 2', 2, 2);

insert into order_detail (order_id, product_id, quantity) values (1, 1, 1);
insert into order_detail (order_id, product_id, quantity) values (1, 2, 2);
insert into order_detail (order_id, product_id, quantity) values (1, 4, 1);
insert into order_detail (order_id, product_id, quantity) values (2, 3, 1);
insert into order_detail (order_id, product_id, quantity) values (2, 4, 1);
