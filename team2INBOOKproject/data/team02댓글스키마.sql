-- 게시판 댓글 스키마 - 테이블 시퀀스
-- 제거 -> 생성 -> 샘플데이터
-- 제거 : FK(board_rep) -> PK(board)
DROP TABLE board_reply CASCADE CONSTRAINTS;
DROP SEQUENCE board_reply_seq;

-- 생성 : PK(board) -> FK(board_rep)
CREATE TABLE board_reply(
rno NUMBER PRIMARY KEY,
no NUMBER REFERENCES board(no) ON DELETE CASCADE,
content VARCHAR2(600) NOT NULL,
id VARCHAR2(20) NOT NULL REFERENCES member(id) ON DELETE CASCADE,
writeDate DATE DEFAULT SYSDATE
);

CREATE SEQUENCE board_reply_seq;

-- 샘플 데이터
-- 게시판의 있는 글번호
select * from board;
-- 게시판에 2번 글이 있고 거기에 댓글 작성
INSERT INTO board_reply(rno, no, content, id)
VALUES(board_reply_seq.nextval, 22, '사용자 댓글 작성', 'test');
INSERT INTO board_reply(rno, no, content, id)
VALUES(board_reply_seq.nextval, 22, '관리자 댓글 작성', 'admin');
COMMIT;

-- 게시판 글보기할 때 하단에 표시한다.(같이 불러온다.)
select * from board where no = 22;
select * from board_reply where no = 22 order by rno desc;