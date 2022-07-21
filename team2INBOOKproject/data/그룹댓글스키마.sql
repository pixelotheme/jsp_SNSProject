DROP TABLE groupList_reply CASCADE CONSTRAINTS;
DROP SEQUENCE groupList_reply_seq;

-- ���� : PK(board) -> FK(board_rep)
CREATE TABLE groupList_reply(
rno NUMBER PRIMARY KEY,
no NUMBER REFERENCES groupList(no) ON DELETE CASCADE,
content VARCHAR2(600) NOT NULL,
id VARCHAR2(20) NOT NULL REFERENCES member(id) ON DELETE CASCADE,
writeDate DATE DEFAULT SYSDATE
);

CREATE SEQUENCE groupList_reply_seq;

-- ���� ������
-- �Խ����� �ִ� �۹�ȣ
select * from groupList;
-- �Խ��ǿ� 2�� ���� �ְ� �ű⿡ ��� �ۼ�
INSERT INTO groupList_reply(rno, no, content, id)
VALUES(groupList_reply_seq.nextval, 2, '����� ��� �ۼ�', 'test');
INSERT INTO groupList_reply(rno, no, content, id)
VALUES(groupList_reply_seq.nextval, 2, '������ ��� �ۼ�', 'admin');
COMMIT;

-- �Խ��� �ۺ����� �� �ϴܿ� ǥ���Ѵ�.(���� �ҷ��´�.)
select * from groupList where no = 22;
select * from groupList_reply where no = 22 order by rno desc;