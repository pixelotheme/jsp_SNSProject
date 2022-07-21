DROP TABLE friend CASCADE CONSTRAINTS;
DROP SEQUENCE friend_seq ;

--1��° friendId�� host�� �Ǿ����� ģ�� �θ��°��� host,fiend �α��뿡�� relationship�� 
--relationship�� 1�� ��� �������� �ߺ��Ǵ°� ����
--1-1) ����Ʈ �����ö� host,friend �� �����;��� ���߿� �񱳱��� �ؾ���
-- ģ�� ���� Ȯ���Ҷ� friend or host �� �ش��ϴ� ���̵� relationship 1���� Ȯ��

--2��° ��� ģ�������Ѱ�� friendId �� hostid �� �߰� �ؼ�����
CREATE TABLE friend(
no NUMBER PRIMARY KEY,
hostId VARCHAR2(20) not null REFERENCES member(id) on delete CASCADE,
friendId VARCHAR2(20) not null REFERENCES member(id) on delete CASCADE,
-- 1 = ģ��, 0 = ��û��, �ź��Ѱ�� ����, �����Ѱ��  
relationship number(1) default 0,
--ģ����û���� ��¥ ( ������ update sysdate
acceptDate date default null
);

CREATE SEQUENCE friend_seq;

INSERT INTO friend(no, hostId, friendId, relationship) 
VALUES (FRIEND_SEQ.nextval, 'test', 'test3', 0);

INSERT INTO friend(no, hostId, friendId, relationship) 
VALUES (FRIEND_SEQ.nextval, 'test', 'test1', 0);

INSERT INTO friend(no, hostId, friendId, relationship) 
VALUES (FRIEND_SEQ.nextval, 'test', 'test2', 0);

INSERT INTO friend(no, hostId, friendId, relationship) 
VALUES (FRIEND_SEQ.nextval, 'test', 'test4', 0);

INSERT INTO friend(no, hostId, friendId, relationship) 
VALUES (FRIEND_SEQ.nextval, 'test', 'test5', 0);

INSERT INTO friend(no, hostId, friendId, relationship) 
VALUES (FRIEND_SEQ.nextval, 'test', 'test6', 0);


INSERT INTO friend(no, hostId, friendId, relationship) 
VALUES (FRIEND_SEQ.nextval, 'test5', 'test', 0);


COMMIT;
