insert into users (username,password,enabled) values ('user','password',true);
insert into users (username,password,enabled) values ('admin','password',true);
insert into authorities (user_username,authority) values ('user','USER');
insert into authorities (user_username,authority) values ('admin','USER');
insert into authorities (user_username,authority) values ('admin','ADMIN');