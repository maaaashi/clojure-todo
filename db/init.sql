-- テーブル作成
create table todos (
  id serial primary key,
  title text not null,
  completed boolean not null
);

-- テストデータ挿入
insert into todos (title, completed) values ('朝食を食べる', false);
