-- �Խ��� ���ƿ� ��Ű��
-- ȸ���� ���� - �Ѱ��� �̹����ۿ� ���� �ѹ��� �����ϰ� ���ƿ� <-> ���ƿ� ���
-- ���ƿ��� : ���̵�� �̹��� ��ȣ �����Ͱ� �ִ�.
-- ���ƿ���� : ���̵�� �̹��� ��ȣ �����Ͱ� ����.

SELECT A.SID
,A.SERIAL#
,A.STATUS
FROM V$SESSION A
,V$LOCK B
,DBA_OBJECTS C
WHERE A.SID   = B.SID
AND B.ID1  = C.OBJECT_ID
AND B.TYPE  = 'TM'
AND C.OBJECT_NAME = 'board'
;

ALTER SYSTEM KILL SESSION 'sid, serial#';





-- ���� ���� ���̺� : ���̵�(FK-member(id)), �̹��� ��ȣ(FK-image(no))
DROP TABLE board_like CASCADE CONSTRAINTS;

CREATE TABLE board_like(
id VARCHAR2(20) not null, 
no NUMBER CONSTRAINT board_like_no_fk REFERENCES board(no) ON DELETE CASCADE, 
PRIMARY KEY (id, no)
);

-- id�� no�� ���ļ� PK�� ����Ű �����Ѵ�. table ���� - ����Ű ����
-- ALTER TABLE board_like
-- ADD CONSTRAINT board_like_no_pk PRIMARY KEY(id, no);

---- ���ƿ� ���̺� �����Ͱ� ������ ���ƿ並 ������. �����Ͱ� ������ ���ƿ並 ������ �ʾҴ�.
--- test3�� 2�� �̹��� �ۿ� ���ƿ� ������ ó��
INSERT INTO board_like
VALUES(27, 'test9');
COMMIT;

select no, 'LIKED' 
from board_like
where no = 2 and id = 'test';

--- test3�� 2�� �̹��� �ۿ� ���ƿ� ��� ó��
delete from board_like
where no = 26 and id = 'tes9';
COMMIT;