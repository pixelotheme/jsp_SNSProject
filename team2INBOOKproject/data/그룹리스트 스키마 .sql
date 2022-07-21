-- 그룹 리스트 스키마 - 테이블, 시퀀스
-- 제거 -> 생성 -> 샘플데이터
-- 제거 : FK(image) -> PK(member) : 이미지 게시판만 작업
DROP TABLE groupList;
DROP SEQUENCE groupList_seq;

-- 생성 : PK(member) -> FK(image) : 이미지 게시판만 작업

CREATE TABLE groupList(
 no NUMBER PRIMARY KEY,
  title VARCHAR2(300) NOT NULL,
  content VARCHAR2(2000) NOT NULL,
  id VARCHAR2(20) NOT NULL REFERENCES member(id) ON DELETE CASCADE,
  writeDate DATE DEFAULT SYSDATE,
  hashtag VARCHAR2(50) NOT NULL,
  fileName VARCHAR2(50) NOT NULL,
  --state VARCHAR2(6) DEFAULT '일반' CHECK (state in ('일반','그룹')),
  hit NUMBER DEFAULT 0
);
CREATE SEQUENCE groupList_seq;

-- 샘플데이터 : PK(member) -> FK(image) - 회원으로 등록되어 있는 아이디만 사용 가능 - test, admin
INSERT INTO groupList(no, title, content, id, hashtag, fileName )
VALUES(groupList_seq.NEXTVAL, '의정부 이야기', '음식사진은 가능하나 상업적 광고는 no', 'test1', '#의정부', '/upload/grouplist/uijeongbu.JPG' );
INSERT INTO groupList(no, title, content, id, hashtag, fileName)
VALUES(groupList_seq.NEXTVAL, '해킹 공격의 예술', '컴퓨터에 관련 모든 질문/답변 보안관련 지식 공유', 'test1', '#컴퓨터', '/upload/grouplist/computer.JPG');
INSERT INTO groupList(no, title, content, id, hashtag, fileName)
VALUES(groupList_seq.NEXTVAL, '생활 코딩', '코딩을 처음 시작하는 분들이 서로를 돕기위한 커뮤니티', 'test1', '#코딩 #어렵다', '/upload/grouplist/coding.JPG');
INSERT INTO groupList(no, title, content, id, hashtag, fileName)
VALUES(groupList_seq.NEXTVAL, '여미', '여행자가 모야 여행 이야기와 경험을 나누는 커뮤니티', 'test1', '#여행 #코로나', '/upload/grouplist/travel.JPG');
COMMIT;

select * from groupList;