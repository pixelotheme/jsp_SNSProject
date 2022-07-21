-- 회원관리 운영 스키마
-- C - 회원가입
-- R - 회원리스트, 회원보기(내정보보기), 로그인, 아이디찾기, 비밀번호 찾기
-- U - 회원정보 수정, 등급수정, 상태수정
-- D - 사용하지 않는다. : 회원탈퇴 - 회원 상태 탈퇴로 바꿔 준다. -> U

-- 1. 리스트 - 아이디, 이름, 생년월일, 연락처, 등급번호, 등급명, 최종접속일
SELECT m.id, m.name, m.birth, m.tel, m.gradeNo, g.gradeName, m.conDate
FROM member m, grade g
WHERE m.gradeNo = g.gradeNo
ORDER BY m.id;
-- 2. 보기
SELECT m.id, m.name, m.gender, m.birth, m.tel, m.email, m.regDate, m.conDate, m.status, m.gradeNo, g.gradeName
FROM member m, grade g 
WHERE (id = 'test') AND (m.gradeNo = g.gradeNo);
-- 3. 쓰기 (회원가입)
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo)
VALUES ('angel', '1111', '아이유', '여자', '1993-03-01', '010-1004-1004',
'angel@naver.com', '/upload/member/angel.jpg');
COMMIT;
-- 4. 수정
UPDATE member SET id = '고양', name = '고양', birth = '1992-11-00=1', tel = '010-2222-1111', email = 'gsagag@naver.com' WHERE id = test1;


-- 5. 삭제
delete