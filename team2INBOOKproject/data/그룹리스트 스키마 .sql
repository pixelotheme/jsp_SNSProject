-- �׷� ����Ʈ ��Ű�� - ���̺�, ������
-- ���� -> ���� -> ���õ�����
-- ���� : FK(image) -> PK(member) : �̹��� �Խ��Ǹ� �۾�
DROP TABLE groupList;
DROP SEQUENCE groupList_seq;

-- ���� : PK(member) -> FK(image) : �̹��� �Խ��Ǹ� �۾�

CREATE TABLE groupList(
 no NUMBER PRIMARY KEY,
  title VARCHAR2(300) NOT NULL,
  content VARCHAR2(2000) NOT NULL,
  id VARCHAR2(20) NOT NULL REFERENCES member(id) ON DELETE CASCADE,
  writeDate DATE DEFAULT SYSDATE,
  hashtag VARCHAR2(50) NOT NULL,
  fileName VARCHAR2(50) NOT NULL,
  --state VARCHAR2(6) DEFAULT '�Ϲ�' CHECK (state in ('�Ϲ�','�׷�')),
  hit NUMBER DEFAULT 0
);
CREATE SEQUENCE groupList_seq;

-- ���õ����� : PK(member) -> FK(image) - ȸ������ ��ϵǾ� �ִ� ���̵� ��� ���� - test, admin
INSERT INTO groupList(no, title, content, id, hashtag, fileName )
VALUES(groupList_seq.NEXTVAL, '������ �̾߱�', '���Ļ����� �����ϳ� ����� ����� no', 'test1', '#������', '/upload/grouplist/uijeongbu.JPG' );
INSERT INTO groupList(no, title, content, id, hashtag, fileName)
VALUES(groupList_seq.NEXTVAL, '��ŷ ������ ����', '��ǻ�Ϳ� ���� ��� ����/�亯 ���Ȱ��� ���� ����', 'test1', '#��ǻ��', '/upload/grouplist/computer.JPG');
INSERT INTO groupList(no, title, content, id, hashtag, fileName)
VALUES(groupList_seq.NEXTVAL, '��Ȱ �ڵ�', '�ڵ��� ó�� �����ϴ� �е��� ���θ� �������� Ŀ�´�Ƽ', 'test1', '#�ڵ� #��ƴ�', '/upload/grouplist/coding.JPG');
INSERT INTO groupList(no, title, content, id, hashtag, fileName)
VALUES(groupList_seq.NEXTVAL, '����', '�����ڰ� ��� ���� �̾߱�� ������ ������ Ŀ�´�Ƽ', 'test1', '#���� #�ڷγ�', '/upload/grouplist/travel.JPG');
COMMIT;

select * from groupList;