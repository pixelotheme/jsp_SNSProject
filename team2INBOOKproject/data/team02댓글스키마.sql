-- �Խ��� ��� ��Ű�� - ���̺� ������
-- ���� -> ���� -> ���õ�����
-- ���� : FK(board_rep) -> PK(board)
DROP TABLE board_reply CASCADE CONSTRAINTS;
DROP SEQUENCE board_reply_seq;

-- ���� : PK(board) -> FK(board_rep)
CREATE TABLE board_reply(
rno NUMBER PRIMARY KEY,
no NUMBER REFERENCES board(no) ON DELETE CASCADE,
content VARCHAR2(600) NOT NULL,
id VARCHAR2(20) NOT NULL REFERENCES member(id) ON DELETE CASCADE,
writeDate DATE DEFAULT SYSDATE
);

CREATE SEQUENCE board_reply_seq;

-- ���� ������
-- �Խ����� �ִ� �۹�ȣ
select * from board;
-- �Խ��ǿ� 2�� ���� �ְ� �ű⿡ ��� �ۼ�
INSERT INTO board_reply(rno, no, content, id)
VALUES(board_reply_seq.nextval, 22, '����� ��� �ۼ�', 'test');
INSERT INTO board_reply(rno, no, content, id)
VALUES(board_reply_seq.nextval, 22, '������ ��� �ۼ�', 'admin');
COMMIT;

-- �Խ��� �ۺ����� �� �ϴܿ� ǥ���Ѵ�.(���� �ҷ��´�.)
select * from board where no = 22;
select * from board_reply where no = 22 order by rno desc;