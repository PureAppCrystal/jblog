-- seq_category

DROP SEQUENCE seq_category;

CREATE SEQUENCE seq_category
START WITH 1
INCREMENT BY 1
MAXVALUE 9999999999;

-- seq_post

DROP SEQUENCE seq_post;

CREATE SEQUENCE seq_post
START WITH 1
INCREMENT BY 1
MAXVALUE 9999999999;

commit;

select * from member;
select * from blog;

delete from blog
where length(id) < 5;

delete from member
where length(id) < 5;

select * from member
where id = 'admin';

insert into member
	 values('jack', '잭', '1234', sysdate);

alter table member
add ( role varchar2(10) default 'USER' );

select * from member;

update member
set role = 'ADMIN'
where id = 'admin';

commit;
