create table todos (
  id int primary key not null AUTO_INCREMENT,
  title text not null,
  completed boolean not null
);

-- テストデータとして1件入れておく
insert into todos (title, completed) values ('朝食を食べる', false);