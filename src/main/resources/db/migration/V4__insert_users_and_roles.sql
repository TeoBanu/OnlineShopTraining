insert into user (id, username, password, email, customer_id) values (1, 'minitop', '$2a$10$pznuVaoOz3AEC3jxdzDTB.98nFmXFgw3zal8DxtKN1cMwjW9vrTju', 'mini@top.com', 1);
insert into user (id, username, password, email, customer_id) values (2, 'picpoc', '$2a$10$DZyafQBcsLPWrFkEudfUrem5cpad4dBAgFyk4zqtewlLoppcPStrq', 'pic@poc.com', 2);
insert into role (id, user_id, name) values (1, '1', 'customer');
insert into role (id, user_id, name) values (2, '2', 'customer');
