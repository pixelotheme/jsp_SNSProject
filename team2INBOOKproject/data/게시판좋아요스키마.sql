-- 게시판 좋아요 스키마
-- 회원만 가능 - 한개의 이미지글에 대해 한번만 가능하고 좋아요 <-> 좋아요 취소
-- 좋아요함 : 아이디와 이미지 번호 데이터가 있다.
-- 좋아요안함 : 아이디와 이미지 번호 데이터가 없다.

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





-- 정보 저장 테이블 : 아이디(FK-member(id)), 이미지 번호(FK-image(no))
DROP TABLE board_like CASCADE CONSTRAINTS;

CREATE TABLE board_like(
id VARCHAR2(20) not null, 
no NUMBER CONSTRAINT board_like_no_fk REFERENCES board(no) ON DELETE CASCADE, 
PRIMARY KEY (id, no)
);

-- id와 no를 합쳐서 PK로 복합키 지정한다. table 수정 - 복합키 지정
-- ALTER TABLE board_like
-- ADD CONSTRAINT board_like_no_pk PRIMARY KEY(id, no);

---- 좋아요 테이블에 데이터가 있으면 좋아요를 눌렀다. 데이터가 없으면 좋아요를 누르지 않았다.
--- test3가 2번 이미지 글에 좋아요 눌려서 처리
INSERT INTO board_like
VALUES(27, 'test9');
COMMIT;

select no, 'LIKED' 
from board_like
where no = 2 and id = 'test';

--- test3가 2번 이미지 글에 좋아요 취소 처리
delete from board_like
where no = 26 and id = 'tes9';
COMMIT;