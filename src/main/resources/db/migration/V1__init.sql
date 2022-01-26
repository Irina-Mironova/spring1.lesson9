CREATE TABLE categories
(
    id bigserial primary key,
    title varchar(255)
);

insert into categories (title) values ('Рыба'), ('Сыр'),('Фрукты'),('Овощи'),('Мясо');


create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       float,
    category_id bigint references categories (id)
);


insert into products (title, price, category_id)
values ('Горбуша', 250, 1),
       ('Кета', 300, 1),
       ('Сыр Гауда', 210, 2),
       ('Сыр Голландский', 160, 2),
       ('Дыня', 100, 3),
       ('Арбуз', 55, 3),
       ('Слива', 280, 3),
       ('Ананас',100 , 3);

create table products_images
(
    id          bigserial primary key,
    path       varchar(255),
    product_id bigint references products (id)
);

insert into products_images (path, product_id)
values ('1.bmp', 1),
       ('2.jpg', 2),
       ('3.jpg', 5);

create table orders
(
   id bigserial primary key,
   price float,
   data  datetime
);

create table orders_item
(
    id     bigserial primary key,
    quantity  bigint,
    item_price float,
    total_price float,
    product_id bigint references products (id),
    order_id bigint references orders (id)
);

create table users
(
   id  bigserial primary key,
   username varchar(30) not null unique,
   password varchar(80) not null,
   first_name varchar(30),
   last_name varchar(30),
   email varchar(255)
);


create table roles
(
  id bigserial primary key,
  name varchar(50) not null
);

create table users_roles
(
  user_id bigint,
  role_id bigint,
  primary key(user_id, role_id),
  foreign key (user_id) references users(id),
  foreign key (role_id) references roles(id)
);

insert into roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users(username, password, first_name, last_name, email)
values ('bob', '$2a$12$uY2t0/L2eQ4cCiyCuQ36FOT7S3e/jzTte.aIVXYkD6HqPG6wTl826', 'Борис', 'Иванов', 'bob123@mail.ru'),
       ('ali', '$2a$12$MsuaCpjj2DVyukZmKPdbPOnLvZ.b712lGz.yjT2522w9RGCYtgBs6', 'Александр','Сергеев','ali1234@mail.ru');

insert into users_roles (user_id, role_id)
values  (1, 2),
        (2, 1);


