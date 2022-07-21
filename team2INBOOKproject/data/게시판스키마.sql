--DB ���� - ������ ��� �ȴ�. => ���� ���� ����� �ٽ� ������ �Ѵ�. ���õ����� �ֱ�. COMMIT -> SELECT
-- 1. �Խ��� ��ü �����.(�� ó������ board ���̺��� ������ ������ ����.)
DROP TABLE board CASCADE CONSTRAINTS;
DROP SEQUENCE board_seq; -- ������ ����

-- 2. �Խ��� ���� ��ü�� �����.(���̺�, ������) - �۹�ȣ, ����, ����, �ۼ���, �ۼ���, ��ȸ��
--  2-1. �÷��̸�, Ÿ��, ���ڿ��� ��� ũ�� �ʼ�
--  2-2. �۹�ȣ�� �ߺ��� �Ǹ� �ȵ˴ϴ�. �����Ͱ� ���� ��(NULL)�� �ȵ�. -> PRIMARY KEY(�⺻Ű)
--  2-3. ����, ����, �ۼ��ڴ� ����ڰ� �� �Է��ؼ� �־ƾ߸� �Ѵ�. -> NULL �ȵ�. :  NOT NULL
--  2-4. �Ȱ��� �����ͷ� �־����� �� : �ۼ��� - �׻� ���� ��¥�� �ð� -> SYSDATE, ��ȸ�� - 0 => DEFAULT
CREATE TABLE board(
  no NUMBER PRIMARY KEY,
  title VARCHAR2(300) NOT NULL,
  content VARCHAR2(2000) NOT NULL,
  id VARCHAR2(20) NOT NULL REFERENCES member(id) ON DELETE CASCADE,
  writeDate DATE DEFAULT SYSDATE,
  hashtag VARCHAR2(50) NOT NULL,
  fileName VARCHAR2(50) NOT NULL,
  state VARCHAR2(6) DEFAULT '�Ϲ�' CHECK (state in ('�Ϲ�','�׷�')),
  hit NUMBER DEFAULT 0
);

CREATE SEQUENCE board_seq;

-- 3. ���� ������ �ֱ�
--  3-1. �۹�ȣ(�ڵ� - ��ü:������), ����, ����, ���̵�, �ؽ��±�, ���ϳ���
INSERT INTO board(no, title, content, id, hashtag, fileName, state)
VALUES(board_seq.NEXTVAL, '���� ��ȹǥ', '�����͹̳ο��� ���ʰ��� ������ Ÿ�� ~~~', 'test1', '#���� #����', '/upload/image/sokcho.jpg', '�׷�');
INSERT INTO board(no, title, content, id, hashtag, fileName)
VALUES(board_seq.NEXTVAL, '��� ��ȹǥ', '�����͹̳ο��� ��簡�� ������ Ÿ�� ~~~', 'test3', '#��� #����', '/upload/image/yangyang.jpg');
-- 4. ������ ���� ����
COMMIT;

-- 5. ������ Ȯ��
SELECT * FROM board;


