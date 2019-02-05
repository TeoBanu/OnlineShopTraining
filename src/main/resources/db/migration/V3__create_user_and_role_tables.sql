create table user (id integer not null, username varchar(255) not null, password varchar(255) not null, email varchar(255) not null, unique(email), unique(username), primary key (id));
create table role (id integer not null, user_id integer not null, name varchar(255) not null, primary key (id), FOREIGN KEY (user_id) references user(id));
