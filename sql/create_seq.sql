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

delete from member
where id='sqqs12';

select * from member
where id = 'admin';