-- SQL 게시판 운영 쿼리
-- 1. 리스트(R) - 번호, 제목, 작성자, 작성일, 해시태그, 파일 조회수
-- SELECT : 데이터 가져오기(선택). from 뒤에 데이터가 있는 덩어리(board=table)
-- order by : 가져오는 순서 -> ASC - 오름차순(생략), DESC - 내림차순(생략불가능)
-- to_char(date, 패턴) - 날짜->문자열, 숫자->문자열
-- 패턴 : - yyyy: 년, mm:월, dd:일, hh:시, mi:분, ss:초
-- 함수를 데이터 가져가는 것으로 사용할 수는 없다. 대신 사용하는 별칭(Alias)
SELECT b.no, b.title, b.content, m.id, to_char(b.writeDate, 'yyyy-mm-dd') writeDate, b.hashtag, b.fileName, m.photo, b.hit, br.no FROM board b, member m, board_reply br 
WHERE (b.id = m.id) and (b.no = br.no)
ORDER BY b.id;

-- 댓글없는 버전
SELECT b.no, b.title, b.content, m.id, to_char(b.writeDate, 'yyyy-mm-dd') writeDate, b.hashtag, b.fileName, m.photo, b.hit FROM board b, member m 
WHERE b.id = m.id
ORDER BY b.id;

-- 조회수 1 증가
UPDATE board SET hit = hit + 1 WHERE no = 22;
COMMIT;

SELECT no, title, content, id, to_char(writeDate, 'yyyy-mm-dd') writeDate, hashtag, fileName, hit FROM board WHERE no = 3;

-- 2. 글보기
SELECT b.no, b.title, b.content, m.id, to_char(b.writeDate, 'yyyy-mm-dd') writeDate,  b.fileName, m.photo, b.hit, br.no 
FROM board b, member m, board_reply br
WHERE (no = '22') and (b.id = m.id) and (b.no = br.no);

-- 댓글없는 버전
SELECT b.no, b.title, b.content, m.id, to_char(b.writeDate, 'yyyy-mm-dd') writeDate,  b.fileName, m.photo, b.hit
FROM board b, member m
WHERE (no = '22') and (b.id = m.id);

-- 3. 글쓰기(C) -> 글번호(자동 - 객체:시퀀스), 제목, 내용, 아이디, 해시태그, 파일네임
INSERT INTO board(no, title, content, id, hashtag, fileName)
VALUES (BOARD_SEQ.nextval, '안녕', '테스트하는중', 'test', '#가입인사 #테스트', '/upload/image/test.jpg');
COMMIT;

-- 4. 글수정(U)
-- 2번글(DB에 있는 글중에 수정하려는 글을 선택)의 내용을 수정하자. - 제목, 내용, 작성자, 해시태그, 파일
UPDATE board SET title = '반갑습니다.', content = '테스트 완료합니다.', id = 'test3', hashtag = '#테스트완료', filename = '/upload/image/test1.jpg' WHERE no = 3;
COMMIT;

-- 5. 글삭제(D)
-- 삭제할 글번호를 확인하고 삭제 진행 : 2
DELETE FROM board WHERE no = 3;
COMMIT;

-- 트렌젝션 처리
-- 작업한 내용 취소 시키기(데이터 변경이 일어난 경우) : ROLLBACK
-- 작업한 내용을 완전 적용시키기 : commit
ROLLBACK;

