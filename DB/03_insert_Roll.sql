conn SALESAMPLE/SALESAMPLE

insert into MT_ROLE values('2','manager','課長');
insert into MT_ROLE values('3','user','課員');


/* Insert確認用ユーザ登録 */
/* ハッシュ化されていないパスワードを登録しているので */
/* セキュリティ実装後にはレコードを削除すること */
insert into MT_USER values('u003','平川　太郎','user', '3', 1);
insert into MT_USER values('u004','大手　次郎','user', '3', 1);


commit;