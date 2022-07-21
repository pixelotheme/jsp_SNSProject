DROP TABLE friend CASCADE CONSTRAINTS;
DROP SEQUENCE friend_seq ;

--1번째 friendId가 host가 되었을때 친구 부르는것은 host,fiend 두군대에서 relationship이 
--relationship이 1인 사람 꺼내오기 중복되는것 제외
--1-1) 리스트 꺼내올때 host,friend 다 꺼내와야함 나중에 비교까지 해야함
-- 친구 인지 확인할때 friend or host 에 해당하는 아이디에 relationship 1인지 확인

--2번째 방법 친구수락한경우 friendId 를 hostid 에 추가 해서진행
CREATE TABLE friend(
no NUMBER PRIMARY KEY,
hostId VARCHAR2(20) not null REFERENCES member(id) on delete CASCADE,
friendId VARCHAR2(20) not null REFERENCES member(id) on delete CASCADE,
-- 1 = 친구, 0 = 요청중, 거부한경우 삭제, 수락한경우  
relationship number(1) default 0,
--친구요청수락 날짜 ( 봤을때 update sysdate
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
