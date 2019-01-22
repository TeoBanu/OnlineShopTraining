DROP table if exists suppliers;
drop table if exists categories;
drop table if exists products;
create table suppliers (id INT NOT NULL AUTO_INCREMENT, name VARCHAR NOT NULL, PRIMARY KEY (id));
CREATE TABLE categories (id INT NOT NULL AUTO_INCREMENT, name VARCHAR NOT NULL, description VARCHAR NOT NULL, PRIMARY KEY (id));
CREATE TABLE products (id INT NOT NULL AUTO_INCREMENT, name VARCHAR NOT NULL, description VARCHAR NOT NULL, price DECIMAL NOT NULL, weight DOUBLE NOT NULL, categoryId INT NOT NULL, supplierId INT NOT NULL, PRIMARY KEY (id), FOREIGN KEY (categoryId) references categories(id), FOREIGN KEY (supplierId) references suppliers(id));




