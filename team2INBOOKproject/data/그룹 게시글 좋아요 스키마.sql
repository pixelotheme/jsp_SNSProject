DROP TABLE groupList_Like CASCADE CONSTRAINTS;

CREATE TABLE groupList_Like(
id VARCHAR2(20) not null, 
no NUMBER CONSTRAINT groupList_Like_no_fk REFERENCES groupList(no) ON DELETE CASCADE, 
PRIMARY KEY (id, no)
);
INSERT INTO groupList_Like
VALUES('test', 2);
COMMIT;

select no, 'LIKED' 
from groupList_Like
where no = 2 and id = 'test1';

--- test3�� 2�� �̹��� �ۿ� ���ƿ� ��� ó��
delete from groupList_Like
where no = 3 and id = 'tes2';
COMMIT;