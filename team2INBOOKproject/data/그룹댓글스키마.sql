DROP TABLE groupList_reply CASCADE CONSTRAINTS;
DROP SEQUENCE groupList_reply_seq;

-- 생성 : PK(board) -> FK(board_rep)
CREATE TABLE groupList_reply(
rno NUMBER PRIMARY KEY,
no NUMBER REFERENCES groupList(no) ON DELETE CASCADE,
content VARCHAR2(600) NOT NULL,
id VARCHAR2(20) NOT NULL REFERENCES member(id) ON DELETE CASCADE,
writeDate DATE DEFAULT SYSDATE
);

CREATE SEQUENCE groupList_reply_seq;

-- 샘플 데이터
-- 게시판의 있는 글번호
select * from groupList;
-- 게시판에 2번 글이 있고 거기에 댓글 작성
INSERT INTO groupList_reply(rno, no, content, id)
VALUES(groupList_reply_seq.nextval, 2, '사용자 댓글 작성', 'test');
INSERT INTO groupList_reply(rno, no, content, id)
VALUES(groupList_reply_seq.nextval, 2, '관리자 댓글 작성', 'admin');
COMMIT;

-- 게시판 글보기할 때 하단에 표시한다.(같이 불러온다.)
select * from groupList where no = 22;
select * from groupList_reply where no = 22 order by rno desc;