--DB 개발 - 수정이 계속 된다. => 원래 것은 지우고 다시 만들어야 한다. 샘플데이터 넣기. COMMIT -> SELECT
-- 1. 게시판 객체 지운다.(맨 처음에는 board 테이블이 없으면 오류가 난다.)
DROP TABLE board CASCADE CONSTRAINTS;
DROP SEQUENCE board_seq; -- 시퀀스 삭제

-- 2. 게시판 관련 객체를 만든다.(테이블, 시퀀스) - 글번호, 제목, 내용, 작성자, 작성일, 조회수
--  2-1. 컬럼이름, 타입, 문자열인 경우 크기 필수
--  2-2. 글번호는 중복이 되면 안됩니다. 데이터가 없는 것(NULL)도 안됨. -> PRIMARY KEY(기본키)
--  2-3. 제목, 내용, 작성자는 사용자가 꼭 입력해서 넣아야만 한다. -> NULL 안됨. :  NOT NULL
--  2-4. 똑같은 데이터로 넣어지는 것 : 작성일 - 항상 현재 날짜와 시간 -> SYSDATE, 조회수 - 0 => DEFAULT
CREATE TABLE board(
  no NUMBER PRIMARY KEY,
  title VARCHAR2(300) NOT NULL,
  content VARCHAR2(2000) NOT NULL,
  id VARCHAR2(20) NOT NULL REFERENCES member(id) ON DELETE CASCADE,
  writeDate DATE DEFAULT SYSDATE,
  hashtag VARCHAR2(50) NOT NULL,
  fileName VARCHAR2(50) NOT NULL,
  state VARCHAR2(6) DEFAULT '일반' CHECK (state in ('일반','그룹')),
  hit NUMBER DEFAULT 0
);

CREATE SEQUENCE board_seq;

-- 3. 샘플 데이터 넣기
--  3-1. 글번호(자동 - 객체:시퀀스), 제목, 내용, 아이디, 해시태그, 파일네임
INSERT INTO board(no, title, content, id, hashtag, fileName, state)
VALUES(board_seq.NEXTVAL, '속초 계획표', '버스터미널에서 속초가는 버스를 타고 ~~~', 'test1', '#속초 #동해', '/upload/image/sokcho.jpg', '그룹');
INSERT INTO board(no, title, content, id, hashtag, fileName)
VALUES(board_seq.NEXTVAL, '양양 계획표', '버스터미널에서 양양가는 버스를 타고 ~~~', 'test3', '#양양 #동해', '/upload/image/yangyang.jpg');
-- 4. 데이터 완전 적용
COMMIT;

-- 5. 데이터 확인
SELECT * FROM board;


