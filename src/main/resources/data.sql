insert into article (title, content, author, created_at, updated_at) values ('title 1', 'content 1','user 1',NOW(),NOW())
insert into article (title, content, author, created_at, updated_at) values ('title 2', 'content 2','user 2',NOW(),NOW())
insert into article (title, content, author, created_at, updated_at) values ('title 3', 'content 3','user 3',NOW(),NOW())

insert into comments (article_id, author, content, created_at) values (1, 'user1' , '댓글1' ,NOW())
insert into comments (article_id, author, content, created_at) values (2, 'user3' , '댓글3' ,NOW())