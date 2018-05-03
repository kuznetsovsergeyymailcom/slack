insert into users(name,login,password)values('user','user',123);
insert into users(name,login,password)values('admin','admin',123);
insert into users(name,login,password)values('user2','user2',123);

insert into role(role_desc)values('user');
insert into role(role_desc)values('admin');

insert into join_user_role(user_id,role_id)values(1,1);
insert into join_user_role(user_id,role_id)values(2,1);
insert into join_user_role(user_id,role_id)values(2,2);
insert into join_user_role(user_id,role_id)values(3,1);
insert into join_user_role(user_id,role_id)values(3,2);
