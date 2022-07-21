--ģ������ �
--ģ�� ��û 0, ���� 1
--ģ�� �ƴѻ��(���� ģ�� ��û�ѻ����) �ҷ����� ���� rel 0 
-- ģ���� ����� rel �� 1�μ���
-- host �� test �� �� �߿� friend�� memberid �� ���� ���λ��� ��������
select f.no, f.friendId, m.name, m.email, m.tel,m.photo, f.relationship
from friend f, member m 
where(f.hostId = 'test' and f.friendId = m.id and f.relationship = 1) 
order by no desc;


--������ȣ
select rownum rnum, no, friendId, name, email, tel, photo, relationship 
from(
    select f.no, f.friendId, m.name, m.email, m.tel,m.photo, f.relationship
    from friend f, member m 
    where(f.hostId = 'test' and f.friendId = m.id and f.relationship = 0)
    order by no desc
       );
 
--�� �������� ��µǴ°�
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
       
--ģ�� ��û
--�ڹٴܿ��� friendid �� ��ϵ�ģ���� �����߻�
insert into friend (no,hostId, friendId)
VALUES(FRIEND_SEQ.nextval,'test', 'test9');
commit;

--ģ�� ����
--��û���� ���(friendId �� ��ϵǾ��ִ� ���)�� hostId�� ���̿÷��ش�
--�ڹ� �ܿ��� if ��? Ȥ�� dao�޼��� �ϳ� ��?
-- acceptDate �� update ���Ѿ���
insert into friend (no, hostId, friendId, acceptdate)
values(FRIEND_SEQ.nextval,'��û �������', '�������', 1);
-- ������ ���� ��� �� update���� ���
UPDATE friend set relationship = 1 where (hostId = '�������' and friendId = '�������'); 

--1. ������ ģ����û�� friendId ��������
select  f.friendId
from friend f, member m 
where(f.hostId = 'test' and f.friendId = m.id and f.relationship = 0) 
order by no desc;

--ģ����û���� ���� �޾ƿ���
--�α����� ���̵� friendId ���ְ� relationship �� 0 �� ����
select count(*) from friend where(friendId = '�α����Ѿ��̵�' and relationship = 0);

--3.���� - ģ���� �����(��û ������ �����޼��� ���)
DELETE from friend where (friendId = 'Ŭ���� ���̵�' and relationship = 1);

--��û ����(�׳� ���� �޼��� ���� �ɵ�?)
DELETE from friend where (friendId = 'Ŭ���� ���̵�' and relationship = 0);


--2. rel 0 �� ��� �������� ��ư�� ģ�� ��û ��ҷ� ���϶� ������
SELECT friendId from friend where(hostId= 'test' and relationship = 0);
--2-1. rel 1 �� ģ�� �������� ģ�� ������ ��ư �ø��� ������
SELECT friendId from friend where(hostId= 'test' and relationship = 1);