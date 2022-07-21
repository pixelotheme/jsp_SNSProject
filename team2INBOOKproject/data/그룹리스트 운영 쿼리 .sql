
SELECT g.no, g.title, g.content, m.id, to_char(g.writeDate, 'yyyy-mm-dd') writeDate, g.hashtag, g.fileName, m.photo, g.hit, br.no FROM groupList g, member m, board_reply br 
WHERE (g.id = m.id) and (g.no = br.no)
ORDER BY g.id;

-- 조회수 1 증가
UPDATE groupList SET hit = hit + 1 WHERE no = 3;
COMMIT;

SELECT no, title, content, id, to_char(writeDate, 'yyyy-mm-dd') writeDate, hashtag, fileName, hit FROM groupList WHERE no = 3;

-- 2. 글보기
SELECT g.no, g.title, g.content, m.id, to_char(g.writeDate, 'yyyy-mm-dd') writeDate, g.hashtag,  g.fileName, m.photo, g.hit, br.no 
FROM groupList g, member m, board_reply br
WHERE (g.id = 'test1') and (g.id = m.id) and (g.no = br.no);

-- 3. 글쓰기(C) -> 글번호(자동 - 객체:시퀀스), 제목, 내용, 아이디, 해시태그, 파일네임
INSERT INTO groupList(no, title, content, id, hashtag, fileName)
VALUES (GROUPLIST_SEQ.nextval, '안녕하십니까', '테스트하는 중입니다.', 'test', '#가입인사 #테스트', '/upload/image/test.jpg');
COMMIT;

-- 4. 글수정(U)
-- 2번글(DB에 있는 글중에 수정하려는 글을 선택)의 내용을 수정하자. - 제목, 내용, 작성자, 해시태그, 파일
UPDATE groupList SET title = '반갑습니다.', content = '테스트 완료합니다.', id = 'test3', hashtag = '#테스트완료', filename = '/upload/image/test1.jpg' WHERE no = 3;
COMMIT;

-- 5. 글삭제(D)
-- 삭제할 글번호를 확인하고 삭제 진행 : 2
DELETE FROM groupList WHERE no = 5;
COMMIT;

-- 트렌젝션 처리
-- 작업한 내용 취소 시키기(데이터 변경이 일어난 경우) : ROLLBACK
-- 작업한 내용을 완전 적용시키기 : commit
ROLLBACK;
