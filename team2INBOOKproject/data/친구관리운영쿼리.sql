--친구관리 운영
--친구 신청 0, 수락 1
--친구 아닌사람(내가 친구 신청한사람만) 불러오는 쿼리 rel 0 
-- 친구인 사람은 rel 만 1로설정
-- host 가 test 인 것 중에 friend가 memberid 와 같은 세부사항 가져오기
select f.no, f.friendId, m.name, m.email, m.tel,m.photo, f.relationship
from friend f, member m 
where(f.hostId = 'test' and f.friendId = m.id and f.relationship = 1) 
order by no desc;


--순서번호
select rownum rnum, no, friendId, name, email, tel, photo, relationship 
from(
    select f.no, f.friendId, m.name, m.email, m.tel,m.photo, f.relationship
    from friend f, member m 
    where(f.hostId = 'test' and f.friendId = m.id and f.relationship = 0)
    order by no desc
       );
 
--한 페이지에 출력되는것
select rnum, no, friendid, name, email, tel, photo, relationship 
from (
select rownum rnum, no, friendId, name, email, tel, photo, relationship 
from(
    select f.no, f.friendId, m.name, m.email, m.tel,m.photo, f.relationship
    from friend f, member m 
    where(f.hostId = 'test' and f.friendId = m.id and f.relationship = 1)
    order by no desc
       )
);
       
--친구 신청
--자바단에서 friendid 가 등록된친구면 에러발생
insert into friend (no,hostId, friendId)
VALUES(FRIEND_SEQ.nextval,'test', 'test9');
commit;

--친구 수락
--신청받은 사람(friendId 로 등록되어있는 사람)도 hostId에 같이올려준다
--자바 단에서 if 문? 혹은 dao메서드 하나 더?
-- acceptDate 도 update 시켜야함
insert into friend (no, hostId, friendId, acceptdate)
values(FRIEND_SEQ.nextval,'신청 받은사람', '보낸사람', 1);
-- 기존에 보낸 사람 은 update문을 사용
UPDATE friend set relationship = 1 where (hostId = '보낸사람' and friendId = '받은사람'); 

--1. 나에게 친구신청한 friendId 가져오기
select  f.friendId
from friend f, member m 
where(f.hostId = 'test' and f.friendId = m.id and f.relationship = 0) 
order by no desc;

--친구신청받은 개수 받아오기
--로그인한 아이디가 friendId 에있고 relationship 이 0 인 개수
select count(*) from friend where(friendId = '로그인한아이디' and relationship = 0);

--3.삭제 - 친구인 사람만(신청 거절과 같은메서드 사용)
DELETE from friend where (friendId = '클릭한 아이디' and relationship = 1);

--신청 거절(그냥 삭제 메서드 쓰면 될듯?)
DELETE from friend where (friendId = '클릭한 아이디' and relationship = 0);


--2. rel 0 인 사람 가져오기 버튼을 친구 신청 취소로 보일때 쓸예정
SELECT friendId from friend where(hostId= 'test' and relationship = 0);
--2-1. rel 1 인 친구 가져오기 친구 삭제로 버튼 올릴때 쓸예정
SELECT friendId from friend where(hostId= 'test' and relationship = 1);